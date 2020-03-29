package com.cplanet.toring.controller;

import com.cplanet.toring.domain.Member;
import com.cplanet.toring.domain.security.MemberPrincipal;
import com.cplanet.toring.wrapper.RequestWrapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController  {

    public BaseController() {
    }

    public String getAccessToken() {
        RequestWrapper request = new RequestWrapper(getServletRequest());
        return request.getAccessToken();
    }

    public Member getMemberInfo() {
        MemberPrincipal memberPrincipal = (MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return memberPrincipal.getMember();
    }

    public Long getMemberId() {
        Member member = getMemberInfo();
        return member.getId();
    }

    protected HttpServletRequest getServletRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra == null || sra.getRequest() == null) {
            return null;
        }
        return sra.getRequest();
    }
}
