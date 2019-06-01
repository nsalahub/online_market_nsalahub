package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ProfileDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.UserPrincipal;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.getById(((UserPrincipal)userDetails).getIdFromUserPrincipal());
        model.addAttribute("user", userDTO);
        return "profile";
    }

    @PostMapping("/profile/password/change")
    public String changePasswordThisUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.getById(((UserPrincipal)userDetails).getIdFromUserPrincipal());
        userService.updatePassword(userDTO);
        return REDIRECT_PROFILE_URL;
    }

    @PostMapping("/profile/update")
    public String updateProfilePage(
            UserDTO userDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDTO.setId(((UserPrincipal)userDetails).getIdFromUserPrincipal());
        userService.updateProfile(userDTO);
        return REDIRECT_PROFILE_URL;
    }

}
