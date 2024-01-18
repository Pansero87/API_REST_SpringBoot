package com.project.springproject.dto;

import com.project.springproject.model.Orchestra;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a Data Transfer Object (DTO) for the Orchestra entity.
 * It contains the necessary fields and methods to convert between the DTO and
 * entity objects.
 */
@Getter
@Setter
public class OrchestraDTO {

    private Long id;
    private String name;
    private String city;
    private String country;
    private String estile;
    private String conductor;

    /**
     * Converts an Orchestra entity object to an OrchestraDTO object.
     *
     * @param orchestra The Orchestra entity object to be converted.
     * @return The converted OrchestraDTO object.
     */
    public static OrchestraDTO convertToDTO(Orchestra orchestra) {
        OrchestraDTO orchestraDTO = new OrchestraDTO();
        orchestraDTO.setId(orchestra.getId());
        orchestraDTO.setName(orchestra.getName());
        orchestraDTO.setCity(orchestra.getCity());
        orchestraDTO.setCountry(orchestra.getCountry());
        orchestraDTO.setEstile(orchestra.getEstile());
        orchestraDTO.setConductor(orchestra.getConductor());

        return orchestraDTO;
    }

    /**
     * Converts an OrchestraDTO object to an Orchestra entity object.
     *
     * @param dto The OrchestraDTO object to be converted.
     * @return The converted Orchestra entity object.
     */
    public static Orchestra convertToEntity(OrchestraDTO dto) {
        Orchestra orchestra = new Orchestra();
        orchestra.setId(dto.getId());
        orchestra.setName(dto.getName());
        orchestra.setCity(dto.getCity());
        orchestra.setCountry(dto.getCountry());
        orchestra.setEstile(dto.getEstile());
        orchestra.setConductor(dto.getConductor());

        return orchestra;
    }
}
