package com.example.tpo_project_03;

import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class EntryService {
    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public void addEntry(Entry entry) {
        entryRepository.addEntry(entry);
    }

    public List<Entry> getAllEntries() {
        return entryRepository.getAllEntries();
    }

    public List<Entry> searchEntries(String term) {
        return entryRepository.searchEntriesByWord(term);
    }

    public void deleteEntry(Long id) {
        entryRepository.deleteEntry(id);
    }

    public void updateEntry(Long id, String polish, String german, String english) {
        Entry entry = entryRepository.getEntryById(id);
        if (entry == null) {
            throw new IllegalArgumentException("Entry with ID " + id + " not found");
        }
        entry.setPolish(polish);
        entry.setGerman(german);
        entry.setEnglish(english);
        entryRepository.updateEntry(entry);
    }

    public List<Entry> sortEntries(String language, String order) {
        List<Entry> entries = entryRepository.getAllEntries();

        boolean ascending = order.equalsIgnoreCase("asc");

        Comparator<Entry> comparator = null;

        if (language.equalsIgnoreCase("polish")) {
            comparator = Comparator.comparing(e -> e.getPolish().toLowerCase());
        } else if (language.equalsIgnoreCase("german")) {
            comparator = Comparator.comparing(e -> e.getGerman().toLowerCase());
        } else if (language.equalsIgnoreCase("english")) {
            comparator = Comparator.comparing(e -> e.getEnglish().toLowerCase());
        } else {
            throw new IllegalArgumentException("Invalid language: " + language);
        }

        // If descending order is needed, reverse the comparator
        if (!ascending) {
            comparator = comparator.reversed();
        }

        // Sort the entries using the comparator
        entries.sort(comparator);

        return entries;
    }

}
