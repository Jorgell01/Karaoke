package aed.app;

import aed.controllers.LoginController;
import aed.controllers.RootController;
import aed.model.User;
import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.NordDark;
import atlantafx.base.theme.NordLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class KaraokeApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());

        User loggedInUser = showLoginDialog(primaryStage);
        if (loggedInUser == null) {
            System.exit(0);
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(KaraokeApp.class.getResource("/fxml/RootView.fxml"));
        loader.load();

        RootController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setCurrentUser(loggedInUser);

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/app-icon.png")));

        primaryStage.getIcons().add(icon);
        Scene scene = new Scene(loader.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Karaoke");
        primaryStage.show();
    }

    private User showLoginDialog(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(KaraokeApp.class.getResource("/fxml/LoginView.fxml"));
        Stage dialogStage = new Stage();

        Image dialogIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/app-icon.png")));

        dialogStage.getIcons().add(dialogIcon);
        dialogStage.setTitle("Login");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setResizable(false);
        dialogStage.setScene(new Scene(loader.load()));

        LoginController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();
        return controller.isLoginSuccessful() ? controller.getLoggedInUser() : null;
    }
}