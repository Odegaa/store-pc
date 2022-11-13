package com.storepc.controllers;

import com.storepc.models.Card;
import com.storepc.services.CardService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class CardController {

    private final CardService service;

    @PostMapping
    private ResponseEntity<Result> addCard(@Valid @RequestBody Card card) {
        Result result = service.addCard(card);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    private ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(service.getAllCards());
    }

    @GetMapping("/{cardId}")
    private ResponseEntity<Card> getCard(@PathVariable Long cardId) {
        return ResponseEntity.ok(service.getCard(cardId));
    }

    @PutMapping("/{cardId}")
    private ResponseEntity<Result> updateCard(@PathVariable Long cardId,
                                              @Valid @RequestBody Card card) {
        Result result = service.updateCard(cardId, card);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{cardId}")
    private ResponseEntity<Result> deleteCard(@PathVariable Long cardId) {
        Result result = service.deleteCard(cardId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}