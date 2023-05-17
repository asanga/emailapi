package com.aviation.emailapi.util;

import com.aviation.emailapi.model.Email;
import com.aviation.emailapi.model.EmailDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class ValidatorUtil {
    private Validator validator;
    private ModelMapper modelMapper;
    public Email validateEmailRequest(EmailDto emailDto) {

        Email email = modelMapper.map(emailDto, Email.class);
        Set<ConstraintViolation<Email>> violations = validator.validate(email);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return email;
    }
}
