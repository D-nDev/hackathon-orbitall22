package com.orbitallpayments.cards.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.orbitallpayments.cards.domains.Card;
import com.orbitallpayments.cards.repositories.CardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> findAll() {
        List<Card> cards = new ArrayList<>();
        cardRepository.findAll().forEach(cards::add);

        return cards;
    }

    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public Card updateOne(Card card, Long id) {
        Card currentCard = cardRepository.findById(id).get();

        currentCard.setCardNumber(card.getCardNumber() != null ? card.getCardNumber() : currentCard.getCardNumber());
        currentCard.setEmbossName(card.getEmbossName() != null ? card.getEmbossName() : currentCard.getEmbossName());
        currentCard.setCustomerName(
                card.getCustomerName() != null ? card.getCustomerName() : currentCard.getCustomerName());
        currentCard.setDocumentNumber(
                card.getDocumentNumber() != null ? card.getDocumentNumber() : currentCard.getDocumentNumber());
        currentCard.setMotherName(card.getMotherName() != null ? card.getMotherName() : currentCard.getMotherName());
        currentCard.setAddress(card.getAddress() != null ? card.getAddress() : currentCard.getAddress());
        currentCard.setCity(card.getCity() != null ? card.getCity() : currentCard.getCity());

        Card updateCard = cardRepository.save(currentCard);

        return updateCard;
    }

    public void deleteOne(Long id) {
        cardRepository.deleteById(id);
    }

    public Optional<Card> findOne(Long id) {
        return cardRepository.findById(id);
    }

    public List<Card> findbyPage(Pageable pageable) {
        List<Card> cards = new ArrayList<>();
        cardRepository.findAll(pageable).forEach(cards::add);

        return cards;
    }
}
