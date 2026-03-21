package fr.eidd.strategy;

import fr.eidd.model.Student;

import java.util.Comparator;
import java.util.List;

public class SortByGrade implements SortStrategy {
    @Override
    public List<Student> sort(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getGrade))
                .toList();
    }
}
