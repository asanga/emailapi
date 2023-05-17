package com.aviation.emailapi.service;

import com.aviation.emailapi.model.Email;
import com.aviation.emailapi.model.EmailStatusEnum;

import java.util.List;
import java.util.Optional;

public interface EmailService {
    Email createEmail(Email email);

    Optional<Email> getEmailById(Long id);

    Optional<List<Email>> getEmailList(Integer pageNumber, Integer pageSize,
                                       EmailStatusEnum statusEnum);

}
