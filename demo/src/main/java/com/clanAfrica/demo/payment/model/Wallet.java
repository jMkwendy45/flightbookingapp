package com.clanAfrica.demo.payment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long walletId;
    private Long flightId;
    private String walletName;
    private String walletBank;
    private BigDecimal walletBalance;
    private String walletPin;
    private String paymentLink;
    private String walletBVN;
    private LocalDate dateCreated;

}
