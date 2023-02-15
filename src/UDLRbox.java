import java.awt.*;

public class UDLRbox extends Collidable {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT=2;
    public static final int RIGHT=3;
    public static final int MOC=4;
    public static final int MU=9;
    public static final int MD=6;
    public static final int ML=7;
    public static final int MR=8;
    public static int lives=0;
    public static int ON=10;
    public static int ON2=105;
    public static int OFF=11;
    public static int OFF2=115;
    private int direction;
    private Color color;
    private int ONOFF;


    public UDLRbox(Rectangle rect, int direction, Color color) {
        super(rect);
        this.direction = direction;
        this.color = color;
    }
    public UDLRbox(Rectangle rect, int direction, Color color, int lives) {
        super(rect);
        this.direction = direction;
        this.color = color;
        this.lives=lives;
    }

    public void reset(int x, int y)
    {
        setRect(new Rectangle(x,
                y,
                (int)getRect().getWidth(),
                (int)getRect().getHeight()));
    }
    public int getLives() {
        return lives;
    }
    public void setLives(int l){
        this.lives=l;
    }
    public int getDirection() {
        return direction;
    }

    public void changeDirection(){
        //if direction equals whatever change to opposite
        if(direction==UP)
            direction=DOWN;
        else if(direction==DOWN)
            direction=UP;
        else if(direction==LEFT)
            direction=RIGHT;
        else if(direction==RIGHT)
            direction=LEFT;
    }
    public void changeDirectionCircle(){
        if(direction==UP)
            direction=LEFT;
        else if(direction==LEFT)
            direction=DOWN;
        else if(direction==DOWN)
            direction=RIGHT;
        else if(direction==RIGHT)
            direction=UP;
    }
    public void changePower(){
        if(direction==ON)
            direction=OFF;
        else
            direction=ON;
    }


    public void setDirection(int direction)
    {
        //sets direction to direction
        this.direction=direction;
    }

    public void update()
    {
        //if   if not

        if(direction<=1){
            int yChange = (direction == UP) ? -1 : 1;
            setRect(new Rectangle((int) getRect().getX(),
                (int) getRect().getY() + yChange,
                (int) getRect().getWidth(),
                (int) getRect().getHeight())); }
        else if(direction==10||direction==105){
            setRect(new Rectangle((int) getRect().getX(),
                    (int) getRect().getY() ,
                    (int) getRect().getWidth(),
                    (int) getRect().getHeight())); }
        else if(direction==11|| direction==115){
            setRect(new Rectangle((int) getRect().getX(),
                    (int) getRect().getY() ,
                    0,
                    0)); }
         else if(direction==2|| direction==3){
        int xChange=(direction==RIGHT)?-1:1;
        setRect(new Rectangle((int)getRect().getX()+xChange,
                (int)getRect().getY(),
                (int)getRect().getWidth(),
                (int)getRect().getHeight()));


//        if(direction==MU)
//            up();
//        if(direction==MD)
//            down();
//        if(direction==ML)
//            left();
//        if(direction==MR)
//            right();
    }
    }

    public void up()
    {
        int yChange=1;
        setRect(new Rectangle((int)getRect().getX(),
                (int)getRect().getY()-yChange,
                (int)getRect().getWidth(),
                (int)getRect().getHeight()));
        //setRect(new Rectangle(getX(),getY(),15,15));
    }

    public void down()
    {
        int yChange=1;
        setRect(new Rectangle((int)getRect().getX(),
                (int)getRect().getY()+yChange,
                (int)getRect().getWidth(),
                (int)getRect().getHeight()));    }

    public void left()
    {
        int xChange=1;
        setRect(new Rectangle((int)getRect().getX()-xChange,
                (int)getRect().getY(),
                (int)getRect().getWidth(),
                (int)getRect().getHeight()));    }

    public void right()
    {
        int xChange=1;
        setRect(new Rectangle((int)getRect().getX()+xChange,
                (int)getRect().getY(),
                (int)getRect().getWidth(),
                (int)getRect().getHeight()));    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}