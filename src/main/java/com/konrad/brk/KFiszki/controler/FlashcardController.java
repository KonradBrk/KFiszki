package com.konrad.brk.KFiszki.controler;

import com.konrad.brk.KFiszki.dto.FlashcardDto;
import com.konrad.brk.KFiszki.model.Flashcard;
import com.konrad.brk.KFiszki.model.FlashcardPackage;
import com.konrad.brk.KFiszki.service.FlashcardPackageService;
import com.konrad.brk.KFiszki.service.FlashcardService;
import com.konrad.brk.KFiszki.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlashcardController {

    private UserService userService;
    private FlashcardService flashcardService;
    private FlashcardPackageService flashcardPackageService;

    public FlashcardController(UserService userService, FlashcardService flashcardService, FlashcardPackageService flashcardPackageService) {
        this.userService = userService;
        this.flashcardService = flashcardService;
        this.flashcardPackageService = flashcardPackageService;
    }

    @PostMapping("/{username}/{flashcardPackageId}/newflashcard")
    @PreAuthorize("#username == authentication.getName() && @flashcardPackageSecurityService.isOwnerOfFlashcardPackage(#username, #flashcardPackageId)")
    public ResponseEntity<FlashcardPackage> addFlashcardToFlashcardPackage(
            @RequestBody FlashcardDto flashcardDto,
            @PathVariable String flashcardPackageId,
            @PathVariable String username){

        Flashcard flashcard = flashcardService.addNewFlashcard(flashcardDto);
        FlashcardPackage flashcardPackage = flashcardPackageService.addFlashcardToThePackage(flashcardPackageId, flashcard);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flashcardPackage);

    }

    @GetMapping("/{username}/{flashcardPackageId}/allflashcards")
    @PreAuthorize("#username == authentication.getName() && @flashcardPackageSecurityService.isOwnerOfFlashcardPackage(#username, #flashcardPackageId)")
    public ResponseEntity<List<Flashcard>> getAllFlashcardsFromFlashcardPackage(
            @PathVariable String username,
            @PathVariable String flashcardPackageId){

        FlashcardPackage flashcardPackage = flashcardPackageService.getFlashcardPackageById(flashcardPackageId);
        List<Flashcard> flashcardList = flashcardService.getAllFlashcardsFromFlashcardPackage(flashcardPackage);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flashcardList);

    }

    @GetMapping("/{username}/{flashcardPackageId}/{flashcardId}")
    @PreAuthorize("#username == authentication.getName() && @flashcardPackageSecurityService.isOwnerOfFlashcardPackage(#username, #flashcardPackageId)")
    public ResponseEntity<Flashcard> getFlashcardFromFlashcardPackage(
            @PathVariable String username,
            @PathVariable String flashcardPackageId,
            @PathVariable String flashcardId){

        FlashcardPackage flashcardPackage = flashcardPackageService.getFlashcardPackageById(flashcardPackageId);
        Flashcard flashcard = flashcardService.getFlashcardFromFlashcardPackage(flashcardPackage, flashcardId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flashcard);
    }

    @DeleteMapping("/{username}/{flashcardPackageId}/{flashcardId}")
    @PreAuthorize("#username == authentication.getName() && @flashcardPackageSecurityService.isOwnerOfFlashcardPackage(#username, #flashcardPackageId)")
    public ResponseEntity<FlashcardPackage> deleteFlashcardFromFlashcardPackage(
            @PathVariable String username,
            @PathVariable String flashcardPackageId,
            @PathVariable String flashcardId){

        FlashcardPackage flashcardPackage = flashcardPackageService.deleteFlashcardFromFlashcardPackage(flashcardPackageId, flashcardId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flashcardPackage);
    }

    @PatchMapping("/{username}/{flashcardPackageId}/{flashcardId}")
    @PreAuthorize("#username == authentication.getName() && @flashcardPackageSecurityService.isOwnerOfFlashcardPackage(#username, #flashcardPackageId)")
    public ResponseEntity<Flashcard> updateFlashcardFromFlashcardPackage(
            @PathVariable String username,
            @PathVariable String flashcardPackageId,
            @PathVariable String flashcardId,
            @RequestBody FlashcardDto flashcardDto){

        FlashcardPackage flashcardPackage = flashcardPackageService.getFlashcardPackageById(flashcardPackageId);
        Flashcard flashcard = flashcardService.updateFlashcardFromFlashcardPackage(flashcardPackage, flashcardId, flashcardDto);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flashcard);
    }



}
