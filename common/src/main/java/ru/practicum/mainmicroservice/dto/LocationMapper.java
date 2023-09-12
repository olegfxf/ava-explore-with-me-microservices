package ru.practicum.mainmicroservice.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.mainmicroservice.dto.location.LocationDto;
import ru.practicum.mainmicroservice.model.Location;

@UtilityClass
public class LocationMapper {
    public LocationDto toLocationDto(Location location) {
        return LocationDto
                .builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
    }

    public Location toLocation(LocationDto locationDto) {
        return Location
                .builder()
                .lat(locationDto.getLat())
                .lon(locationDto.getLon())
                .build();
    }
}
