package aed.controllers;

import aed.dao.UserDAO;
import aed.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private Stage dialogStage;
    private boolean loginSuccessful = false;
    private User loggedInUser;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            loginSuccessful = true;
            loggedInUser = user;
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid Credentials");
            alert.setContentText("Please enter a valid username and password.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Registration Error");
            alert.setHeaderText("Incomplete Fields");
            alert.setContentText("Please enter both username and password.");
            alert.showAndWait();
            return;
        }

        UserDAO userDAO = new UserDAO();
        User existingUser = userDAO.getUserByUsername(username);

        if (existingUser != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Registration Error");
            alert.setHeaderText("User Already Exists");
            alert.setContentText("Please choose a different username.");
            alert.showAndWait();
        } else {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            userDAO.saveUser(newUser);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText("User Registered");
            alert.setContentText("You can now log in with your new credentials.");
            alert.showAndWait();
        }
    }
}