package fr.eidd;

import fr.eidd.repository.InMemoryStudentRepository;
import fr.eidd.repository.StudentRepository;
import fr.eidd.service.StudentService;

public class Main {
    public static void main(String[] args) {
        StudentRepository repo = new InMemoryStudentRepository();
        StudentService service = new StudentService(repo);
        service.addStudent("1", "Alice", 15.5);
        service.addStudent("2", "Bob", 12.0);
        System.out.println(service.getAllStudents());
    }
}