package com.java.rs.model;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="email")
    private String email;
    
    @Column(name="password")
    private String password;

    @OneToOne(mappedBy = "realEstate", cascade = CascadeType.ALL)
    private Property property;

    @OneToMany(mappedBy = "realEstate", cascade = CascadeType.ALL)
    private List<Agent> agents;

    

    public RealEstate(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
