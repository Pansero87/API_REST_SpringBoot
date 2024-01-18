package com.project.springproject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springproject.dto.OrchestraDTO;
import com.project.springproject.model.Orchestra;
import com.project.springproject.repositories.OrchestraRepository;
import com.project.springproject.services.servicesInterfaces.OrchestraService;

/**
 * Service class for managing orchestras.
 */
@Service
public class OrchestraServiceImpl implements OrchestraService {

    /**
     * Repository for accessing orchestra data.
     */
    @Autowired
    private OrchestraRepository orchestraRepository;

    /**
     * Saves an orchestra to the database.
     *
     * @param orchestraDTO the orchestra to save
     * @return the saved orchestra
     * @throws RuntimeException if an error occurs while saving the orchestra
     */
    @Override
    public OrchestraDTO saveOrchestra(OrchestraDTO orchestraDTO) {
        try {
            // Convert the DTO to an entity
            Orchestra orchestra = OrchestraDTO.convertToEntity(orchestraDTO);

            // Save the entity to the database
            Orchestra savedOrchestra = orchestraRepository.save(orchestra);

            // Convert the saved entity to a DTO and return it
            return OrchestraDTO.convertToDTO(savedOrchestra);
        } catch (Exception e) {
            // Handle the error and return a custom error message
            throw new RuntimeException("Error saving orchestra: " + e.getMessage());
        }
    }

    /**
     * Retrieves an orchestra by their ID.
     *
     * @param id the ID of the orchestra to retrieve
     * @return the retrieved orchestra, or null if no orchestra is found with the
     *         given ID
     * @throws RuntimeException if an error occurs while retrieving the orchestra
     */
    @Override
    public OrchestraDTO getOrchestraById(Long id) {
        try {
            Optional<Orchestra> orchestra = orchestraRepository.findById(id);
            if (orchestra.isPresent()) {
                return OrchestraDTO.convertToDTO(orchestra.get());
            } else {
                return null;
            }
        } catch (Exception e) {
            // Handle the error and return a custom error message
            throw new RuntimeException("Error getting orchestra: " + e.getMessage());
        }
    }

    /**
     * Retrieves all orchestras from the database.
     *
     * @return a list of all orchestras
     * @throws RuntimeException if an error occurs while retrieving the orchestras
     */
    @Override
    public List<OrchestraDTO> getAllOrchestras() {
        try {
            List<Orchestra> orchestras = orchestraRepository.findAll();
            List<OrchestraDTO> orchestraResult = new ArrayList<OrchestraDTO>();
            for (Orchestra orchestra : orchestras) {
                orchestraResult.add(OrchestraDTO.convertToDTO(orchestra));
            }
            return orchestraResult;
        } catch (Exception e) {
            // Handle the error and return a custom error message
            throw new RuntimeException("Error getting all orchestras: " + e.getMessage());
        }
    }

    /**
     * Deletes an orchestra by their ID.
     *
     * @param id the ID of the orchestra to delete
     * @throws RuntimeException if an error occurs while deleting the orchestra
     */
    @Override
    public void deleteOrchestraById(Long id) {
        try {
            orchestraRepository.deleteById(id);
        } catch (Exception e) {
            // Handle the error and return a custom error message
            throw new RuntimeException("Error deleting orchestra: " + e.getMessage());
        }
    }

}
