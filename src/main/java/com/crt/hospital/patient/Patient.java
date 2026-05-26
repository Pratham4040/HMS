package com.crt.hospital.patient;

public class Patient {
    private final long id;
    private final String name;
    private final String department;
    private final String time;
    private final Long doctorId;
    private final String doctorName;

    public Patient(long id, String name, String department, String time, Long doctorId, String doctorName) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.time = time;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getTime() {
        return time;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }
}
