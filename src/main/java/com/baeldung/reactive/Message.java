package com.baeldung.reactive;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private long id;
    private String value;
}
