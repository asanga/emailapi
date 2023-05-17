package com.aviation.emailapi.service;

import com.aviation.emailapi.model.Email;
import com.aviation.emailapi.model.EmailStatusEnum;
import com.aviation.emailapi.repo.MemoryEmailDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
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

    @Test
    @DisplayName("Given empty email list, when retrieving EmailList, then returns empty list")
    public void givenEmptyList_whenGetEmailList_thenReturnEmptyList() {
        when(memoryEmailDB.getEmailList(anyInt(), anyInt(), any())).thenReturn(Optional.empty());
        Optional<List<Email>> optionalEmailList = emailService.getEmailList(1, 2, EmailStatusEnum.SENT);
        assertTrue(optionalEmailList.isEmpty());
    }

    @Test
    @DisplayName("Given empty email list, when retrieving EmailList, then returns empty list")
    public void givenValidEmailList_whenGetEmailList_thenReturnFilterdEmailList() {
        List<Email> emailList = new ArrayList<>();
        Email savingEmail3 = Email.builder().subject("Test Subject 3").id(3l).status(EmailStatusEnum.SENT).build();
        emailList.add(savingEmail3);
        Email savingEmail4 = Email.builder().subject("Test Subject 4").id(4l).status(EmailStatusEnum.SENT).build();
        emailList.add(savingEmail4);

        when(memoryEmailDB.getEmailList(anyInt(), anyInt(), any())).thenReturn(Optional.of(emailList));
        Optional<List<Email>> optionalEmailList = emailService.getEmailList(1, 2, EmailStatusEnum.SENT);
        assertEquals(2, optionalEmailList.map(List::size).orElse(0));
    }
}