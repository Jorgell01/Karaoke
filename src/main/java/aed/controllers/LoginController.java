package aed.controllers;

import aed.dao.UserDAO;
import aed.model.User;
import aed.util.DialogUtil;
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
            DialogUtil.showErrorDialog("Login Error", "Invalid Credentials", "Please enter a valid username and password.");
        }
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            DialogUtil.showWarningDialog("Registration Error", "Incomplete Fields", "Please enter both username and password.");
            return;
        }

        UserDAO userDAO = new UserDAO();
        User existingUser = userDAO.getUserByUsername(username);

        if (existingUser != null) {
            DialogUtil.showWarningDialog("Registration Error", "User Already Exists", "Please choose a different username.");
        } else {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            userDAO.saveUser(newUser);

            DialogUtil.showInformationDialog("Registration Successful", "User Registered", "You can now log in with your new credentials.");
        }
    }
}