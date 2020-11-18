package com.konrad.brk.KFiszki.repository;

import com.konrad.brk.KFiszki.model.Flashcard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlashcardRepository extends MongoRepository<Flashcard, String> {
    Optional<Flashcard> findFlashcardById(String id);
}
