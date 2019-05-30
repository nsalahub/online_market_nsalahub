package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.user;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.User;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ProfileConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.RoleConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("userProfileConverter")
public class UserProfileConverterImpl implements UserConverter {

    private final ProfileConverter profileShowingConverter;
    private final RoleConverter roleConverter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserProfileConverterImpl(@Qualifier("profileShowingConverter") ProfileConverter profileShowingConverter,
                                    RoleConverter roleConverter,
                                    PasswordEncoder passwordEncoder){
        this.profileShowingConverter = profileShowingConverter;
        this.roleConverter = roleConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setPatronymic(user.getPatronymic());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoleDTO(roleConverter.toDTO(user.getRole()));
        userDTO.setDeleted(user.isDeleted());
        userDTO.setPassword(user.getPassword());
        userDTO.setId(user.getId());
        userDTO.setPosition(user.getPosition());
        userDTO.setProfileDTO(profileShowingConverter.toDTO(user.getProfile()));
        return userDTO;
    }

    @Override
    public User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPatronymic(userDTO.getPatronymic());
        user.setSurname(userDTO.getSurname());
        user.setRole(roleConverter.fromDTO(userDTO.getRoleDTO()));
        user.setDeleted(userDTO.isDeleted());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setId(userDTO.getId());
        user.setPosition(userDTO.getPosition());
        user.setProfile(profileShowingConverter.fromDTO(userDTO.getProfileDTO()));
        return user;
    }
}
