package com.studentsaccount.app;

import java.util.Date;

public class Students {

    private Integer studentId;

    private String studentName;

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentId() {
        return studentId;
    }
}
