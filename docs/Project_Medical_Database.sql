use medicaldb;

INSERT INTO Doctors (Doc_Name, Doc_Gender, specialization)
VALUES ('Salim Alkupar','Male','Cardiology');

INSERT INTO Appointments (patient_id, doctor_id, appointment_date, start_time, end_time,reason, appoint_status, created_at, updated_at) 
VALUES (22, 26, '2025-06-11', '18:30:00', '19:30:00', 'Immunization', 'Scheduled', DEFAULT, DEFAULT);

INSERT INTO Appointments (patient_id, doctor_id, appointment_date, start_time, end_time,reason, appoint_status, created_at, updated_at) 
VALUES (23, 25, '2024-07-05', '10:15:00', '10:40:00', 'PTSD', 'No Show', DEFAULT, DEFAULT);

INSERT INTO Appointments (patient_id, doctor_id, appointment_date, start_time, end_time,reason, appoint_status, created_at, updated_at) 
VALUES (24, 24, '2025-12-19', '16:30:00', '17:00:00', 'Physical', 'Completed', DEFAULT, DEFAULT);

INSERT INTO Appointments (patient_id, doctor_id, appointment_date, start_time, end_time,reason, appoint_status, created_at, updated_at) 
VALUES (25, 23, '2026-02-16', '12:00:00', '12:30:00', 'Migrane', 'Cancelled', DEFAULT, DEFAULT);

INSERT INTO Appointments (patient_id, doctor_id, appointment_date, start_time, end_time,reason, appoint_status, created_at, updated_at) 
VALUES (26, 22, '2020-07-12', '14:30:00', '15:00:00', 'Heartburn', 'Scheduled', DEFAULT, DEFAULT);

Select * FROM Appointments;



Insert into Medical_Records (patient_id, doctor_id, appointment_id, visit_date, Diagnosis, Symptoms,Treatment,prescription,Notes,created_at,updated_at)
VALUES (22, 26, 1 , DEFAULT, 'Weak Immune System', 'Cold and Cough', 'Flu Shot', 'N/A', 'N/a', Default, Default);

Insert into Medical_Records (patient_id, doctor_id, appointment_id, visit_date, Diagnosis, Symptoms,Treatment,prescription,Notes,created_at,updated_at)
VALUES (23, 25, 2 , DEFAULT, 'PTSD', 'Memories of War', 'Psychotherapy', 'N/A', 'No Sudden Movements', Default, Default);

Insert into Medical_Records (patient_id, doctor_id, appointment_id, visit_date, Diagnosis, Symptoms,Treatment,prescription,Notes,created_at,updated_at)
VALUES (24, 24, 3 , DEFAULT, 'Yearly Check-up', 'Pre-diabetic', 'Weight loss', 'Ozempic', 'Workout routine', Default, Default);

Insert into Medical_Records (patient_id, doctor_id, appointment_id, visit_date, Diagnosis, Symptoms,Treatment,prescription,Notes,created_at,updated_at)
VALUES (25, 23, 4 , DEFAULT, 'Migrane', 'Smashing headache', 'Medication', 'Aspirin', 'Take Twice per day', Default, Default);

Insert into Medical_Records (patient_id, doctor_id, appointment_id, visit_date, Diagnosis, Symptoms,Treatment,prescription,Notes,created_at,updated_at)
VALUES (26, 22, 5 , DEFAULT, 'Heartburn', 'Chest pain', 'Antacids', 'Tums', 'No more than thrice a day', Default, Default);

Select * From Medical_Records;

Select * From Patients;


DELETE FROM Doctors WHERE idDoctors = 21;


