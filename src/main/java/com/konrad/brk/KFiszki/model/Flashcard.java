package com.konrad.brk.KFiszki.model;

import com.konrad.brk.KFiszki.dto.FlashcardDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Flashcard {
    @Id
    private String id;
    private String front;
    private String reverse;
    private Integer checks; //How many times this flashcards appeared in the learning process (easiest/hardest)

    private Boolean isLearned;

    public static Flashcard apply(FlashcardDto flashcardDto){
        Flashcard flashcard = new Flashcard();
        flashcard.setFront(flashcardDto.getFront());
        flashcard.setReverse(flashcardDto.getReverse());
        return flashcard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getReverse() {
        return reverse;
    }

    public void setReverse(String reverse) {
        this.reverse = reverse;
    }

    public Integer getChecks() {
        return checks;
    }

    public void setChecks(Integer checks) {
        this.checks = checks;
    }

    public Boolean getLearned() {
        return isLearned;
    }

    public void setLearned(Boolean learned) {
        isLearned = learned;
    }



    @Override
    public String toString() {
        return "Flashcard{" +
                "id='" + id + '\'' +
                ", front='" + front + '\'' +
                ", reverse='" + reverse + '\'' +
                ", checks=" + checks +
                ", isLearned=" + isLearned +
                '}';
    }
}
