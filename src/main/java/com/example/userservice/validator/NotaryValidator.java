package com.example.userservice.validator;

import com.example.userservice.dto.notary.NotaryDtoForAdd;
import com.example.userservice.service.UserService;
import com.example.userservice.validator.util.ValidImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class NotaryValidator implements Validator {
    private final UserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return NotaryDtoForAdd.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NotaryDtoForAdd studentDtoForAdd = (NotaryDtoForAdd) target;
        if (studentDtoForAdd.getId() == null) {
            ValidImageUtil.validImage(studentDtoForAdd.getImage(), errors);
            if (userService.validPhone(studentDtoForAdd.getPhone()))
                errors.rejectValue("phone", "", "The phone is already in use.");
            if (userService.validEmail(studentDtoForAdd.getEmail()))
                errors.rejectValue("email", "", "The email is already in use.");
        }
    }
}
