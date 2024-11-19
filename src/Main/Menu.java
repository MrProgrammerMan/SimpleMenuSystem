package Main;

import java.util.Arrays;
import java.util.Scanner;

public class Menu implements Runnable {
  Scanner sc;
  private final String title;
  private final String[] options;
  private final Runnable[] callbacks;

  /**
   * Constructor
   *
   * @param title   Title of the menu
   * @param options Options of the menu
   * @param callbacks Callbacks to run when an option is selected
   */
  public Menu(Scanner sc, String title, String[] options, Runnable[] callbacks) {
    if (options.length != callbacks.length) {
      throw new IllegalArgumentException("Options and callbacks must have the same length");
    }

    this.sc = sc;
    this.title = title;
    this.options = Arrays.copyOf(options, options.length + 1);
    this.options[options.length] = "Exit menu";
    this.callbacks = callbacks;
  }

  /**
   * Run the menu
   */
  @Override
  public void run() {
    while (true) {
      int choice = present(sc);
      if (choice == options.length) {
        return;
      }
      callbacks[choice-1].run();
    }
  }

  /**
   * Display the menu and get user input
   *
   * @param sc Scanner object to get user input
   * @return User choice
   */
  private int present(Scanner sc) {
    int choice = -1; // invalid choice
    while (!(choice > 0 && choice <= options.length)) { // while choice is invalid
      try {
        printConsoleMenu();
        choice = sc.nextInt();
        if (!(choice > 0 && choice <= options.length)) {
          throw new Exception(); // force catch block if choice is not in menu
        }
      } catch (Exception e) {
        System.out.println("\u001B[31mPlease choose a number between 1 and " + options.length + "\u001B[0m");
        sc.nextLine(); // clear buffer
      }
    }
    return choice;
  }

  /**
   * Print the menu to the console
   */
  private void printConsoleMenu() {
    System.out.println("\u001B[32m" + title + "\u001B[0m");
    for (int i = 0; i < options.length; i++) {
      System.out.println((i + 1) + ". " + options[i]);
    }
    System.out.print("Enter choice: ");
  }
}