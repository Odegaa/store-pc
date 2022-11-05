package com.storepc.services.impls;

import com.storepc.models.Card;
import com.storepc.repositories.CardRepository;
import com.storepc.services.CardService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCard(Long cardId) {
        return null;
    }

    @Override
    public Result addCard(Card card) {
        return null;
    }

    @Override
    public Result updateCard(Long cardId, Card card) {
        return null;
    }

    @Override
    public Result deleteCard(Long cardId) {
        return null;
    }
}