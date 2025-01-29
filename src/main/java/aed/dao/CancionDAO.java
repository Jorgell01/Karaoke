package aed.dao;

import aed.model.Cancion;
import aed.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import aed.util.DatabaseManager;

import java.util.List;

public class CancionDAO {
    public void saveSong(Cancion song) {
        Transaction transaction = null;
        try (Session session = DatabaseManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(song);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Cancion> getAllSongs() {
        try (Session session = DatabaseManager.getSessionFactory().openSession()) {
            return session.createQuery("from Cancion", Cancion.class).list();
        }
    }

    public List<Cancion> getSongsByUser(User user) {
        try (Session session = DatabaseManager.getSessionFactory().openSession()) {
            return session.createQuery("from Cancion where user = :user", Cancion.class)
                    .setParameter("user", user)
                    .list();
        }
    }

    public void deleteSong(Cancion song) {
        Transaction transaction = null;
        try (Session session = DatabaseManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(song);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}