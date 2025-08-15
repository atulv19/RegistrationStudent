package com.apidemo.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDetails {
    private String message;
    private Date date;
    private String description;

    public ErrorDetails(String message, Date date, String description) {
        this.message = message;
        this.date = date;
        this.description = description;
    }
}
