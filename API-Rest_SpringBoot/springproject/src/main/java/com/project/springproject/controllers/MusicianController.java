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
import org.springframework.web.servlet.ModelAndView;

import com.project.springproject.dto.MusicianDTO;
import com.project.springproject.services.servicesInterfaces.MusicianService;

/**
 * This class represents the controller for managing musicians.
 */
@RestController
@RequestMapping()
public class MusicianController {

    @Autowired
    private MusicianService musicianService;

    /**
     * Retrieves all musicians.
     * 
     * @return ResponseEntity<Map<String, List<MusicianDTO>>> - The response entity
     *         containing a map of musicians.
     */
    @GetMapping("/musicians")
    public ResponseEntity<Map<String, List<MusicianDTO>>> getAllMusicians() {
        try {
            logger.info("Getting all musicians");
            List<MusicianDTO> musiciansList = musicianService.getAllMusicians();
            Map<String, List<MusicianDTO>> musicianResponse = Map.of("musicians", musiciansList);
            logger.info("Musicians retrieved successfully");
            return new ResponseEntity<>(musicianResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Internal server error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a ModelAndView object containing the view name and model attributes
     * for displaying a list of musicians.
     * 
     * @return ModelAndView object representing the musicians list view
     */
    @GetMapping("/musicians/view-list")
    public ModelAndView getAllMusiciansView() {
        ModelAndView mav = new ModelAndView("musiciansList");
        try {
            logger.info("Getting all musicians");
            List<MusicianDTO> musiciansList = musicianService.getAllMusicians();
            mav.addObject("musicians", musiciansList);
            logger.info("Musicians retrieved successfully");
            return mav;
        } catch (Exception e) {
            logger.error("Internal server error: " + e.getMessage());
            return mav;
        }
    }

    /**
     * Finds a musician by ID.
     * 
     * @param id - The ID of the musician to find.
     * @return ResponseEntity<MusicianDTO> - The response entity containing the
     *         musician.
     */
    @GetMapping("/musicians/{id}")
    public ResponseEntity<MusicianDTO> findMusician(@PathVariable Long id) {
        try {
            MusicianDTO musician = musicianService.getMusicianById(id);
            if (musician != null) {
                return new ResponseEntity<>(musician, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(MusicianController.class);

    /**
     * Creates a new musician.
     * 
     * @param newMusician - The musician to create.
     * @return ResponseEntity<?> - The response entity.
     */
    @PostMapping("/musicians/add")
    public ResponseEntity<?> createMusician(@RequestBody MusicianDTO newMusician) {
        try {
            MusicianDTO musician = musicianService.saveMusician(newMusician);
            if (musician == null) {
                logger.error("Error saving musician");
                return new ResponseEntity<>(musician, HttpStatus.BAD_REQUEST);

            } else {
                logger.info("Musician saved successfully");
                return new ResponseEntity<>(musician, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Internal server error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing musician.
     * 
     * @param updMusician - The musician to update.
     * @return ResponseEntity<MusicianDTO> - The response entity containing the
     *         updated musician.
     */
    @PutMapping("/musicians/upd")
    public ResponseEntity<MusicianDTO> updateMusician(@RequestBody MusicianDTO updMusician) {
        try {
            MusicianDTO musician = musicianService.getMusicianById(updMusician.getId());
            if (musician == null) {
                logger.error("Musician not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                MusicianDTO musicianUPD = musicianService.saveMusician(updMusician);
                logger.info("Musician updated successfully");
                return new ResponseEntity<>(musicianUPD, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a musician by ID.
     * 
     * @param id - The ID of the musician to delete.
     * @return ResponseEntity<String> - The response entity.
     */
    @DeleteMapping("musicians/del/{id}")
    public ResponseEntity<String> deleteMusician(@PathVariable Long id) {
        try {
            musicianService.deleteMusicianById(id);
            // ResponseEntity
            return new ResponseEntity<>("Musician deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds a musician to an orchestra.
     * 
     * @param orchestraId - The ID of the orchestra.
     * @param newMusician - The musician to add.
     * @return ResponseEntity<?> - The response entity.
     */
    @PostMapping("/orchestras/{orchestraId}/musicians")
    public ResponseEntity<?> addMusicianOrchestra(@PathVariable Long orchestraId,
            @RequestBody MusicianDTO newMusician) {
        try {
            MusicianDTO musician = musicianService.addMusicianToOrchestra(orchestraId, newMusician);
            if (musician == null) {
                logger.error("Error adding musician to orchestra");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                logger.info("Musician added to orchestra successfully");
                return new ResponseEntity<>(musician, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Removes a musician from an orchestra.
     * 
     * @param orchestraId - The ID of the orchestra.
     * @param musicianId  - The ID of the musician.
     * @return ResponseEntity<?> - The response entity.
     */
    @DeleteMapping("/orchestras/{orchestraId}/musicians/{musicianId}")
    public ResponseEntity<?> removeMusicianFromOrchestra(@PathVariable Long orchestraId,
            @PathVariable Long musicianId) {
        try {
            boolean removed = musicianService.removeMusicianFromOrchestra(orchestraId, musicianId);
            if (removed) {
                logger.info("Musician removed from orchestra successfully");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                logger.error("Error removing musician from orchestra");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
