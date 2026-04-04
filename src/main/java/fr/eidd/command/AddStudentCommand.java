package fr.eidd.command;

import fr.eidd.service.StudentService;

import java.util.Scanner;

public class AddStudentCommand implements Command {
    private StudentService service;
    private Scanner scanner;

    public AddStudentCommand(StudentService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Grade (0-20): ");
        double grade = Double.parseDouble(scanner.nextLine());
        try {
            service.addStudent(id, name, grade);
            System.out.println("Student added!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getName() { return "add"; }

    @Override
    public String getDescription() { return "Add a new student"; }
}