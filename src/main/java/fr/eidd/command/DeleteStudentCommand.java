package fr.eidd.command;

import fr.eidd.service.StudentService;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class DeleteStudentCommand implements Command {
    private final StudentService studentService;
    private final Scanner scanner;

    @Override
    public void execute() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        studentService.deleteStudent(id);
        System.out.println("Student deleted!");
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "Delete a student";
    }
}
