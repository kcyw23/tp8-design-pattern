package fr.eidd.repository;

import fr.eidd.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void save(Student student);

    List<Student> findAll();

    Optional<Student> findById(String id);

    void delete(String id);
}
