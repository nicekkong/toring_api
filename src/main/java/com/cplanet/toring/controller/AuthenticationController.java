package com.cplanet.toring.controller;


import com.cplanet.toring.component.JwtTokenProvider;
import com.cplanet.toring.domain.AuthenticationBean;
import com.cplanet.toring.domain.Member;
import com.cplanet.toring.dto.ApiResponse;
import com.cplanet.toring.dto.JwtAuthenticationResponse;
import com.cplanet.toring.dto.LoginRequestDto;
import com.cplanet.toring.dto.SignUpRequest;
import com.cplanet.toring.repository.MemberRepository;
import com.cplanet.toring.service.AuthenticationService;
import com.cplanet.toring.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
                                    AuthenticationService authenticationServic) {
        this.authenticationManager = authenticationManager;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
        this.authenticationService = authenticationServic;
    }

    @GetMapping(path = "/basicauth")
    public AuthenticationBean auth() {

        return new AuthenticationBean("Login");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {

        String jwt = authenticationService.makeLoginToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        Member member = modelMapper.map(signUpRequest, Member.class);
        ApiResponse response = authenticationService.signUpMember(member);

        return new ResponseEntity(response, response.getSuccess()?HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}