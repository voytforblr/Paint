package sample.transport;

import javafx.event.Event;

import java.io.Serializable;

public abstract class Transport implements Serializable {
    double power;
    double maxSpeed;
    int numOfSeats;
    public Transport() {
    }
    public Transport(double power, double maxSpeed, int numOfSeats) {
        this.power = power;
        this.maxSpeed = maxSpeed;
        this.numOfSeats = numOfSeats;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public abstract void addToList();
    public abstract void changeInList(Event event);
}
