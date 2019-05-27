package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.impl.profile;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Profile;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.converter.ProfileConverter;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ProfileDTO;
import org.springframework.stereotype.Component;

@Component("profileShowingConverter")
public class ProfileShowingConverterImpl implements ProfileConverter {
    @Override
    public Profile fromDTO(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setAddress(profileDTO.getAddress());
        profile.setTelephone(profileDTO.getTelephone());
        profile.setId(profileDTO.getId());
        profile.setDeleted(profileDTO.isDeleted());
        return profile;
    }

    @Override
    public ProfileDTO toDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setAddress(profile.getAddress());
        profileDTO.setId(profile.getId());
        profileDTO.setTelephone(profile.getTelephone());
        profileDTO.setDeleted(profile.isDeleted());
        return profileDTO;
    }
}
