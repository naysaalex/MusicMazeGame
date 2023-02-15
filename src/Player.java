import java.awt.*;

public class Player
{
    public double x;
    public double y;
    public int numLives;
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT=2;
    public static final int RIGHT=3;
    public static final int UL=4;
    public static final int UR=5;
    public static final int DL=6;
    public static final int DR=7;
    Rectangle r;

    private int direction;
    public Player(Rectangle m)
    {
        x=10;
        y=10;
        numLives=5;
        direction=99;
        r = m;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getNumLives() {
        return numLives;
    }

    public void setNumLives(int numLives) {
        this.numLives = numLives;
    }
    public void setRect(Rectangle r) {
        this.r=r;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void move(int direction)
    {
        if(direction==UP)
        {
            up();
        }
        if(direction==DOWN)
        {
            down();
        }
        if(direction==LEFT)
        {
            left();
        }
        if(direction==RIGHT)
        {
            right();
        }
        if(direction==UL)
        {
            up();
            left();
        }
        if(direction==DL)
        {
            down();
            left();
        }
        if(direction==UR)
        {
            up();
            right();
        }
        if(direction==DR)
        {
            right();
            down();
        }
    }

    public void up()
    {
        setY(getY()-0.5);
        //setRect(new Rectangle(getX(),getY(),15,15));
    }

    public void down()
    {
        setY(getY()+0.5);
    }

    public void left()
    {
        setX(getX()-0.5);
    }

    public void right()
    {
        setX(getX()+0.5);
    }

    @Override
    public String toString() {
        return "Player{" +
                "x=" + x +
                ", y=" + y +
                ", numLives=" + numLives +
                '}';
    }

    public Rectangle getRect()
    {
        return new Rectangle((int)x,(int)y,20,20);
    }

}