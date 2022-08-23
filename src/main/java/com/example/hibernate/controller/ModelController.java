package com.example.hibernate.controller;

import com.example.hibernate.model.*;
import com.example.hibernate.service.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/car/model")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("")
    public ResponseEntity<List<Model>> getAllModels() {
        try {
            List<Model> models = modelService.getModels();

            if (models.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(models, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Model> getModelById(@PathVariable("id") UUID id) {
        try {
            return new ResponseEntity<>(modelService.getModelById(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Model> createModel(@RequestBody AddModel model) {
        try {
            return new ResponseEntity<>(modelService.addModel(model), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Model> updateModel(@PathVariable("id") UUID id, @RequestBody UpdateModel updateModel) {
        try {
            return new ResponseEntity<>(modelService.updateModel(id, updateModel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{model_id}/hasManufacturer/{manufacturer_id}")
    public ResponseEntity<Model> hasManufacturer(@PathVariable("model_id") UUID modelId, @PathVariable("manufacturer_id") UUID manufacturerId) {
        try {
            return new ResponseEntity<>(modelService.hasManufacturer(modelId, manufacturerId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Model> deleteModel(@PathVariable("id") UUID id) {
        try {
            return new ResponseEntity<>(modelService.deleteById(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllModels() {
        try {
            modelService.deleteAllModels();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
