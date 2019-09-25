package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.model.Student;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {

    public static final int STUDENT_NAME_MAX_SIZE = 255;


    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "studentName", "studentName.empty");
        Student student = (Student) o;

        if (StringUtils.hasLength(student.getStudentName())
                && student.getStudentName().length() > STUDENT_NAME_MAX_SIZE) {
            errors.rejectValue("studentName", "studentName.maxSize255");
        }
    }
}