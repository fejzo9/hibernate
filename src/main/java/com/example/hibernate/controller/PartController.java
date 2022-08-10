package com.example.hibernate.controller;

import com.example.hibernate.model.Model;
import com.example.hibernate.model.Part;
import com.example.hibernate.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PartController {
    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/parts")
    public ResponseEntity<List<Part>> getAllParts(){
        try {
            List<Part> parts = partService.getPart();

            if (parts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(parts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/parts/{id}")
    public ResponseEntity<Part> getPartById(@PathVariable("id") UUID id){
        try {
            Part part = partService.getPartById(id);
            return new ResponseEntity<>(part,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("parts")
    public ResponseEntity<Part> createPart(@RequestBody Part part) {
        try{
            return new ResponseEntity<>(partService.addPart(part), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/parts/{id}")
    public ResponseEntity<Part> updatePart(@PathVariable("id") UUID id, @RequestBody Part part){
        try {
            return new ResponseEntity<>(partService.updatePart(id,part), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/part/{id}")
    public ResponseEntity<Part> deletePart(@PathVariable("id") UUID id) {
        try {
            return new ResponseEntity<>(partService.deleteById(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/parts")
    public ResponseEntity<HttpStatus> deleteAllParts() {
        try{
            partService.deleteAllModels();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
