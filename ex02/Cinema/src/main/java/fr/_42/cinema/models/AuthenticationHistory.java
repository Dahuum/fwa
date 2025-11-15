package fr._42.cinema.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuthenticationHistory {
    private Long id;
    private Long userId;
    private LocalDateTime loginTime;
    private String ipAddress;

    public AuthenticationHistory() {}

    public AuthenticationHistory(Long userId, String ipAddress) {
        this.userId = userId;
        this.ipAddress = ipAddress;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDateTime getLoginTime() { return loginTime; }
    public void setLoginTime(LocalDateTime loginTime) { this.loginTime = loginTime; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    
    public String getFormattedLoginTime() {
            if (loginTime == null) return "";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return loginTime.format(formatter);
    }
}