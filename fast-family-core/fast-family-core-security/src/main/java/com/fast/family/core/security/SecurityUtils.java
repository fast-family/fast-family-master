package com.fast.family.security;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 张顺
 * @version 1.0
 */
public class SecurityUtils {

    public static boolean skipPathRequest(HttpServletRequest request, String[] whiteList) {
        List<String> pathsToSkip = new ArrayList();
        pathsToSkip.addAll(Arrays.asList(whiteList));
        List<RequestMatcher> m = (List) pathsToSkip.stream().map((path) -> {
            return new AntPathRequestMatcher(path);
        }).collect(Collectors.toList());
        OrRequestMatcher matchers = new OrRequestMatcher(m);
        return matchers.matches(request);
    }

}
