package sample.data_type;

import javafx.beans.property.SimpleStringProperty;
import sample.transport.Transport;

import java.io.*;

public class TypeOfTransport implements  Externalizable {
    
    public Transport transport;
    public TypeOfTransport(String nameOfTransport, Transport transport) {
        this.nameOfTransport=new SimpleStringProperty(nameOfTransport);
        this.transport = transport;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
    private SimpleStringProperty nameOfTransport;
    public TypeOfTransport() {
        this.nameOfTransport = new SimpleStringProperty("");
    }
    public String getNameOfTransport() {
        return nameOfTransport.get();
    }

    public SimpleStringProperty nameOfTransportProperty() {
        return nameOfTransport;
    }

    public void setNameOfTransport(String nameOfTransport) {
        this.nameOfTransport.set(nameOfTransport);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getTransport());
        out.writeObject(this.getNameOfTransport());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        transport=(Transport)in.readObject();
        setNameOfTransport((String)in.readObject());
    }
}
