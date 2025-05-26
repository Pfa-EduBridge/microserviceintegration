package tn.esprit.course.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path rootLocation = Paths.get("uploads");
    public String storeFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IOException("File is empty or null");
        }
        if (!"application/pdf".equals(file.getContentType())) {
            throw new IOException("Only PDF files are allowed");
        }

        String originalFilename = file.getOriginalFilename()
                .replaceAll("[^a-zA-Z0-9.-]", "_");
        String fileName = UUID.randomUUID().toString() + "_" + originalFilename;
        Path filePath = rootLocation.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        return "/files/" + fileName;
    }

}