package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.figures.Figures;
import sample.figures.ellipse.MyCircle;
import sample.figures.ellipse.MyEllipse;
import sample.figures.polyline.MyLine;
import sample.figures.polyline.MyQuad;
import sample.figures.polyline.MyTriangle;
import sample.figures.rectangle.MyRectangle;
import java.io.IOException;

public class Controller {

    @FXML
    private Button ButtonLine;

    @FXML
    private Button ButtonEllipse;

    @FXML
    private Button ButtonCircle;

    @FXML
    private Button ButtonRectangle;

    @FXML
    private Button ButtonQuad;

    @FXML
    private Button ButtonTriangle;

    Figures figure=new MyLine();

    @FXML
    void initialize() {

        ButtonLine.setOnAction(actionEvent -> {

            figure=new MyLine();

        });

        ButtonTriangle.setOnAction(actionEvent -> {

            figure=new MyTriangle();

        });

        ButtonQuad.setOnAction(actionEvent -> {

            figure=new MyQuad();

        });

        ButtonRectangle.setOnAction(actionEvent -> {

            figure=new MyRectangle();

        });

        ButtonEllipse.setOnAction(actionEvent -> {

            figure=new MyEllipse();

        });

        ButtonCircle.setOnAction(actionEvent -> {

            figure=new MyCircle();

        });

        Main.scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                figure.clickCoordinates((int)mouseEvent.getSceneX(),(int)mouseEvent.getSceneY());
            }
        });

    }

}