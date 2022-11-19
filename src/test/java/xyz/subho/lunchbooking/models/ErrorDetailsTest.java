package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ErrorDetailsTest {
    /**
     * Method under test: {@link ErrorDetails#ErrorDetails(String)}
     */
    @Test
    void testConstructor() {
        assertEquals("Not all who wander are lost", (new ErrorDetails("Not all who wander are lost")).getMessage());
    }
}

