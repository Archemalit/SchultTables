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
    public static String[] menu_choices = new String[] { "1) Start playing", "2) Change the size of the table", "3) Select the order of characters", "4) Select the type of table", "5) Additional settings", "6) Change the language"};
    public static String[] menu_choices_two = new String[] {"1) Yes", "2) No"};
    public static String[] menu_choices_three = new String[] { "1) Straight", "2) Reverse" };

    public static String[] menu_choices_four = new String[] {"1) Numbers", "2) Letters (in this mode, a table with a size of only 5 by 5 is available)"};

    public static boolean valueInCellsNumbers = true;
    public static int tableSize = 25;
    private static Table t = new Table();

    public static int interChecker(int count_of_choices) {
        int choice = 0;
        System.out.print("\nTo select, write the number of actions: ");
        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice > count_of_choices | choice <= 0) {
                    throw new OutCountOfChoices("Something happened");
                }
                return choice;
            }
            catch (InputMismatchException e) {
                System.out.print(ANSI_RED_BACKGROUND + "You entered something wrong!" + ANSI_RESET + " Enter the number again: ");
                scanner.next();
            }
            catch (OutCountOfChoices e2) {
                choice = 0;
                System.out.print(ANSI_RED_BACKGROUND + "There is no such action. You may have entered the wrong number." + ANSI_RESET + " Enter the number again: ");
            }
        }
    }

    // public static void drawTable(List<Integer> data_numbers, int count) {
    //     System.out.println();
    //     for (int i = 0; i < count; i++) {
    //         System.out.format("%8d", data_numbers.get(i));
    //         if ((i + 1) % (int) Math.pow(count, 0.5) == 0) {
    //             System.out.println();
    //         }
    //     }
    // }
    public static void OneStartGameMenu(){
        if(valueInCellsNumbers){
            OneStartGame();
        }else if(!valueInCellsNumbers){
            // OneStartGameSymbols();
        }
    }
    public static void OneStartGame() {
        int size = tableSize;
        t.setTable(size);
        t.play(size);

        System.out.println("Congratulations, you have passed the table for ...\n");
        ChoosingAnAction();

//        System.out.println(data_numbers);
//        System.out.println(data_numbers_ids);
    }

    public static void ThreeChangeCharacterOrder() {
        System.out.println("Modes:");
        for (int i = 0; i < menu_choices_three.length; i++) {
            System.out.println(menu_choices_three[i]);
        }
        if (interChecker(menu_choices_three.length) == 1) {
            t.setOrder(true);
        } else {
            t.setOrder(false);
        }

        System.out.println(ANSI_GREEN_BACKGROUND + "Saved changes." + ANSI_RESET + '\n');
    }

    public static void TwoShufleTable() {
        System.out.println("Do you want to shuffle the table after selecting the correct cell?");
        for (int i = 0; i < menu_choices_two.length; i++) {
            System.out.println(menu_choices_two[i]);
        }
        if (interChecker(menu_choices_two.length) == 1) {
            t.setShufle(true);
        } else {
            t.setShufle(false);
        }
        System.out.println(ANSI_GREEN_BACKGROUND + "Saved changes." + ANSI_RESET + '\n');
    }

    public static void FourChangeValueInCells() {
        System.out.println("Modes:");
        for (int i = 0; i < menu_choices_four.length; i++) {
            System.out.println(menu_choices_four[i]);
        }
        if (interChecker(menu_choices_four.length) == 1) {
            valueInCellsNumbers = true;
        } else {
            valueInCellsNumbers = false;
        }
        System.out.println(ANSI_GREEN_BACKGROUND + "Saved changes." + ANSI_RESET + '\n');
    }

    public static void TwoSizeTable(){
        System.out.println("Select the table size:");
        System.out.println("1) 3*3\n" + "2) 4*4\n" + "3) 5*5");
        scanner.nextLine();
        String size = scanner.nextLine();
        if(Integer.parseInt(size) == 1){
            tableSize = 9;
        }else if(Integer.parseInt(size) == 2){
            tableSize = 16;
        }else if(Integer.parseInt(size) == 3){
            tableSize = 25;
        }else{
            System.out.println("Incorrect input!");
            TwoSizeTable();
        }
        System.out.println(ANSI_GREEN_BACKGROUND + "Saved changes." + ANSI_RESET + '\n');
    }

    public static void ChoosingAnAction() {
    for (int i = 0; i < menu_choices.length; i++) {
        System.out.println(menu_choices[i]);
    }
    int choice = interChecker(menu_choices.length);
    if (choice == 1) {
            OneStartGameMenu();
    } else {
        if (choice == 2) {
            TwoSizeTable();
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
        System.out.println("Hello, welcome to the simulator \"Schulte Table\".\n");
        ChoosingAnAction();
    }
}