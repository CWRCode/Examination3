package com.example.examinationslab3.controller;

import com.example.examinationslab3.model.Member;
import com.example.examinationslab3.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberAPI {

    private final MemberService memberService;

    @Autowired
    public MemberAPI(MemberService memberService) {
        this.memberService = memberService;
    }



    @PostMapping("/login")
    public String authorizeMember(@RequestParam String userName, @RequestParam String userPassword, Model m){

        Boolean check = memberService.verifyMember(userName, userPassword);

        if(check) {
            return "adminlogin";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String authorizeMember(Model m){

        m.addAttribute("userName", null);
        m.addAttribute("userPassword", null);

        return "login";
    }


    @GetMapping("/new")
    String addNewForm(Model m){
        m.addAttribute("member", new Member());
        return "newmember";
    }

    @PostMapping("/new")
    String addNewMember(@Valid Member member, BindingResult br, Model m ) {
        m.addAttribute("member", new Member());

        if(br.hasErrors()){
            m.addAttribute("errormessage", "not correctly done");
            return "newmember";
        } else {
            memberService.addMember(member);
        }
        return "login";
    }



}

