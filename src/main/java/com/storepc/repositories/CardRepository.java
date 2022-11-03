package com.storepc.repositories;

import com.storepc.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);

    Card findByCardUser(String cardUser);

}