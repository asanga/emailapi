package com.aviation.emailapi.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ValidationError {
    private List<Violation> violations = new ArrayList<>();
}
