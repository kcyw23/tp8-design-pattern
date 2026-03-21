package fr.eidd.service;

import fr.eidd.model.Student;
import fr.eidd.repository.StudentRepository;
import fr.eidd.strategy.SortStrategy;

import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(String id, String name, double grade) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (grade < 0 || grade > 20) {
            throw new IllegalArgumentException("Grade must be between 0 and 20");
        }
        repository.save(new Student(id, name, grade));
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public void deleteStudent(String id) {
        repository.delete(id);
    }

    public List<Student> getSortedStudents(SortStrategy strategy) {
        return strategy.sort(repository.findAll());
    }
}
