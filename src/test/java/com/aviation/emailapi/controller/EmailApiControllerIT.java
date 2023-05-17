package com.aviation.emailapi.controller;

import com.aviation.emailapi.model.EmailDto;
import com.aviation.emailapi.util.ValidatorUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmailApiController.class)
@ComponentScan({"com.aviation.emailapi.util", "com.aviation.emailapi.configuration"})
class EmailApiControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmailApiController emailApiController;

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    @DisplayName("Given empty body, when create email, then return validation exception")
    public void givenInvalidInput_whenCallCreateEmail_thenReturnCreatedResponse() throws Exception {

        mockMvc.perform(post("/v1/emails")
                        .header("username", "abc@eroad.com")
                        .content(getInValidEmailDtoJsonString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().doesNotExist("location-url"))
                .andExpect(jsonPath("$.violations[0].message").value("body cannot be null or blank"))
                .andExpect(status().isBadRequest());
    }

    private static String getInValidEmailDtoJsonString() throws ParseException, JsonProcessingException {
        EmailDto dto = new EmailDto();
        dto.setBody("");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(dto );
    }

}