INSERT INTO doctors (name, specialty)
SELECT 'Dr. Anita Rao', 'Cardiology'
WHERE NOT EXISTS (SELECT 1 FROM doctors WHERE name = 'Dr. Anita Rao' AND specialty = 'Cardiology');

INSERT INTO doctors (name, specialty)
SELECT 'Dr. Nikhil Verma', 'General Medicine'
WHERE NOT EXISTS (SELECT 1 FROM doctors WHERE name = 'Dr. Nikhil Verma' AND specialty = 'General Medicine');

INSERT INTO doctors (name, specialty)
SELECT 'Dr. Meera Iyer', 'Pediatrics'
WHERE NOT EXISTS (SELECT 1 FROM doctors WHERE name = 'Dr. Meera Iyer' AND specialty = 'Pediatrics');

INSERT INTO patients (name, department, time, doctor_id)
SELECT 'Aarav Mehta', 'General', '10:30 AM', d.id
FROM doctors d
WHERE d.name = 'Dr. Nikhil Verma'
	AND NOT EXISTS (SELECT 1 FROM patients WHERE name = 'Aarav Mehta' AND department = 'General' AND time = '10:30 AM');

INSERT INTO patients (name, department, time, doctor_id)
SELECT 'Sara Khan', 'Cardiology', '11:00 AM', d.id
FROM doctors d
WHERE d.name = 'Dr. Anita Rao'
	AND NOT EXISTS (SELECT 1 FROM patients WHERE name = 'Sara Khan' AND department = 'Cardiology' AND time = '11:00 AM');

INSERT INTO patients (name, department, time, doctor_id)
SELECT 'Riya Sharma', 'Pediatrics', '12:15 PM', d.id
FROM doctors d
WHERE d.name = 'Dr. Meera Iyer'
	AND NOT EXISTS (SELECT 1 FROM patients WHERE name = 'Riya Sharma' AND department = 'Pediatrics' AND time = '12:15 PM');
