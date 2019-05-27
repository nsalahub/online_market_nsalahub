package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_PROFILE_URL;

@Controller
@RequestMapping("/public")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String showProfilePage(
            Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = userService.getByUsername(authentication.getName());
        model.addAttribute("user", userDTO);
        return "profile";
    }

    @PostMapping("/profile/password/change")
    public String changePasswordThisUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = userService.getByUsername(authentication.getName());
        userService.updatePassword(userDTO);
        return REDIRECT_PROFILE_URL;
    }

    @PostMapping("/profile/update")
    public String updateProfilePage(
            UserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userDTO.setEmail(authentication.getName());
        userService.updateProfile(userDTO);
        return REDIRECT_PROFILE_URL;
    }

}
