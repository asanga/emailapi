package com.aviation.emailapi.service;

import com.aviation.emailapi.model.Email;
import com.aviation.emailapi.repo.MemoryEmailDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class EmailServiceImplTest {

    private EmailService emailService;
    private MemoryEmailDB memoryEmailDB;

    @BeforeEach
    void setUp() {
        memoryEmailDB = Mockito.mock(MemoryEmailDB.class);
        emailService = new EmailServiceImpl(memoryEmailDB);
    }

    @Test
    @DisplayName("Given valid email, when saving it, then returns saved email with correct id ")
    void givenValidEmail_whenSaving_thenReturnSavedCorrectEmailId() {
        Email savingEmail = Email.builder().subject("Test subject").build();
        Email savedEmail = Email.builder().subject("Test subject").id(1l).build();

        when(memoryEmailDB.addEmail(any())).thenReturn(savedEmail);
        Email email = emailService.createEmail(savingEmail);
        assertEquals(1, email.getId());
    }

    @Test
    @DisplayName("Give empty email list, when retrieve email, then returns null optional")
    void givenEmptyEmailList_whenRetrieveEmail_thenReturnNull() {
        when(memoryEmailDB.getEmailById(anyLong())).thenReturn(Optional.empty());
        Optional<Email> emailOptional = emailService.getEmailById(1l);
        assertTrue(emailOptional.isEmpty());
    }

    @Test
    @DisplayName("Give email includes in the email list, when retrieve email, then returns correct email")
    void givenEmailExistInEmailList_whenRetrieveEmail_thenReturnEmail() {
        Email savedEmail = Email.builder().subject("Test Subject").id(1l).build();
        when(memoryEmailDB.getEmailById(anyLong())).thenReturn(Optional.of(savedEmail));
        Optional<Email> emailOptional = emailService.getEmailById(1l);
        assertEquals(1, emailOptional.map(e -> e.getId()).get());
    }
}