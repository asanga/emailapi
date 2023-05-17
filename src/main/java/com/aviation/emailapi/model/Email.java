package com.aviation.emailapi.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
public class Email {
    private Long id;
    private String receiver;
    private List<String> ccList;
    private List<String> bccList = null;
    private String subject;

    @NotBlank(message = "body cannot be null or blank")
    private String body;

    private EmailStatusEnum status;
}
