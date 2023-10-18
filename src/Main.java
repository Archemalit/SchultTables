import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class OutCountOfChoices extends Exception
{
    public OutCountOfChoices(final String message) {
        super(message);
    }
}

class notRealFormat extends Exception
{
    public notRealFormat(final String message) {
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

    public static void drawTable(List<Integer> data_numbers, int count) {
        System.out.println();
        for (int i = 0; i < count; i++) {
            System.out.format("%8d", data_numbers.get(i));
            if ((i + 1) % (int) Math.pow(count, 0.5) == 0) {
                System.out.println();
            }
        }
    }
    public static void OneStartGame() {
        Dictionary data_numbers_ids = new Hashtable();
        List<Integer> data_numbers = new ArrayList<>();
        for (int i = 0; i < 25; i++ ) {
            data_numbers.add(i + 1);
        }
        Collections.shuffle(data_numbers);
        for (int i = 0; i < 25; i++) {
            int left = (i / (int) Math.pow(25, 0.5)) + 1;
            int right = (i % (int) Math.pow(25, 0.5)) + 1;
            data_numbers_ids.put(data_numbers.get(i), left + " " + right);
        }

        drawTable(data_numbers, 25);
        String answer;
        String a = scanner.nextLine();

        int count = 1;
        int all_count = 1;
        while (count <= 25) {
            while (true) {
                try {
                    if (all_count % 10 == 0) {
                        drawTable(data_numbers, 25);
                    }
                    all_count += 1;
                    answer = scanner.nextLine();
                    Pattern p = Pattern.compile("[1-9]+ [1-9]+");
                    Matcher m = p.matcher(answer);
                    if (!m.matches()) {
                        throw new notRealFormat("Something happened");
                    } else {
                        try {
                            String [] coords = answer.split(" ");
                            int row = Integer.parseInt(coords[0]);
                            int column = Integer.parseInt(coords[1]);
                            if (row > 5 | column > 5) {
                                throw new notRealFormat("Something happened");
                            } else {
                                if (Objects.equals(data_numbers_ids.get(count).toString(), answer)) {
                                    System.out.println("Верно");
                                    break;
                                } else {
                                    System.out.println("Неверно");
                                }
                            }
                        } catch (notRealFormat e3) {
                            System.out.print(ANSI_RED_BACKGROUND + "Вы ввели что-то не то!" + ANSI_RESET + " Введите ещё раз (в формате \"строка столбец\"): ");
                        }
                    }
                }  catch (notRealFormat e2) {
                    System.out.print(ANSI_RED_BACKGROUND + "Вы ввели что-то не то!" + ANSI_RESET + " Введите ещё раз (в формате \"строка столбец\"): ");
                }
            }
            count++;
        }

        System.out.println("Поздравляю,вы прошли таблицу за ...\n");
        ChoosingAnAction();

//        System.out.println(data_numbers);
//        System.out.println(data_numbers_ids);
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