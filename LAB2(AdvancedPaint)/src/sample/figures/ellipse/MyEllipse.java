package sample.figures.ellipse;

import javafx.scene.shape.Ellipse;
import sample.Main;

public class MyEllipse extends MyCircle {

    public MyEllipse(){

    }

    public MyEllipse(int x1, int maxCoordinateCount, int y1, int r, int a, int b) {
        super(x1, maxCoordinateCount, y1, r);
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
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

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getY0() {
        return y0;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }

    private int x2;
    private int y2;

    private int x0;
    private int y0;

    private int a;
    private int b;

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

                if (getX2()<getX1() && getY1()>getY2()){

                    int temp=getX1();
                    setX1(getX2());
                    setX2(temp);

                    temp=getY1();
                    setY1(getY2());
                    setY2(temp);

                }

                if (getX2()<getX1() && getY1()<getY2()){

                    int temp=getX1();
                    setX1(getX2());
                    setX2(temp);

                }

                if (getX2()>getX1() && getY1()>getY2()){

                    int temp=getX1();
                    setX1(getX2());
                    setX2(temp);

                    temp=getY1();
                    setY1(getY2());
                    setY2(temp);

                    temp=getX1();
                    setX1(getX2());
                    setX2(temp);

                }

                setX0(getX1()+(getX2()-getX1())/2);
                setY0(getY1()+(getY2()-getY1())/2);

                setA((getX2()-getX1())/2);
                setB((getY2()-getY1())/2);

                setMaxCoordinateCount(0);
                if(isCorrectBottom(Math.min(getX1(),getX2()))){
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
        Ellipse ellipse=new Ellipse(getX0(),getY0(),getA(),getB());
        Main.group.getChildren().add(ellipse);
    }
}
