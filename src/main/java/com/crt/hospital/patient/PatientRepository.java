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
            "SELECT p.id, p.name, p.department, p.time, p.doctor_id, d.name AS doctor_name "
                + "FROM patients p LEFT JOIN doctors d ON d.id = p.doctor_id ORDER BY p.id DESC",
                (rs, rowNum) -> new Patient(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                rs.getString("time"),
                rs.getObject("doctor_id") == null ? null : rs.getLong("doctor_id"),
                rs.getString("doctor_name")));
    }

        public Patient findById(long id) {
        List<Patient> patients = jdbcTemplate.query(
            "SELECT p.id, p.name, p.department, p.time, p.doctor_id, d.name AS doctor_name "
                + "FROM patients p LEFT JOIN doctors d ON d.id = p.doctor_id WHERE p.id = ?",
            (rs, rowNum) -> new Patient(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("department"),
                rs.getString("time"),
                rs.getObject("doctor_id") == null ? null : rs.getLong("doctor_id"),
                rs.getString("doctor_name")),
            id);
        return patients.isEmpty() ? null : patients.get(0);
        }

        public Patient create(String name, String department, String time, long doctorId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO patients (name, department, time, doctor_id) VALUES (?, ?, ?, ?)",
                    new String[] { "id" });
            statement.setString(1, name);
            statement.setString(2, department);
            statement.setString(3, time);
            statement.setLong(4, doctorId);
            return statement;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long id = key == null ? 0L : key.longValue();
        Patient created = findById(id);
        return created == null ? new Patient(id, name, department, time, doctorId, null) : created;
    }

    public boolean deleteById(long id) {
        int rows = jdbcTemplate.update("DELETE FROM patients WHERE id = ?", id);
        return rows > 0;
    }
}
