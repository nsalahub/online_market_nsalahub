package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.profile;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Profile;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ProfileConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ProfileDTO;
import org.springframework.stereotype.Component;

@Component("profileUpdateConverter")
public class ProfileUpdateConverterImpl implements ProfileConverter {
    @Override
    public Profile fromDTO(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setTelephone(profileDTO.getTelephone());
        profile.setAddress(profileDTO.getAddress());
        return profile;
    }

    @Override
    public ProfileDTO toDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setTelephone(profile.getTelephone());
        profileDTO.setAddress(profile.getAddress());
        return profileDTO;
    }
}
