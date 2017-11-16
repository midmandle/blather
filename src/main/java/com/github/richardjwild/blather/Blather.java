package com.github.richardjwild.blather;

import com.github.richardjwild.blather.command.Command;
import com.github.richardjwild.blather.io.Output;
import com.github.richardjwild.blather.parsing.CommandReader;

import static com.github.richardjwild.blather.ApplicationState.RUNNING;

public class Blather {

    public static void main(String[] args) {
        Blather blather = ApplicationBuilder.build();
        blather.runApplication();
    }

    private final AppController appController;
    private final CommandReader commandReader;
    private Output output;

    public Blather(AppController appController, CommandReader commandReader, Output output) {
        this.appController = appController;
        this.commandReader = commandReader;
        this.output = output;
    }

    void runApplication() {
        output.writeLine("Welcome to Blather");
        eventLoop();
    }

    private void eventLoop() {
        do {
            Command command = commandReader.readNextCommand();
            command.execute();
        } while (appIsRunning());
    }

    private boolean appIsRunning() {
        return appController.applicationState() == RUNNING;
    }
}
