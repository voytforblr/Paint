package sample.transport;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.ControllerOfCar;
import sample.Main;

import java.io.IOException;
import java.io.Serializable;

public class Car extends Transport {
   // private static final long serialVersionUID=1L;

    public Car() {
    }


    public Car(double power, double maxSpeed, int numOfSeats) {
        super(power, maxSpeed, numOfSeats);
    }

    @Override
    public void addToList() {
        FXMLLoader path=new FXMLLoader();
        Parent root = null;
        try {
            root = path.load(getClass().getResource("/sample/Forms/car.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.stage.setScene(new Scene(root));
    }

    @Override
    public void changeInList(Event event) {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Forms/car.fxml"));
        Parent parent= null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene=new Scene(parent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

        ControllerOfCar controller=loader.getController();
        controller.setPower(String.valueOf(this.getPower()));
        controller.setNumOfSeats(String.valueOf(this.getNumOfSeats()));
        controller.setMaxSpeed(String.valueOf(this.getMaxSpeed()));
        controller.setCar(this);
    }

}
