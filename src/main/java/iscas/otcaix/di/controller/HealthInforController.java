package iscas.otcaix.di.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import iscas.otcaix.di.entity.HealthBasicInfo;
import iscas.otcaix.di.entity.HealthDiagnose;
import iscas.otcaix.di.entity.HealthLaboratory;
import iscas.otcaix.di.entity.HealthMedicine;
import iscas.otcaix.di.entity.HealthOperation;
import iscas.otcaix.di.service.IHealthRetrievalService;

@RestController
@RequestMapping(value = "/healthinfo")
public class HealthInforController {

	@Autowired
	private IHealthRetrievalService healthService;

	private static final Logger logger = LogManager.getLogger(HealthInforController.class);

	public IHealthRetrievalService getHealthService() {
		return healthService;
	}

	public void setHealthService(IHealthRetrievalService healthService) {
		this.healthService = healthService;
	}

	@RequestMapping(value = "/{patientId}/basic-info")
	public ModelAndView getHealthBasicInforByPatientId(@PathVariable String patientId) {
		logger.entry();
		HealthBasicInfo result = healthService.queryHealthBasicInfoPatientId(patientId);
		if (result != null) {
			return new ModelAndView("sections/basic-info", "basicInfor", result);
		}
		logger.exit();
		return null;
	}

	@RequestMapping(value = "/{patientId}/laboratory")
	public ModelAndView getLaboratoryByPatientId(@PathVariable String patientId) {
		logger.entry();
		List<HealthLaboratory> result = healthService.queryHealthLaboratoryByPatientId(patientId);
		if (result != null) {
			return new ModelAndView("sections/laboratory", "laboratory", result);
		}
		logger.exit();
		return null;
	}

	@RequestMapping(value = "/{patientId}/diagnose")
	public ModelAndView getDiagnoseByPatientId(@PathVariable String patientId) {
		logger.entry();
		List<HealthDiagnose> result = healthService.queryHealthDiagnoseByPatientId(patientId);
		if (result != null) {
			return new ModelAndView("sections/diagnose", "diagnose", result);
		}
		logger.exit();
		return null;
	}

	@RequestMapping(value = "/{patientId}/medicine")
	public ModelAndView getMedicineByPatientId(@PathVariable String patientId) {
		logger.entry();
		List<HealthMedicine> result = healthService.queryHealthMedicineByPatientId(patientId);
		if (result != null) {
			return new ModelAndView("sections/medicine", "medicine", result);
		}
		logger.exit();
		return null;
	}

	@RequestMapping(value = "/{patientId}/operation")
	public ModelAndView getOperationByPatientId(@PathVariable String patientId) {
		logger.entry();
		List<HealthOperation> result = healthService.queryHealthOperationByPatientId(patientId);
		if (result != null) {
			return new ModelAndView("sections/operation", "operation", result);
		}
		logger.exit();
		return null;
	}

}
