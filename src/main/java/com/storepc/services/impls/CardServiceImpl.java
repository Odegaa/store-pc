package com.storepc.services.impls;

import com.storepc.models.Card;
import com.storepc.repositories.CardRepository;
import com.storepc.services.CardService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return cardRepository.findById(cardId).orElse(null);
    }

    @Override
    public Result addCard(Card card) {
        boolean byCardNumber = cardRepository.existsByCardNumber(card.getCardNumber());
        if (byCardNumber) {
            return new Result("This card already exist!", false, HttpStatus.CONFLICT);
        }
        Card newCard = new Card();
        newCard.setCardNumber(card.getCardNumber());
        newCard.setCardUser(card.getCardUser());
        newCard.setCvc(card.getCvc());
        cardRepository.save(newCard);
        return new Result("Card saved!", true, HttpStatus.CREATED);
    }

    @Override
    public Result updateCard(Long cardId, Card card) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isPresent()) {
            Card newCard = optionalCard.get();
            newCard.setCardNumber(card.getCardNumber());
            newCard.setCardUser(card.getCardUser());
            newCard.setCvc(card.getCvc());
            cardRepository.save(newCard);
            return new Result("Card updated!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Card not found!", false, HttpStatus.NOT_FOUND);
    }

    @Override
    public Result deleteCard(Long cardId) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isPresent()) {
            cardRepository.deleteById(cardId);
            return new Result("Card deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Card not found!", false, HttpStatus.NOT_FOUND);
    }
}