package com.aviation.emailapi.service;

import com.aviation.emailapi.model.Email;

import java.util.Optional;

public interface EmailService {
    Email createEmail(Email email);

    Optional<Email> getEmailById(Long id);

}
