package com.aviation.emailapi.repo;

import com.aviation.emailapi.model.Email;
import com.aviation.emailapi.model.EmailStatusEnum;
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

    public Optional<List<Email>> getEmailList(Integer pageNumber, Integer pageSize, EmailStatusEnum status) {

        int startingId = pageNumber * pageSize;
        long existingListLength = emailList.stream().filter(e -> e.getStatus() == status).count();
        if (startingId > existingListLength) {
            return Optional.empty();
        }

        List<Email> emails = emailList.stream().skip(startingId).limit(pageSize).toList();
        return  Optional.ofNullable(emails);
    }

}
