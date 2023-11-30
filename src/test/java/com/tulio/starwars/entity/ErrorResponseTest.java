package com.tulio.starwars.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ErrorResponseTest {
    @Test
    void testConstructor() {
        ErrorResponse actualErrorResponse = new ErrorResponse("error en la solicitud");
        actualErrorResponse.setMessage("error en la solicitud");
        assertEquals("error en la solicitud", actualErrorResponse.getMessage());
    }
}
