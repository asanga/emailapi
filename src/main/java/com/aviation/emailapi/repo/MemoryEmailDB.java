package com.aviation.emailapi.repo;

import com.aviation.emailapi.model.Email;

import java.util.ArrayList;
import java.util.List;

public class MemoryEmailDB {

    private List<Email> emailList = new ArrayList<>();

    public Email addEmail(Email email) {
        Long maxId = emailList.stream().mapToLong(e -> e.getId()).max().orElse(0);
        email.setId((maxId + 1));
        emailList.add(email);
        return email;
    }

}
