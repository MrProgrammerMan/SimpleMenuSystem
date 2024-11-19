package Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // This is FUCKING AWESOME
        // Please keep this for later

        Scanner sc = new Scanner(System.in);

        Menu nestedSubMenu = new Menu(sc, "Nested Sub-menu",
            new String[] {
                "Do 3 ^ 2",
                "Do 4 % 5"
            },
            new Runnable[]{
                () -> System.out.println(Math.pow(3, 2)),
                () -> System.out.println(4 % 5)
            }
        );

        Menu subMenu1 = new Menu(sc, "Sub-menu",
            new String[] {
                "Do 3 + 2",
                "Do 4 * 5",
                "Nested sub-menu"
            },
            new Runnable[]{
                () -> System.out.println(3 + 2),
                () -> System.out.println(4 * 5),
                nestedSubMenu
            }
        );

        Menu subMenu2 = new Menu(sc, "Sub-menu",
            new String[] {
                "Do 3 - 2",
                "Do 4 / 5"
            },
            new Runnable[]{
                () -> System.out.println(3 - 2),
                () -> System.out.println(4 / 5)
            }
        );

        Menu mainMenu = new Menu(sc, "Main Menu",
            new String[] {
                "Display word of wisdom",
                "Display weather",
                "Enter sub-menu 1",
                "Enter sub-menu 2"
            },
            new Runnable[] {
                new displayWordOfWisdom(),
                new displayWeather(),
                subMenu1,
                subMenu2
            }
        );

        mainMenu.run();
    }
}