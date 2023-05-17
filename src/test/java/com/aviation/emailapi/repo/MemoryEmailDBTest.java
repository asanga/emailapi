package com.aviation.emailapi.repo;

import com.aviation.emailapi.model.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MemoryEmailDBTest {

    private MemoryEmailDB memoryEmailDB;

    @BeforeEach
    void setUp() {
        memoryEmailDB  = new MemoryEmailDB();
    }

    @Test
    @DisplayName("Give empty email list, when saving first email, then returns saved email with id as 1")
    void givenEmptyEmailListAndValidEmail_whenSavingEmail_thenReturnSavedEmailWithIdAs1() {
        Email savingEmail = Email.builder().subject("Test subject").build();
        Email email = memoryEmailDB.addEmail(savingEmail);
        assertEquals(1, email.getId());
    }
    @Test
    @DisplayName("Give empty email list, when retrieve email, then returns null optional")
    void givenEmptyEmailList_whenRetrieveEmail_thenReturnNull() {
        Optional<Email> emailOptional = memoryEmailDB.getEmailById(1l);
        assertTrue(emailOptional.isEmpty());
    }

    @Test
    @DisplayName("Give email includes in the email list, when retrieve email, then returns correct email")
    void givenEmailExistInEmailList_whenRetrieveEmail_thenReturnEmail() {
        Email savingEmail = Email.builder().subject("Test Subject").id(1l).build();
        memoryEmailDB.addEmail(savingEmail);
        Optional<Email> emailOptional = memoryEmailDB.getEmailById(1l);
        assertEquals(1, emailOptional.map(e -> e.getId()).get());
    }

}