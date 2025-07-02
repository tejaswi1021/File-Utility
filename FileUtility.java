import java.io.*;
import java.util.Scanner;

/**
 * FileUtility provides basic file operations: create, write, read, and modify text files.
 */
public class FileUtility {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create File");
            System.out.println("2. Write to File");
            System.out.println("3. Read File");
            System.out.println("4. Modify File");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter file path: ");
                        String path = scanner.nextLine();
                        createFile(path);
                    }
                    case 2 -> {
                        System.out.print("Enter file path: ");
                        String path = scanner.nextLine();
                        System.out.print("Enter content to write: ");
                        String content = scanner.nextLine();
                        writeToFile(path, content);
                    }
                    case 3 -> {
                        System.out.print("Enter file path: ");
                        String path = scanner.nextLine();
                        readFile(path);
                    }
                    case 4 -> {
                        System.out.print("Enter file path: ");
                        String path = scanner.nextLine();
                        System.out.print("Enter text to find: ");
                        String oldText = scanner.nextLine();
                        System.out.print("Enter replacement text: ");
                        String newText = scanner.nextLine();
                        modifyFile(path, oldText, newText);
                    }
                    case 5 -> {
                        System.out.println("Exiting program.");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Method to create a file
    public static void createFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getAbsolutePath());
        } else {
            System.out.println("File already exists.");
        }
    }

    // Method to write content to a file
    public static void writeToFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, true)) { // Append mode
            writer.write(content + System.lineSeparator());
            System.out.println("Content written to file.");
        }
    }

    // Method to read content from a file
    public static void readFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("File contents:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    // Method to modify content in a file
    public static void modifyFile(String filePath, String oldText, String newText) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                contentBuilder.append(currentLine.replace(oldText, newText)).append(System.lineSeparator());
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(contentBuilder.toString());
        }

        System.out.println("File modified successfully.");
    }
}
