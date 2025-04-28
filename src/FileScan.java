import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileScan {
    public static void main(String[] args) {
        // Check if user provided filename in command line
        if (args.length > 0) {
            scanFile(args[0]);
        } else {
            // No filename provided, use file chooser
            openFileChooser();
        }
    }

    // Count lines, words and chars in file
    private static void scanFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            // Initialize counters
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;
            ArrayList<String> lines = new ArrayList<>();

            // Read file line by line
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lines.add(line);
                lineCount++;
                charCount += line.length();

                // Count words in current line
                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNext()) {
                    lineScanner.next();
                    wordCount++;
                }
                lineScanner.close();
            }
            fileScanner.close();

            // Print results to console
            printResults(file.getName(), lineCount, wordCount, charCount);

        } catch (FileNotFoundException e) {
            System.out.println("Error: Couldn't find file " + filePath);
        }
    }

    // Let user pick file with GUI
    private static void openFileChooser() {
        JFileChooser chooser = new JFileChooser();
        // Start in src folder
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/src"));

        try {
            int choice = chooser.showOpenDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                scanFile(selectedFile.getPath());
            } else {
                System.out.println("No file selected.");
            }
        } catch (Exception e) {
            System.out.println("Error opening file chooser");
        }
    }

    // Display the counting results
    private static void printResults(String filename, int lines, int words, int chars) {
        System.out.println("\n----- File Report -----");
        System.out.println("Filename: " + filename);
        System.out.println("Lines: " + lines);
        System.out.println("Words: " + words);
        System.out.println("Characters: " + chars);
    }
}