package com.aviation.emailapi.repo;

import com.aviation.emailapi.model.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryEmailDBTest {

    private MemoryEmailDB memoryEmailDB;

    @BeforeEach
    void setUp() {
        memoryEmailDB  = new MemoryEmailDB();
    }

    @Test
    @DisplayName("Give empty email list, when saving first email, then returns saved email with id as 1")
    void givenEmptyEmailListAndValidEmail_whenSavingEmail_thenReturnSavedEmailWithIdAs1() {
        Email savingEmail = new Email();
        savingEmail.setSubject("Test subject");

        Email email = memoryEmailDB.addEmail(savingEmail);
        assertEquals(1, email.getId());
    }
}