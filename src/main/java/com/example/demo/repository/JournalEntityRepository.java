package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.JournalEntity;

public interface JournalEntityRepository extends JpaRepository<JournalEntity,String>{

}
