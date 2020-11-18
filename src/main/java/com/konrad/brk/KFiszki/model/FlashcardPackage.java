package com.konrad.brk.KFiszki.model;

import com.konrad.brk.KFiszki.dto.FlashcardPackageDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class FlashcardPackage {
    @Id
    private String id;
    private String packageName;
    private List<String> flashcardsIds;

    public static FlashcardPackage apply(FlashcardPackageDto flashcardPackageDto){
        FlashcardPackage flashcardPackage = new FlashcardPackage();
        flashcardPackage.setPackageName(flashcardPackageDto.getPackageName());
        flashcardPackage.setFlashcardsIds(flashcardPackageDto.getFlashcardsIds());
        return flashcardPackage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getFlashcardsIds() {
        return flashcardsIds;
    }

    public void setFlashcardsIds(List<String> flashcardsIds) {
        this.flashcardsIds = flashcardsIds;
    }

    @Override
    public String toString() {
        return "FlashcardPackage{" +
                "id='" + id + '\'' +
                ", packageName='" + packageName + '\'' +
                ", flashcardsIds=" + flashcardsIds +
                '}';
    }
}
