package com.training.controller;

import com.training.convertor.Mp3MetadataConvertor;
import com.training.model.Resource;
import com.training.model.ResourceMetadata;
import com.training.service.ResourceService;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @Transactional
    @PostMapping(path = "resources", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uploadNewResource(@RequestParam MultipartFile file) {
        try {
            ResourceMetadata resourceMetadata = Mp3MetadataConvertor.convert(new ByteArrayInputStream(file.getBytes()));
            Resource uploadedResource = resourceService.store(file);

            resourceMetadata.setResourceId(String.valueOf(uploadedResource.getId()));
            resourceService.storeSongMetadata(resourceMetadata);

            return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("id", uploadedResource.getId().toString()));
        } catch (IOException | SAXException | TikaException e) {
            throw new RuntimeException("Exception while saving the Resource. ");
        }
    }


    @GetMapping(path = "resources/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> getResource(@PathVariable Integer id) {
        try {
            Resource resource = resourceService.getFile(id);
            if (resource == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            resourceService.getSongMetadata(id);
            return ResponseEntity.status(HttpStatus.OK).body(resource);
        } catch (Exception e) {
            throw new RuntimeException("Exception while fetching the Resource. ");
        }
    }

    @Transactional
    @DeleteMapping(path = "resources", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteResources(@RequestParam(value = "id") List<Integer> id) {
        // call song service to remove the metadata first
        resourceService.removeFiles(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
    }
}
