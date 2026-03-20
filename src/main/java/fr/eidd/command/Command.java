package fr.eidd.command;

public interface Command {
    void execute();

    String getName();

    String getDescription();
}
