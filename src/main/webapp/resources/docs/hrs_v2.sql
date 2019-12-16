/*
Navicat MySQL Data Transfer

Source Server         : Local_Mysql
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : hrs_v2

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-03-13 21:16:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for archive_base_info
-- ----------------------------
DROP TABLE IF EXISTS `archive_base_info`;
CREATE TABLE `archive_base_info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ARVHIVE_DATE` date DEFAULT NULL,
  `ARCHIVE_ID` varchar(255) NOT NULL,
  `PSN_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of archive_base_info
-- ----------------------------

-- ----------------------------
-- Table structure for diagnose
-- ----------------------------
DROP TABLE IF EXISTS `diagnose`;
CREATE TABLE `diagnose` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IPID` varchar(255) DEFAULT NULL,
  `JBDM` varchar(255) DEFAULT NULL,
  `ZD` varchar(255) DEFAULT NULL,
  `ZDSJ` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of diagnose
-- ----------------------------

-- ----------------------------
-- Table structure for health_abstract
-- ----------------------------
DROP TABLE IF EXISTS `health_abstract`;
CREATE TABLE `health_abstract` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(255) DEFAULT NULL,
  `PSN_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of health_abstract
-- ----------------------------

-- ----------------------------
-- Table structure for health_evn_index
-- ----------------------------
DROP TABLE IF EXISTS `health_evn_index`;
CREATE TABLE `health_evn_index` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ARCHIVE_ID` varchar(255) DEFAULT NULL,
  `ENCOUNTER_NAME` varchar(255) DEFAULT NULL,
  `ENCOUNTER_TYPE` varchar(255) DEFAULT NULL,
  `HEALTH_EVN_ID` bigint(20) NOT NULL,
  `PSN_ID` bigint(20) DEFAULT NULL,
  `VISIT_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of health_evn_index
-- ----------------------------

-- ----------------------------
-- Table structure for inp_file_master
-- ----------------------------
DROP TABLE IF EXISTS `inp_file_master`;
CREATE TABLE `inp_file_master` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTION_CODE` varchar(255) DEFAULT NULL,
  `ADMISSION_CONDITION_CODE` varchar(255) DEFAULT NULL,
  `ADMISSION_CONDITION_DESCRIPTION` varchar(255) DEFAULT NULL,
  `ADMISSION_DATE_TIME` datetime DEFAULT NULL,
  `ADMISSION_DESRIPTION` varchar(255) DEFAULT NULL,
  `ADMISSION_WAY_CODE` varchar(255) DEFAULT NULL,
  `ADMISSION_WAY_DESCRIPTION` varchar(255) DEFAULT NULL,
  `ALLERGY_MEDICATIONS_FLAG` varchar(255) DEFAULT NULL,
  `AUTOTRANSFUSION_FLAG` varchar(255) DEFAULT NULL,
  `AUTOTRANSFUSION` bigint(20) DEFAULT NULL,
  `BLOOD_TYPE` varchar(255) DEFAULT NULL,
  `BLOOD_TYPE_RH` varchar(255) DEFAULT NULL,
  `CAUSES_OF_DEATH_CODE` varchar(255) DEFAULT NULL,
  `CAUSES_OF_DEATH_NAME` varchar(255) DEFAULT NULL,
  `CCU_DAYS` bigint(20) DEFAULT NULL,
  `DEATH_DATE_TIME` datetime DEFAULT NULL,
  `DEPT_ADMISSION_TO_NAME` varchar(255) DEFAULT NULL,
  `DIAGNOSE_DATE` datetime DEFAULT NULL,
  `DIAGNOSE_DAYS` bigint(20) DEFAULT NULL,
  `DISCHARGE_DATE_TIME` datetime DEFAULT NULL,
  `HEALTH_EVN_ID` bigint(20) DEFAULT NULL,
  `HOSPITAL_DAYS` bigint(20) DEFAULT NULL,
  `HOSPITAL_INFECTION_FLAG` varchar(255) DEFAULT NULL,
  `ICU_DAYS` bigint(20) DEFAULT NULL,
  `INJURY_INCIDENT_CODE` varchar(255) DEFAULT NULL,
  `INJURY_INCIDENT_DESCRIPTION` varchar(255) DEFAULT NULL,
  `IS_AUTOPSY` varchar(255) DEFAULT NULL,
  `OCCURRENCE_BEDSORES_VIEWS` bigint(20) DEFAULT NULL,
  `PHYSICAL` varchar(255) DEFAULT NULL,
  `PRIMARY_CARE_DAYS` bigint(20) DEFAULT NULL,
  `RESCUE_NUMBER` bigint(20) DEFAULT NULL,
  `RESCUE_SUCCESS_NUMBER` bigint(20) DEFAULT NULL,
  `SECONDARY_CARE_DAYS` bigint(20) DEFAULT NULL,
  `SERIOUSLY_ILL_DAYS` bigint(20) DEFAULT NULL,
  `SPECIAL_CARE_DAYS` bigint(20) DEFAULT NULL,
  `SWITCH_DEPT` varchar(255) DEFAULT NULL,
  `TIMES_OF_HOSPITALIZATION` bigint(20) DEFAULT NULL,
  `TRANSFUSION_FLAG` varchar(255) DEFAULT NULL,
  `TRANSFUSION_REACTIONS` varchar(255) DEFAULT NULL,
  `TRANSFUSION_REACTIONS_TIMES` bigint(20) DEFAULT NULL,
  `TRANSFUSION_TIMES` bigint(20) DEFAULT NULL,
  `WAS_DYING_DAYS` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of inp_file_master
-- ----------------------------

-- ----------------------------
-- Table structure for inp_operation_items
-- ----------------------------
DROP TABLE IF EXISTS `inp_operation_items`;
CREATE TABLE `inp_operation_items` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ANESTHESIA_DOCTOR` varchar(255) DEFAULT NULL,
  `ANESTHESIA_METHOD` varchar(255) DEFAULT NULL,
  `ANESTHSIA_METHOD_CODE` varchar(255) DEFAULT NULL,
  `FIRST_ASSISTANT` varchar(255) DEFAULT NULL,
  `FOURTH_ASSISTANT` varchar(255) DEFAULT NULL,
  `HEAL_GRADE` varchar(255) DEFAULT NULL,
  `HEALTH_EVN_ID` bigint(20) DEFAULT NULL,
  `OPERATING_DEPT` varchar(255) DEFAULT NULL,
  `OPERATING_DEPT_NAME` varchar(255) DEFAULT NULL,
  `OPERATION` varchar(255) DEFAULT NULL,
  `OPERATION_CLASS` varchar(255) DEFAULT NULL,
  `OPERATION_CODE` varchar(255) DEFAULT NULL,
  `OPERATION_NAME` varchar(255) DEFAULT NULL,
  `OPERATION_NO` varchar(255) DEFAULT NULL,
  `OPERATION_SCALE` varchar(255) DEFAULT NULL,
  `SCHEDULE_DATE_TIME` datetime DEFAULT NULL,
  `SECOND_ASSISTANT` varchar(255) DEFAULT NULL,
  `SURGEON` varchar(255) DEFAULT NULL,
  `TARGET_SITE_CODE` varchar(255) DEFAULT NULL,
  `THIRD_ASSISTANT` varchar(255) DEFAULT NULL,
  `WOUND_GRADE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of inp_operation_items
-- ----------------------------

-- ----------------------------
-- Table structure for laboratory
-- ----------------------------
DROP TABLE IF EXISTS `laboratory`;
CREATE TABLE `laboratory` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CKFW` varchar(255) DEFAULT NULL,
  `IPID` varchar(255) DEFAULT NULL,
  `JG` varchar(255) DEFAULT NULL,
  `JGBJ` varchar(255) DEFAULT NULL,
  `JGDW` varchar(255) DEFAULT NULL,
  `JYSJ` varchar(255) DEFAULT NULL,
  `XMBM` varchar(255) DEFAULT NULL,
  `XMMC` varchar(255) DEFAULT NULL,
  `YBMC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of laboratory
-- ----------------------------

-- ----------------------------
-- Table structure for psn_master_info
-- ----------------------------
DROP TABLE IF EXISTS `psn_master_info`;
CREATE TABLE `psn_master_info` (
  `PSN_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ARCHIVE_DATE` datetime DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `COUNTRY_CODE` varchar(255) DEFAULT NULL,
  `EDUCATION_LEVEL_CODE` varchar(255) DEFAULT NULL,
  `FAMILY_NAME` varchar(255) DEFAULT NULL,
  `GIVEN_NAME` varchar(255) DEFAULT NULL,
  `MARITAL_STATUS_CODE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `NAME_PHONETIC` varchar(255) DEFAULT NULL,
  `NATION_CODE` varchar(255) DEFAULT NULL,
  `SEX_CODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PSN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of psn_master_info
-- ----------------------------

-- ----------------------------
-- Table structure for similarity
-- ----------------------------
DROP TABLE IF EXISTS `similarity`;
CREATE TABLE `similarity` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PSN_ID_X` bigint(20) DEFAULT NULL,
  `PSN_ID_Y` bigint(20) DEFAULT NULL,
  `SIMILARITY` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of similarity
-- ----------------------------
