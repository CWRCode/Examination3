package com.example.examinationslab3.service;

import com.example.examinationslab3.dao.MemberDAO;
import com.example.examinationslab3.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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




/*    public String findByUsername(String username) {
        Optional<Member> member = memberDAO.findByUsername(username);
        if (member.isPresent()) {
            return member.get().getUsername();
        } else {
            return null;
        }
    }

    public String findByPassword(String password) {
        Optional<Member> member = memberDAO.findByPassword(password);
        if (member.isPresent()) {
            return member.get().getPassword();
        } else {
            return null;
        }
    }*/



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
