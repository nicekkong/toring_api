package com.cplanet.toring.controller;

import com.cplanet.toring.dto.ApiResponse;
import com.cplanet.toring.dto.ProfileDto;
import com.cplanet.toring.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = {"/api/my"})
public class MyController extends BaseController {
    private MemberService memberService;

    public MyController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "profile")
    public ProfileDto getMyProfile() {
        ProfileDto response = null;
        long memberId = this.getMemberInfo().getId();
        response = memberService.getMemberProfile(memberId, memberId);
        return response;
    }

    @PostMapping(value ="profile/save")
    public ApiResponse saveMyProfile(@RequestBody ProfileDto profile) {
        profile.setMemberid(this.getMemberInfo().getId());
        boolean result = memberService.saveProfile(profile);
        String message = "success";
        if(!result) {
            message = "fail";
        }
        return new ApiResponse(result, message);
    }
}
