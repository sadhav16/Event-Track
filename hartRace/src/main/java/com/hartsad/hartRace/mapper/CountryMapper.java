package com.hartsad.hartRace.mapper;

import com.hartsad.hartRace.dto.CountryDto;
import com.hartsad.hartRace.entity.Country;

public class CountryMapper {
    public static CountryDto maptoCdto(Country cnt){
        return new CountryDto(
                          cnt.getCountryCode(),
                          cnt.getCname(),
                          cnt.getGold(),
                          cnt.getSilver(),
                          cnt.getBronze(),
                          cnt.getPoints()
                );
    }

    public static Country mapDtoc(CountryDto cdto){
        return new Country(
                cdto.getCcode(),
                cdto.getCname(),
                cdto.getGold(),
                cdto.getSilver(),
                cdto.getBronze(),
                cdto.getPoints()
        );
    }
}
