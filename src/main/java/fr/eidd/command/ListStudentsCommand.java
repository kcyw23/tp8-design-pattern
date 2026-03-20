package fr.eidd.command;

import fr.eidd.model.Student;
import fr.eidd.service.StudentService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListStudentsCommand implements Command {
    private final StudentService studentService;

    @Override
    public void execute() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            students.forEach(System.out::println);
        }
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "List all students";
    }
}
