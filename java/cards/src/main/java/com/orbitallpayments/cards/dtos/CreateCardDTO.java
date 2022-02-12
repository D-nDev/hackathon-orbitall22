package com.orbitallpayments.cards.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateCardDTO {

    @NotNull(message = "cardNumber should not be null")
    private Integer cardNumber;

    @NotEmpty(message = "embossName should not be null")
    private String embossName;

    @NotEmpty(message = "customerName should not be null")
    private String customerName;

    @NotNull(message = "documentNumber should not be null")
    private Integer documentNumber;

    @NotEmpty(message = "motherName should not be null")
    private String motherName;

    @NotEmpty(message = "address should not be null")
    private String address;

    @NotEmpty(message = "city should not be null")
    private String city;

    public Integer getCardNumber() {
        return cardNumber;
    }

    public String getEmbossName() {
        return embossName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setEmbossName(String embossName) {
        this.embossName = embossName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
