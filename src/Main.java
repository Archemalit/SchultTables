import java.util.InputMismatchException;
import java.util.Scanner;

class OutCountOfChoices extends Exception
{
    public OutCountOfChoices(final String message) {
        super(message);
    }
}

public class Main
{
    public static Scanner scanner = new Scanner(System.in);;
    public static String[] menu_choices = new String[] { "1) Начать играть", "2) Изменить размер таблицы", "3) Выбрать порядок символов", "4) Выбрать тип таблицы", "5) Изменить язык"};;
    public static String[] menu_choices_three = new String[] { "1) Прямой", "2) Обратный" };;

    public static int interChecker(int count_of_choices) {
        int choice = 0;
        System.out.print("\nДля выбора напишите число действия: ");
        while (true) {
            try {
                choice = Main.scanner.nextInt();
                if (choice > count_of_choices | choice <= 0) {
                    throw new OutCountOfChoices("Something happened");
                }
                return choice;
            }
            catch (InputMismatchException e) {
                System.out.print("Вы ввели что-то не то! Введите число ещё раз: ");
                scanner.next();
            }
            catch (OutCountOfChoices e2) {
                choice = 0;
                System.out.print("Такого действия не существует. Возможно, вы ввели не то число. Введите число ещё раз: ");
            }
        }
    }

    public static int first() {
        System.out.println("Вы выбрали первый пункт.");
        return 1;
    }

    public static int ThreeChangeCharacterOrder() {
        System.out.println("Режимы:");
        for (int i = 0; i < menu_choices_three.length; ++i) {
            System.out.println(menu_choices_three[i]);
        }
        int choice = interChecker(Main.menu_choices_three.length);
        return choice;
    }

    public static void ChoosingAnAction() {
    for (int i = 0; i < Main.menu_choices.length; ++i) {
        System.out.println(Main.menu_choices[i]);
    }
    final int choice = interChecker(Main.menu_choices.length);
    if (choice == 1) {
        first();
    }
    else if (choice == 3) {
        ThreeChangeCharacterOrder();
    }
}

    public static void main(final String[] args) {
        System.out.println("Привет, добро пожаловать в тренажёр \"Таблицы Шульте\".\n");
        ChoosingAnAction();
    }
}