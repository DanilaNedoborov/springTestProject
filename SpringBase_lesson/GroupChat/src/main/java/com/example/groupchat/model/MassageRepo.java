package com.example.groupchat.model;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MassageRepo extends JpaRepository<Massage, Integer> {
    //List<Massage> findAll(Sort dateTime);
}
