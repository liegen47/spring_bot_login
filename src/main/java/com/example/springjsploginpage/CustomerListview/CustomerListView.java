package com.example.springjsploginpage.CustomerListview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Controller
public class CustomerListView {

    private static final String API_URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
    private static final String BEARER_TOKEN = "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM="; 

    @GetMapping("/customerList")
    public String getCustomerList(Model model) {

        // Set up headers with bearer token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);

        // Create the HTTP entity with headers
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        // Make the API call with bearer token
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<customer>> responseEntity = restTemplate.exchange(
            API_URL,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<List<customer>>() {}
        );

        // Check the response status code
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            List<customer> customerList = responseEntity.getBody();
            model.addAttribute("customerList", customerList);
            return "CustomerList"; // Return the view name for customer list
        } else {
            model.addAttribute("errorMsg", "Failed to fetch customer list from the API.");
            return "error"; // Return the view name for error page
        }
    }
}
