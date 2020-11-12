package ru.jalolov.webCRUD.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_USER") && !roles.contains("ROLE_ADMIN")) { // надо переопределить этот метод, так как роль User есть и у админа, из-за этого оба прилетают на одну и ту же страницу
            httpServletResponse.sendRedirect("/users");
        } else if (roles.contains("ROLE_ADMIN")){
            httpServletResponse.sendRedirect("/admin");
        }
        else {
            httpServletResponse.sendRedirect("/");
        }
    }
}
