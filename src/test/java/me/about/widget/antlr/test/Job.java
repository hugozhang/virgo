package me.about.widget.antlr.test;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Job {

    private String name;

    private int salary;

    public Job() {
    }
    public Job(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

}
