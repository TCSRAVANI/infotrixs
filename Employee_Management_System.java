 import java.io.*;  
 import java.util.*;   

 public class Employee_Management_System{

	public static void menu(){
        System.out.println("\n** OPERATION LIST ** \n");
		System.out.println("1.create Employee Details");
		System.out.println("2.Add Employee Details");
		System.out.println("3.Read Employee Details");
		System.out.println("4.Update Employee Details");
		System.out.println("5.Remove Employee Details");
		System.out.println("6.Search Employee Details");
		System.out.println("7.Clear Employee Details");
        System.out.println("\nNOTE : TEXT 'exit' TO EXIT FROM EMPLOYEE MANAGEMENT SYSTEM");
	}

public static void Create(){
    System.out.println("\n*Performing create operation*");
	try {
            // Specify the file path and name
            String filePath = "D:/javaa/EMS.txt";
            
            // Create a File object
            File file = new File(filePath);
            
            // Check if the file already exists
            if (file.exists()) {
                System.out.println("\nFile already exists.");
            } else {
                // Create a new file
                if (file.createNewFile()) {
                    System.out.println("\nFile created successfully.");
                } else {
                    System.out.println("\nFailed to create file.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

 }
public static void Add() {
    System.out.println("\n*Performing add operation*");
    try {
        // Specify the file path
        String filePath = "D:/javaa/EMS.txt";

        // Create a FileReader object to read the existing file
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Create a Set to store the existing IDs
        Set<Integer> existingIds = new HashSet<>();

        // Read the existing IDs from the file and store them in the Set
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("ID: ")) {
                int id = Integer.parseInt(line.substring(4).trim());
                existingIds.add(id);
            }
        }

        // Create a FileWriter object with append mode
        FileWriter fileWriter = new FileWriter(filePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter employee details:");

        // Take input for different fields

        int id;
        while (true) {
            System.out.print("\tID: ");
            id = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading an int

            if (existingIds.contains(id)) {
                System.out.println("\nID already exists. Please enter a unique ID.");
            } else {
                existingIds.add(id);
                break;
            }
        }

        System.out.print("\tName: ");
        String name = scanner.nextLine();

        System.out.print("\tDesignation: ");
        String designation = scanner.nextLine();

        System.out.print("\tGmail ID: ");
        String gmailId = scanner.nextLine();

        // Write the employee details to the file
        printWriter.println("ID: " + id);
        printWriter.println("Name: " + name);
        printWriter.println("Designation: " + designation);
        printWriter.println("Gmail ID: " + gmailId);
        printWriter.println();

        // Close the resources
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
        bufferedReader.close();
        fileReader.close();
       // scanner.close();

        System.out.println("\nEmployee details added to the file successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static void Read(){

    System.out.println("\n*Performing read operation*\n");
	try {
            // Specify the file path
            String filePath = "D:/javaa/EMS.txt";

            // Create a FileReader object to read the file
            FileReader fileReader = new FileReader(filePath);

            // Create a BufferedReader to read text from the FileReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Close the resources
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
 }
public static void Update(){
    System.out.println("\n*Performing update operation*");
	try {
            // Specify the file path
            String filePath = "D:/javaa/EMS.txt";

            // Create a temporary file to store updated records
            String tempFilePath = "D:/javaa/copyEMS.txt";
            File tempFile = new File(tempFilePath);
            tempFile.createNewFile();

            // Create FileReader and BufferedReader to read the file
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Create FileWriter and BufferedWriter to write to the temporary file
            FileWriter fileWriter = new FileWriter(tempFilePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            // Create a Scanner object to read the input from the user
            Scanner scanner = new Scanner(System.in);

            System.out.print("\nEnter the field (Name, ID, Designation, or Gmail ID) to update: ");
            String fieldToUpdate = scanner.nextLine();

            System.out.print("\nEnter the value to search: ");
            String searchValue = scanner.nextLine();

            String line;
            boolean found = false;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(fieldToUpdate + ": " + searchValue)) {
                    // Update the value based on the field
                    if (fieldToUpdate.equalsIgnoreCase("name")) {
                        System.out.print("Enter the updated name: ");
                        String updatedValue = scanner.nextLine();
                        line = "Name: " + updatedValue;
                    } else if (fieldToUpdate.equalsIgnoreCase("ID")) {
                        System.out.print("Enter the updated ID: ");
                        String updatedValue = scanner.nextLine();
                        line = "ID: " + updatedValue;
                    } else if (fieldToUpdate.equalsIgnoreCase("designation")) {
                        System.out.print("Enter the updated designation: ");
                        String updatedValue = scanner.nextLine();
                        line = "Designation: " + updatedValue;
                    } else if (fieldToUpdate.equalsIgnoreCase("Gmail ID")) {
                        System.out.print("Enter the updated Gmail ID: ");
                        String updatedValue = scanner.nextLine();
                        line = "Gmail ID: " + updatedValue;
                    }

                    found = true;
                }

                printWriter.println(line);
            }

            if (!found) {
                System.out.println("\nRecord with " + fieldToUpdate + " " + searchValue + " not found.");
            } else {
                System.out.println("\nRecord updated successfully.");
            }

            // Close the resources
            bufferedReader.close();
            fileReader.close();
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
            //scanner.close();

            // Replace the original file with the temporary file
            File originalFile = new File(filePath);
            originalFile.delete();
            tempFile.renameTo(originalFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

 }
public static void Remove(){
    System.out.println("\n*Performing remove operation*");
	
		try {
            // Specify the file path
            String filePath = "D:/javaa/EMS.txt";

            // Create a temporary file to store records without the removed fields
            String tempFilePath = "D:/javaa/copyEMS.txt";
            File tempFile = new File(tempFilePath);
            tempFile.createNewFile();

            // Create FileReader and BufferedReader to read the file
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Create FileWriter and BufferedWriter to write to the temporary file
            FileWriter fileWriter = new FileWriter(tempFilePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            // Create a Scanner object to read the input from the user
            Scanner scanner = new Scanner(System.in);

            //System.out.print("Enter the field (ID) to remove: ");
            //String fieldToRemove = scanner.nextLine();
			System.out.print("\nEnter the ID to remove : ");
            String searchValue = scanner.nextLine();

            String line;
            boolean found = false;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(searchValue)) {
                    found = true;
                    skipLines(bufferedReader, 3); // Skip the next 3 lines (Name, designation, Gmail ID)
                } else {
                    printWriter.println(line);
                }
            }

            if (!found) {
                System.out.println("\nNo fields with the value " + searchValue + " found.");
            } else {
                System.out.println("\nRecord and associated fields removed successfully.");
            }

            // Close the resources
            bufferedReader.close();
            fileReader.close();
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
            //scanner.close();

            // Replace the original file with the temporary file
            File originalFile = new File(filePath);
            originalFile.delete();
            tempFile.renameTo(originalFile);

            // Display the updated file contents
            System.out.println("\nUpdated file contents:");
            FileReader updatedFileReader = new FileReader(filePath);
            BufferedReader updatedBufferedReader = new BufferedReader(updatedFileReader);
            String updatedLine;
            while ((updatedLine = updatedBufferedReader.readLine()) != null) {
                System.out.println(updatedLine);
            }
            updatedBufferedReader.close();
            updatedFileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     private static void skipLines(BufferedReader reader, int linesToSkip) throws IOException {
        for (int i = 0; i < linesToSkip; i++) {
            reader.readLine(); // Read and discard the line
        }
    }

public static void Search(){
    System.out.println("\n*Performing search operation*");
	try {
            // Specify the file path
            String filePath = "D:/javaa/EMS.txt";

            // Create a FileReader object to read the file
            FileReader fileReader = new FileReader(filePath);

            // Create a BufferedReader to read text from the FileReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Create a Scanner object to read the ID from the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter the ID to search: ");
            int searchId = scanner.nextInt();

            String line;
            boolean found = false;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("ID: " + searchId)) {
                    // Display the data for the matching ID
                    System.out.println(line);
                    System.out.println(bufferedReader.readLine());  // Display the next line
                    System.out.println(bufferedReader.readLine());  // Display the next line
                    System.out.println(bufferedReader.readLine());  // Display the next line
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\nEmployee with ID " + searchId + " not found.");
            }

            // Close the resources
            bufferedReader.close();
            fileReader.close();
            //scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

}
public static void Clear(){
    System.out.println("\n*Performing clear operation*");
	try {
            // Specify the file path
            String filePath = "D:/javaa/EMS.txt";

            // Create a FileWriter object
            FileWriter fileWriter = new FileWriter(filePath);

            // Write empty content to the file
            fileWriter.write("");

            // Close the FileWriter
            fileWriter.close();

            System.out.println("\nFile cleared successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

}
	 public static void main(String []args) {
		
		Scanner sc= new Scanner(System.in);
		System.out.println("\n\n******* WELCOME TO EMPLOYEE MANAGEMENT SYSTEM *******\n");
		
        String key;
		do{
            menu();
		System.out.print("\nSELECT THE OPERATION TO PERFORM : ");
		 key=sc.nextLine();
     
		switch (key) {
			case "1":
				Create();
				break;
			case "2":
				Add();
				break;
			case "3":
				Read();
				break;
			case "4":
				Update();
				break;
			case "5":
				Remove();
				break;
			case "6":
				Search();
				break;
			case "7":
			    Clear();
				break;	
            case "exit":
                    System.out.println("\nExiting the program.\n");
                    break;    					
			default:
			    System.out.println("\nINVALID OPTION! PLEASE SELECT VALID OPTION..\n");
				break;
				
		}
        } while (!key.equalsIgnoreCase("exit"));
		
	}
 }
 
