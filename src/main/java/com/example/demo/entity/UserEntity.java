package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @NonNull
    
    private String username;
    @NonNull
    private String password;

   
   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   @JsonManagedReference
private List<JournalEntity> journalEntries = new ArrayList<>();

}
