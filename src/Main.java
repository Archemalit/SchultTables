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
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static Scanner scanner = new Scanner(System.in);
    public static String[] menu_choices = new String[] { "1) Начать играть", "2) Изменить размер таблицы", "3) Выбрать порядок символов", "4) Выбрать тип таблицы", "5) Дополнительные настройки", "6) Изменить язык"};
    public static String[] menu_choices_two = new String[] {"1) Да", "2) Нет"};
    public static String[] menu_choices_three = new String[] { "1) Прямой", "2) Обратный" };

    public static String[] menu_choices_four = new String[] {"1) Числа", "2) Буквы (в этом режиме доступна таблица размером только 5 на 5)"};

    public static boolean characterOrderStraight = true;
    public static boolean shufle = false;
    public static boolean valueInCellsNumbers = true;

    public static int interChecker(int count_of_choices) {
        int choice = 0;
        System.out.print("\nДля выбора напишите число действия: ");
        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice > count_of_choices | choice <= 0) {
                    throw new OutCountOfChoices("Something happened");
                }
                return choice;
            }
            catch (InputMismatchException e) {
                System.out.print(ANSI_RED_BACKGROUND + "Вы ввели что-то не то!" + ANSI_RESET + " Введите число ещё раз: ");
                scanner.next();
            }
            catch (OutCountOfChoices e2) {
                choice = 0;
                System.out.print(ANSI_RED_BACKGROUND + "Такого действия не существует. Возможно, вы ввели не то число." + ANSI_RESET + " Введите число ещё раз: ");
            }
        }
    }

    public static void OneStartGame() {
        System.out.println("Вы выбрали первый пункт.");
    }

    public static void ThreeChangeCharacterOrder() {
        System.out.println("Режимы:");
        for (int i = 0; i < menu_choices_three.length; i++) {
            System.out.println(menu_choices_three[i]);
        }
        if (interChecker(menu_choices_three.length) == 1) {
            characterOrderStraight = true;
        } else {
            characterOrderStraight = false;
        }

        System.out.println(ANSI_GREEN_BACKGROUND + "Сохранили изменения." + ANSI_RESET + '\n');
    }

    public static void TwoShufleTable() {
        System.out.println("Хотите ли вы перемешивать таблицу после правильного выбора ячейки?");
        for (int i = 0; i < menu_choices_two.length; i++) {
            System.out.println(menu_choices_two[i]);
        }
        if (interChecker(menu_choices_two.length) == 1) {
            shufle = true;
        } else {
            shufle = false;
        }
        System.out.println(ANSI_GREEN_BACKGROUND + "Сохранили изменения." + ANSI_RESET + '\n');
    }

    public static void FourChangeValueInCells() {
        System.out.println("Режимы:");
        for (int i = 0; i < menu_choices_four.length; i++) {
            System.out.println(menu_choices_four[i]);
        }
        if (interChecker(menu_choices_four.length) == 1) {
            valueInCellsNumbers = true;
        } else {
            valueInCellsNumbers = false;
        }
        System.out.println(ANSI_GREEN_BACKGROUND + "Сохранили изменения." + ANSI_RESET + '\n');
    }

    public static void ChoosingAnAction() {
    for (int i = 0; i < menu_choices.length; i++) {
        System.out.println(menu_choices[i]);
    }
    int choice = interChecker(menu_choices.length);
    if (choice == 1) {
        OneStartGame();
    } else {
        if (choice == 2) {

        } else if (choice == 3) {
            ThreeChangeCharacterOrder();
        } else if (choice == 4) {
            FourChangeValueInCells();
        } else if (choice == 5) {
            TwoShufleTable();
        }
        ChoosingAnAction();
    }
}

    public static void main(String[] args) {
        System.out.println("Привет, добро пожаловать в тренажёр \"Таблицы Шульте\".\n");
        ChoosingAnAction();
    }
}