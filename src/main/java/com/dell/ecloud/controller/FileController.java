package com.dell.ecloud.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    private final FileStorageService storageService;

    @Autowired
    public FileController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/uploads/get/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> fileDownload(@PathVariable("filename") String fileName) {
        Resource file = storageService.toResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/uploads/listNames")
    @ResponseBody
    public ResponseEntity<String> fileList() {
        return ResponseEntity.ok(new Gson().toJson(storageService.getListNames()));
    }

    @PostMapping("/uploads")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        return "redirect:/";
    }

    @GetMapping("/remove/{filename}")
    public boolean fileRemove(@PathVariable("filename") String fileName) {
        return storageService.remove(fileName);
    }

}
