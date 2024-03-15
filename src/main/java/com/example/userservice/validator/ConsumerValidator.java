package com.example.userservice.validator;

import com.example.userservice.dto.consumer.ConsumerDtoForAdd;
import com.example.userservice.service.UserService;
import com.example.userservice.validator.util.ValidImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ConsumerValidator implements Validator {
    private final UserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return ConsumerDtoForAdd.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ConsumerDtoForAdd consumerDtoForAdd = (ConsumerDtoForAdd) target;
        if (consumerDtoForAdd.getId() == null) {
            ValidImageUtil.validImage(consumerDtoForAdd.getImage(), errors);
            if (userService.validPhone(consumerDtoForAdd.getPhone()))
                errors.rejectValue("phone", "", "The phone is already in use.");
            if (userService.validEmail(consumerDtoForAdd.getEmail()))
                errors.rejectValue("email", "", "The email is already in use.");
        }
    }
}