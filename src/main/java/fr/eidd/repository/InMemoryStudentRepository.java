package fr.eidd.repository;

import fr.eidd.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class InMemoryStudentRepository implements StudentRepository {
    private final List<Student> students = new ArrayList<>();

    @Override
    public void save(Student student) {
        findById(student.getId()).ifPresentOrElse(s -> {
            s.setName(student.getName());
            s.setGrade(student.getGrade());
        }, () -> students.add(student));
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public Optional<Student> findById(String id) {
        return students.stream()
                .filter(s -> Objects.equals(id, s.getId()))
                .findFirst();
    }

    @Override
    public void delete(String id) {
        students.removeIf(s -> Objects.equals(id, s.getId()));
    }
}
