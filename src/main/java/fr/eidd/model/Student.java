package fr.eidd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Student {
    private String id;
    private String name;
    private double grade;

    @Override
    public String toString() {
        return String.format("[%s] %s - %.1f/20", id, name, grade);
    }
}
