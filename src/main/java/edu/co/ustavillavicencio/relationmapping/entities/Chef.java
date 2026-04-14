package edu.co.ustavillavicencio.relationmapping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chefs")
@Getter
@Setter
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserApp user;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private Integer age;

    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kitchen> kitchens = new ArrayList<>();
}
