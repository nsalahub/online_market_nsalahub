package com.gmail.salahub.nikolay.online.market.nsalahub.service.converter;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.Profile;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ProfileDTO;

public interface ProfileConverter {
    Profile fromDTO(ProfileDTO profileDTO);

    ProfileDTO toDTO(Profile profile);
}
