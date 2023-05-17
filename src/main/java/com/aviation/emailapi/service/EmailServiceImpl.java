package com.aviation.emailapi.service;

import com.aviation.emailapi.model.Email;
import com.aviation.emailapi.model.EmailStatusEnum;
import com.aviation.emailapi.repo.MemoryEmailDB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final MemoryEmailDB memoryEmailDB;

    public Email createEmail(Email email) {
         return memoryEmailDB.addEmail(email);
    }

    public Optional<Email> getEmailById(Long id) {
        return memoryEmailDB.getEmailById(id);

    }

    public Optional<List<Email>> getEmailList(Integer pageNumber, Integer pageSize,
                                       EmailStatusEnum statusEnum){
        return memoryEmailDB.getEmailList(pageNumber, pageSize, statusEnum);
    }

}
