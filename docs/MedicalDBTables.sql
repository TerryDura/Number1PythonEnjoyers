CREATE DATABASE  IF NOT EXISTS `medicaldb` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `medicaldb`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: medicaldb
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointments`
--

Select * From Doctors;

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `Appointment_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int DEFAULT NULL,
  `doctor_id` int DEFAULT NULL,
  `appointment_date` date NOT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `appoint_status` enum('Scheduled','Completed','Cancelled','No Show') DEFAULT 'Scheduled',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Appointment_id`),
  KEY `patient_id_idx` (`patient_id`),
  KEY `(Doctor_id)_idx` (`doctor_id`),
  CONSTRAINT `FK_doctor_id` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`idDoctors`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (1,22,26,'2025-06-11','18:30:00','19:30:00','Immunization','Scheduled','2025-11-04 18:19:12','2025-11-04 18:19:12'),(2,23,25,'2024-07-05','10:15:00','10:40:00','PTSD','No Show','2025-11-04 18:22:52','2025-11-04 18:22:52'),(3,24,24,'2025-12-19','16:30:00','17:00:00','Physical','Completed','2025-11-04 18:25:52','2025-11-04 18:25:52'),(4,25,23,'2026-02-16','12:00:00','12:30:00','Migrane','Cancelled','2025-11-04 18:27:22','2025-11-04 18:27:22'),(5,26,22,'2020-07-12','14:30:00','15:00:00','Heartburn','Scheduled','2025-11-04 18:28:34','2025-11-04 18:28:34');
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departments` (
  `Dept_id` int NOT NULL AUTO_INCREMENT,
  `Dept_name` varchar(50) NOT NULL,
  `Dept_code` varchar(10) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Dept_id`),
  UNIQUE KEY `Dept_name_UNIQUE` (`Dept_name`),
  UNIQUE KEY `Dept_code_UNIQUE` (`Dept_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'Gastroenterology','534','xxx-xxx-xxxx','Gastro@gymail.com'),(2,'Neurology','333','xxx-xxx-xxxx','Neuro@gymail.com'),(3,'Family Medicine','762','xxx-xxx-xxxx','FamMed@gymail.com'),(4,'Psychiatry','157','xxx-xxx-xxxx','Psychiatry@gymail.com'),(5,'Pediatrics','239','xxx-xxx-xxxx','Pedia@gymail.com');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `idDoctors` int NOT NULL AUTO_INCREMENT,
  `Doc_Name` varchar(45) NOT NULL,
  `Doc_Gender` enum('Male','Female') DEFAULT NULL,
  `Specialization` varchar(50) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(75) DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  PRIMARY KEY (`idDoctors`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (22,'Dr. Payne','Male','Gastroenterology','xxx-xxx-xxxx','prof_payne@gymail.com',1),(23,'Dr. Helen','Female','Neurology','xxx-xxx-xxxx','MistHelen@gymail.com',2),(24,'Dr. Leo','Male','Family Medicine','xxx-xxx-xxxx','FrankLeo@gymail.com',3),(25,'Dr. Jane','Female','Psychiatry','xxx-xxx-xxxx','HelvetJane17@gymail.com',4),(26,'Dr. Lazer','Male','Pediatrics','xxx-xxx-xxxx','shoopdawhoop23@gymail.cm',5);
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_records`
--

DROP TABLE IF EXISTS `medical_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_records` (
  `MedicalRec_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `doctor_id` int DEFAULT NULL,
  `appointment_id` int DEFAULT NULL,
  `visit_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `Diagnosis` varchar(255) DEFAULT NULL,
  `Symptoms` text,
  `Treatment` text,
  `prescription` text,
  `Notes` text,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`MedicalRec_id`),
  KEY `(patient_id)_idx` (`patient_id`),
  KEY `(doctor_id)_idx` (`doctor_id`),
  KEY `(appointment_id)_idx` (`appointment_id`),
  CONSTRAINT `appointment_id_fk` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`Appointment_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `doctor_id_fk` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`idDoctors`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `patient_id_fk` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_records`
--

LOCK TABLES `medical_records` WRITE;
/*!40000 ALTER TABLE `medical_records` DISABLE KEYS */;
INSERT INTO `medical_records` VALUES (3,22,26,1,'2025-11-04 18:43:01','Weak Immune System','Cold and Cough','Flu Shot','N/A','N/a','2025-11-04 18:43:01','2025-11-04 18:43:01'),(4,23,25,2,'2025-11-04 18:46:52','PTSD','Memories of War','Psychotherapy','N/A','No Sudden Movements','2025-11-04 18:46:52','2025-11-04 18:46:52'),(5,24,24,3,'2025-11-04 18:49:48','Yearly Check-up','Pre-diabetic','Weight loss','Ozempic','Workout routine','2025-11-04 18:49:48','2025-11-04 18:49:48'),(6,25,23,4,'2025-11-04 18:53:10','Migrane','Smashing headache','Medication','Aspirin','Take Twice per day','2025-11-04 18:53:10','2025-11-04 18:53:10'),(7,26,22,5,'2025-11-04 18:55:54','Heartburn','Chest pain','Antacids','Tums','No more than thrice a day','2025-11-04 18:55:54','2025-11-04 18:55:54');
/*!40000 ALTER TABLE `medical_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `patient_id` int NOT NULL AUTO_INCREMENT,
  `Full_Name` varchar(70) NOT NULL,
  `Pat_Gender` enum('Male','Female') DEFAULT NULL,
  `DoB` date DEFAULT NULL,
  `Phone_Number` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `blood_type` enum('A+','A-','B+','B-','AB+','AB-','O+','O-') DEFAULT NULL,
  `Pat_Insurance` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`patient_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (22,'Amelie Griffith','Female','1990-01-15','xxx-xxx-xxxx','AmeGri51@gymail.com','A+','Blue Cross'),(23,'Jasper Smith','Male','2000-05-20','xxx-xxx-xxxx','BigJasp25@gymail.com','O-','Cigna'),(24,'Lola Small','Female','1980-06-26','xxx-xxx-xxxx','LolaByun97@gymail.com','B-','Humana'),(25,'Briggs Uvalde','Male','2015-09-12','xxx-xxx-xxxx','UvBr17@gymail.com','AB+','Kaiser Permanente'),(26,'Arabella riviera','Female','2008-11-22','xxx-xxx-xxxx','BellaMitsu89@gymail.com','O+','Imperial Insurance'),(27,'Alice Green','Female','1990-05-11','555-882-3401','alice.green@example.com','O+','United HealthCare');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-10 11:42:10
