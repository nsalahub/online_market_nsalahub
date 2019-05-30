package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UpdateUserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.constant.ControllerConstant.REDIRECT_USER_URL;

@Controller
@RequestMapping("/private")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    private String showUsers(@RequestParam(defaultValue = "1", value = "currentPage")
                                     Integer page, Model model) {
        List<UserDTO> users = userService.getByPageNumber(page);
        model.addAttribute("users", users);
        int valueOfPages = userService.getNumberOfPages();
        model.addAttribute("numberPage", valueOfPages);
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        model.addAttribute("updateUserDTO", updateUserDTO);
        logger.info("start showing page with users");
        return "user";
    }

    @PostMapping("user/delete")
    public String showUserWithDeleted(
            @RequestParam(value = "id") String[] ids) {
        userService.deleteByListIds(Arrays.stream(ids).map(id -> Long.valueOf(id))
                .collect(Collectors.toList()));
        logger.info("start deleted butch users");
        return REDIRECT_USER_URL;
    }

    @PostMapping("user/role/update")
    public String updateItem(
            UpdateUserDTO updateUserDTO) {
        userService.update(updateUserDTO);
        logger.info(updateUserDTO.getEmail() + " role success changed");
        return REDIRECT_USER_URL;
    }

    @PostMapping("/user/change/password")
    public String changePassword(
            UserDTO userDTO) {
        userService.updatePassword(userDTO);
        logger.info(userDTO.getEmail() + " password success changed");
        return REDIRECT_USER_URL;
    }

    @GetMapping("/user/new")
    public String showAddUserPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        logger.info("start showing add user page");
        return "adduserpage";
    }

    @PostMapping("/user/new")
    public String getUserFromAddUserPage(UserDTO userDTO) {
        userService.create(userDTO);
        logger.info(userDTO.getName(), userDTO.getEmail() + " success add");
        return REDIRECT_USER_URL;
    }
}
