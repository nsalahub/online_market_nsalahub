package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.RoleRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.UserRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Profile;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Role;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.User;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.PageService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.RandomService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.constant.ServiceConstant;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ProfileConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UpdateUserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UpdateUserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.salahub.nikolay.online.market.nsalahub.repository.constant.RepositoryConstant.LIMIT_USER_VALUE;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final UpdateUserConverter updateUserConverter;
    private final RandomService randomService;
    private final PasswordEncoder passwordEncoder;
    private final PageService pageService;
    private final RoleRepository roleRepository;
    private final UserConverter userProfileConverter;
    private final ProfileConverter profileUpdateConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserConverter userConverter,
                           UpdateUserConverter updateUserConverter,
                           RandomService randomService,
                           PasswordEncoder passwordEncoder,
                           PageService pageService,
                           RoleRepository roleRepository,
                           @Qualifier("userProfileConverter") UserConverter userProfileConverter,
                           @Qualifier("profileUpdateConverter") ProfileConverter profileUpdateConverter) {
        this.profileUpdateConverter = profileUpdateConverter;
        this.updateUserConverter = updateUserConverter;
        this.roleRepository = roleRepository;
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.userProfileConverter = userProfileConverter;
        this.randomService = randomService;
        this.passwordEncoder = passwordEncoder;
        this.pageService = pageService;
    }

    @Override
    @Transactional
    public UserDTO getByUsername(String email) {
        return userProfileConverter.toDTO(userRepository
                .findByEmail(email));
    }

    @Override
    @Transactional
    public List<UserDTO> getByPageNumber(int pageNumber) {
        List<UserDTO> userDTOS;
        List<User> users = userRepository.findAll((pageNumber - 1) * LIMIT_USER_VALUE, LIMIT_USER_VALUE);
        userDTOS = users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    @Transactional
    public int getNumberOfPages() {
        Integer valueOfUsers = userRepository.getCountOfEntities();
        Integer valueOfPages = pageService.getValueOfPages(valueOfUsers, LIMIT_USER_VALUE);
        return valueOfPages;
    }

    @Override
    @Transactional
    public void create(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(randomService
                .getRandomString(ServiceConstant.BEGINNING_OF_LINE_FOR_RANDOM_PASSWORD,
                        ServiceConstant.END_OF_LINE_FOR_RANDOM_PASSWORD)));
        Role role = roleRepository.findByName(userDTO.getRoleDTO().getName());
        User user = userConverter.fromDTO(userDTO);
        user.setRole(role);
        user.setPosition("user");
        user.setDeleted(false);
        Profile profile = getDefaultProfile();
        user.setProfile(profile);
        profile.setUser(user);
        userRepository.persist(user);
    }

    @Override
    @Transactional
    public void update(UpdateUserDTO updateUserDTO) {
        Role role = roleRepository.findByName(updateUserDTO.getRole());
        User user = userRepository.findByEmail(updateUserDTO.getEmail());
        user.setRole(role);
        userRepository.merge(user);
    }

    @Override
    @Transactional
    public void updatePassword(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(
                randomService.getRandomString(ServiceConstant.BEGINNING_OF_LINE_FOR_RANDOM_PASSWORD,
                        ServiceConstant.END_OF_LINE_FOR_RANDOM_PASSWORD)));
        userRepository.updatePassword(userDTO.getPassword(), userDTO.getEmail());
    }

    @Override
    @Transactional
    public void deleteByListEmails(List<String> emails) {
        userRepository.deleteByEmails(emails);
    }

    @Override
    @Transactional
    public void updateProfile(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        Profile profile = user.getProfile();
        profile.setAddress(userDTO.getProfileDTO().getAddress());
        profile.setTelephone(userDTO.getProfileDTO().getTelephone());
        user.setProfile(profile);
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        userRepository.merge(user);
    }

    private Profile getDefaultProfile() {
        Profile profile = new Profile();
        profile.setAddress(" ");
        profile.setTelephone(" ");
        profile.setDeleted(false);
        return profile;
    }
}
