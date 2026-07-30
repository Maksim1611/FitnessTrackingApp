package app.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@Service
public class FileStorageService {

    private static final Set<String> ALLOWED_IMAGE_TYPES = Set.of("image/jpeg", "image/png", "image/webp");

    /**
     * Stores an uploaded image under uploads/{subdirectory}/{baseName}.{ext}
     * and returns the relative URL (with a cache-busting version parameter).
     */
    public String storeImage(MultipartFile file, String subdirectory, String baseName) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("No image provided");
        }

        String contentType = file.getContentType();
        // Some clients (desktop pickers) send octet-stream — fall back to the extension.
        if (contentType == null || contentType.equals("application/octet-stream")) {
            String filename = file.getOriginalFilename();
            String extension = filename != null && filename.contains(".")
                    ? filename.substring(filename.lastIndexOf('.') + 1).toLowerCase()
                    : "";
            contentType = switch (extension) {
                case "png" -> "image/png";
                case "webp" -> "image/webp";
                case "jpg", "jpeg" -> "image/jpeg";
                default -> contentType;
            };
        }
        if (contentType == null || !ALLOWED_IMAGE_TYPES.contains(contentType)) {
            throw new IllegalArgumentException("Only JPEG, PNG or WebP images are allowed");
        }

        String extension = switch (contentType) {
            case "image/png" -> "png";
            case "image/webp" -> "webp";
            default -> "jpg";
        };

        try {
            Path dir = Paths.get("uploads", subdirectory);
            Files.createDirectories(dir);
            Path target = dir.resolve(baseName + "." + extension);
            file.transferTo(target.toAbsolutePath());
            return "/uploads/" + subdirectory + "/" + baseName + "." + extension + "?v=" + System.currentTimeMillis();
        } catch (IOException e) {
            throw new IllegalStateException("Could not store image: " + e.getMessage(), e);
        }
    }
}
