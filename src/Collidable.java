import java.awt.*;

public class Collidable {
    private Rectangle rect;
    public Player oval;

    public Collidable(Rectangle rect,Player oval) {
        this.rect = rect;
        this.oval=oval;
    }
    public Collidable(Rectangle rect) {
        this.rect = rect;
    }
    public Collidable(Player oval) {
        this.oval=oval;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public Player getOval() {
        return oval;
    }

    public void setOval(Player oval) {
        this.oval = oval;
    }

    public boolean collidesWith(Rectangle oval,Rectangle rect)
    {
        /*if((oval.getX()+15)>=rect.getX()||(oval.getY()+15)>=rect.getY())
            return true;
        if((oval.getX()-15)>=rect.getX()||(oval.getY()-15)>=rect.getY())
        return true;*/
        oval.intersects(rect);
        return false;
    }
}