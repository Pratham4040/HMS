package com.crt.hospital.patient;

public class Patient {
    private final long id;
    private final String name;
    private final String department;
    private final String time;

    public Patient(long id, String name, String department, String time) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.time = time;
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
}
