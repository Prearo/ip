package duke;

import java.io.IOException;

public class Dukebot {
    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    private void handleStartup() {
        this.ui = new Ui();
        this.parser = new Parser();
        ui.display(Messages.STARTUP);
        try {
            this.storage = new Storage();
            storage.createStorage();
            this.taskList = storage.loadFromStorage();
        } catch (IOException e) {
            ui.display(Messages.LOAD_ERROR);
            this.taskList = new TaskList();
        }

    }

    private void handleBye() {
        ui.display(Messages.ENDING);
        System.exit(0);
    }

    private void handleList() {
        ui.display(Messages.LIST_TASKS);
        for (int i = 0; i < this.taskList.getSize(); i++) {
            ui.display((i + 1) + "." + taskList.get(i).toString());
        }
    }

    private void handleRemove(String[] inputArgs) throws DukeException {
        int index = parser.parseIndex(inputArgs);
        ui.display(Messages.TASK_REMOVED);
        ui.display(taskList.get(index).toString());
        taskList.remove(Integer.parseInt(inputArgs[1]) - 1);
        ui.display(String.format(Messages.TASK_COUNT, taskList.getSize()));
    }

    private void handleMark(String[] inputArgs) throws DukeException {
        int index = parser.parseIndex(inputArgs);
        taskList.mark(index);
        ui.display(Messages.TASK_MARKED);
        ui.display(this.taskList.get(index).toString());
    }

    private void handleUnmark(String[] inputArgs) throws DukeException {
        int index = parser.parseIndex(inputArgs);
        taskList.get(index).markUndone();
        ui.display(Messages.TASK_UNMARKED);
        ui.display(taskList.get(index).toString());
    }

    private void handleTodo(String[] inputArgs) throws DukeException {
        Todo t = parser.parseTodo(inputArgs);
        taskList.add(t);
        ui.display(Messages.TASK_ADDED);
        ui.display(this.taskList.get(this.taskList.getSize() - 1).toString());
        ui.display(String.format(Messages.TASK_COUNT, this.taskList.getSize()));
    }

    private void handleDeadline(String[] inputArgs) throws DukeException {
        Deadline d = parser.parseDeadline(inputArgs);
        taskList.add(d);
        ui.display(Messages.TASK_ADDED);
        ui.display(this.taskList.get(this.taskList.getSize() - 1).toString());
        ui.display(String.format(Messages.TASK_COUNT, this.taskList.getSize()));
    }

    private void handleEvent(String[] inputArgs) throws DukeException {
        Event e = parser.parseEvent(inputArgs);
        taskList.add(e);
        ui.display(Messages.TASK_ADDED);
        ui.display(this.taskList.get(this.taskList.getSize() - 1).toString());
        ui.display(String.format(Messages.TASK_COUNT, this.taskList.getSize()));
    }

    protected void handleInput(String input) {
        String[] inputArgs = input.split("\\s+", 2);
        String keyWord = inputArgs[0];
        try {
            switch (keyWord) {
            case "bye":
                this.handleBye();
            case "list":
                this.handleList();
                break;
            case "remove":
                this.handleRemove(inputArgs);
                storage.writeToStorage(taskList);
                break;
            case "mark":
                this.handleMark(inputArgs);
                storage.writeToStorage(taskList);
                break;
            case "unmark":
                this.handleUnmark(inputArgs);
                storage.writeToStorage(taskList);
                break;
            case "todo":
                this.handleTodo(inputArgs);
                storage.writeToStorage(taskList);
                break;
            case "deadline":
                this.handleDeadline(inputArgs);
                storage.writeToStorage(taskList);
                break;
            case "event":
                this.handleEvent(inputArgs);
                storage.writeToStorage(taskList);
                break;
            default:
                throw new DukeException(ExceptionMessages.UNSUPPORTED_ACTION);
            }
        } catch (DukeException e) {
            ui.display(e.getMessage());
        } catch (IOException e) {
            ui.display("Error writing to storage");
        }

    }


    public Dukebot() {
        this.handleStartup();
    }

}
