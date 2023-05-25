package com.example.examinationslab3.dao;


import com.example.examinationslab3.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberDAO extends JpaRepository<Member, String> {


    Optional<Member> findByUsername(String username);

    Optional<Member> findByPassword(String password);
}
