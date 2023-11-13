import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Table{
	private Map<Integer, String> data_numbers_ids = new Hashtable<Integer, String>();
    private List<Integer> data_numbers = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    private static boolean shufle;
    private static boolean order;

    public void setOrder(boolean order){
    	this.order = order;
    }

	public void setTable(int size){
        for (int i = 1; i <= size; i++) {
    		data_numbers.add(i);
		}

		Collections.shuffle(data_numbers);

		for (int i = 0; i < size; i++) {
    		int left = (i / (int) Math.sqrt(size)) + 1;
    		int right = (i % (int) Math.sqrt(size)) + 1;
    		data_numbers_ids.put(data_numbers.get(i), left + " " + right);
    	}
	}

	public void setShufle(boolean shufle){
		this.shufle = shufle;
	}

	public void drawTable(int count){//count = size
		System.out.println();
        for (int i = 0; i < count; i++) {
            System.out.format("%8d", data_numbers.get(i));
            if ((i + 1) % (int) Math.sqrt(count) == 0) {
                System.out.println();
            }
        }
	}

	private void shuffleTable(int size){
		for (int i = 0; i < size; i++ ) {
            data_numbers.add(i + 1);
        }
        Collections.shuffle(data_numbers);
        for (int i = 0; i < size; i++) {
            int left = (i / (int) Math.pow(size, 0.5)) + 1;
            int right = (i % (int) Math.pow(size, 0.5)) + 1;
            data_numbers_ids.put(data_numbers.get(i), left + " " + right);
        }
	}

	public void play(int size){
		String answer;
		int count;
		if(order){
			count = 1;
		}else{
			count = size;
		}
        
        int all_count = 1;
        while (count <= size) {
            while (true) {
            	System.out.println("Find count " + count);
                try {
                    if(this.shufle){
                        shuffleTable(size);
                    }
                    drawTable(size);
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
                                    System.out.println("Right");

                                    break;
                                } else {
                                    System.out.println("Incorrect");
                                }
                            }
                        } catch (notRealFormat e3) {
                            System.out.print(Main.ANSI_RED_BACKGROUND + "You entered something wrong!" + Main.ANSI_RESET + " Enter again (in the format \"row column\"): ");
                        }
                    }
                }  catch (notRealFormat e2) {
                    System.out.print(Main.ANSI_RED_BACKGROUND + "You entered something wrong!" + Main.ANSI_RESET + " Enter again (in the format \"row column\"): ");
                }
            }
            if(order){
				count++;
			}else{
				count--;
			}
        }
	}
}