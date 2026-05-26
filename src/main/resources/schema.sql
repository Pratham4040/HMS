CREATE TABLE IF NOT EXISTS doctors (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  specialty TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS patients (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  department TEXT NOT NULL,
  time TEXT NOT NULL,
  doctor_id BIGINT,
  CONSTRAINT fk_patients_doctor
    FOREIGN KEY (doctor_id)
    REFERENCES doctors (id)
    ON DELETE SET NULL
);
