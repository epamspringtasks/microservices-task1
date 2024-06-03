package com.training.service;

import com.training.model.Resource;
import com.training.model.ResourceMetadata;
import com.training.model.SongDto;
import com.training.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ResourceService {

    ResourceRepository resourceRepository;

    RestTemplate restTemplate;

    @Autowired
    ResourceService(ResourceRepository resourceRepository, RestTemplate restTemplate) {
        this.resourceRepository = resourceRepository;
        this.restTemplate = restTemplate;
    }

    public Resource store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Resource resource = new Resource(fileName, file.getContentType(), file.getBytes());
        return resourceRepository.save(resource);
    }

    public void storeSongMetadata(ResourceMetadata resourceMetadata) throws IOException {
        ResponseEntity<SongDto> responseEntity = restTemplate
                .postForEntity("http://localhost:8084/songs",
                        resourceMetadata, SongDto.class);
    }

    public Resource getFile(Integer id) {
        return resourceRepository.findById(id).orElse(null);
    }

    public void getSongMetadata(Integer id) throws IOException {
        ResponseEntity<SongDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8084/songs/" + id, SongDto.class);
    }

    public void removeFiles(List<Integer> ids) {
        resourceRepository.deleteAllById(ids);
    }

    public void removeSongsMetadata(List<Integer> ids) throws IOException {
        restTemplate.delete("http://localhost:8084/songs", ids);
    }

}
