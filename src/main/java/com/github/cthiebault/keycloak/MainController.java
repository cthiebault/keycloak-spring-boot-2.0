package com.github.cthiebault.keycloak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  private final OAuth2AuthorizedClientService authorizedClientService;

  @Autowired
  public MainController(OAuth2AuthorizedClientService authorizedClientService) {
    this.authorizedClientService = authorizedClientService;
  }

  @RequestMapping("/")
  public String index(Model model, OAuth2AuthenticationToken authentication) {
    OAuth2AuthorizedClient authorizedClient = authorizedClientService
        .loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

    model.addAttribute("userName", authentication.getName());
    model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
    model.addAttribute("userAttributes", authentication.getPrincipal().getAttributes());
    model.addAttribute("authorities", authentication.getAuthorities());

    return "index";
  }

}
