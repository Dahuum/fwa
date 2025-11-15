
package fr._42.cinema.models;

import java.time.LocalDateTime;

public class Image {
    private Long id;
    private Long userId;
    private String originalName;
    private String fileName;
    private LocalDateTime uploadedAt;

    public Image() {}

    public Image(Long userId, String originalName, String fileName) {
        this.userId = userId;
        this.originalName = originalName;
        this.fileName = fileName;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getOriginalName() { return originalName; }
    public void setOriginalName(String originalName) { this.originalName = originalName; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}