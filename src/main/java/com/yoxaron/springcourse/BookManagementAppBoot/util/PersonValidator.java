package com.yoxaron.springcourse.BookManagementAppBoot.util;


import com.yoxaron.springcourse.BookManagementAppBoot.model.Person;
import com.yoxaron.springcourse.BookManagementAppBoot.service.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personService.getPersonByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "This full name is already taken");
        }
    }
}
