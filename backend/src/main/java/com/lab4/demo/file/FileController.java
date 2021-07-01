package com.lab4.demo.file;


import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.lab4.demo.UrlMapping.FILES;

@RestController
@RequestMapping(FILES)
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping()
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {


        return fileService.upload(file);
    }

    @GetMapping("/{fileName}")
    public String download(@PathVariable String fileName) throws IOException {

        return fileService.download(fileName);
    }
}
