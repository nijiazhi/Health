package iscas.otcaix.di.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import iscas.otcaix.di.pojo.TaPatient;
import iscas.otcaix.di.service.impl.MedicalPortraitService;

@Controller
public class MedicalPortraitController {
    @Autowired
    private MedicalPortraitService mtLableService;

    public MedicalPortraitService getMtLableService() {
        return mtLableService;
    }

    public void setMtLableService(MedicalPortraitService mtLableService) {
        this.mtLableService = mtLableService;
    }


    @ResponseBody
    @RequestMapping(value = "/lables/portrait", method = RequestMethod.GET)
    public TaPatient getMtLablePortraitByPatient(@RequestParam(value = "zyh", required = true) String zyh) {
        return mtLableService.getMtLablePortraitByPatient(zyh);
    }


    @RequestMapping(value = "/medical_portrait_zd")
    public String medical_portrait_zd(HttpServletResponse response) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        return "medical_portrait_zd";
    }

    @RequestMapping(value = "/medical_portrait_yp")
    public String medical_portrait_yp(HttpServletResponse response) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        return "medical_portrait_yp";
    }

    @RequestMapping(value = "/medical_portrait_jy")
    public String medical_portrait_jy(HttpServletResponse response) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        return "medical_portrait_jy";
    }

    @RequestMapping(value = "/medical_portrait_hy")
    public String medical_portrait_hy(HttpServletResponse response) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        return "medical_portrait_hy";
    }

    @RequestMapping(value = "/medical_portrait_ss")
    public String medical_portrait_ss(HttpServletResponse response) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        return "medical_portrait_ss";
    }

    @RequestMapping(value = "/medical_portrait_zyh")
    public String medical_portrait_zyh(HttpServletResponse response) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        return "medical_portrait_zyh";
    }

    /**
     * 诊断
     *
     * @param taglibId
     */
    @ResponseBody
    @RequestMapping(value = "/lables/analyse/zd", method = RequestMethod.GET)
    public Map<String, List<String>> getTaLableInfoByZD() {
        return mtLableService.getTaLableInfoByZD();
    }

    @ResponseBody
    @RequestMapping(value = "/lables/analyse/zdfx", method = RequestMethod.GET)
    public Map<String, List<String>> getTaLableInfoByZDFX() {
        return mtLableService.getTaLableInfoByZD_FX();
    }

    /**
     * 手术
     *
     * @param taglibId
     */
    @ResponseBody
    @RequestMapping(value = "/lables/analyse/ss", method = RequestMethod.GET)
    public Map<String, List<String>> getTaLableInfoBySS() {
        return mtLableService.getTaLableInfoBySS();
    }

    @ResponseBody
    @RequestMapping(value = "/lables/analyse/sshx", method = RequestMethod.GET)
    public Map<String, List<String>> getTaLableInfoBySS_HX() {
        return mtLableService.getTaLableInfoBySS_HX();
    }

    /**
     * 用药
     *
     * @param taglibId
     */
    @ResponseBody
    @RequestMapping(value = "/lables/analyse/yp", method = RequestMethod.GET)
    public Map<String, List<String>> getTaLableInfoByYP() {
        return mtLableService.getTaLableInfoByYP();
    }

    @ResponseBody
    @RequestMapping(value = "/lables/analyse/ypfl", method = RequestMethod.GET)
    public Map<String, List<String>> getTaLableInfoByYP_FL(@RequestParam(value = "name", required = true) String name) {
        return mtLableService.getTaLableInfoByYP_FL(name);
    }

    @ResponseBody
    @RequestMapping(value = "/lables/analyse/yphx", method = RequestMethod.GET)
    public Map<String, List<String>> getTaLableInfoByYP_HX() {
        return mtLableService.getTaLableInfoByYP_HX();
    }

    /**
     * 检查
     *
     * @param taglibId
     */
    @ResponseBody
    @RequestMapping(value = "/lables/analyse/jy", method = RequestMethod.GET)
    public Map<String, List<String>> getTaLableInfoByJY() {
        return mtLableService.getTaLableInfoByJY();
    }

    @ResponseBody
    @RequestMapping(value = "/lables/analyse/jyhx", method = RequestMethod.GET)
    public Map<String, List<String>> getTaLableInfoByJY_HX() {
        return mtLableService.getTaLableInfoByJY_HX();
    }

    @ResponseBody
    @RequestMapping(value = "/lables/portrait/zd", method = RequestMethod.GET)
    public List<String> getPortraitZDInfo() {
        return mtLableService.getPortraitZDInfo();
    }

    @ResponseBody
    @RequestMapping(value = "/lables/portrait/zyh", method = RequestMethod.GET)
    public List<String> getPortraitZyhList(@RequestParam(value = "zd_name", required = true) String zdName) {
        return mtLableService.getPortraitZyhList(zdName);
    }

    @RequestMapping(value = "/goto_url")
    @ResponseBody
    public void goto_url(@RequestParam(value = "url", required = false) String url, HttpServletRequest request, HttpServletResponse response) {


        try {
            System.out.println(url);
            //	response.setHeader("X-Frame-Options", "ALLOW-FROM");
            response.sendRedirect(url);
            // return;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //return new ModelAndView("error");
    }

}