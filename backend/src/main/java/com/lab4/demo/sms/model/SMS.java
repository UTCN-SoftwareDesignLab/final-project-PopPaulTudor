package com.lab4.demo.sms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SMS {
    private String to;
    private String message;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "SMS{" +
                "to='" + to + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}