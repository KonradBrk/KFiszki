package com.konrad.brk.KFiszki.service;

import com.konrad.brk.KFiszki.dto.FlashcardPackageDto;
import com.konrad.brk.KFiszki.exception.FlashcardPackageAlreadyExistsException;
import com.konrad.brk.KFiszki.exception.FlashcardPackageNotFoundException;
import com.konrad.brk.KFiszki.model.Flashcard;
import com.konrad.brk.KFiszki.model.FlashcardPackage;
import com.konrad.brk.KFiszki.repository.FlashcardsPackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlashcardPackageService {

    private FlashcardsPackageRepository flashcardsPackageRepository;
    private FlashcardService flashcardService;

    public FlashcardPackageService(FlashcardsPackageRepository flashcardsPackageRepository, FlashcardService flashcardService) {
        this.flashcardsPackageRepository = flashcardsPackageRepository;
        this.flashcardService = flashcardService;
    }

    public FlashcardPackage addFlashcardPackage(FlashcardPackageDto flashcardPackageDto) {
        if (getOptFlashcardPackageById(flashcardPackageDto.getId()).isPresent()) {
            throw new FlashcardPackageAlreadyExistsException("Flashcard Package already exists!");
        }
        FlashcardPackage flashcardPackage = FlashcardPackage.apply(flashcardPackageDto);
        return flashcardsPackageRepository.save(flashcardPackage);
    }

    private Optional<FlashcardPackage> getOptFlashcardPackageById(String flashcardPackageId) {
        return flashcardsPackageRepository.findFlashcardPackageById(flashcardPackageId);
    }

    public FlashcardPackage getFlashcardPackageById(String id) {
        return flashcardsPackageRepository.findById(id).stream()
                .findFirst().orElseThrow(() -> new FlashcardPackageNotFoundException("There is no flashcard package with id: " + id));
    }

    public FlashcardPackage addFlashcardToThePackage(String flashcardPackageId, Flashcard flashcard) {
        System.out.println(flashcardPackageId);
        System.out.println(flashcard);
        FlashcardPackage flashcardPackage = getFlashcardPackageById(flashcardPackageId);
        flashcardPackage.getFlashcardsIds().add(flashcard.getId());
        return flashcardsPackageRepository.save(flashcardPackage);
    }

    public void deleteFlashcardPackage(String flashcardPackageId) {
        FlashcardPackage flashcardPackage = getFlashcardPackageById(flashcardPackageId);
        List<String> flashcardsIds = flashcardPackage.getFlashcardsIds();
        for (String flashcard : flashcardsIds) {
            flashcardService.deleteFlashcardById(flashcard);
        }
        flashcardsPackageRepository.delete(flashcardPackage);
    }

    public FlashcardPackage updateFlashcardPackage(String flashcardPackageId, FlashcardPackageDto flashcardPackageDto) {
        FlashcardPackage flashcardPackage = getFlashcardPackageById(flashcardPackageId);
        flashcardPackage.setFlashcardsIds(flashcardPackageDto.getFlashcardsIds());
        flashcardPackage.setPackageName(flashcardPackageDto.getPackageName());
        flashcardsPackageRepository.save(flashcardPackage);
        return flashcardPackage;
    }

    public void updateFlashcardPackage(FlashcardPackage flashcardPackage) {
        flashcardsPackageRepository.save(flashcardPackage);
    }
}
