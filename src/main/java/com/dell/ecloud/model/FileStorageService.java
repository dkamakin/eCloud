package com.dell.ecloud.model;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FileStorageService {

    private final Path rootPath = Paths.get("uploads");
    private final UserFileRepository repository;
    private final Time time;

    @Autowired
    public FileStorageService(UserFileRepository repository) {
        this.repository = repository;
        this.time = new Time();
    }

    public void saveEntity(MultipartFile file, String nickname) {
        log.info("Saving file (" + file.getOriginalFilename() + ") entity to the repository");


        UserFile entity = new UserFile(file.getOriginalFilename(), time.update().toString(),
                null, null, nickname);
        repository.save(entity);
        log.info("File saved to the repository");
    }

    public Iterable<UserFile> getFilesList() {
        return repository.findAll();
    }

    public void store(MultipartFile file, String nickname) {
        log.info("Storing a file (" + file.getOriginalFilename() + ')');

        try {
            if (file.isEmpty())
                throw new IOException("Error: the file (" + file.getOriginalFilename() + ") is empty");

            Path destinationFile = this.rootPath.resolve(Paths
                    .get(Objects.requireNonNull(file.getOriginalFilename()))
                    .normalize()).toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            saveEntity(file, nickname);
            log.info("File stored");
        } catch (IOException e) {
            log.error("Couldn't save the file. " + e.getMessage());
        }
    }

    public boolean remove(String fileName) {
        Path file = this.rootPath.resolve(Paths
                .get(fileName).normalize()).toAbsolutePath();
        boolean result = file.toFile().delete();
        log.info("File (" + fileName + ") is " + (result ? "deleted" : "not deleted"));
        return result;
    }

    public Resource toResource(String fileName) {
        try {
            Path file = this.rootPath.resolve(Paths
                    .get(fileName).normalize()).toAbsolutePath();

            Resource resource = new UrlResource(file.toUri());

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
