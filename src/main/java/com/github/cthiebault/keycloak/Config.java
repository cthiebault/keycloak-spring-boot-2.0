package com.github.cthiebault.keycloak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

@Configuration
public class Config {

  private final Logger log = LoggerFactory.getLogger(getClass());

  // used to log stuff
  @Bean
  public GrantedAuthoritiesMapper userAuthoritiesMapper() {
    return (authorities) -> {

      authorities.forEach(authority -> {
        log.debug("authority: {}", authority);

        if(OidcUserAuthority.class.isInstance(authority)) {
          OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) authority;
          OidcIdToken idToken = oidcUserAuthority.getIdToken();
          OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();

          log.debug("idToken claims: {}", idToken.getClaims());
          log.debug("userInfo claims: {}", userInfo.getClaims());

          // TODO: get user role from keycloak

        }
      });

      return authorities;
    };
  }

}
