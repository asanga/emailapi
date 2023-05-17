package com.aviation.emailapi.repo;

import com.aviation.emailapi.model.Email;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemoryEmailDB {

    private List<Email> emailList = new ArrayList<>();

    public Email addEmail(Email email) {
        Long maxId = emailList.stream().mapToLong(e -> e.getId()).max().orElse(0);
        email.setId((maxId + 1));
        emailList.add(email);
        return email;
    }

    public Optional<Email> getEmailById(Long id) {
        return Optional.ofNullable(emailList.stream().filter(e -> e.getId() == id).findFirst().orElse(null));
    }

}
