package com.orbitallpayments.cards.domains;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cardNumber;

    private String embossName;

    private String customerName;

    private Integer documentNumber;

    private String motherName;

    private String address;

    private String city;
}
