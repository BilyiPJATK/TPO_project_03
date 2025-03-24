package com.example.tpo_project_03;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Controller
public class FlashcardsController {
    private final EntryService entryService;
    private final DisplayProfile displayProfile;

    public FlashcardsController(EntryService entryService, DisplayProfile displayProfile) {
        this.displayProfile = displayProfile;
        this.entryService = entryService;
    }

    public void addEntry(String polish, String german, String english) {
        Entry entry = new Entry(polish, german, english);
        entryService.addEntry(entry);
        System.out.println("Entry added: " + entry);
    }

    public void displayEntries() {
        List<Entry> entries = entryService.getAllEntries();
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
            return;
        }
        for (Entry entry : entries){
            displayProfile.display(entry);
        }
    }
    public void searchEntries(String term) {
        List<Entry> entries = entryService.searchEntries(term);
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
        } else {
            for (Entry entry : entries) {
                System.out.println(entry);
            }
        }
    }

    public void sortEntries(String language, String order) {
        List<Entry> sortedEntries = entryService.sortEntries(language, order);
        if (sortedEntries.isEmpty()) {
            System.out.println("No entries to display.");
        } else {
            for (Entry entry : sortedEntries) {
                System.out.println(entry);
            }
        }
    }

    public void deleteEntry(long id) {
        entryService.deleteEntry(id);
        System.out.println("Entry with ID " + id + " has been deleted.");
    }

    public void modifyEntry(long id, String polish, String german, String english) {
        entryService.updateEntry(id, polish, german, english);
        System.out.println("Entry with ID " + id + " has been modified.");
    }

    public void startTest() {
        Scanner scanner = new Scanner(System.in);

        List<Entry> words = entryService.getAllEntries();
        if (words.isEmpty()) {
            System.out.println("No words available!");
            return;
        }

        Entry randomEntry = words.get(new Random().nextInt(words.size()));
        System.out.println("Translate: " + randomEntry.getEnglish());

        System.out.print("Polish: ");
        String userPolish = scanner.nextLine().trim().toLowerCase();

        System.out.print("German: ");
        String userGerman = scanner.nextLine().trim().toLowerCase();

        if (userPolish.equals(randomEntry.getPolish().toLowerCase()))
            System.out.println("Correct polish translation");
        else
            System.out.println("Incorrect polish, correct: " + randomEntry.getPolish());

        if (userGerman.equals(randomEntry.getGerman().toLowerCase()))
            System.out.println("Correct german translation!");
        else
            System.out.println("Incorrect german, correct: " + randomEntry.getGerman());
    }

    public void runApplicationConsole() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Entry\n2. Display Entries\n3. Start Test\n4. Search Entries\n5. Sort Entries\n6. Delete Entry\n7. Modify Entry\n8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Polish word: ");
                    String polish = scanner.nextLine().trim();

                    System.out.print("Enter German word: ");
                    String german = scanner.nextLine().trim();

                    System.out.print("Enter English word: ");
                    String english = scanner.nextLine().trim();

                    addEntry(polish, german, english);
                    break;
                case 2:
                    displayEntries();
                    break;
                case 3:
                    startTest();
                    break;
                case 4:
                    System.out.print("Enter search word: ");
                    String searchTerm = scanner.nextLine().trim();
                    searchEntries(searchTerm);
                    break;
                case 5:
                    System.out.print("Enter language to sort by (polish/german/english): ");
                    String language = scanner.nextLine().trim();

                    System.out.print("Enter sorting order (asc/desc): ");
                    String order = scanner.nextLine().trim();

                    sortEntries(language, order);
                    break;
                case 6:
                    System.out.print("Enter the ID of the entry to delete: ");
                    long deleteId = scanner.nextLong();
                    scanner.nextLine();
                    deleteEntry(deleteId);
                    break;
                case 7:
                    System.out.print("Enter the ID of the entry to modify: ");
                    long modifyId = scanner.nextLong();
                    scanner.nextLine();

                    System.out.print("Enter new Polish word: ");
                    String newPolish = scanner.nextLine().trim();

                    System.out.print("Enter new German word: ");
                    String newGerman = scanner.nextLine().trim();

                    System.out.print("Enter new English word: ");
                    String newEnglish = scanner.nextLine().trim();

                    modifyEntry(modifyId, newPolish, newGerman, newEnglish);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}
