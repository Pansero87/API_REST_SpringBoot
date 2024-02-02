package com.project.springproject.model.dto;

import com.project.springproject.model.OrchestraEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a Data Transfer Object (DTO) for the Orchestra entity.
 * It contains the necessary fields and methods to convert between the DTO and
 * entity objects.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    public static OrchestraDTO convertToDTO(OrchestraEntity orchestra) {
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
    public static OrchestraEntity convertToEntity(OrchestraDTO dto) {
        OrchestraEntity orchestra = new OrchestraEntity();
        orchestra.setId(dto.getId());
        orchestra.setName(dto.getName());
        orchestra.setCity(dto.getCity());
        orchestra.setCountry(dto.getCountry());
        orchestra.setEstile(dto.getEstile());
        orchestra.setConductor(dto.getConductor());

        return orchestra;
    }
}
