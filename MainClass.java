import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		File file = new File( args[0] );
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;

		Table addressBook = new Table();
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			addressBook.insert(line, br.readLine());
		}
		Scanner scn = new Scanner(System.in);
		while (true) {
			System.out.println("1. Add a contact (a)");
			System.out.println("2. Look up a contact (l)");
			System.out.println("3. Update address (u)");
			System.out.println("4. Delete a contact (d)");
			System.out.println("5. Display all contacts (ac)");
			System.out.println("6. Save and exit (q)");

			String key = "";
			String value = "";
			System.out.print("ENTER CHOICE: ");
			String input = scn.nextLine();
			int num = 0;
			try {
				num = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid input");
			}
			switch (num) {
			case 1:
				System.out.print("Name: ");
				key = scn.nextLine();
				String isPresent = addressBook.lookUp(key);
				if ("" == isPresent) {
					System.out.print("Address: ");
					value = scn.nextLine();
					addressBook.insert(key, value);
				} else {
					System.out.println(key + " is already present in the book");
				}

				System.out.println();
				break;
			case 2:
				System.out.print("Name: ");
				key = scn.nextLine();
				String val = addressBook.lookUp(key);
				if ("" == val) {
					System.out.println(key + " is not in the book \n");
				} else {
					System.out.println("Address is " + val + "\n");
				}
				break;
			case 3:
				System.out.print("Name: ");
				key = scn.nextLine();
				if (addressBook.lookUp(key) == "") {
					System.out.print(key + " is not in the book \n");
				} else {
					System.out.print("Address: ");
					value = scn.nextLine();
					if (addressBook.update(key, value)) {
						System.out.print("Address is successfully updated \n");
					} else {
						System.out.print("Address is not updated Successfully \n");
					}

				}
				System.out.println();
				break;
			case 4:
				System.out.print("Name: ");
				key = scn.nextLine();
				boolean isFound = addressBook.deleteContact(key);
				if (!isFound) {
					System.out.println(key + " is not in the book");
				}
				System.out.println();
				break;
			case 5:
				System.out.println();
				addressBook.displayAll();
				System.out.println();
				break;
			case 6:
				addressBook.save();
				System.exit(0);
				break;
			default:
				System.out.println("Not a valid input.");
				System.out.println();
				break;
			}
		}
	}
}
