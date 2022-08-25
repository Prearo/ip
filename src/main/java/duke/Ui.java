package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getInput() {
        out.print("Enter command: ");
        return in.nextLine();
    }

    public void display(String s) {
        out.println(s);
    }

}