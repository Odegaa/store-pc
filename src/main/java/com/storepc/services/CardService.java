package com.storepc.services;

import com.storepc.models.Card;
import com.storepc.templates.Result;

import java.util.List;

public interface CardService {

    List<Card> getAllCards();

    Card getCard(Long cardId);

    Result addCard(Card card);

    Result updateCard(Long cardId, Card card);

    Result deleteCard(Long cardId);

}
