package com.example.vaibhav.sahajya;

public class MessageOBJ {

    public String message, receiver, sender;

    MessageOBJ(){}


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String toString() {
        return ""+message;
    }
}
