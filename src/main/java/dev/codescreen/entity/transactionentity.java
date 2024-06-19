package dev.codescreen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "transactiondata")
@Getter
@Setter
public class transactionentity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "userId")
    private String userId;


    @Column(name = "messageId")
    private String messageId;


    @Column(name = "amount")
    private float amount;

    @Column(name = "debitcredit")
    private String debitcredit;

    @Column(name = "datetimevalue")
    private String datetimevalue;

    @Column(name = "status")
    private String status;



}
