package fr.eidd.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CLI {
    private Map<String, Command> commands = new LinkedHashMap<>();

    public void register(Command cmd) {
        commands.put(cmd.getName(), cmd);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Student Manager ===");
            commands.values().forEach(c ->
                    System.out.println("  " + c.getName() + " - " + c.getDescription()));
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            Command cmd = commands.get(input);
            if (cmd != null) {
                cmd.execute();
            } else {
                System.out.println("Unknown command. Try again.");
            }
        }
    }
}