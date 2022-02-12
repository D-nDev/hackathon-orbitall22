package com.orbitallpayments.cards.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.orbitallpayments.cards.domains.Card;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import com.orbitallpayments.cards.dtos.CreateCardDTO;
import com.orbitallpayments.cards.dtos.UpdateCardDTO;
import com.orbitallpayments.cards.services.CardService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<Card>> findAll() {
        try {
            List<Card> allcards = cardService.findAll();

            if (allcards.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No cards found");
            }

            return ResponseEntity.status(200).body(allcards);
        } catch (Error error) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid CreateCardDTO createCardDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<FieldError> errors = bindingResult.getFieldErrors();
                List<String> message = new ArrayList<>();
                for (FieldError e : errors) {
                    message.add(e.getDefaultMessage());
                }
                JSONObject jsonerrors = new JSONObject();
                jsonerrors.put("errors", message);
                return ResponseEntity.status(400).body(jsonerrors);
            }
            var cardDomain = new Card();
            BeanUtils.copyProperties(createCardDTO, cardDomain);
            Card saveCard = cardService.save(cardDomain);

            return ResponseEntity.status(201).body(saveCard);
        } catch (Error error) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateOne(@RequestBody @Valid UpdateCardDTO updateCardDTO, @PathVariable Long id,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<String> message = new ArrayList<>();
            for (FieldError e : errors) {
                message.add(e.getDefaultMessage());
            }
            JSONObject jsonerrors = new JSONObject();
            jsonerrors.put("errors", message);
            return ResponseEntity.status(400).body(jsonerrors);
        }

        Optional<Card> cardExists = cardService.findOne(id);

        if (!cardExists.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
        }

        var cardDomain = new Card();
        BeanUtils.copyProperties(updateCardDTO, cardDomain);
        Card updateCard = cardService.updateOne(cardDomain, id);

        return ResponseEntity.status(201).body(updateCard);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> deleteOne(@PathVariable Long id) {
        try {
            Optional<Card> cardExists = cardService.findOne(id);

            if (!cardExists.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
            }

            cardService.deleteOne(id);
            JSONObject success = new JSONObject();
            success.put("success", "Card deleted");
            return ResponseEntity.status(200).body(success);
        } catch (Error error) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Card>> findOne(@PathVariable(value = "id") Long id) {
        try {
            Optional<Card> oneCard = cardService.findOne(id);

            if (!oneCard.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
            }

            return ResponseEntity.status(200).body(oneCard);
        } catch (Error error) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    @RequestMapping(value = "/paginationAndSorting", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> findAllPaging(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            List<Card> allcards = cardService.findbyPage(pageable);

            if (allcards.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No cards found on this page");
            }
            return ResponseEntity.status(200).body(allcards);
        } catch (Error error) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}
