package sample.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Main;
import sample.data_type.TypeOfTransport;
import sample.transport.Bus;


import static sample.Controllers.Controller.typeOfTransports;

public class ControllerOfBus {

    @FXML
    private Button Button_save;

    @FXML
    private TextField TextField_power;

    @FXML
    private TextField TextField_maxSpeed;

    @FXML
    private TextField TextField_numOfSeats;

    @FXML
    private TextField TextField_timetable;

    public void setMaxSpeed(String speed){
        TextField_maxSpeed.setText(speed);
    }
    public void setNumOfSeats(String numOfSeats){
        TextField_numOfSeats.setText(numOfSeats);
    }
    public void setPower(String power){
        TextField_power.setText(power);
    }
    public void setTimetable(String timetable){
        TextField_timetable.setText(timetable);
    }
    private Bus bus;

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    @FXML
    void initialize() {
        Button_save.setOnAction(event -> {
            boolean flag=false;
            if(bus==null){
                bus=new Bus();
                flag=true;
            }
            try {
                bus.setMaxSpeed(Double.parseDouble(TextField_maxSpeed.getText()));
                bus.setNumOfSeats(Integer.parseInt(TextField_numOfSeats.getText()));
                bus.setPower(Double.parseDouble(TextField_power.getText()));
                bus.setTimetable(TextField_timetable.getText());
            } catch(NumberFormatException e){
                Main.showErrorWindow("Ошибка","Проверьте корректность введённых данных");
                if(flag){
                    bus=null;
                }
                return;
            }

            if(flag){
                TypeOfTransport typeOfTransport=new TypeOfTransport("Автобус",bus);
                typeOfTransports.add(typeOfTransport);
            }
            Main.stage.setScene(Main.start);

        });
    }
}
