package sample.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Main;
import sample.data_type.TypeOfTransport;
import sample.transport.Bus;
import sample.transport.Plane;

import static sample.Controllers.Controller.typeOfTransports;

public class ControllerOfPlane {

    @FXML
    private Button Button_save;

    @FXML
    private TextField TextField_power;

    @FXML
    private TextField TextField_maxSpeed;

    @FXML
    private TextField TextField_numOfSeats;

    @FXML
    private TextField TextField_lengthOfWing;
    public void setMaxSpeed(String speed){
        TextField_maxSpeed.setText(speed);
    }
    public void setNumOfSeats(String numOfSeats){
        TextField_numOfSeats.setText(numOfSeats);
    }
    public void setPower(String power){
        TextField_power.setText(power);
    }
    public void setLengthOfWing(String lengthOfWing){
        TextField_lengthOfWing.setText(lengthOfWing);
    }


   private Plane plane;

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @FXML
    void initialize() {
        Button_save.setOnAction(event -> {
            boolean flag=false;
            if(plane==null){
                plane=new Plane();
                flag=true;
            }
            try {
                plane.setMaxSpeed(Double.parseDouble(TextField_maxSpeed.getText()));
                plane.setNumOfSeats(Integer.parseInt(TextField_numOfSeats.getText()));
                plane.setPower(Double.parseDouble(TextField_power.getText()));
                plane.setLengthOfWing(Double.parseDouble(TextField_lengthOfWing.getText()));
            }catch(NumberFormatException e){
                Main.showErrorWindow("Ошибка","Проверьте корректность введённых данных");
                if(flag){
                    plane=null;
                }
                return;
            }

            if(flag){
                TypeOfTransport typeOfTransport=new TypeOfTransport("Самолёт",plane);
                typeOfTransports.add(typeOfTransport);
            }
            Main.stage.setScene(Main.start);
        });
    }
}
