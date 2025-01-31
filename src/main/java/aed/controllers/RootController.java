package aed.controllers;

import aed.app.KaraokeApp;
import aed.dao.CancionDAO;
import aed.dao.UserDAO;
import aed.model.Cancion;
import aed.model.User;
import aed.util.DialogUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

public class RootController {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Cancion> songTable;
    @FXML
    private TableColumn<Cancion, String> titleColumn;
    @FXML
    private TableColumn<Cancion, String> artistColumn;
    @FXML
    private TableColumn<Cancion, Integer> countColumn;
    @FXML
    private TableColumn<Cancion, Date> lastPlayedColumn;
    @FXML
    private Label usernameLabel;

    private ObservableList<Cancion> songData = FXCollections.observableArrayList();
    private Stage primaryStage;
    private User currentUser;

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        lastPlayedColumn.setCellValueFactory(new PropertyValueFactory<>("lastPlayed"));

        loadSongData();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        usernameLabel.setText("Connected as: " + user.getUsername());
        loadSongData();
    }

    private void loadSongData() {
        CancionDAO cancionDAO = new CancionDAO();
        songData.setAll(cancionDAO.getAllSongsByUser(currentUser).stream()
                .sorted((s1, s2) -> Integer.compare(s2.getCount(), s1.getCount()))
                .collect(Collectors.toList()));
        songTable.setItems(songData);
    }

    @FXML
    private void handleAddSong() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Song");
        dialog.setHeaderText("Enter song details");

        dialog.setContentText("Title:");
        Optional<String> title = dialog.showAndWait();
        if (!title.isPresent()) return;

        dialog.setContentText("Artist:");
        Optional<String> artist = dialog.showAndWait();
        if (!artist.isPresent()) return;

        dialog.setContentText("Username:");
        Optional<String> username = dialog.showAndWait();
        if (!username.isPresent()) return;

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username.get());
        if (user == null) {
            DialogUtil.showErrorDialog("Error", "User not found", "The specified user does not exist.");
            return;
        }

        Cancion newSong = new Cancion();
        newSong.setTitle(title.get());
        newSong.setArtist(artist.get());
        newSong.setCount(0);
        newSong.setUser(user);

        CancionDAO cancionDAO = new CancionDAO();
        cancionDAO.saveSong(newSong);

        loadSongData();
    }

    @FXML
    private void handleDeleteSong() {
        Cancion selectedSong = songTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            boolean confirmed = DialogUtil.showConfirmationDialog("Delete Song", "Are you sure you want to delete this song?", "Title: " + selectedSong.getTitle() + "\nArtist: " + selectedSong.getArtist());
            if (confirmed) {
                CancionDAO cancionDAO = new CancionDAO();
                cancionDAO.deleteSong(selectedSong);

                songData.remove(selectedSong);
            }
        } else {
            DialogUtil.showWarningDialog("No Selection", "No Song Selected", "Please select a song in the table.");
        }
    }

    @FXML
    private void handlePlaySong() {
        Cancion selectedSong = songTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            CancionDAO cancionDAO = new CancionDAO();
            cancionDAO.playSong(selectedSong);
            loadSongData();
        } else {
            DialogUtil.showWarningDialog("No Selection", "No Song Selected", "Please select a song in the table.");
        }
    }

    @FXML
    private void handleLogout() {
        try {
            KaraokeApp app = new KaraokeApp();
            app.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getRoot() {
        return root;
    }
}