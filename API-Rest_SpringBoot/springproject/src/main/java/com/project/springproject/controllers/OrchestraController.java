package com.project.springproject.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springproject.dto.OrchestraDTO;
import com.project.springproject.services.servicesInterfaces.OrchestraService;

/**
 * This class represents the controller for managing orchestras in the Spring
 * Project.
 */
@RestController
@RequestMapping()
public class OrchestraController {

    // @GetMapping("/hello")
    // public String displayWelcomeMessage() {
    // String res = "Hello from Spring Boot!\n";
    // res += "You are in the root directory!\n";
    // res += "You are running " + SpringApplication.class.getName() + "\n";
    // return res;
    // }

    @Autowired
    private OrchestraService orchestraService;

    /**
     * Retrieves all orchestras.
     *
     * @return A response entity containing a map of orchestras.
     */
    @GetMapping("/orchestras")
    public ResponseEntity<Map<String, List<OrchestraDTO>>> getAllOrchestras() {
        try {
            List<OrchestraDTO> orchestraList = orchestraService.getAllOrchestras();
            Map<String, List<OrchestraDTO>> orchestraResponse = Map.of("orchestras", orchestraList);
            return new ResponseEntity<>(orchestraResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Finds an orchestra by its ID.
     *
     * @param id The ID of the orchestra to find.
     * @return A response entity containing the found orchestra.
     */
    @GetMapping("/orchestras/{id}")
    public ResponseEntity<OrchestraDTO> findOrchestra(@PathVariable Long id) {
        try {
            OrchestraDTO orchestra = orchestraService.getOrchestraById(id);
            if (orchestra != null) {
                return new ResponseEntity<>(orchestra, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(OrchestraController.class);

    /**
     * Creates a new orchestra.
     *
     * @param newOrchestra The orchestra to create.
     * @return A response entity containing the created orchestra.
     */
    @PostMapping("/orchestras/add")
    public ResponseEntity<?> createOrchestra(@RequestBody OrchestraDTO newOrchestra) {
        try {
            OrchestraDTO orchestra = orchestraService.saveOrchestra(newOrchestra);
            if (orchestra == null) {
                logger.error("Failed to create orchestra");
                return new ResponseEntity<>(orchestra, HttpStatus.BAD_REQUEST);
            } else {
                logger.info("Orchestra created successfully");
                return new ResponseEntity<>(orchestra, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            logger.error("Internal server error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing orchestra.
     *
     * @param updOrchestra The updated orchestra.
     * @return A response entity containing the updated orchestra.
     */
    @PutMapping("/orchestras/upd")
    public ResponseEntity<OrchestraDTO> updateOrchestra(@RequestBody OrchestraDTO updOrchestra) {
        try {
            OrchestraDTO orchestra = orchestraService.getOrchestraById(updOrchestra.getId());
            if (orchestra == null) {
                logger.error("Orchestra not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                OrchestraDTO orchestraUPD = orchestraService.saveOrchestra(updOrchestra);
                logger.info("Orchestra updated successfully");
                return new ResponseEntity<>(orchestraUPD, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes an orchestra by its ID.
     *
     * @param id The ID of the orchestra to delete.
     * @return A response entity indicating the result of the deletion.
     */
    @DeleteMapping("orchestras/del/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            orchestraService.deleteOrchestraById(id);
            return new ResponseEntity<>("Orchestra deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
