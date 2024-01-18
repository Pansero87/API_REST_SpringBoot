package com.project.springproject.services.servicesInterfaces;

import java.util.List;

import com.project.springproject.dto.MusicianDTO;

/**
 * The MusicianService interface provides methods to manage musicians and their
 * association with orchestras.
 */
public interface MusicianService {

    /**
     * Saves a musician.
     *
     * @param musicianDTO The musician to be saved.
     * @return The saved musician.
     */
    MusicianDTO saveMusician(MusicianDTO musicianDTO);

    /**
     * Retrieves a musician by their ID.
     *
     * @param id The ID of the musician.
     * @return The musician with the specified ID.
     */
    MusicianDTO getMusicianById(Long id);

    /**
     * Retrieves all musicians.
     *
     * @return A list of all musicians.
     */
    List<MusicianDTO> getAllMusicians();

    /**
     * Deletes a musician by their ID.
     *
     * @param id The ID of the musician to be deleted.
     */
    void deleteMusicianById(Long id);

    /**
     * Adds a musician to an orchestra.
     *
     * @param orchestraId The ID of the orchestra.
     * @param musicianDTO The musician to be added.
     * @return The updated musician.
     */
    MusicianDTO addMusicianToOrchestra(Long orchestraId, MusicianDTO musicianDTO);

    /**
     * Removes a musician from an orchestra.
     *
     * @param orchestraId The ID of the orchestra.
     * @param musicianId  The ID of the musician to be removed.
     * @return True if the musician was successfully removed, false otherwise.
     */
    boolean removeMusicianFromOrchestra(Long orchestraId, Long musicianId);

}
