package com.konrad.brk.KFiszki.dto;

public class FlashcardDto {
    private String id;
    private String front;
    private String reverse;

    public FlashcardDto(String front, String reverse) {
        this.front = front;
        this.reverse = reverse;
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


    @Override
    public String toString() {
        return "FlashcardDto{" +
                "id='" + id + '\'' +
                ", front='" + front + '\'' +
                ", reverse='" + reverse + '\'' +
                '}';
    }
}
