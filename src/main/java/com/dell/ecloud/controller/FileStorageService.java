package com.dell.ecloud.controller;

import com.dell.ecloud.model.StudentFile;
import com.dell.ecloud.model.StudentFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {

    private final Path rootPath = Paths.get("uploads");
    private final StudentFileRepository repository;

    @Autowired
    public FileStorageService(StudentFileRepository repository) {
        this.repository = repository;
    }

    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IOException("Error: the file is empty");
            }

            Path destinationFile = this.rootPath.resolve(Paths
                    .get(Objects.requireNonNull(file.getOriginalFilename()))
                    .normalize()).toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Failed to store the file. " + e.getMessage());
        }
    }

    public boolean remove(String fileName) {
        Path file = this.rootPath.resolve(Paths
                .get(fileName).normalize()).toAbsolutePath();

        return file.toFile().delete();
    }

    public Resource toResource(String fileName) {
        try {
            Path file = this.rootPath.resolve(Paths
                    .get(fileName).normalize()).toAbsolutePath();

            Resource resource = new UrlResource(file.toUri());
            System.out.println(file.getParent());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new MalformedURLException("Error: wrong path");
            }
        } catch (MalformedURLException e) {
            System.out.println("Failed to return the file. " + e.getMessage());
            return null;
        }
    }

}
