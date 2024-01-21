
package com.project.springproject.services.servicesInterfaces;

import java.util.List;

import com.project.springproject.dto.OrchestraDTO;

/**
 * The OrchestraService interface provides methods to manage orchestras.
 */
public interface OrchestraService {
    /**
     * Saves an orchestra.
     *
     * @param orchestraDTO The orchestra to be saved.
     * @return The saved orchestra.
     */
    OrchestraDTO saveOrchestra(OrchestraDTO orchestraDTO);

    /**
     * Retrieves an orchestra by its ID.
     *
     * @param id The ID of the orchestra to retrieve.
     * @return The retrieved orchestra.
     */
    OrchestraDTO getOrchestraById(Long id);

    /**
     * Retrieves all orchestras.
     *
     * @return A list of all orchestras.
     */
    List<OrchestraDTO> getAllOrchestras();

    /**
     * Deletes an orchestra by its ID.
     *
     * @param id The ID of the orchestra to delete.
     */
    void deleteOrchestraById(Long id);
}