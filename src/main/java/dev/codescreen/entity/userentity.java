package dev.codescreen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userdata")
@Getter
@Setter
public class userentity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    @Column(name = "userId")
    private String userId;

   
    @Column(name = "balance")
    private float balance;
    
}
