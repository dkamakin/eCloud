package com.dell.ecloud.controller;

import com.dell.ecloud.model.FileStorageService;
import com.dell.ecloud.model.UserFile;
import com.dell.ecloud.model.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class FileController {

    private final FileStorageService storageService;
    private final UserRepository userRepository;

    @Autowired
    public FileController(FileStorageService storageService, UserRepository userRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
    }

    @GetMapping("/uploads/{id}/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> fileDownload(@PathVariable("id") long id, @PathVariable("filename") String fileName) {
        Resource file = storageService.getResource(String.valueOf(id) + '/' + fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/uploads/{id}")
    public Iterable<UserFile> filesByUser(@PathVariable long id) {
        log.info("Searching for files uploaded by " + id);
        return storageService.getFilesByUser(id);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/uploads/{filename}")
    public boolean fileDelete(@PathVariable("filename") String fileName) {
        return storageService.remove(fileName);
    }

    @GetMapping("/uploads")
    @ResponseBody
    public Iterable<UserFile> filesList() {
        return storageService.getFilesList();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/uploads")
    public String fileUpload(@RequestParam("file") MultipartFile file, String description, String university, String name) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication().getName();

        long id = userRepository.findByUsername(username).getId();

        storageService.store(file, id, university, name, description);
        return "redirect:/";
    }

}
