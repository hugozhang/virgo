package me.about.widget.antlr.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class User {

    private String name;

    private int age;

    private Date birthday;

    private Date birthday2;

    private List<Job> jobs;

}
