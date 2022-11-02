package sample.figures.polyline;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import sample.Main;
import sample.figures.Figures;

public class MyTriangle extends MyLine {

    public MyTriangle(int x1, int y1, int x2, int y2, int maxCoordinateCount, int x3, int y3) {
        super(x1, y1, x2, y2, maxCoordinateCount);
        this.x3 = x3;
        this.y3 = y3;
    }

    public MyTriangle(){

    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }

    private int x3;
    private int y3;

    @Override
    public void clickCoordinates(int x, int y) {
        setMaxCoordinateCount(getMaxCoordinateCount()+1);
        switch (getMaxCoordinateCount()){
            case 1:
                setX1(x);
                setY1(y);
            break;

            case 2:
                setX2(x);
                setY2(y);
            break;

            case 3:
                setX3(x);
                setY3(y);

                setMaxCoordinateCount(0);

                if(isCorrectBottom(Math.min(Math.min(getX1(),getX2()),getX3()))){
                    display();
                }else{
                    showAlert("Going out of the bounds");
                }

            break;
            default:
        }
    }

    @Override
    public void display() {
        Polyline triangle=new Polyline(getX1(),getY1(),getX2(),getY2(),getX3(),getY3(),getX1(),getY1());
        triangle.setFill(Color.BLACK);

        Main.group.getChildren().add(triangle);
    }
}
