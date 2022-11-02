package sample.transport;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.ControllerOfBus;
import sample.Main;

import java.io.IOException;
import java.io.Serializable;

public class Bus extends Car  {
    String timetable;

    public Bus() {
    }

    public Bus(String timetable, double power, double maxSpeed, int numOfSeats) {
        super(power, maxSpeed, numOfSeats);
        this.timetable = timetable;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    @Override
    public void addToList() {
        FXMLLoader path=new FXMLLoader();
        Parent root = null;
        try {
            root = path.load(getClass().getResource("/sample/Forms/bus.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.stage.setScene(new Scene(root));
    }
    @Override
    public void changeInList(Event event) {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Forms/bus.fxml"));
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
        ControllerOfBus controller=loader.getController();
        controller.setPower(String.valueOf(this.getPower()));
        controller.setNumOfSeats(String.valueOf(this.getNumOfSeats()));
        controller.setMaxSpeed(String.valueOf(this.getMaxSpeed()));
        controller.setTimetable(this.getTimetable());
        controller.setBus(this);
    }
}
