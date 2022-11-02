package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    public static Group group=new Group();
    public static Scene scene=new Scene(group,675,482);

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Hello World");
        FXMLLoader loaderOfStartMenu= new FXMLLoader();
        loaderOfStartMenu.setLocation(getClass().getResource("sample.fxml"));
        Parent tempRoot=loaderOfStartMenu.load();
        primaryStage.setResizable(false);
        group.getChildren().add(tempRoot);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
