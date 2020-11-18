package com.konrad.brk.KFiszki.repository;

import com.konrad.brk.KFiszki.model.FlashcardPackage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlashcardsPackageRepository extends MongoRepository<FlashcardPackage,String> {
    Optional<FlashcardPackage> findFlashcardPackageById(String id);
}
