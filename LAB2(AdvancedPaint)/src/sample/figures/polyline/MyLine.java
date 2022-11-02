package sample.figures.polyline;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import sample.Main;
import sample.figures.Figures;

public class MyLine extends Figures {

    public MyLine(){

    }

    public MyLine(int x1, int y1, int x2, int y2, int maxCoordinateCount) {
        super(x1, maxCoordinateCount, y1);
        this.x2=x2;
        this.y2=y2;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    private int x2;
    private int y2;

    @Override
    public void clickCoordinates(int x, int y) {
        setMaxCoordinateCount(getMaxCoordinateCount()+1);
        switch (getMaxCoordinateCount()){
            case 1:
            {
                setX1(x);
                setY1(y);
            }
            break;
            case 2:
            {
                setX2(x);
                setY2(y);


                setMaxCoordinateCount(0);
                if(isCorrectBottom(Math.min(getX1(),getX2()))){
                    display();
                }else{
                    showAlert("Going out of the bounds");
                }

            }
            break;
            default:
        }
    }

    @Override
    public void display() {
        Polyline line=new Polyline(getX1(),getY1(),getX2(),getY2());
        line.setFill(Color.BLACK);

        Main.group.getChildren().add(line);
    }


}
