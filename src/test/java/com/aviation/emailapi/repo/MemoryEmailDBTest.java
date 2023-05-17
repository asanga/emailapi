package com.aviation.emailapi.repo;

import com.aviation.emailapi.model.Email;
import com.aviation.emailapi.model.EmailStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
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

    @Test
    @DisplayName("Given empty email list, when retrieving EmailList, then returns empty list")
    public void givenEmptyList_whenGetEmailList_thenReturnEmptyList() {
        Optional<List<Email>> optionalEmailList = memoryEmailDB.getEmailList(1, 2, EmailStatusEnum.SENT);
        assertTrue(optionalEmailList.isEmpty());
    }

    @Test
    @DisplayName("Given empty email list, when retrieving EmailList, then returns empty list")
    public void givenValidEmailList_whenGetEmailList_thenReturnFilterdEmailList() {
        Email savingEmail1 = Email.builder().subject("Test Subject 1").id(1l).status(EmailStatusEnum.SENT).build();
        memoryEmailDB.addEmail(savingEmail1);
        Email savingEmail2 = Email.builder().subject("Test Subject 2").id(2l).status(EmailStatusEnum.SENT).build();
        memoryEmailDB.addEmail(savingEmail2);
        Email savingEmail3 = Email.builder().subject("Test Subject 3").id(3l).status(EmailStatusEnum.SENT).build();
        memoryEmailDB.addEmail(savingEmail3);
        Email savingEmail4 = Email.builder().subject("Test Subject 4").id(4l).status(EmailStatusEnum.SENT).build();
        memoryEmailDB.addEmail(savingEmail4);
        Email savingEmail5 = Email.builder().subject("Test Subject 5").id(5l).status(EmailStatusEnum.SENT).build();
        memoryEmailDB.addEmail(savingEmail5);

        Optional<List<Email>> optionalEmailList = memoryEmailDB.getEmailList(1, 2, EmailStatusEnum.SENT);
        assertEquals(2, optionalEmailList.map(List::size).orElse(0));
    }

}