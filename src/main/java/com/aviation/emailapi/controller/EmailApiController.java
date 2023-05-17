package com.aviation.emailapi.controller;

import com.aviation.emailapi.api.EmailApi;
import com.aviation.emailapi.controller.exception.InvalidRequestException;
import com.aviation.emailapi.model.Email;
import com.aviation.emailapi.model.EmailDto;
import com.aviation.emailapi.model.EmailStatusEnum;
import com.aviation.emailapi.service.EmailService;
import com.aviation.emailapi.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class EmailApiController implements EmailApi{

    @Autowired
    private ValidatorUtil validatorUtil;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ModelMapper modelMapper;

    @Value("${max.email.pagesize}")
    private int maxPageSize;

    private static final String REQUEST_MSG = "Request received to {}  method with data {} ";
    @Override
    public ResponseEntity<Void> createEmail(String username, EmailDto emailDto){
        log.info(REQUEST_MSG, "createEmail", emailDto.toString());
        Email email = validatorUtil.validateEmailRequest(emailDto);
        email.setStatus(EmailStatusEnum.DRAFT);
        Email savedEmail = emailService.createEmail(email);
        HttpHeaders headers = new HttpHeaders();
        headers.add("location-url", "/v1/emails/"+ savedEmail.getId());
        return new ResponseEntity<> (headers, HttpStatus.CREATED);
    }

    @Override
    public  ResponseEntity<EmailDto> getEmailById(String username, Long id) {
        log.info(REQUEST_MSG, "getEmailById", "id : " + id);
        Optional<Email> optionalEmail = emailService.getEmailById(id);
        EmailDto emailDto = null;
        emailDto =  modelMapper.map(
                optionalEmail.orElseThrow(() -> new InvalidRequestException(
                        HttpStatus.BAD_REQUEST.value(), "No element found for the id")), EmailDto.class);
        return new ResponseEntity<>(emailDto, HttpStatus.OK);
    }

    @Override
    public  ResponseEntity<List<EmailDto>> getEmailList(String username, String status, Integer pageNumber, Integer pageSize) {
        log.info(REQUEST_MSG, "pageNumber : " + pageNumber + " pageSize : "+ pageSize +" status : " + status);

        if (pageSize > maxPageSize) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST.value(), "Exceed max pagesize : " + maxPageSize);
        }
        Optional<List<Email>> optionalEmailList = emailService.getEmailList(pageNumber, pageSize,
                EmailStatusEnum.fromValue(status));
        List<Email> emailList = optionalEmailList.orElse(null);
        List<EmailDto> emailDtos = new ArrayList<>();
        if (emailList != null) {
            emailDtos = emailList.stream().map(e -> modelMapper.map(e, EmailDto.class)).toList();
        }

        return new ResponseEntity<>(emailDtos, HttpStatus.OK);
    }

}
