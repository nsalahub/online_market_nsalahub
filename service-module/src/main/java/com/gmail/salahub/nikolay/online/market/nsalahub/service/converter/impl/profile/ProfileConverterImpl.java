package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.profile;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Profile;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ProfileConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.UserConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("profileConverter")
public class ProfileConverterImpl implements ProfileConverter {

    private UserConverter userConverter;

    @Autowired
    public ProfileConverterImpl(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public Profile fromDTO(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setAddress(profileDTO.getAddress());
        profile.setTelephone(profileDTO.getTelephone());
        profile.setId(profileDTO.getId());
        profile.setDeleted(profileDTO.isDeleted());
        profile.setUser(userConverter.fromDTO(profileDTO.getUserDTO()));
        return profile;
    }

    @Override
    public ProfileDTO toDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setAddress(profile.getAddress());
        profileDTO.setId(profile.getId());
        profileDTO.setTelephone(profile.getTelephone());
        profileDTO.setUserDTO(userConverter.toDTO(profile.getUser()));
        profileDTO.setDeleted(profile.isDeleted());
        return profileDTO;
    }
}
