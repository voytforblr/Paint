package sample.figures;

import javafx.scene.control.Alert;

public abstract class Figures {

    public Figures(int x1, int maxCoordinateCount, int y1) {
        this.x1 = x1;
        this.maxCoordinateCount = maxCoordinateCount;
        this.y1 = y1;
    }

    public Figures(){

    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getMaxCoordinateCount() {
        return maxCoordinateCount;
    }

    public void setMaxCoordinateCount(int maxCoordinateCount) {
        this.maxCoordinateCount = maxCoordinateCount;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    private int x1;
    private int maxCoordinateCount;
    private int y1;

    public abstract void display();
    public abstract void clickCoordinates(int x1, int y1);

    public static void showAlert(String temp){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(temp);
        alert.showAndWait();
    }
    public boolean isCorrectBottom(int x){
        return x >= 186;
    }

    public boolean isCorrectAllSides(int xMin, int yMin, int xMax, int yMax ){
        return xMin >= 186 && xMax <= 675 && yMin <= 482 && yMax >= 0;
    }

}
