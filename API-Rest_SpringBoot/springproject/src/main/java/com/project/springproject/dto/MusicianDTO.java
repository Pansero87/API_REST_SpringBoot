package com.project.springproject.dto;

import java.util.Date;

import com.project.springproject.model.Musician;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a Data Transfer Object (DTO) for a Musician.
 * It contains the musician's information such as id, firstname, lastname,
 * birthdate, instrument, email, and orchestraId.
 */
@Getter
@Setter
public class MusicianDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String instrument;
    private String email;
    private Long orchestraId;

    /**
     * Converts a Musician entity to a MusicianDTO object.
     * 
     * @param musician The Musician entity to be converted.
     * @return The converted MusicianDTO object.
     */
    public static MusicianDTO convertToDTO(Musician musician) {
        MusicianDTO musicianDTO = new MusicianDTO();
        musicianDTO.setId(musician.getId());
        musicianDTO.setFirstname(musician.getFirstname());
        musicianDTO.setLastname(musician.getLastname());
        musicianDTO.setBirthdate(musician.getBirthdate());
        musicianDTO.setInstrument(musician.getInstrument());
        musicianDTO.setEmail(musician.getEmail());
        if (musician.getOrchestra() != null) {
            musicianDTO.setOrchestraId(musician.getOrchestra().getId());
        }

        return musicianDTO;
    }

    /**
     * Converts a MusicianDTO object to a Musician entity.
     * 
     * @param dto The MusicianDTO object to be converted.
     * @return The converted Musician entity.
     */
    public static Musician convertToEntity(MusicianDTO dto) {
        Musician musician = new Musician();
        musician.setId(dto.getId());
        musician.setFirstname(dto.getFirstname());
        musician.setLastname(dto.getLastname());
        musician.setBirthdate(dto.getBirthdate());
        musician.setInstrument(dto.getInstrument());
        musician.setEmail(dto.getEmail());

        return musician;
    }
}
