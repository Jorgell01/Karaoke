package aed.app;

import aed.util.DatabaseInitializer;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        // Initialize the database
        DatabaseInitializer.initializeDatabase("root", "");

        // Launch the JavaFX application
        Application.launch(KaraokeApp.class, args);
    }
}