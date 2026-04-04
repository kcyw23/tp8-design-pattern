package fr.eidd.command;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Bye!");
        System.exit(0);
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Exit";
    }
}
