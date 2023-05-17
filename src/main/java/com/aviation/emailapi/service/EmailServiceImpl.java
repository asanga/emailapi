package com.aviation.emailapi.service;

import com.aviation.emailapi.model.Email;
import com.aviation.emailapi.repo.MemoryEmailDB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final MemoryEmailDB memoryEmailDB;

    public Email createEmail(Email email) {
         return memoryEmailDB.addEmail(email);
    }

}
