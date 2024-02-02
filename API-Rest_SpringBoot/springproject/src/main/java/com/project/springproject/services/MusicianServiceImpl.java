package com.project.springproject.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springproject.model.MusicianEntity;
import com.project.springproject.model.OrchestraEntity;
import com.project.springproject.model.dto.MusicianDTO;
import com.project.springproject.repositories.MusicianRepository;
import com.project.springproject.repositories.OrchestraRepository;
import com.project.springproject.services.servicesInterfaces.MusicianService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
            MusicianEntity musician = MusicianDTO.convertToEntity(musicianDTO);
            if (musicianDTO.getOrchestraId() != null) {
                OrchestraEntity orchestra = orchestraRepository.findById(musicianDTO.getOrchestraId())
                        .orElseThrow(() -> new NoSuchElementException(
                                "No orchestra found with id: " + musicianDTO.getOrchestraId()));
                musician.setOrchestra(orchestra);
            }
            MusicianEntity saveMusician = musicianRepository.save(musician);
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
            Optional<MusicianEntity> musician = musicianRepository.findById(id);
            if (musician.isPresent()) {
                return MusicianDTO.convertToDTO(musician.get());
            } else {
                throw new NoSuchElementException("No musician found with id: " + id);
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Failed to get musician by id: " + e.getMessage());
        }
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MusicianDTO> getAllMusicians() {
        List<MusicianEntity> musicians = entityManager.createQuery(
                "SELECT m FROM MusicianEntity m LEFT JOIN FETCH m.orchestra", MusicianEntity.class).getResultList();

        return musicians.stream()
                .map(musician -> modelMapper.map(musician, MusicianDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all musicians along with their details.
     *
     * @return A list of MusicianDTO objects representing the musicians.
     */
    // @Override
    // public List<MusicianDTO> getAllMusicians() {
    // List<MusicianEntity> musicians = musicianRepository.findAll();
    // List<MusicianDTO> musicianDTOs = new ArrayList<>();
    // for (MusicianEntity musician : musicians) {
    // MusicianDTO dto = new MusicianDTO();
    // // dto.setId(musician.getId());
    // dto.setFirstname(musician.getFirstname());
    // dto.setLastname(musician.getLastname());
    // // dto.setBirthdate(musician.getBirthdate());
    // dto.setInstrument(musician.getInstrument());
    // dto.setEmail(musician.getEmail());
    // // Check if the musician has an orchestra
    // if (musician.getOrchestra() != null) {
    // // Get the orchestra
    // OrchestraEntity orchestra =
    // orchestraRepository.findById(musician.getOrchestra().getId())
    // .orElseThrow(() -> new NoSuchElementException(
    // "No orchestra found with id: " + musician.getOrchestra().getId()));
    // // dto.setOrchestraId(orchestra.getId());
    // dto.setOrchestraName(orchestra.getName());

    // }
    // musicianDTOs.add(dto);
    // }
    // return musicianDTOs;
    // }

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
            OrchestraEntity orchestra = orchestraRepository.findById(orchestraId)
                    .orElseThrow(() -> new NoSuchElementException("No orchestra found with id: " + orchestraId));
            MusicianEntity musician = MusicianDTO.convertToEntity(musicianDTO);
            musician.setOrchestra(orchestra);
            MusicianEntity savedMusician = musicianRepository.save(musician);
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
            OrchestraEntity orchestra = orchestraRepository.findById(orchestraId)
                    .orElseThrow(() -> new NoSuchElementException("No orchestra found with id: " + orchestraId));
            MusicianEntity musician = musicianRepository.findById(musicianId)
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
