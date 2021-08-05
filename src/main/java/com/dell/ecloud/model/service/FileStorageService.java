package com.dell.ecloud.model.service;

import com.dell.ecloud.model.entity.UserFile;
import com.dell.ecloud.model.repository.UserFileRepository;
import com.dell.ecloud.model.time.ITime;
import com.dell.ecloud.model.time.Time;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Objects;

@Service
@Slf4j
public class FileStorageService {

    private final Path rootPath = Paths.get("uploads");
    private final UserFileRepository repository;
    private final ITime time;

    @Autowired
    public FileStorageService(UserFileRepository repository) {
        this.repository = repository;
        this.time = new Time();
    }

    public Iterable<UserFile> getFilesList() {
        return repository.findAll();
    }

    public Iterable<UserFile> getFilesList(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<UserFile> result = repository.findAll(paging);

        if (result.hasContent()) {
            return result.getContent();
        } else {
            return Collections.emptyList();
        }
    }

    public Iterable<UserFile> getFilesByUser(long id) {
        return repository.findAllByUserid(id);
    }

    public void store(MultipartFile file, long id, String university, String name, String description) {
        log.info("Storing the file {}", file.getOriginalFilename());

        try {
            if (file.isEmpty())
                throw new IOException("Error: the file (" + file.getOriginalFilename() + ") is empty");

            Path dirPath = this.rootPath.resolve(Paths
                    .get(String.valueOf(id))
                    .normalize()).toAbsolutePath();

            if (!dirPath.toFile().exists())
                Files.createDirectory(dirPath);

            Path destinationFile = dirPath.resolve(Paths
                    .get(Objects.requireNonNull(file.getOriginalFilename()))
                    .normalize()).toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            log.info("Saving the file ({}) entity to the repository", file.getOriginalFilename());

            UserFile entity = new UserFile(name, time.update().toString(), university,
                    null, description, id, file.getOriginalFilename());
            repository.save(entity);
            log.info("The file was saved to the repository");

            log.info("The file was stored");
        } catch (IOException e) {
            log.error("Couldn't save the file. {}", e.getMessage());
        }
    }

    public void remove(String fileName) throws IOException {
        Files.delete(this.rootPath.resolve(Paths.get(fileName)).toAbsolutePath());
    }

    public Iterable<UserFile> findAllByNameContaining(String search) {
        return repository.findAllByNameContaining(search);
    }

    public Resource getResource(String fileName) {
        try {
            log.info("Get a file {}", fileName);
            Path file = this.rootPath.resolve(Paths
                    .get(fileName).normalize()).toAbsolutePath();

            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                log.info("File was found, return");
                return resource;
            } else {
                throw new MalformedURLException("Error: wrong path (" + file + ")");
            }
        } catch (MalformedURLException e) {
            log.error("Failed to return the file. {}", e.getMessage());
            return null;
        }
    }

}
