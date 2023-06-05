package com.example.examinationslab3.dao;


import com.example.examinationslab3.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberDAO extends JpaRepository<Member, String> {

    Member findById (long id);
    Member findByUsername (String name);


    //Optional<Member> findByUsername(String username);

    Optional<Member> findByPassword(String password);
}
