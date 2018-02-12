package ru.bva.model;

public class MessageModel {
    private Integer id;
    private String message;

    public MessageModel() {
    }

    public MessageModel(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
