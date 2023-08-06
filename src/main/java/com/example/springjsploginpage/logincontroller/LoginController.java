package com.example.springjsploginpage.logincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Controller
public class LoginController {

    private static final String API_URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String customerListPage(ModelMap model, @RequestParam String LoginID, @RequestParam String password) {

        String requestBody = "{\"login_id\":\"" + LoginID + "\",\"password\":\"" + password + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
    
            model.put("LoginID", LoginID);
            return "CustomerList";
        } else {
            
            model.put("errorMsg", "Please provide correct login credentials!");
            return "login";
        }
    }
}
