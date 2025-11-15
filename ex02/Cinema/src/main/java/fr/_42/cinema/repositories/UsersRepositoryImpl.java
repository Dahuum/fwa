package fr._42.cinema.repositories;

import fr._42.cinema.models.User;
import fr._42.cinema.models.Image;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.util.Map;
import fr._42.cinema.models.AuthenticationHistory;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return user;
    };
    
    private final RowMapper<AuthenticationHistory> authHistoryRowMapper = (rs, rowNum) -> {
        AuthenticationHistory auth = new AuthenticationHistory();
        auth.setId(rs.getLong("id"));
        auth.setUserId(rs.getLong("user_id"));
        auth.setLoginTime(rs.getTimestamp("login_time").toLocalDateTime());
        auth.setIpAddress(rs.getString("ip_address"));
        return auth;
    };
    private final RowMapper<Image> imageRowMapper = (rs, rowNum) -> {
        Image image = new Image();
        image.setId(rs.getLong("id"));
        image.setUserId(rs.getLong("user_id"));
        image.setOriginalName(rs.getString("original_name"));
        image.setFileName(rs.getString("file_name"));
        image.setUploadedAt(rs.getTimestamp("uploaded_at").toLocalDateTime());
        return image;
    };
    
    public UsersRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            return ps;
        }, keyHolder);
       
        Map<String, Object> keys = keyHolder.getKeys();
            user.setId(((Number) keys.get("id")).longValue());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, email);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }
    
    @Override
    public void saveAuthenticationHistory(AuthenticationHistory auth) {
        String sql = "INSERT INTO authentication_history (user_id, ip_address) VALUES (?, ?)";
        jdbcTemplate.update(sql, auth.getUserId(), auth.getIpAddress());
    }
       
    @Override
    public List<AuthenticationHistory> findAuthHistoryByUserId(Long userId) {
        String sql = "SELECT * FROM authentication_history WHERE user_id = ? ORDER BY login_time DESC";
        return jdbcTemplate.query(sql, authHistoryRowMapper, userId);
    }
    
    @Override
    public void saveImage(Image image) {
        String sql = "INSERT INTO images (user_id, original_name, file_name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, image.getUserId(), image.getOriginalName(), image.getFileName());
    }
    
    @Override
    public List<Image> findImagesByUserId(Long userId) {
        String sql = "SELECT * FROM images WHERE user_id = ? ORDER BY uploaded_at DESC";
        return jdbcTemplate.query(sql, imageRowMapper, userId);
    }

}