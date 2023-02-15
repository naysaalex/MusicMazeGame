import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Level5 extends JPanel implements Runnable,KeyListener{
    int x=5;
    int y=5;
    public static final int UPS=140;
    public static final double laserUPS=0.5;
    private UDLRbox box1,box2,box3,box4,box5,box6,box7,box8;
    public UDLRbox player;
    private Wall wall1,wall2,wall3,wall4,wall5,wall6,wall7,wall8,wall9,wall10,wall11,wall12,wall13,wall14;
    private Rectangle laser1,laser2,laser3,laser4,laser5,laser6,laser1box,laser2box,laser3box,laser4box,laser5box,laser6box,end;
    private Player oval;
    private double timeBetweenUpdates=1000.0/UPS;
    private double lasertimeBetweenUpdates=1000.0/laserUPS;
    private BufferedImage buffer;
    public boolean endgame=false;
    public boolean win=false;
    public boolean laseron=false;
    Clip clip;
    Collidable c= new Collidable(oval);
    public Level5(int panelWidth, int panelHeight, int lives)
    {
        super();
        try
        {
//            URL fileLocation= new URL("file:WR.wav");
//            question5= Applet.newAudioClip(fileLocation);
//            question5.play();
//            question5.loop();
//            Thread.sleep(3000);
            AudioInputStream ao= AudioSystem.getAudioInputStream(new File("C:\\Users\\naysa\\Desktop\\oth School\\highschool\\compsci 2\\IdeaProjects\\FlyingCoconut\\FlyingCoconut\\Music\\HC.wav").getAbsoluteFile());
            clip= AudioSystem.getClip();
            clip.open(ao);
            clip.start();

        }
        catch(Exception ex)
        {
            System.out.println("Can not find: WR.wav");
        }
        setSize(panelWidth, panelHeight);
        box1= new UDLRbox(new Rectangle(5,270,20,20),
                UDLRbox.RIGHT, Color.RED);
        box2= new UDLRbox(new Rectangle(10,360,20,20),
                UDLRbox.DOWN, Color.RED);
        box3= new UDLRbox(new Rectangle(60,470,20,20),
                UDLRbox.UP, Color.RED);
        box4= new UDLRbox(new Rectangle(210,220,20,20),
                UDLRbox.RIGHT, Color.RED);
        box5= new UDLRbox(new Rectangle(315,160,20,20),
                UDLRbox.LEFT, Color.RED);
        box6= new UDLRbox(new Rectangle(5,215,20,20),
                UDLRbox.RIGHT, Color.RED);
        box7= new UDLRbox(new Rectangle(10,10,20,20),
                UDLRbox.DOWN, Color.RED);
        box8= new UDLRbox(new Rectangle(180,125,20,20),
                UDLRbox.UP, Color.RED);
        laser1=new Rectangle(5,40,495,10);
        laser2=new Rectangle(0,170,205,10);
        laser3= new Rectangle(335,155,80,10);
        laser4= new Rectangle(420,420,75,10);
        laser5= new Rectangle(210,355,10,75);
        laser6= new Rectangle(325,355,10,75);
        laser1box=new Rectangle(480,35,20,20);
        laser2box=new Rectangle(0,165,20,20);
        laser3box= new Rectangle(335,150,20,20);
        laser4box= new Rectangle(415,415,20,20);
        laser5box= new Rectangle(205,350,20,20);
        laser6box= new Rectangle(320,350,20,20);
        end= new Rectangle(210,300,50,50);
        player= new UDLRbox(new Rectangle(170,310,20,20),
                UDLRbox.MOC, Color.BLACK,lives);
        setSize(500,600);
        buffer= new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        setVisible(true);
        wall1= new Wall(new Rectangle(0,0,5,500));
        wall2= new Wall(new Rectangle(0,0,500,5));
        wall3= new Wall(new Rectangle(495,0,5,500));
        wall4= new Wall(new Rectangle(0,495,500,5));
        wall5= new Wall( new Rectangle(85,430,330,5));
        wall6= new Wall(new Rectangle(0,350,340,5));
        wall7= new Wall(new Rectangle(415,70,5,365));
        wall8= new Wall(new Rectangle(335,150,5,200));
        wall9= new Wall(new Rectangle(275,150,65,5));
        wall10= new Wall(new Rectangle(205,70,213,5));
        wall11= new Wall(new Rectangle(205,70,5,285));
        wall12= new Wall(new Rectangle(205,210,70,5));
        wall13= new Wall(new Rectangle(90,250,115,5));
        wall14= new Wall(new Rectangle(0,150,115,5));

        oval=new Player(new Rectangle(getX(),getY(),13,13));

        addKeyListener( this);


        Thread t=new Thread(this);
        t.start();
    }
    public void update()
    {
        if (player.getRect().intersects(box1.getRect()) || player.getRect().intersects(box2.getRect()) || player.getRect().intersects(box3.getRect()) || player.getRect().intersects(box4.getRect()) || player.getRect().intersects(box5.getRect()) || player.getRect().intersects(box6.getRect())|| player.getRect().intersects(box7.getRect())|| player.getRect().intersects(box8.getRect()))
        {
            player.setLives(player.getLives() -1);
            System.out.println(player.getLives());
            player.reset(170,310);
            if(player.getLives()==0)
            {
                clip.stop();
              // player.setLives(player.getLives()+1);
               player.reset(10,10);
               setVisible(false);
              new GameBase(new lose(500,600),"Set Panel Size", 500,600);
            }
        }
        if(laseron)
        {
            if(player.getRect().intersects(laser1)||player.getRect().intersects(laser2)||player.getRect().intersects(laser3)||player.getRect().intersects(laser4)||player.getRect().intersects(laser5)||player.getRect().intersects(laser6)){
                player.setLives(player.getLives()-1);
                System.out.println(player.getLives());
                player.reset(170,310);
                if(player.getLives()==0)
                {
                    clip.stop();
                 //      player.setLives(player.getLives()+1);
                    player.reset(10,10);
                    setVisible(false);
                    new GameBase(new lose(500,600),"Set Panel Size", 500,600);
                }
            }
        }

        //oval.move(oval.getDirection());
        box1.update();
        box2.update();
        box3.update();
        box4.update();
        box5.update();
        box6.update();
        box7.update();
        box8.update();
        if (player.getDirection() == 9)
            player.up();
        if (player.getDirection() == 6)
            player.down();
        if (player.getDirection() == 7)
            player.left();
        if (player.getDirection() == 8)
            player.right();
        if (box1.getRect().getX() < 5 || box1.getRect().getX() + box1.getRect().getWidth() > 205)
            box1.changeDirection();
        if (box2.getRect().getX() < 10 || box2.getRect().getX() > 60|| box2.getRect().getY()<360||box2.getRect().getY()>470)
            box2.changeDirectionCircle();
        if (box3.getRect().getX() < 10 || box3.getRect().getX()  > 60|| box3.getRect().getY()<360||box3.getRect().getY()>470)
            box3.changeDirectionCircle();
        if (box4.getRect().getX() < 210 || box4.getRect().getX() + box4.getRect().getWidth() > 335)
            box4.changeDirection();
        if (box5.getRect().getX() < 210 || box5.getRect().getX() + box5.getRect().getWidth() > 335)
            box5.changeDirection();
        if (box6.getRect().getX() < 5 || box6.getRect().getX() + box6.getRect().getWidth() > 205)
            box6.changeDirection();
        if (box7.getRect().getX() < 10 || box7.getRect().getX()  > 180|| box7.getRect().getY()<10||box7.getRect().getY()>125)
            box7.changeDirectionCircle();
        if (box8.getRect().getX() < 10 || box8.getRect().getX() > 180|| box8.getRect().getY()<10||box8.getRect().getY()>125)
            box8.changeDirectionCircle();
        //System.out.println( " wall 1"+wall1.getRect().getX() +" h "+wall1.getRect());

        if(player.getRect().intersects(laser1box))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)laser1box.getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)laser1box.getX()+20,(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)laser1box.getY()+(int)laser1box.getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)laser1box.getY()-20);
            }
        }
        if(player.getRect().intersects(laser2box))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)laser2box.getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)laser2box.getX()+20,(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)laser2box.getY()+(int)laser2box.getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)laser2box.getY()-20);
            }
        }
        if(player.getRect().intersects(laser3box))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)laser3box.getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)laser3box.getX()+20,(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)laser3box.getY()+(int)laser3box.getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)laser3box.getY()-20);
            }
        }
        if(player.getRect().intersects(laser4box))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)laser4box.getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)laser4box.getX()+20,(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)laser4box.getY()+(int)laser4box.getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)laser4box.getY()-20);
            }
        }
        if(player.getRect().intersects(laser5box))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)laser5box.getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)laser5box.getX()+20,(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)laser5box.getY()+(int)laser5box.getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)laser5box.getY()-20);
            }
        }
        if(player.getRect().intersects(laser6box))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)laser6box.getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)laser6box.getX()+20,(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)laser6box.getY()+(int)laser6box.getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)laser6box.getY()-20);
            }
        }
        if(player.getRect().intersects(wall2.getRect()))
        {
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),5);
            }
        }
        if(player.getRect().intersects(wall1.getRect()))
        {
            if(player.getDirection()==7)
            {
                player.reset(5,(int)player.getRect().getY());
            }
        }
        if(player.getRect().intersects(wall3.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset(495-20,(int)player.getRect().getY());
            }
        }
        if(player.getRect().intersects(wall4.getRect()))
        {
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),495-20);
            }
        }
        if(player.getRect().intersects(wall5.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)wall5.getRect().getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)wall5.getRect().getX()+(int)wall5.getRect().getWidth(),(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)wall5.getRect().getY()+(int)wall5.getRect().getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)wall5.getRect().getY()-20);
            }
        }
        if(player.getRect().intersects(wall6.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)wall6.getRect().getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)wall6.getRect().getX()+(int)wall6.getRect().getWidth(),(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)wall6.getRect().getY()+(int)wall6.getRect().getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)wall6.getRect().getY()-20);
            }
        }
        if(player.getRect().intersects(wall7.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)wall7.getRect().getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)wall7.getRect().getX()+(int)wall7.getRect().getWidth(),(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)wall7.getRect().getY()+(int)wall7.getRect().getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)wall7.getRect().getY()-20);
            }
        }
        if(player.getRect().intersects(wall8.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)wall8.getRect().getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)wall8.getRect().getX()+(int)wall8.getRect().getWidth(),(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)wall8.getRect().getY()+(int)wall8.getRect().getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)wall8.getRect().getY()-20);
            }
        }
        if(player.getRect().intersects(wall9.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)wall9.getRect().getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)wall9.getRect().getX()+(int)wall9.getRect().getWidth(),(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)wall9.getRect().getY()+(int)wall9.getRect().getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)wall9.getRect().getY()-20);
            }
        }
        if(player.getRect().intersects(wall10.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)wall10.getRect().getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)wall10.getRect().getX()+(int)wall10.getRect().getWidth(),(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)wall10.getRect().getY()+(int)wall10.getRect().getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)wall10.getRect().getY()-20);
            }
        }
        if(player.getRect().intersects(wall11.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)wall11.getRect().getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)wall11.getRect().getX()+(int)wall11.getRect().getWidth(),(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)wall11.getRect().getY()+(int)wall11.getRect().getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)wall11.getRect().getY()-20);
            }
        }
        if(player.getRect().intersects(wall12.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)wall12.getRect().getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)wall12.getRect().getX()+(int)wall12.getRect().getWidth(),(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)wall12.getRect().getY()+(int)wall12.getRect().getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)wall12.getRect().getY()-20);
            }
        }
        if(player.getRect().intersects(wall13.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)wall13.getRect().getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)wall13.getRect().getX()+(int)wall13.getRect().getWidth(),(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)wall13.getRect().getY()+(int)wall13.getRect().getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)wall13.getRect().getY()-20);
            }
        }
        if(player.getRect().intersects(wall14.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset((int)wall14.getRect().getX()-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset((int)wall14.getRect().getX()+(int)wall14.getRect().getWidth(),(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)wall14.getRect().getY()+(int)wall14.getRect().getHeight());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)wall14.getRect().getY()-20);
            }
        }
//        if(player.getLives()==0){
//        clip.stop();
//        player.setLives(player.getLives()+1);
//        player.reset(10,10);
//        setVisible(false);
//        new GameBase(new lose(500,600),"Set Panel Size", 500,600);
//    }
        if(player.getRect().intersects(end))
        {
            System.out.print("I'm donee");
            clip.stop();
            player.setLives(player.getLives()+1);
            player.reset(10,10);
            setVisible(false);
            //setPreferredSize(new Dimension(0, 0));

            new GameBase(new win(500,600),"Set Panel Size", 500,600);

        }
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.CYAN);//CHANGE COLOR
        g.fillRect(0,0,getWidth(),500);
        /*g.setColor(Color.GREEN);
        g.fillRect(0,450,getWidth(),50);*/


        g.setColor(Color.BLACK);
        g.fillRect((int)wall1.getX(),(int)wall1.getY(),(int)wall1.getWidth(),(int)wall1.getHeight());
        g.fillRect((int)wall2.getX(),(int)wall2.getY(),(int)wall2.getWidth(),(int)wall2.getHeight());
        g.fillRect((int)wall3.getX(),(int)wall3.getY(),(int)wall3.getWidth(),(int)wall3.getHeight());
        g.fillRect((int)wall4.getX(),(int)wall4.getY(),(int)wall4.getWidth(),(int)wall4.getHeight());
        g.fillRect((int)wall5.getX(),(int)wall5.getY(),(int)wall5.getWidth(),(int)wall5.getHeight());
        g.fillRect((int)wall6.getX(),(int)wall6.getY(),(int)wall6.getWidth(),(int)wall6.getHeight());
        g.fillRect((int)wall7.getX(),(int)wall7.getY(),(int)wall7.getWidth(),(int)wall7.getHeight());
        g.fillRect((int)wall8.getX(),(int)wall8.getY(),(int)wall8.getWidth(),(int)wall8.getHeight());
        g.fillRect((int)wall9.getX(),(int)wall9.getY(),(int)wall9.getWidth(),(int)wall9.getHeight());
        g.fillRect((int)wall10.getX(),(int)wall10.getY(),(int)wall10.getWidth(),(int)wall10.getHeight());
        g.fillRect((int)wall11.getX(),(int)wall11.getY(),(int)wall11.getWidth(),(int)wall11.getHeight());
        g.fillRect((int)wall12.getX(),(int)wall12.getY(),(int)wall12.getWidth(),(int)wall12.getHeight());
        g.fillRect((int)wall13.getX(),(int)wall13.getY(),(int)wall13.getWidth(),(int)wall13.getHeight());
        g.fillRect((int)wall14.getX(),(int)wall14.getY(),(int)wall14.getWidth(),(int)wall14.getHeight());


        /*g.fillRect(10,450,30,30);
        g.fillRect(10,470,80,20);
        g.fillRect(90,450,30,40);*/
        g.setColor(Color.GREEN);
        g.fillRect(210,300,50,50);
        g.setColor(Color.YELLOW);
        g.fillRect(155,300,50,50);
        g.setColor(Color.RED);
        g.fillRect((int)box1.getRect().getX(),
                (int)box1.getRect().getY(),
                (int)box1.getRect().getWidth(),
                (int)box1.getRect().getHeight());
        g.fillRect((int)box2.getRect().getX(),
                (int)box2.getRect().getY(),
                (int)box2.getRect().getWidth(),
                (int)box2.getRect().getHeight());
        g.fillRect((int)box3.getRect().getX(),
                (int)box3.getRect().getY(),
                (int)box3.getRect().getWidth(),
                (int)box3.getRect().getHeight());
        g.fillRect((int)box4.getRect().getX(),
                (int)box4.getRect().getY(),
                (int)box4.getRect().getWidth(),
                (int)box4.getRect().getHeight());
        g.fillRect((int)box5.getRect().getX(),
                (int)box5.getRect().getY(),
                (int)box5.getRect().getWidth(),
                (int)box5.getRect().getHeight());
        g.fillRect((int)box6.getRect().getX(),
                (int)box6.getRect().getY(),
                (int)box6.getRect().getWidth(),
                (int)box6.getRect().getHeight());
        g.fillRect((int)box7.getRect().getX(),
                (int)box7.getRect().getY(),
                (int)box7.getRect().getWidth(),
                (int)box7.getRect().getHeight());
        g.fillRect((int)box8.getRect().getX(),
                (int)box8.getRect().getY(),
                (int)box8.getRect().getWidth(),
                (int)box8.getRect().getHeight());
        if(laseron) {
            g.setColor(Color.MAGENTA);
            g.fillRect((int) laser1.getX(),
                    (int) laser1.getY(),
                    (int) laser1.getWidth(),
                    (int) laser1.getHeight());
            g.fillRect((int) laser2.getX(),
                    (int) laser2.getY(),
                    (int) laser2.getWidth(),
                    (int) laser2.getHeight());
            g.fillRect((int) laser3.getX(),
                    (int) laser3.getY(),
                    (int) laser3.getWidth(),
                    (int) laser3.getHeight());
            g.fillRect((int) laser4.getX(),
                    (int) laser4.getY(),
                    (int) laser4.getWidth(),
                    (int) laser4.getHeight());
            g.fillRect((int) laser5.getX(),
                    (int) laser5.getY(),
                    (int) laser5.getWidth(),
                    (int) laser5.getHeight());
            g.fillRect((int) laser6.getX(),
                    (int) laser6.getY(),
                    (int) laser6.getWidth(),
                    (int) laser6.getHeight());
        }
        g.setColor(Color.WHITE);
        g.fillRect((int) laser1box.getX(),
                (int) laser1box.getY(),
                (int) laser1box.getWidth(),
                (int) laser1box.getHeight());

        g.fillRect((int) laser2box.getX(),
                (int) laser2box.getY(),
                (int) laser2box.getWidth(),
                (int) laser2box.getHeight());

        g.fillRect((int) laser3box.getX(),
                (int) laser3box.getY(),
                (int) laser3box.getWidth(),
                (int) laser3box.getHeight());

        g.fillRect((int) laser4box.getX(),
                (int) laser4box.getY(),
                (int) laser4box.getWidth(),
                (int) laser4box.getHeight());

        g.fillRect((int) laser5box.getX(),
                (int) laser5box.getY(),
                (int) laser5box.getWidth(),
                (int) laser5box.getHeight());

        g.fillRect((int) laser6box.getX(),
                (int) laser6box.getY(),
                (int) laser6box.getWidth(),
                (int) laser6box.getHeight());

        g.setColor(Color.BLUE);//CHANGE TO BROWN LATER
        g.fillRect((int)player.getRect().getX(),
                (int)player.getRect().getY(),
                (int)player.getRect().getWidth(),
                (int)player.getRect().getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(0,500,500,100);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Sans Serif",Font.BOLD,20));
        g.drawString("Level 5       Lives: "+player.getLives(),150,550);}
    public void run() {
        long startTime=System.nanoTime();
        long updatesDone=0;
        long updatesDone3=0;
        while(true)
        {
            long updatesNeed=(long)(((System.nanoTime()-startTime)/1000000)/  timeBetweenUpdates) ;
            boolean shouldRepaint=false;
            for(;updatesDone<updatesNeed;updatesDone++)
            {
                update();
                shouldRepaint=true;
            }
            if(shouldRepaint)
                repaint();
            try
            {
                Thread.sleep((long)timeBetweenUpdates);
            }catch(Exception e)
            {}

            long updatesNeed3=(long)(((System.nanoTime()-startTime)/1000000)/  lasertimeBetweenUpdates) ;
            boolean shouldRepaint3=false;
            for(;updatesDone3<updatesNeed3;updatesDone3++)
            {
                update();
                shouldRepaint3=true;
                if(laseron==true)
                    laseron=false;
                else if(laseron==false)
                    laseron=true;
            }
            if(shouldRepaint3)
                repaint();
            try
            {
                Thread.sleep((long)timeBetweenUpdates);
            }catch(Exception e)
            {}
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
        else if (e.getKeyCode() == KeyEvent.VK_UP){//&& !player.getRect().intersects(wall2.getRect())){
            player.setDirection(9);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){//&& !player.getRect().intersects(wall4.getRect())) {
            player.setDirection(6);
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT){//&& !player.getRect().intersects(wall1.getRect())&&!player.getRect().intersects(wall5.getRect())&&!player.getRect().intersects(wall6.getRect())) {
            player.setDirection(7);
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT){//&& !player.getRect().intersects(wall3.getRect())&&!player.getRect().intersects(wall5.getRect())&&!player.getRect().intersects(wall6.getRect())){
            player.setDirection(8);
        }
        else if(e.getKeyCode()==KeyEvent.VK_C)
            player.reset((int)end.getX()+5,(int)end.getY());
        else
            player.setDirection(4);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN||
                e.getKeyCode()==KeyEvent.VK_UP||
                e.getKeyCode()==KeyEvent.VK_RIGHT||
                e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            player.setDirection(4);
        }
    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }

}