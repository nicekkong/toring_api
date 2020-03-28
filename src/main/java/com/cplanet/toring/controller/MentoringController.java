package com.cplanet.toring.controller;

import com.cplanet.toring.domain.Content;
import com.cplanet.toring.dto.ApiResponse;
import com.cplanet.toring.dto.CategoryDto;
import com.cplanet.toring.dto.ContentRequest;
import com.cplanet.toring.service.MemberService;
import com.cplanet.toring.service.MentoringService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = {"/mentoring"})
public class MentoringController {

    private MentoringService mentoringService;
    private MemberService memberService;
    private ModelMapper modelMapper;

    public MentoringController(MentoringService mentoringService,
                               MemberService memberService,
                               ModelMapper modelMapper) {
        this.mentoringService = mentoringService;
        this.memberService = memberService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "content/save")
    public ApiResponse contentSave(@Valid @RequestBody ContentRequest contentRequest) {
        Content content = modelMapper.map(contentRequest, Content.class);
        ApiResponse response = mentoringService.registerContent(content);
        return response;
    }

    @GetMapping(value = "content/category")
    public ResponseEntity<CategoryDto> getCategory() {
        CategoryDto response = new CategoryDto();
        response.setCategory(mentoringService.getToringCategories());
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

}
