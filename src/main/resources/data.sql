INSERT INTO patients (name, department, time)
SELECT 'Aarav Mehta', 'General', '10:30 AM'
WHERE NOT EXISTS (SELECT 1 FROM patients WHERE name = 'Aarav Mehta' AND department = 'General' AND time = '10:30 AM');

INSERT INTO patients (name, department, time)
SELECT 'Sara Khan', 'Cardiology', '11:00 AM'
WHERE NOT EXISTS (SELECT 1 FROM patients WHERE name = 'Sara Khan' AND department = 'Cardiology' AND time = '11:00 AM');

INSERT INTO patients (name, department, time)
SELECT 'Riya Sharma', 'Pediatrics', '12:15 PM'
WHERE NOT EXISTS (SELECT 1 FROM patients WHERE name = 'Riya Sharma' AND department = 'Pediatrics' AND time = '12:15 PM');
