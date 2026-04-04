package fr.eidd.command;

import fr.eidd.model.Student;
import fr.eidd.service.StudentService;
import fr.eidd.strategy.SortByGrade;
import fr.eidd.strategy.SortByName;
import fr.eidd.strategy.SortStrategy;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class ListSortedStudentsCommand implements Command {
    private final StudentService service;
    private final Scanner scanner;

    @Override
    public void execute() {
        System.out.println("Sort by: 1) Name  2) Grade");
        String choice = scanner.nextLine();

        SortStrategy strategy;
        if (choice.equals("1")) {
            strategy = new SortByName();
        } else {
            strategy = new SortByGrade();
        }

        List<Student> sorted = service.getSortedStudents(strategy);
        sorted.forEach(System.out::println);
    }

    @Override
    public String getName() {
        return "sorted";
    }

    @Override
    public String getDescription() {
        return "List all students sorted";
    }
}
