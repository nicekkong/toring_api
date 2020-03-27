package com.cplanet.toring.controller;


import com.cplanet.toring.annotation.CurrentUser;
import com.cplanet.toring.component.JwtTokenProvider;
import com.cplanet.toring.domain.Member;
import com.cplanet.toring.domain.security.MemberPrincipal;
import com.cplanet.toring.dto.ApiResponse;
import com.cplanet.toring.dto.JwtAuthenticationResponse;
import com.cplanet.toring.dto.LoginRequestDto;
import com.cplanet.toring.dto.SignUpRequest;
import com.cplanet.toring.repository.MemberRepository;
import com.cplanet.toring.service.AuthenticationService;
import com.cplanet.toring.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

//@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class AuthenticationController {

    final private AuthenticationManager authenticationManager;
    final private MemberRepository memberRepository;
    final private MemberService memberService;
    final private PasswordEncoder passwordEncoder;
    final private JwtTokenProvider jwtTokenProvider;
    final private ModelMapper modelMapper;

    final private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    MemberRepository memberRepository,
                                    MemberService memberService,
                                    PasswordEncoder passwordEncoder,
                                    JwtTokenProvider jwtTokenProvider,
                                    ModelMapper modelMapper,
                                    AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {

        String jwt = authenticationService.makeLoginToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ApiResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        Member member = modelMapper.map(signUpRequest, Member.class);
        ApiResponse response = authenticationService.signUpMember(member);
//        return new ResponseEntity(response, response.getSuccess()?HttpStatus.OK : HttpStatus.BAD_REQUEST);
        return response;
    }

    @GetMapping(value = "/auth/logout")
    public ApiResponse logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ApiResponse(true, "logout success");
    }

    @GetMapping(value = "/member")
    public Member sampleMember(@CurrentUser MemberPrincipal member) {
        return member.getMember();
    }


}
