package com.cplanet.toring.controller;

import com.cplanet.toring.dto.CategoryDto;
import com.cplanet.toring.dto.request.ContentRequest;
import com.cplanet.toring.dto.response.ContentResponse;
import com.cplanet.toring.exception.UnauthorizedException;
import com.cplanet.toring.service.MentoringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = {"/api/mentoring"})
public class MentoringController extends BaseController {

    private MentoringService mentoringService;

    public MentoringController(MentoringService mentoringService) {
        this.mentoringService = mentoringService;
    }

    @GetMapping(value = "content/info")
    public ContentResponse getContent(@RequestParam(value = "contentid") long contentid) {
        return mentoringService.getContentInfo(contentid);
    }

    @PostMapping(value = "content/save")
    public ContentResponse contentSave(@RequestBody ContentRequest content) {
        content.setMemberid(this.getMemberInfo().getId());
        if(StringUtils.isEmpty(content.getMemberid())) {
            throw new UnauthorizedException("content/save > memberId is not recognized");
        }
        return mentoringService.registerContent(content);
    }

    @GetMapping(value = "content/category")
    public ResponseEntity<CategoryDto> getCategory() {
        CategoryDto response = new CategoryDto();
        response.setCategory(mentoringService.getToringCategories());
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }
}
