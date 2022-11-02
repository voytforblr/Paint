package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


public class Main extends Application {
    public static Stage stage;
    public static Scene start;
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loaderOFStartMenu = new FXMLLoader();
        loaderOFStartMenu.setLocation(getClass().getResource("Forms/sample.fxml"));
        Parent root = loaderOFStartMenu.load();
        Scene startMenuScene = new Scene(root);
       Main.stage=primaryStage;
       Main.start=startMenuScene;
        primaryStage.setResizable(false);
        primaryStage.setScene(startMenuScene);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }
    public static void showErrorWindow(String str1,String str2) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(str1);
        alert.setContentText(str2);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(new ButtonType("Понятно", ButtonBar.ButtonData.CANCEL_CLOSE));
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
