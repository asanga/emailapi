package com.aviation.emailapi.service;

import com.aviation.emailapi.model.Email;
import com.aviation.emailapi.repo.MemoryEmailDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        Email savingEmail = new Email();
        savingEmail.setSubject("Test subject");

        Email savedEmail = new Email();
        savedEmail.setSubject("Test subject");
        savedEmail.setId(1l);

        when(memoryEmailDB.addEmail(any())).thenReturn(savedEmail);
        Email email = emailService.createEmail(savingEmail);
        assertEquals(1, email.getId());
    }
}