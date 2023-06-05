package com.example.examinationslab3.service;

import com.example.examinationslab3.dao.MemberDAO;
import com.example.examinationslab3.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberDAO memberDAO;

    private Member activeMember;

    @Autowired
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void addMember(Member member){
        memberDAO.save(member);
    }

    public Member getActiveMember() {
        return activeMember;
    }

    public void setActiveMember(Member activeMember) {
        this.activeMember = activeMember;
    }

    public Boolean verifyMember(String username, String password){

        Member member = memberDAO.findByUsername(username);

        if(member != null && member.getPassword().equals(password)) {
            setActiveMember(member);
            return true;
        }
        return false;
    }

    public Member findMemberById(long id){
        return memberDAO.findById(id);

        //return memberDAO.findById(id).orElseThrow(NullPointerException::new);
    }



    public void deleteMember(long id){
        memberDAO.delete(findMemberById(id));
    }

    public List<Member> findMembers(){
        return memberDAO.findAll();
    }

    public void updateMemberPassword(long id, String newPassword){
        Member member = findMemberById(id);
        member.setPassword(newPassword);
        memberDAO.save(member);
    }

    public Member save(Member member){
        member = memberDAO.save(member);
        return member;
    }



}
