package com.orbitallpayments.cards.dtos;

public class UpdateCardDTO {
    private Integer cardNumber;

    private String embossName;

    private String customerName;

    private Integer documentNumber;

    private String motherName;

    private String address;

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
