package com.baeldung.reactive;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class MessageController {

    @GetMapping(value = "/messages", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> getMessages() {
        return Flux.interval(Duration.ofSeconds(1))
            .map(this::generateMessage);

    }

    private Message generateMessage(long interval) {
        return new Message(interval, "foo");
    }
}
