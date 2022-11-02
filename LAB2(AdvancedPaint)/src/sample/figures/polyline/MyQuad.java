package sample.figures.polyline;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import sample.Main;

public class MyQuad extends MyTriangle {

    public MyQuad(){

    }

    public MyQuad(int x1, int y1, int x2, int y2, int maxCoordinateCount, int x3, int y3, int x4, int y4) {
        super(x1, y1, x2, y2, maxCoordinateCount, x3, y3);
        this.x4 = x4;
        this.y4 = y4;
    }

    public int getX4() {
        return x4;
    }

    public void setX4(int x4) {
        this.x4 = x4;
    }

    public int getY4() {
        return y4;
    }

    public void setY4(int y4) {
        this.y4 = y4;
    }

    private int x4;
    private int y4;

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

                break;
            case 4:
                setX4(x);
                setY4(y);

                setMaxCoordinateCount(0);

                if(isCorrectBottom(Math.min(Math.min(Math.min(getX1(),getX2()),getX3()),getX4()))){
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
        Polyline triangle=new Polyline(getX1(),getY1(),getX2(),getY2(),getX3(),getY3(),getX4(),getY4(),getX1(),getY1());
        triangle.setFill(Color.BLACK);

        Main.group.getChildren().add(triangle);
    }
}
