package com.apidemo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    private long id;
    private String name;
    private String email;
    private String mobile;
}
