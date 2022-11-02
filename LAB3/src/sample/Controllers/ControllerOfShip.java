package sample.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Main;
import sample.data_type.TypeOfTransport;
import sample.transport.Ship;

import static sample.Controllers.Controller.typeOfTransports;

public class ControllerOfShip {

    @FXML
    private Button Button_save;

    @FXML
    private TextField TextField_power;

    @FXML
    private TextField TextField_maxSpeed;

    @FXML
    private TextField TextField_numOfSeats;

    @FXML
    private TextField TextField_numberOfCrew;

    @FXML
    private TextField TextField_name;

    @FXML
    private TextField TextField_age;

    @FXML
    private TextField TextField_yearsOfExperience;

    public void setMaxSpeed(String speed){
        TextField_maxSpeed.setText(speed);
    }
    public void setNumOfSeats(String numOfSeats){
        TextField_numOfSeats.setText(numOfSeats);
    }
    public void setPower(String power){
        TextField_power.setText(power);
    }
    public void setNumberOfCrew(String numberOfCrew){
        TextField_numberOfCrew.setText(numberOfCrew);
    }
    public void setName(String name){
        TextField_name.setText(name);
    }
    public void setAge(String age){
        TextField_age.setText(age);
    }
    public void setYearsOfExperience(String yearsOfExperience){
        TextField_yearsOfExperience.setText(yearsOfExperience);
    }
    private Ship ship;

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @FXML
    void initialize() {
        Button_save.setOnAction(event -> {
            boolean flag=false;
            if(ship==null){
                ship=new Ship();
                flag=true;
            }
            try {
                ship.setMaxSpeed(Double.parseDouble(TextField_maxSpeed.getText()));
                ship.setNumOfSeats(Integer.parseInt(TextField_numOfSeats.getText()));
                ship.setPower(Double.parseDouble(TextField_power.getText()));
                ship.setNumberOfCrew(Integer.parseInt(TextField_numOfSeats.getText()));
                String name=TextField_name.getText();
                String age=TextField_age.getText();
                String yearsOfExperience=TextField_yearsOfExperience.getText();
                ship.creatCaptain(name,Integer.parseInt(age),Integer.parseInt(yearsOfExperience));
                if(flag){
                    TypeOfTransport typeOfTransport=new TypeOfTransport("Корабль",ship);
                    typeOfTransports.add(typeOfTransport);
                    ship=null;
                }
            }catch(NumberFormatException e){
                Main.showErrorWindow("Ошибка","Проверьте корректность введённых данных");
                return;
            }

            Main.stage.setScene(Main.start);
        });
    }
}
