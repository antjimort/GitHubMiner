package aiss.github.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service

public class IssueService {


    @Autowired
    RestTemplate restTemplate; // Para hacer las llamadas a la api

    String token = "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";
    String baseUrl = "https://api.github.com";
}
