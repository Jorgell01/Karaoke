package aed.app;

import aed.controllers.LoginController;
import aed.controllers.RootController;
import aed.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class KaraokeApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
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

        Scene scene = new Scene(loader.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Karaoke");
        primaryStage.show();
    }

    private User showLoginDialog(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(KaraokeApp.class.getResource("/fxml/LoginView.fxml"));
        Stage dialogStage = new Stage();
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