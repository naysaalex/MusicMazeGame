import java.awt.*;

public class Wall extends Rectangle {

    public  Rectangle r;
    public Wall(Rectangle r)
    {
        this.r=r;
    }

    public Rectangle getR() {
        return r;
    }

    public void setRect(Rectangle r) {
        this.r = r;
    }
    public double getWidth() {
        return r.width;
    }

    public void setWidth(int width) {
        r.width = width;
    }

    public double getHeight() {
        return r.height;
    }

    public  void setHeight(int height) {
        r.height = height;
    }

    public double getX() {
        return r.x;
    }

    public  void setX(int x) {
        r = new Rectangle(x,r.y,r.width,r.height);
    }

    public double getY() {
        return r.y;
    }

    public  void setY(int y) {
        r = new Rectangle(r.x,y,r.width,r.height);
    }
    public Rectangle getRect()
    {
        return r;
    }
}
