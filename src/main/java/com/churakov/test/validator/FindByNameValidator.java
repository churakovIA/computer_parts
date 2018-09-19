package com.churakov.test.validator;

import com.churakov.test.model.Part;
import com.churakov.test.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FindByNameValidator implements Validator {

    @Autowired
    private PartService partService;

    public boolean supports(Class<?> aClass) {
        return Part.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        String name = ((Part) o).getName();

        Part findPart = null;
        if(name!=null && name.trim().length()>0){
            findPart = partService.getPartByName(name);
            if(findPart == null) {
                errors.reject("","Комплектующая не найдена!");
            }
        }

    }
}
