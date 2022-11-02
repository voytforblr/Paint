package sample.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.util.StringConverter;
import sample.data_type.TypeOfTransport;
import sample.plugins.IPlugin;
import sample.plugins.PluginConfiger;
import sample.transport.*;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Controller {


    @FXML
    private Button Button_add;

    @FXML
    private Button Button_delete;

    @FXML
    private Button Button_change;

    @FXML
    private RadioButton RadioButton_String;

    @FXML
    private RadioButton RadioButton_XML;

    @FXML
    private RadioButton RadioButton_Binary;

    @FXML
    private Button Button_Save;

    @FXML
    private Button Button_Load;

    @FXML
    private ComboBox<String> ComboBox;

    @FXML
    private TableView<TypeOfTransport> TableView;

    @FXML
    private TableColumn<TypeOfTransport, String> TableColumn;


    @FXML
    private ChoiceBox<IPlugin> ChoiceBox;

    public static ObservableList<TypeOfTransport> typeOfTransports = FXCollections.observableArrayList();

    private Transport transport = new Car();

    private ToggleGroup method;


    @FXML
    void initialize() throws NoSuchMethodException {
        {
            ComboBox.getItems().add("Машина");
            ComboBox.getItems().add("Автобус");
            ComboBox.getItems().add("Такси");
            ComboBox.getItems().add("Самолёт");
            ComboBox.getItems().add("Корабль");
            ComboBox.setValue("Машина");
            TableView.setItems(typeOfTransports);
            method = new ToggleGroup();
            this.RadioButton_Binary.setToggleGroup(method);
            this.RadioButton_String.setToggleGroup(method);
            this.RadioButton_XML.setToggleGroup(method);
            this.RadioButton_String.fire();


            //загрузка плагинов
            PluginConfiger pluginConfiger = new PluginConfiger("D:\\ООТПиСП\\LAB3\\Plugins");
            ArrayList<IPlugin> plugins = pluginConfiger.config();
            if (plugins != null) {
                ChoiceBox.getItems().addAll(plugins);
            }


            ChoiceBox.setConverter(new StringConverter<IPlugin>() {
                @Override
                public String toString(IPlugin iPlugin) {
                    return iPlugin.getName();
                }

                @Override
                public IPlugin fromString(String s) {
                    return null;
                }
            });
        }

        Button_add.setOnAction(event -> {

            if (ComboBox.getSelectionModel().getSelectedItem().equals("Машина")) {
                transport = new Car();
            }
            if (ComboBox.getSelectionModel().getSelectedItem().equals("Автобус")) {
                transport = new Bus();
            }
            if (ComboBox.getSelectionModel().getSelectedItem().equals("Такси")) {
                transport = new Taxi();
            }
            if (ComboBox.getSelectionModel().getSelectedItem().equals("Самолёт")) {
                transport = new Plane();
            }
            if (ComboBox.getSelectionModel().getSelectedItem().equals("Корабль")) {
                transport = new Ship();
            }

            transport.addToList();

        });
        Button_delete.setOnAction(event -> {
            if (isItemSelected()) {
                int index = TableView.getSelectionModel().getFocusedIndex();
                TableView.getItems().remove(index);
            }
        });
        Button_change.setOnAction(event -> {

            if (isItemSelected()) {
                int index = TableView.getSelectionModel().getFocusedIndex();
                System.out.println(TableView.getItems().get(index).getTransport());
                System.out.println(index);
                TableView.getItems().get(index).getTransport().changeInList(event);
            }
        });
        TableColumn.setCellValueFactory(cellData -> cellData.getValue().nameOfTransportProperty());
        TableView.setItems(typeOfTransports);
        Button_Save.setOnAction(actionEvent -> {
            try {
                writeInfo();
            } catch (IOException | NullPointerException e) {
            }
        });
        Button_Load.setOnAction(actionEvent -> {
            try {
                uploadInfo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }


    boolean isItemSelected() {
        int index = TableView.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            return false;
        }
        return true;
    }

    public void writeInfo() throws IOException {
        IPlugin activePlugin = ChoiceBox.getSelectionModel().getSelectedItem();
        if (method.getSelectedToggle() == RadioButton_XML) {
            if (activePlugin == null) {
                FileOutputStream fos = new FileOutputStream("temp.xml");
                XMLEncoder xmlEncoder = new XMLEncoder(fos);
                for (int i = 0; i < typeOfTransports.size(); i++) {
                    xmlEncoder.writeObject(typeOfTransports.get(i));
                }
                xmlEncoder.close();
                fos.close();
            }

            if (activePlugin != null) {
                FileOutputStream fos = new FileOutputStream("pluginRes.xml");
                XMLEncoder xmlEncoder = new XMLEncoder(fos);
                for (int i = 0; i < typeOfTransports.size(); i++) {
                    xmlEncoder.writeObject(typeOfTransports.get(i));
                }
                xmlEncoder.close();
                fos.close();


                FileInputStream fi = new FileInputStream("pluginRes.xml");
                byte[] buffer = fi.readAllBytes();
                String flag = activePlugin.convertTo("pluginRes.xml", new String(buffer));
                if (flag != null) {
                    FileOutputStream fos1 = new FileOutputStream("pluginRes.xml");
                    fos1.write(flag.getBytes());
                    fos1.close();
                }
                fi.close();
            }

        }
        if (method.getSelectedToggle() == RadioButton_Binary) {
            if (activePlugin == null) {
                FileOutputStream fos = new FileOutputStream("temp.bin");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
                for (int i = 0; i < typeOfTransports.size(); i++) {
                    objectOutputStream.writeObject(typeOfTransports.get(i));
                }
                objectOutputStream.close();
                fos.close();
            }

            if (activePlugin != null) {
                FileOutputStream fos = new FileOutputStream("pluginRes.bin");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
                for (int i = 0; i < typeOfTransports.size(); i++) {
                    objectOutputStream.writeObject(typeOfTransports.get(i));
                }
                objectOutputStream.close();
                fos.close();


                FileInputStream fi = new FileInputStream("pluginRes.bin");
                byte[] buffer = fi.readAllBytes();
                String flag = activePlugin.convertTo("pluginRes.bin", new String(buffer));
                if (flag != null) {
                    FileOutputStream fos1 = new FileOutputStream("pluginRes.bin");
                    fos1.write(flag.getBytes());
                    System.out.println(flag);
                    fos1.close();
                }
                fi.close();
            }

        }
        if (method.getSelectedToggle() == RadioButton_String) {
            if (activePlugin == null) {
                ser("temp.txt");
            }

            if (activePlugin != null) {
                ser("pluginRes.txt");

                FileInputStream fi = new FileInputStream("pluginRes.txt");
                byte[] buffer = fi.readAllBytes();
                String flag = activePlugin.convertTo("pluginRes.txt", new String(buffer));
                if (flag != null) {
                    FileOutputStream fos1 = new FileOutputStream("pluginRes.txt");
                    fos1.write(flag.getBytes());
                    fos1.close();
                }
                fi.close();
            }
        }


    }

    public void uploadInfo() throws IOException {
        IPlugin activePlugin = ChoiceBox.getSelectionModel().getSelectedItem();
        typeOfTransports.clear();
        if (method.getSelectedToggle() == RadioButton_XML) {

            if (activePlugin != null) {
                FileInputStream fos0 = new FileInputStream("pluginRes.xml");
                FileOutputStream fo = new FileOutputStream("tempPluginRes.xml");
                String flag = activePlugin.convertFrom("pluginRes.xml", new String(fos0.readAllBytes()));
                fos0.close();
                fo.write(flag.getBytes());
                fo.close();

                FileInputStream fos = new FileInputStream("tempPluginRes.xml");
                XMLDecoder xmlDecoder = new XMLDecoder(fos);
                try {
                    while (true) {
                        typeOfTransports.add((TypeOfTransport) xmlDecoder.readObject());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // e.printStackTrace();
                }
                xmlDecoder.close();
                fos.close();
                File file = new File("tempPluginRes.xml");
                file.delete();
            }
            if (activePlugin == null) {
                FileInputStream fos = new FileInputStream("temp.xml");
                XMLDecoder xmlDecoder = new XMLDecoder(fos);
                try {
                    while (true) {
                        typeOfTransports.add((TypeOfTransport) xmlDecoder.readObject());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // e.printStackTrace();
                }
                fos.close();
                xmlDecoder.close();
            }
        }
        if (method.getSelectedToggle() == RadioButton_Binary) {
            if (activePlugin != null) {


                FileInputStream fos = new FileInputStream("temp.bin");
                ObjectInputStream objectInputStream = new ObjectInputStream(fos);

                try {
                    while (fos.available() > 0) {
                        typeOfTransports.add((TypeOfTransport) objectInputStream.readObject());
                    }

                } catch (EOFException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                objectInputStream.close();
                fos.close();
            }
            if (activePlugin == null) {
                FileInputStream fos = new FileInputStream("temp.bin");
                ObjectInputStream objectInputStream = new ObjectInputStream(fos);

                try {
                    while (fos.available() > 0) {
                        typeOfTransports.add((TypeOfTransport) objectInputStream.readObject());
                    }

                } catch (EOFException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                objectInputStream.close();
                fos.close();
            }

        }
        if (method.getSelectedToggle() == RadioButton_String) {
            if (activePlugin != null) {
                FileInputStream fos0 = new FileInputStream("pluginRes.txt");
                FileOutputStream fo = new FileOutputStream("tempPluginRes.txt");
                String flag = activePlugin.convertFrom("pluginRes.txt", new String(fos0.readAllBytes()));
                fos0.close();
                fo.write(flag.getBytes());
                fo.close();
                deser("tempPluginRes.txt");
                File file = new File("tempPluginRes.txt");
                file.delete();
            }
            if (activePlugin == null) {
                deser("temp.txt");
            }

        }

    }

    public void ser(String name) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(name));
            fileWriter.write("------------------------------------------------------------------------\n");
            System.out.println("------------------------------------------------------------------------");
            for (int i = 0; i < typeOfTransports.size(); i++) {
                Transport t = typeOfTransports.get(i).getTransport();
                fileWriter.write(" " + typeOfTransports.get(i).getNameOfTransport() + "\n");
                fileWriter.write(" " + t.getClass().toString() + "\n");
                System.out.println(typeOfTransports.get(i).getNameOfTransport());
                System.out.println(t.getClass());
                Class myClass = t.getClass();
                while (myClass != Object.class) {
                    Field[] fields = myClass.getDeclaredFields();
                    for (int j = 0; j < fields.length; j++) {
                        fields[j].setAccessible(true);
                        try {
                            if (!fields[j].getType().isPrimitive() && !fields[j].getType().equals(String.class)) {
                                fileWriter.write("///////////////////////////////////////\n");
                                System.out.println("///////////////////////////////////////");
                                Class ob = fields[j].get(t).getClass();
                                System.out.println(" " + ob);
                                fileWriter.write(" " + ob.toString() + "\n");
                                Ship sh = (Ship) t;
                                Field[] fieldss = ob.getDeclaredFields();
                                for (int k = 0; k < fieldss.length; k++) {
                                    fieldss[k].setAccessible(true);
                                    System.out.println(" " + fieldss[k].getName() + " " + fieldss[k].getType() + " " + fieldss[k].get(sh.getCaptain()) + " ");
                                    fileWriter.write(" " + fieldss[k].getName() + " " + fieldss[k].getType() + " " + fieldss[k].get(sh.getCaptain()) + " \n");
                                }
                                fileWriter.write("///////////////////////////////////////\n");
                                System.out.println("///////////////////////////////////////");
                                continue;
                            }
                            fileWriter.write(" " + fields[j].getName() + " " + fields[j].getType() + " " + fields[j].get(t) + " \n");
                            System.out.println(" " + fields[j].getName() + " " + fields[j].getType() + " " + fields[j].get(t) + " ");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    myClass = myClass.getSuperclass();
                }
                fileWriter.write("------------------------------------------------------------------------\n");
                System.out.println("------------------------------------------------------------------------");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deser(String name) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(name));
            for (int i = 0; i < lines.size() - 1; i++) {
                String str = lines.get(i);
                if (str.charAt(0) == '-') {


                    Object transport;
                    Class<?> myClass;
                    i++;
                    String tra = lines.get(i);
                    i++;
                    str = lines.get(i);
                    String[] line = str.split(" ");
                    myClass = Class.forName(line[2]);
                    i++;
                    str = lines.get(i);
                    transport = myClass.newInstance();


                    ArrayList<Class<?>> paramsType = new ArrayList<>();
                    ArrayList<Object> params = new ArrayList<>();
                    while (str.charAt(0) != '-') {
                        if (str.charAt(0) == '/') {
                            i++;
                            str = lines.get(i);
                            i++;
                            str = lines.get(i);
                            String[] lin1 = str.split(" ");
                            i++;
                            str = lines.get(i);
                            String[] lin2 = str.split(" ");
                            i++;
                            str = lines.get(i);
                            String[] lin3 = str.split(" ");
                            i += 2;
                            str = lines.get(i);
                            Captain captain = new Captain(lin1[4], Integer.parseInt(lin2[3]), Integer.parseInt(lin3[3]));
                            params.add(captain);
                        }
                        String[] lin = str.split(" ");
                        Class<?> temp = null;
                        Object temp2 = null;
                        if (lin[2].equals("double")) {
                            temp = Double.class;
                            temp2 = Double.parseDouble(lin[3]);
                        } else if (lin[2].equals("int")) {
                            temp = Integer.class;
                            temp2 = Integer.parseInt(lin[3]);
                        } else if (lin[3].equals("java.lang.String")) {
                            temp = String.class;
                            temp2 = lin[4];
                        } else {
                            temp2 = lin[3];
                        }
                        params.add(temp2);
                        paramsType.add(temp);
                        i++;
                        str = lines.get(i);
                    }

                    Class<?> superClass = myClass;
                    int counter = 0;
                    while (superClass != Object.class) {
                        Field[] field = superClass.getDeclaredFields();
                        for (int j = 0; j < field.length; j++) {
                            field[j].setAccessible(true);
                            field[j].set(transport, params.get(counter));
                            counter++;
                        }
                        superClass = superClass.getSuperclass();
                    }

                    typeOfTransports.add(new TypeOfTransport(tra, (Transport) transport));
                    i--;
                }

            }
        } catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
