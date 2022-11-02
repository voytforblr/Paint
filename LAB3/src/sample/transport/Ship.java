package sample.transport;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.ControllerOfShip;
import sample.Main;

import java.io.IOException;
import java.io.Serializable;

public class Ship extends Transport implements Serializable  {
    //private static final long serialVersionUID=1L;
    int numberOfCrew;
    Captain captain;
    public Ship() {
    }

    public Ship(double power, double maxSpeed, int numOfSeats, int numberOfCrew, Captain captain) {
        super(power, maxSpeed, numOfSeats);
        this.numberOfCrew = numberOfCrew;
        this.captain = captain;
    }

    public int getNumberOfCrew() {
        return numberOfCrew;
    }

    public void setNumberOfCrew(int numberOfCrew) {
        this.numberOfCrew = numberOfCrew;
    }

    public Captain getCaptain() {
        return captain;
    }

    public void setCaptain(Captain captain) {
        this.captain = captain;
    }

    public void creatCaptain(String name, int age, int yearsOfExperience) {
        captain=new Captain(name, age, yearsOfExperience);
    }
    @Override
    public void addToList() {
        FXMLLoader path=new FXMLLoader();
        Parent root = null;
        try {
            root = path.load(getClass().getResource("/sample/Forms/ship.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.stage.setScene(new Scene(root));
    }
    @Override
    public void changeInList(Event event) {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Forms/ship.fxml"));
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
        ControllerOfShip controller=loader.getController();
        controller.setPower(String.valueOf(this.getPower()));
        controller.setNumOfSeats(String.valueOf(this.getNumOfSeats()));
        controller.setMaxSpeed(String.valueOf(this.getMaxSpeed()));
        controller.setNumberOfCrew(String.valueOf(this.getNumberOfCrew()));
        controller.setName(this.getCaptain().getName());
        controller.setAge(String.valueOf(this.getCaptain().getAge()));
        controller.setYearsOfExperience(String.valueOf(this.getCaptain().getYearsOfExperience()));
        controller.setShip(this);
    }


}
