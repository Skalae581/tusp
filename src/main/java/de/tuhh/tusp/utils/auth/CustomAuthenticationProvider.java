package de.tuhh.tusp.utils.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomAuthenticationProvider extends AuthenticationProvider, UserDetailsService {

}
