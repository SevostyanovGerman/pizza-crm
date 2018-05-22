package com.pizza.crm.service.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Service
public class FailureLoginHandler implements AuthenticationFailureHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private static final Logger log = Logger
            .getLogger("FailureLoginHandler");

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException {
        //При неудачном логине перенаправить вновь на форму логина с соответствующим сообщением.
        String message = "Incorrect login or password";
        log.info(message);
        httpServletRequest.setAttribute("message", message);
        log.info("redirecting to \"/login\"");
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/login");
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
