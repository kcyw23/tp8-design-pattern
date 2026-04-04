package fr.eidd.strategy;

import fr.eidd.model.Student;

import java.util.List;

public interface SortStrategy {
    List<Student> sort(List<Student> students);
}
