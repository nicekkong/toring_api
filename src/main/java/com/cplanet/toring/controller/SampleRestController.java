package com.cplanet.toring.controller;

import com.cplanet.toring.domain.TestData;
import com.cplanet.toring.dto.TestDataResponseDto;
import com.cplanet.toring.repository.TestDataRepository;
import com.cplanet.toring.service.TestDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class SampleRestController {


    final private TestDataService testDataService;
    final private TestDataRepository testDataRepository;

    public SampleRestController(TestDataService testDataService, TestDataRepository testDataRepository) {
        this.testDataService = testDataService;
        this.testDataRepository = testDataRepository;
    }

    @GetMapping(value = "/time")
    public String timme() {
        return "현재 시간 : " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @GetMapping(value = "/data")
    public List<TestDataResponseDto> data() {
        List<TestDataResponseDto> result = testDataService.findAll();
        return result;
    }


    @GetMapping(value = "/data2")
    public List<TestData> data2() {
        List<TestData> result = testDataRepository.findAll();
        return result;
    }

}
