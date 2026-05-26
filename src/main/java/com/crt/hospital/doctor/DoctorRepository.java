package com.crt.hospital.doctor;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorRepository {
    private final JdbcTemplate jdbcTemplate;

    public DoctorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Doctor> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, specialty FROM doctors ORDER BY id DESC",
                (rs, rowNum) -> new Doctor(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("specialty")));
    }

    public Doctor findById(long id) {
        List<Doctor> doctors = jdbcTemplate.query(
                "SELECT id, name, specialty FROM doctors WHERE id = ?",
                (rs, rowNum) -> new Doctor(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("specialty")),
                id);
        return doctors.isEmpty() ? null : doctors.get(0);
    }

    public Doctor create(String name, String specialty) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO doctors (name, specialty) VALUES (?, ?)",
                    new String[] { "id" });
            statement.setString(1, name);
            statement.setString(2, specialty);
            return statement;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long id = key == null ? 0L : key.longValue();
        Doctor created = findById(id);
        return created == null ? new Doctor(id, name, specialty) : created;
    }

    public boolean deleteById(long id) {
        int rows = jdbcTemplate.update("DELETE FROM doctors WHERE id = ?", id);
        return rows > 0;
    }
}