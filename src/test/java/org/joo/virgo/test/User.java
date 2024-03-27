package org.joo.virgo.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {

    private String name;

    private int age;

    private Date birthday;

    private Date birthday2;

}
