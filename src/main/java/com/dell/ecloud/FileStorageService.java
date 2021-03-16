package com.dell.ecloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileStorageService {

    private final Path rootLocation = Path.of("test");

    @Autowired
    public FileStorageService() {}

    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IOException("Failed to store empty file.");
            }

            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(Objects.requireNonNull(file.getOriginalFilename())))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            System.out.println("Failed to store the file. " + e.getMessage());
        }
    }

}
