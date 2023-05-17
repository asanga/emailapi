package com.aviation.emailapi.controller;

import com.aviation.emailapi.api.EmailApi;
import com.aviation.emailapi.model.Email;
import com.aviation.emailapi.model.EmailDto;
import com.aviation.emailapi.service.EmailService;
import com.aviation.emailapi.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class EmailApiController implements EmailApi{

    private ValidatorUtil validatorUtil;
    private EmailService emailService;
    private static final String request_msg = "Request received to {}  method with data {} ";
    @Override
    public ResponseEntity<Void> createEmail(String username, EmailDto emailDto){
        log.info(request_msg, "createEmail", emailDto.toString());
        Email email = validatorUtil.validateEmailRequest(emailDto);
        Email savedEmail = emailService.createEmail(email);
        HttpHeaders headers = new HttpHeaders();
        headers.add("location-url", "/v1/emails/"+ savedEmail.getId());
        return new ResponseEntity<> (headers, HttpStatus.CREATED);
    }

}
