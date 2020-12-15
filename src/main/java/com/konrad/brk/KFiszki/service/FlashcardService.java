package com.konrad.brk.KFiszki.service;

import com.konrad.brk.KFiszki.dto.FlashcardDto;
import com.konrad.brk.KFiszki.dto.RegistrationDto;
import com.konrad.brk.KFiszki.exception.FlashcardAlreadyExistsException;
import com.konrad.brk.KFiszki.exception.FlashcardPackageNotFoundException;
import com.konrad.brk.KFiszki.exception.UserAlreadyExistsException;
import com.konrad.brk.KFiszki.model.Flashcard;
import com.konrad.brk.KFiszki.model.FlashcardPackage;
import com.konrad.brk.KFiszki.model.User;
import com.konrad.brk.KFiszki.repository.FlashcardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;

    public FlashcardService(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    public Flashcard addNewFlashcard(FlashcardDto flashcardDto) {
        if (getOptFlashcardById(flashcardDto.getId()).isPresent()) {
            throw new FlashcardAlreadyExistsException("Flashcard already exists!");
        }
        Flashcard flashcard = Flashcard.apply(flashcardDto);
        return flashcardRepository.save(flashcard);
    }

    private Optional<Flashcard> getOptFlashcardById(String id) {
        return flashcardRepository.findFlashcardById(id);
    }

    public Flashcard getFlashcardById(String id) {
        return flashcardRepository.findById(id).stream()
                .findFirst().orElseThrow(() -> new FlashcardPackageNotFoundException("There is no flashcard with id: " + id));
    }

    public void deleteFlashcardById(String flashcardId) {
        Flashcard flashcard = getFlashcardById(flashcardId);
        flashcardRepository.delete(flashcard);
    }

    public List<Flashcard> getAllFlashcardsFromFlashcardPackage(FlashcardPackage flashcardPackage) {
        List<Flashcard> flashcards = new ArrayList<>();
        List<String> flashcardsIds = flashcardPackage.getFlashcardsIds();
        for(String flashcardId: flashcardsIds) {
            Optional<Flashcard> flashcard = flashcardRepository.findById(flashcardId);
            flashcard.ifPresent(flashcards::add);
        }
        return flashcards;
    }

    public Flashcard getFlashcardFromFlashcardPackage(FlashcardPackage flashcardPackage, String flashcardId) {
        List<String> flashcardsIds = flashcardPackage.getFlashcardsIds();
        if (flashcardsIds.contains(flashcardId)){
            return getFlashcardById(flashcardId);
        } else {
            return null;
        }
    }

    public Flashcard updateFlashcardFromFlashcardPackage(FlashcardPackage flashcardPackage, String flashcardId, FlashcardDto flashcardDto) {
        Flashcard flashcard = getFlashcardFromFlashcardPackage(flashcardPackage, flashcardId);
        if (flashcardDto.getReverse() != null && flashcardDto.getReverse().length() >= 1 ) flashcard.setReverse(flashcardDto.getReverse());
        if (flashcardDto.getFront() != null && flashcardDto.getFront().length() >= 1 ) flashcard.setFront(flashcardDto.getFront());
        return flashcardRepository.save(flashcard);
    }

    public void deleteFlashcard(Flashcard flashcard) {
        flashcardRepository.delete(flashcard);
    }
}
