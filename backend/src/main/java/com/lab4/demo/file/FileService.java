package com.lab4.demo.file;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static com.lab4.demo.Constants.*;

@Service
@RequiredArgsConstructor
public class FileService {

    public String upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

            File file = this.convertToFile(multipartFile, fileName);
            this.uploadFile(file, fileName);
            file.delete();

            return "Successfully Uploaded !";
        } catch (Exception e) {
            e.printStackTrace();
            return "Unsuccessfully Uploaded!";
        }

    }

    public String download(String fileName) throws IOException {
        String destFileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));
        String destFilePath = "D:\\Downloads\\" + destFileName;

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(JSON_PATH));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("gathers-400b0.appspot.com/", fileName));
        blob.downloadTo(Paths.get(destFilePath));
        return "Successfully Downloaded!";
    }


    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("gs://gathers-400b0.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(GENERATED_JSON));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
