package com.fast.family.mvc.filter;

import com.fast.family.commons.utils.WebUtils;
import com.fast.family.mvc.SpringMvcProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
@Order(0)
public class AccessLogFilter extends OncePerRequestFilter{


    @Autowired
    private SpringMvcProperties mvcProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (WebUtils.ignoreRequest(request)){
            filterChain.doFilter(request,response);
        } else {
            final boolean isFirstRequest = !isAsyncDispatch(request);
            final AccessLogger accessLogger = new AccessLogger(mvcProperties);
            HttpServletRequest requestToUse = request;
            ContentCachingResponseWrapper responseToUse =
                    new ContentCachingResponseWrapper(response);
            StopWatch watch = new StopWatch();
            watch.start();
            if (isFirstRequest && !(request instanceof ContentCachingRequestWrapper)){
                requestToUse = new ContentCachingRequestWrapper(request,
                        mvcProperties.getRequestBodyLength());
            }

            try {
                filterChain.doFilter(requestToUse,responseToUse);
            } finally {
                if (isFirstRequest){
                    accessLogger.appendRequestCommonMessage(requestToUse);
                    accessLogger.appendRequestDetailMessage(mvcProperties.isIncludeRequest(),requestToUse);
                }
                watch.stop();
                if (!isAsyncDispatch(requestToUse)){
                    accessLogger.appendResponseCommonMessage(responseToUse,watch.getTotalTimeMillis());
                    if (mvcProperties.isIncludeRequest() && !WebUtils.isBinaryContent(requestToUse)
                            && !WebUtils.isMultipart(requestToUse)){
                        accessLogger.appendResponseDetailMessage(responseToUse);
                    }
                }
                responseToUse.copyBodyToResponse();
                accessLogger.printLog();
            }

        }

    }
}
