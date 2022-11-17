package com.storepc.controllers;

import com.storepc.models.Card;
import com.storepc.services.CardService;
import com.storepc.templates.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class CardController {

    private final CardService service;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('create')")
    private ResponseEntity<Result> addCard(@Valid @RequestBody Card card) {
        Result result = service.addCard(card);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(service.getAllCards());
    }

    @GetMapping("/{cardId}")
    @PreAuthorize(value = "hasAuthority('read')")
    private ResponseEntity<Card> getCard(@PathVariable Long cardId) {
        return ResponseEntity.ok(service.getCard(cardId));
    }

    @PutMapping("/{cardId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> updateCard(@PathVariable Long cardId,
                                              @Valid @RequestBody Card card) {
        Result result = service.updateCard(cardId, card);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping("/{cardId}")
    @PreAuthorize(value = "hasAuthority('write')")
    private ResponseEntity<Result> deleteCard(@PathVariable Long cardId) {
        Result result = service.deleteCard(cardId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}