package com.crt.hospital.patient;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepository {
    private final JdbcTemplate jdbcTemplate;

    public PatientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Patient> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, department, time FROM patients ORDER BY id DESC",
                (rs, rowNum) -> new Patient(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("time")));
    }

    public Patient create(String name, String department, String time) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO patients (name, department, time) VALUES (?, ?, ?)",
                    new String[] { "id" });
            statement.setString(1, name);
            statement.setString(2, department);
            statement.setString(3, time);
            return statement;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long id = key == null ? 0L : key.longValue();
        return new Patient(id, name, department, time);
    }

    public boolean deleteById(long id) {
        int rows = jdbcTemplate.update("DELETE FROM patients WHERE id = ?", id);
        return rows > 0;
    }
}
