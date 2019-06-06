package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ProfileDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.UserPrincipal;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator.ProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_PROFILE_URL;

@Controller
@RequestMapping("/public")
public class ProfileController {

    private final UserService userService;
    private final ProfileValidator profileValidator;

    @Autowired
    public ProfileController(UserService userService,
                             ProfileValidator profileValidator) {
        this.profileValidator = profileValidator;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String showProfilePage(
            Model model) {
        UserDTO userDTO = getUserByUserNameFromUserDetails();
        UserDTO newUserDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "profile";
    }

    @PostMapping("/profile/password/change")
    public String changePasswordThisUser() {
        UserDTO userDTO = getUserByIdFromUserDetails();
        userService.updatePassword(userDTO);
        return REDIRECT_PROFILE_URL;
    }

    @PostMapping("/profile/update")
    public String updateProfilePage(
            @ModelAttribute(value = "user")
                    UserDTO userDTO,
            BindingResult bindingResult,
            Model model) {
        profileValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", getUserByUserNameFromUserDetails());
            return "profile";
        } else {
            userDTO.setId(getUserByIdFromUserDetails().getId());
            userService.updateProfile(userDTO);
            return REDIRECT_PROFILE_URL;
        }
    }

    private UserDTO getUserByUserNameFromUserDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.getByUsername(((UserPrincipal) userDetails).getUsername());
        return userDTO;
    }

    private UserDTO getUserByIdFromUserDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.getById(((UserPrincipal) userDetails).getIdFromUserPrincipal());
        return userDTO;
    }
}
