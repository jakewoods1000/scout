package com.app.cfd.controllers;

import com.app.cfd.models.users.User;
import com.app.cfd.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// make @RestController when we move to a front end
@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            User user = userService.findOrCreateByUserId(principal.getSubject(), principal.getEmail(), principal.getEmailVerified());

            model.addAttribute("profile", principal.getClaims());
            model.addAttribute("scoutProfile", user.getEmail());
            model.addAttribute("createdAt", user.getCreatedAt());
        }
        return "index";
    }
}