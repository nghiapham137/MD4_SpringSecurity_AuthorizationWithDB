package com.example.demo1.model;

import org.springframework.security.core.GrantedAuthority;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AppRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    @Override
    public String getAuthority() {
        return this.name;
    }
}
