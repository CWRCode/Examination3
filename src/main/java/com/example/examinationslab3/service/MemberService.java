package com.example.examinationslab3.service;

import com.example.examinationslab3.dao.MemberDAO;
import com.example.examinationslab3.model.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class MemberService {

    private final MemberDAO memberDAO;


    @Autowired
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void addMember(Member member){
        memberDAO.save(member);
    }

    public Boolean verifyMember(String username, String password){

        Member member = findMemberById(username);

        if(member != null && member.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public Member findMemberById(String id){

        Optional<Member> member = memberDAO.findById(id);
        return member.orElse(null);

        //return memberDAO.findById(id).orElseThrow(NullPointerException::new);
    }



    public void deleteMember(String id){
        memberDAO.delete(findMemberById(id));
    }

    public List<Member> findMembers(){
        return memberDAO.findAll();
    }

    public void updateMemberPassword(String id, String newPassword){
        Member member = findMemberById(id);
        member.setPassword(newPassword);
        memberDAO.save(member);
    }



}
