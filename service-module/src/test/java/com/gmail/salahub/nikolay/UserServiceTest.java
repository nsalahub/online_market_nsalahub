package com.gmail.salahub.nikolay;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.UserRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.exception.UserRepositoryException;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.UpdateUser;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.user.User;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.PageService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.RandomService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UpdateUserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.exception.UserServiceException;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.impl.UserServiceImpl;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UpdateUserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.testmodel.TestModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;

    @Mock
    private UpdateUserConverter updateUserConverter;

    @Mock
    private Connection connection;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RandomService randomService;

    @Mock
    private PageService pageService;

    private UserService userService;
    private User testUser = TestModel.getTestUser();
    private UserDTO testUserDTO = TestModel.getTestUserDTO();
    private int countOfUsers = 5;
    private int countOfPages = 5;
    private UpdateUser updateUser = TestModel.getTestUpdateUser();
    private UpdateUserDTO updateUserDTO = TestModel.getTestUpdateUserDTO();


    @Before
    public void init() {

    }


}


