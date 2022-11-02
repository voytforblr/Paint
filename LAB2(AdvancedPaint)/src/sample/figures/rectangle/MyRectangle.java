package sample.figures.rectangle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import sample.Main;
import sample.figures.Figures;

public class MyRectangle extends Figures {

    public MyRectangle(){

    }

    public MyRectangle(int x1, int maxCoordinateCount, int y1, int width, int height) {
        super(x1, maxCoordinateCount, y1);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private int getX2() {
        return x2;
    }

    private void setX2(int x2) {
        this.x2 = x2;
    }

    private int getY2() {
        return y2;
    }

    private void setY2(int y2) {
        this.y2 = y2;
    }

    private int width;
    private int height;

    private int x2;
    private int y2;

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

                setWidth(getX2()-getX1());
                setHeight(getY2()-getY1());

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
        Rectangle rectangle=new Rectangle(getX1(),getY1(),width,height);
        Main.group.getChildren().add(rectangle);
    }
}
