package com.pizza.crm.service.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SuccessLoginHandler implements AuthenticationSuccessHandler {

    private static final Logger log = Logger
            .getLogger("SecurityHandler");

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) throws IllegalStateException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority g : authorities) {
            log.info("authoritiy: " + g.getAuthority());
            if (g.getAuthority().contains("ADMIN")) {
                log.info("Redirecting to /admin/staff/employee");
                return "/admin/staff/employee";
            }
            if (g.getAuthority().contains("USER")) {
                log.info("Redirecting to /order");
                return "/order";
            }
        }
        log.log(Level.WARNING, "Current Authorities: " +
                Arrays.toString(authorities.toArray()));
        throw new IllegalStateException();
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
