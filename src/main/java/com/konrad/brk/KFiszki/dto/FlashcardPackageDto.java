package com.konrad.brk.KFiszki.dto;

import java.util.List;

public class FlashcardPackageDto {
    private String id;
    private String packageName;
    private List<String> flashcardsIds;

    public FlashcardPackageDto(String id, String packageName, List<String> flashcardsIds) {
        this.id = id;
        this.packageName = packageName;
        this.flashcardsIds = flashcardsIds;
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
        return "FlashcardPackageDto{" +
                "id='" + id + '\'' +
                ", packageName='" + packageName + '\'' +
                ", flashcardsIds=" + flashcardsIds +
                '}';
    }
}
