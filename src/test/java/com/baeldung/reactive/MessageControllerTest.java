package com.baeldung.reactive;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest(MessageController.class)
class MessageControllerTest {

    public static final String EMITTER_URI = "/messages/";
    public static final int MAX_MESSAGES_TO_EXTRACT_FROM_FLUX = 3;

    @Autowired
    private WebTestClient webClient;

    @Test
    void shouldGetMessages() {
        Flux<Message> responseFlux = webClient.get()
            .uri(EMITTER_URI)
            .exchange()
            .returnResult(Message.class)
            .getResponseBody()
            .take(MAX_MESSAGES_TO_EXTRACT_FROM_FLUX);

        StepVerifier.create(responseFlux)
            .expectSubscription()
            .expectNext(new Message(0L, "foo"))
            .expectNext(new Message(1L, "foo"))
            .expectNext(new Message(2L, "foo"))
            .verifyComplete();
    }
}

