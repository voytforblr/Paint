package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Main;
import sample.data_type.TypeOfTransport;
import sample.transport.Bus;
import sample.transport.Taxi;

import static sample.Controllers.Controller.typeOfTransports;

public class ControllerOfTaxi {

    @FXML
    private Button Button_save;

    @FXML
    private TextField TextField_power;

    @FXML
    private TextField TextField_maxSpeed;

    @FXML
    private TextField TextField_numOfSeats;

    @FXML
    private TextField TextField_companyOfTaxi;
    public void setMaxSpeed(String speed){
        TextField_maxSpeed.setText(speed);
    }
    public void setNumOfSeats(String numOfSeats){
        TextField_numOfSeats.setText(numOfSeats);
    }
    public void setPower(String power){
        TextField_power.setText(power);
    }
    public void setCompanyOfTaxi(String companyOfTaxi){
        TextField_companyOfTaxi.setText(companyOfTaxi);
    }

    private Taxi taxi;

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    @FXML
    void initialize() {
        Button_save.setOnAction(event -> {
            boolean flag=false;
            if(taxi==null){
                taxi=new Taxi();
                flag=true;
            }
            try {
                taxi.setMaxSpeed(Double.parseDouble(TextField_maxSpeed.getText()));
                taxi.setNumOfSeats(Integer.parseInt(TextField_numOfSeats.getText()));
                taxi.setPower(Double.parseDouble(TextField_power.getText()));
                taxi.setCompanyOfTaxi(TextField_companyOfTaxi.getText());
            }catch(NumberFormatException e){
                Main.showErrorWindow("Ошибка","Проверьте корректность введённых данных");
                if(flag){
                    taxi=null;
                }
                return;
            }

            if(flag){
                TypeOfTransport typeOfTransport=new TypeOfTransport("Такси",taxi);
                typeOfTransports.add(typeOfTransport);
            }
            Main.stage.setScene(Main.start);

        });
    }
}
