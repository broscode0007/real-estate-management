package virtusa.project.domains.health.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class DatabaseHealthController {

    // JdbcTemplate is a built-in Spring helper that runs raw SQL queries
    private final JdbcTemplate jdbcTemplate;

    public DatabaseHealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/db")
    public ResponseEntity<Map<String, Object>> checkDatabase() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // "SELECT 1" is the simplest query possible. If the DB responds, the connection is alive!
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            
            if (result != null && result == 1) {
                response.put("status", "UP");
                response.put("database", "PostgreSQL is CONNECTED successfully!");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "DOWN");
                response.put("message", "Database returned an unexpected response.");
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
            }
            
        } catch (DataAccessException e) {
            // If the connection fails (bad password, network down, etc.), this block runs
            response.put("status", "DOWN");
            response.put("database", "PostgreSQL Connection FAILED");
            response.put("error_reason", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}