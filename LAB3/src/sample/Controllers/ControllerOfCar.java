package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Main;
import sample.data_type.TypeOfTransport;
import sample.transport.Car;

import static sample.Controllers.Controller.typeOfTransports;

public class ControllerOfCar {
    public ControllerOfCar() {

    }

    @FXML
    private Button Button_save;

    @FXML
    private TextField TextField_power;

    @FXML
    private TextField TextField_maxSpeed;

    @FXML
    private TextField TextField_numOfSeats;

    public void setMaxSpeed(String speed){
        TextField_maxSpeed.setText(speed);
    }
    public void setNumOfSeats(String numOfSeats){
        TextField_numOfSeats.setText(numOfSeats);
    }
    public void setPower(String power){
        TextField_power.setText(power);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    private Car car;
    @FXML
    void initialize() {
        Button_save.setOnAction(event -> {
            boolean flag=false;
            if(car==null){
                car=new Car();
                flag=true;
            }
            try {
                car.setMaxSpeed(Double.parseDouble(TextField_maxSpeed.getText()));
                car.setNumOfSeats(Integer.parseInt(TextField_numOfSeats.getText()));
                car.setPower(Double.parseDouble(TextField_power.getText()));
            }catch(NumberFormatException e){
                 Main.showErrorWindow("Ошибка","Проверьте корректность введённых данных");
                 if(flag){
                     car=null;
                 }
                 return;
            }
            if(flag){
                TypeOfTransport typeOfTransport=new TypeOfTransport("Машина",car);
                typeOfTransports.add(typeOfTransport);
            }
            Main.stage.setScene(Main.start);
        });
    }

}
