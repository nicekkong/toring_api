package com.cplanet.toring.controller;

import com.cplanet.toring.domain.Mentee;
import com.cplanet.toring.dto.ApiResponse;
import com.cplanet.toring.dto.CategoryDto;
import com.cplanet.toring.dto.ProfileDto;
import com.cplanet.toring.dto.request.ContentRequest;
import com.cplanet.toring.dto.request.SubscribeRequest;
import com.cplanet.toring.dto.response.ContentResponse;
import com.cplanet.toring.exception.UnauthorizedException;
import com.cplanet.toring.service.MemberService;
import com.cplanet.toring.service.MenteeService;
import com.cplanet.toring.service.MentoringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = {"/api/mentee"})
public class MenteeController extends BaseController {

    private MenteeService menteeService;

    public MenteeController(MenteeService menteeService) {
        this.menteeService = menteeService;
    }

    @GetMapping(value = "list")
    public List<Mentee> getMenteeList(@RequestParam(value = "keyword") String keyword) {
        String searchKeyword = StringUtils.isEmpty(keyword)? "" : "%"+keyword+"%";
        return menteeService.getMenteeList(searchKeyword);
    }

    @GetMapping(value = "detail")
    public Mentee getMenteeDetail(@RequestParam(value = "id") Long id) {
        return menteeService.getMenteeDetail(id);
    }

    @PostMapping(value = "save")
    public ApiResponse saveMentee(@RequestBody Mentee mentee) {
        mentee.setMemberid(this.getMemberInfo().getId());
        if(StringUtils.isEmpty(mentee.getMemberid())) {
            throw new UnauthorizedException("mentee/save > memberId is not recognized");
        }
        return menteeService.saveMentee(mentee);
    }

    @PostMapping(value = "update")
    public ApiResponse updateMentee(@RequestBody Mentee mentee) {
        return menteeService.updateMentee(mentee);
    }

    @PostMapping(value = "delete")
    public ApiResponse deleteMentee(@RequestParam(value = "id") Long id) {
        return menteeService.deleteMentee(id, this.getMemberInfo().getId());
    }
}
