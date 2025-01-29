package aed.dao;

import aed.model.CancionReproducida;
import org.hibernate.Session;
import org.hibernate.Transaction;
import aed.util.DatabaseManager;

import java.util.Date;
import java.util.List;

public class CancionReproducidaDAO {
    public void saveSongRecord(CancionReproducida songRecord) {
        Transaction transaction = null;
        try (Session session = DatabaseManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(songRecord);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<CancionReproducida> getSongRecordsByDate(Date date) {
        try (Session session = DatabaseManager.getSessionFactory().openSession()) {
            return session.createQuery("from CancionReproducida where date = :date", CancionReproducida.class)
                    .setParameter("date", date)
                    .list();
        }
    }

    public List<Object[]> getSongRecordsOrderedByCount() {
        try (Session session = DatabaseManager.getSessionFactory().openSession()) {
            return session.createQuery("select sr.song, count(sr) as cnt from CancionReproducida sr group by sr.song order by cnt desc", Object[].class)
                    .list();
        }
    }

    // Other CRUD methods (update, delete)
}