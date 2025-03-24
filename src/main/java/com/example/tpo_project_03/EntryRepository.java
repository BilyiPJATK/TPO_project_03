package com.example.tpo_project_03;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntryRepository {
    private final EntityManager entityManager;

    public EntryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addEntry(Entry entry) {
        entityManager.persist(entry);
    }

    public Entry getEntryById(Long id) {
        return entityManager.find(Entry.class, id);
    }

    @Transactional
    public void updateEntry(Entry entry) {
        entityManager.merge(entry);
    }

    @Transactional
    public void deleteEntry(Long id) {
        Entry entry = getEntryById(id);
        if (entry != null) {
            entityManager.remove(entry);
        }
    }

    public List<Entry> getAllEntries() {
        return entityManager.createQuery("SELECT e FROM Entry e", Entry.class).getResultList();
    }

    public List<Entry> searchEntriesByWord(String word) {
        return entityManager.createQuery(
                        "SELECT e FROM Entry e WHERE e.polish LIKE :word OR e.german LIKE :word OR e.english LIKE :word",
                        Entry.class)
                .setParameter("word", "%" + word + "%")
                .getResultList();
    }
}
