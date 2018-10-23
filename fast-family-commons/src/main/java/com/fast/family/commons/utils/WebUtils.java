package com.fast.family.commons.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author 张顺
 * @version 1.0
 */
public class WebUtils {

    private static final String DEFAULT_SKIP_PATTERN =
            "/api-docs.*|/actuator.*|/swagger.*|.*\\.png|.*\\.css|.*\\.js|.*\\.html|/favicon.ico|/hystrix.stream";

    private static final Pattern SKIP_PATTERNS = Pattern.compile(DEFAULT_SKIP_PATTERN);

    public static boolean ignoreRequest(HttpServletRequest request){
        String path = request.getRequestURI();
        return SKIP_PATTERNS.matcher(path).matches();
    }

    public static boolean isBinaryContent(final HttpServletResponse response){
        return response.getContentType() != null && (response.getContentType()
                .startsWith("image") || response.getContentType().startsWith("video") || response
                .getContentType().startsWith("audio"));
    }

    public static boolean isMultipart(final HttpServletResponse response){
        return response.getContentType() != null && (response.getContentType()
                .startsWith("multipart/form-data") || response.getContentType()
                .startsWith("application/octet-stream"));
    }

    public static boolean isAjax(HttpServletRequest request) {
        return request.getHeader("XMLHttpRequest") != null;
    }

    public static boolean isNormalRequest(HttpServletRequest request){
        return !isMultipart(request) && !isBinaryContent(request);
    }

    public static boolean isMultipart(final HttpServletRequest request){
        return request.getContentType() != null && request.getContentType()
                .startsWith("multipart/form-data");
    }

    public static boolean isBinaryContent(final HttpServletRequest request){
        if (request.getContentType() == null){
            return false;
        }
        return request.getContentType().startsWith("image") || request.getContentType()
                .startsWith("video") || request.getContentType().startsWith("audio");
    }




    public static byte[] getRequestPostByteArray(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        /*当无请求参数时，request.getContentLength()返回-1 */
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostByteArray(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }


    public static String encodeString(String source, String encoding) {
        try {
            return URLEncoder.encode(source, encoding);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String decodeString(String source, String encoding) {
        try {
            return URLDecoder.decode(source, encoding);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        return org.springframework.web.util.WebUtils.getCookie(request, name);
    }


    public static String getClientIP(HttpServletRequest request)
            {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress))
            ipAddress = request.getHeader("Proxy-Client-IP");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress))
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                InetAddress inetaddress = null;
                try {
                    inetaddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    return "";
                }
                ipAddress = inetaddress.getHostAddress();
            }
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP地址，多个IP按照","分割
        if (ipAddress.split(",").length > 1)
            ipAddress = ipAddress.split(",")[0];
        return ipAddress;
    }



    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        return basePath;
    }


    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    public static Map<String, String> getRequestParameters(HttpServletRequest request) {
        return buildRequestParams(request.getParameterNames(), request);
    }

    public static Map<String, String> getRequestHeaders(HttpServletRequest request) {
        return buildRequestParams(request.getHeaderNames(), request);
    }



    private static Map<String, String> buildRequestParams(Enumeration enumeration, HttpServletRequest request) {
        Map<String, String> param = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String keyName = String.valueOf(enumeration.nextElement());
            param.put(keyName, request.getParameter(keyName));
        }
        return param;
    }


    public static void writeJson(HttpServletResponse response, byte[] byteArray) throws IOException {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(byteArray, 0, byteArray.length);
        outputStream.flush();
        outputStream.close();
    }

    public static <T> T getNativeRequest(ServletRequest request, Class<T> requiredType) {
        return org.springframework.web.util.WebUtils.getNativeRequest(request, requiredType);
    }

}
