package fr.eidd;

import fr.eidd.command.*;
import fr.eidd.repository.InMemoryStudentRepository;
import fr.eidd.repository.StudentRepository;
import fr.eidd.service.StudentService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentRepository repo = new InMemoryStudentRepository();
        StudentService service = new StudentService(repo);

        CLI cli = new CLI();
        cli.register(new AddStudentCommand(service, scanner));
        cli.register(new ListStudentsCommand(service));
        cli.register(new DeleteStudentCommand(service, scanner));
        cli.register(new ExitCommand());
        cli.run();
    }
}