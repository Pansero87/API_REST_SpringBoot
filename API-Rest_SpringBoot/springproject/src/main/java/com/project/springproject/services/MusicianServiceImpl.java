package com.project.springproject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springproject.dto.MusicianDTO;
import com.project.springproject.model.Musician;
import com.project.springproject.model.Orchestra;
import com.project.springproject.repositories.MusicianRepository;
import com.project.springproject.repositories.OrchestraRepository;
import com.project.springproject.services.servicesInterfaces.MusicianService;

/**
 * Service class for managing musicians.
 */
@Service
public class MusicianServiceImpl implements MusicianService {

    /**
     * Repository for accessing musician data.
     */
    @Autowired
    private MusicianRepository musicianRepository;

    /**
     * Repository for accessing orchestra data.
     */
    @Autowired
    private OrchestraRepository orchestraRepository;

    /**
     * Saves a musician to the database. If the musician is associated with an
     * orchestra, the orchestra is also saved.
     *
     * @param musicianDTO the musician to save
     * @return the saved musician
     * @throws RuntimeException if an error occurs while saving the musician
     */
    @Override
    public MusicianDTO saveMusician(MusicianDTO musicianDTO) {
        try {
            Musician musician = MusicianDTO.convertToEntity(musicianDTO);
            if (musicianDTO.getOrchestraId() != null) {
                Orchestra orchestra = orchestraRepository.findById(musicianDTO.getOrchestraId())
                        .orElseThrow(() -> new NoSuchElementException(
                                "No orchestra found with id: " + musicianDTO.getOrchestraId()));
                musician.setOrchestra(orchestra);
            }
            Musician saveMusician = musicianRepository.save(musician);
            return MusicianDTO.convertToDTO(saveMusician);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save musician: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves a musician by their ID.
     *
     * @param id the ID of the musician to retrieve
     * @return the retrieved musician
     * @throws NoSuchElementException if no musician is found with the given ID
     */
    @Override
    public MusicianDTO getMusicianById(Long id) {
        try {
            Optional<Musician> musician = musicianRepository.findById(id);
            if (musician.isPresent()) {
                return MusicianDTO.convertToDTO(musician.get());
            } else {
                throw new NoSuchElementException("No musician found with id: " + id);
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Failed to get musician by id: " + e.getMessage());
        }
    }

    /**
     * Retrieves all musicians from the database.
     *
     * @return a list of all musicians
     * @throws RuntimeException if an error occurs while retrieving the musicians
     */
    @Override
    public List<MusicianDTO> getAllMusicians() {
        try {
            List<Musician> musicians = musicianRepository.findAll();
            List<MusicianDTO> musicianResult = new ArrayList<MusicianDTO>();
            for (Musician musician : musicians) {
                musicianResult.add(MusicianDTO.convertToDTO(musician));

            }
            return musicianResult;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all musicians: " + e.getMessage());
        }
    }

    /**
     * Deletes a musician by their ID.
     *
     * @param id the ID of the musician to delete
     * @throws RuntimeException if an error occurs while deleting the musician
     */
    @Override
    public void deleteMusicianById(Long id) {
        try {
            musicianRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting musician: " + e.getMessage());
        }
    }

    /**
     * Adds a musician to an orchestra.
     *
     * @param orchestraId the ID of the orchestra to add the musician to
     * @param musicianDTO the musician to add
     * @return the musician after being added to the orchestra
     * @throws RuntimeException if an error occurs while adding the musician to the
     *                          orchestra
     */
    @Override
    public MusicianDTO addMusicianToOrchestra(Long orchestraId, MusicianDTO musicianDTO) {
        try {
            Orchestra orchestra = orchestraRepository.findById(orchestraId)
                    .orElseThrow(() -> new NoSuchElementException("No orchestra found with id: " + orchestraId));
            Musician musician = MusicianDTO.convertToEntity(musicianDTO);
            musician.setOrchestra(orchestra);
            Musician savedMusician = musicianRepository.save(musician);
            return MusicianDTO.convertToDTO(savedMusician);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add musician to orchestra: " + e.getMessage(), e);
        }
    }

    /**
     * Removes a musician from an orchestra.
     *
     * @param orchestraId the ID of the orchestra to remove the musician from
     * @param musicianId  the ID of the musician to remove
     * @return true if the musician was successfully removed, false otherwise
     * @throws RuntimeException if an error occurs while removing the musician from
     *                          the orchestra
     */
    @Override
    public boolean removeMusicianFromOrchestra(Long orchestraId, Long musicianId) {
        try {
            Orchestra orchestra = orchestraRepository.findById(orchestraId)
                    .orElseThrow(() -> new NoSuchElementException("No orchestra found with id: " + orchestraId));
            Musician musician = musicianRepository.findById(musicianId)
                    .orElseThrow(() -> new NoSuchElementException("No musician found with id: " + musicianId));
            if (musician.getOrchestra().equals(orchestra)) {
                musician.setOrchestra(null);
                musicianRepository.save(musician);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove musician from orchestra: " + e.getMessage(), e);
        }
    }

}
