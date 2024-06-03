package com.training.service;

import com.training.model.Resource;
import com.training.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ResourceService {


    ResourceRepository resourceRepository;

    @Autowired
    ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Resource store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Resource resource = new Resource(fileName, file.getContentType(), file.getBytes());
        return resourceRepository.save(resource);
    }

    public Resource getFile(Integer id) {
        return resourceRepository.findById(id).orElse(null);
    }

    public void removeFiles(List<Integer> ids) {
        resourceRepository.deleteAllById(ids);
    }


}
