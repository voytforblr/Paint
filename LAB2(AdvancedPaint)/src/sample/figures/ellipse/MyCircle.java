package sample.figures.ellipse;

import javafx.scene.shape.Ellipse;
import sample.Main;
import sample.figures.Figures;

public class MyCircle extends Figures {

    public MyCircle(){

    }

    public MyCircle(int x1, int maxCoordinateCount, int y1, int r) {
        super(x1, maxCoordinateCount, y1);
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
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

    private int r;

    private int x2;
    private int y2;

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

                setR((int)Math.sqrt(Math.pow(getX1()-getX2(),2)+Math.pow(getY1()-getY2(),2)));

                setMaxCoordinateCount(0);

                if(isCorrectAllSides(getX1()-getR(),getY1()+getR(),getX1()+getR(),getY1()-getR())){
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
        Ellipse circle=new Ellipse(getX1(),getY1(),getR(),getR());
        Main.group.getChildren().add(circle);
    }
}
