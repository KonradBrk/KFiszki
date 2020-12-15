package com.konrad.brk.KFiszki.controler;

import com.konrad.brk.KFiszki.dto.FlashcardPackageDto;
import com.konrad.brk.KFiszki.model.FlashcardPackage;
import com.konrad.brk.KFiszki.service.FlashcardPackageService;
import com.konrad.brk.KFiszki.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api")
public class FlashcardPackageController {
    private FlashcardPackageService flashcardPackageService;
    private UserService userService;

    public FlashcardPackageController(FlashcardPackageService flashcardPackageService, UserService userService) {
        this.flashcardPackageService = flashcardPackageService;
        this.userService = userService;
    }


    @PostMapping("/{username}/newflashcardpackage")
    @PreAuthorize("#username == authentication.getName() && @flashcardPackageSecurityService.isOwnerOfFlashcardPackage(#username, #flashcardPackageId)")
    public ResponseEntity<FlashcardPackage> addNewFlashcardPackage(@PathVariable String username, @RequestBody FlashcardPackageDto flashcardPackageDto){
        FlashcardPackage flashcardPackage = flashcardPackageService.addFlashcardPackage(flashcardPackageDto);
        userService.addFlashcardPackageToTheUser(username, flashcardPackage);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flashcardPackage);
    }

    @DeleteMapping("/{username}/{flashcardPackageId}")
    @PreAuthorize("#username == authentication.getName() && @flashcardPackageSecurityService.isOwnerOfFlashcardPackage(#username, #flashcardPackageId)")
    public ResponseEntity<String> deleteFlashcardPackage(@PathVariable String username, @PathVariable String flashcardPackageId){
        flashcardPackageService.deleteFlashcardPackage(flashcardPackageId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Flashcard with following packageID has been removed: " + flashcardPackageId);
    }

    @GetMapping("/{username}/{flashcardPackageId}")
    @PreAuthorize("#username == authentication.getName() && @flashcardPackageSecurityService.isOwnerOfFlashcardPackage(#username, #flashcardPackageId)")
    public ResponseEntity<FlashcardPackage> getFlashcardPackage(@PathVariable String username, @PathVariable String flashcardPackageId){
        FlashcardPackage flashcardPackage = flashcardPackageService.getFlashcardPackageById(flashcardPackageId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flashcardPackage);
    }

    @GetMapping("/{username}/allflashcardpackages")
    @PreAuthorize("#username == authentication.getName()")
    public ResponseEntity<List<String>> getFlashcardPackage(@PathVariable String username){
        List<String> flashcardsPackagesIds = userService.getUserByUsername(username).getFlashcardsPackagesIds();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flashcardsPackagesIds);
    }

    @PatchMapping("/{username}/{flashcardPackageId}")
    @PreAuthorize("#username == authentication.getName() && @flashcardPackageSecurityService.isOwnerOfFlashcardPackage(#username, #flashcardPackageId)")
    public ResponseEntity<FlashcardPackage> updateFlashcardPackageName(@PathVariable String username,@PathVariable String flashcardPackageId, @RequestBody FlashcardPackageDto flashcardPackageDto){
        FlashcardPackage flashcardPackage = flashcardPackageService.updateFlashcardPackage(flashcardPackageId,flashcardPackageDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flashcardPackage);
    }

}
