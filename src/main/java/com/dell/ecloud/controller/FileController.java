package com.dell.ecloud.controller;

import com.dell.ecloud.model.FileStorageService;
import com.dell.ecloud.model.UserFile;
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

    @Autowired
    public FileController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/uploads/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> fileDownload(@PathVariable("filename") String fileName) {
        Resource file = storageService.toResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
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
    public String fileUpload(@RequestParam("file") MultipartFile file, String description, String university, String category) {
        log.info("Description = " + description);
        String nickname = SecurityContextHolder
                .getContext()
                .getAuthentication().getName();

        storageService.store(file, nickname, university, category, description);
        return "redirect:/";
    }

}
