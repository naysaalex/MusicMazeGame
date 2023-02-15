import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Level3 extends JPanel implements Runnable,KeyListener{
    int x=5;
    int y=5;
    public static final int UPS=120;
    private UDLRbox box1,box2,box3,box4,box5,box6,box7,box8,box9,box10,box11,box12,box13,box14,box15,box16,box17;
    public UDLRbox player;
    private Wall wall1,wall2,wall3,wall4,wall5,wall6,wall7,wall8,wall9;
    private Rectangle end;
    private Player oval;
    private double timeBetweenUpdates=1000.0/UPS;
    private BufferedImage buffer;
    public boolean endgame=false;
    public boolean win=false;
    Clip clip;
    Collidable c= new Collidable(oval);
    public Level3(int panelWidth, int panelHeight,int lives)
    {
        super();
        try
        {
//            URL fileLocation= new URL("file:WR.wav");
//            question5= Applet.newAudioClip(fileLocation);
//            question5.play();
//            question5.loop();
//            Thread.sleep(3000);
            AudioInputStream ao= AudioSystem.getAudioInputStream(new File("C:\\Users\\naysa\\Desktop\\oth School\\highschool\\compsci 2\\IdeaProjects\\FlyingCoconut\\FlyingCoconut\\Music\\BBY.wav").getAbsoluteFile());
            clip= AudioSystem.getClip();
            clip.open(ao);
            clip.start();

        }
        catch(Exception ex)
        {
            System.out.println("Can not find: WR.wav");
        }
        setSize(panelWidth, panelHeight);
        box1= new UDLRbox(new Rectangle(305,450,20,20),
                UDLRbox.RIGHT, Color.RED);
        box2= new UDLRbox(new Rectangle(270,80,20,20),
                UDLRbox.RIGHT, Color.RED);
        box3= new UDLRbox(new Rectangle(393,151,20,20),
                UDLRbox.UP, Color.RED);
        box4= new UDLRbox(new Rectangle(195,151,20,20),
                UDLRbox.DOWN, Color.RED);
        box8= new UDLRbox(new Rectangle(10,150,20,20),
                UDLRbox.DOWN, Color.RED);
        box9= new UDLRbox(new Rectangle(158,151,20,20),
                UDLRbox.UP, Color.RED);
        box10= new UDLRbox(new Rectangle(70,80,20,20),
                UDLRbox.RIGHT, Color.RED);
        box5= new UDLRbox(new Rectangle(255,400,20,20),
                UDLRbox.RIGHT, Color.RED);
        box6= new UDLRbox(new Rectangle(90,400,20,20),
                UDLRbox.RIGHT, Color.RED);
        box7= new UDLRbox(new Rectangle(188,471,20,20),
                UDLRbox.LEFT, Color.RED);
        box11= new UDLRbox(new Rectangle(305,375,20,20),
                UDLRbox.RIGHT, Color.RED);
        box12= new UDLRbox(new Rectangle(85,345,20,20),
                UDLRbox.RIGHT, Color.RED);
        box13= new UDLRbox(new Rectangle(305,200,20,20),
                UDLRbox.RIGHT, Color.RED);
        box14= new UDLRbox(new Rectangle(400,285,20,20),
                UDLRbox.LEFT, Color.RED);
        box15= new UDLRbox(new Rectangle(190,285,20,20),
                UDLRbox.RIGHT, Color.RED);
        box16= new UDLRbox(new Rectangle(85,200,20,20),
                UDLRbox.RIGHT, Color.RED);
        box17= new UDLRbox(new Rectangle(163,285,20,20),
                UDLRbox.LEFT, Color.RED);
        player= new UDLRbox(new Rectangle(10,10,20,20),
                UDLRbox.MOC, Color.BLACK,lives);
        setSize(500,600);
        buffer= new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        setVisible(true);
        wall1= new Wall(new Rectangle(0,0,5,500));
        wall2= new Wall(new Rectangle(0,0,500,5));
        wall3= new Wall(new Rectangle(495,0,5,500));
        wall4= new Wall(new Rectangle(0,495,500,5));
        wall5= new Wall( new Rectangle(0,70,420,5));
        wall6= new Wall(new Rectangle(420,70,5,370));
        wall7= new Wall(new Rectangle(300,180,5,320));
        wall8= new Wall(new Rectangle(183,70,5,325));
        wall9= new Wall(new Rectangle(80,180,5,320));
        end= new Rectangle(0,500-50,50,50);
        oval=new Player(new Rectangle(getX(),getY(),13,13));

        addKeyListener( this);


        Thread t=new Thread(this);
        t.start();
    }
    public void update()
    {
//        if(player.getLives()==0){
//            clip.stop();
//            player.setLives(1);
//            setVisible(false);
//            new GameBase(new lose(500,600),"Set Panel Size", 500,600);
//        }
        if(player.getRect().intersects(end))
        {
            System.out.print("I'm donee");
            clip.stop();
            player.setLives(player.getLives()+1);
            player.reset(300,300);
            setVisible(false);
            //setPreferredSize(new Dimension(0, 0));

            new GameBase(new Level4(500,600,player.getLives()+1),"Set Panel Size", 500,600);

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
        box9.update();
        box10.update();
        box11.update();
        box12.update();
        box13.update();
        box14.update();
        box15.update();
        box16.update();
        box17.update();
        if (player.getDirection() == 9)
            player.up();
        if (player.getDirection() == 6)
            player.down();
        if (player.getDirection() == 7)
            player.left();
        if (player.getDirection() == 8)
            player.right();
        if (box1.getRect().getX() < 305 || box1.getRect().getX() + box1.getRect().getWidth() > getWidth()-5)
            box1.changeDirection();
        if ( box11.getRect().getX()<305||box11.getRect().getX()+box11.getRect().getWidth()>420)
            box11.changeDirection();
        if ( box12.getRect().getX()<85||box12.getRect().getX()+box12.getRect().getWidth()>183)
            box12.changeDirection();
        if ( box13.getRect().getX()<305||box13.getRect().getX()+box13.getRect().getWidth()>420)
            box13.changeDirection();
        if ( box14.getRect().getX()<305||box14.getRect().getX()+box14.getRect().getWidth()>420)
            box14.changeDirection();
        if ( box15.getRect().getX()<188||box15.getRect().getX()+box15.getRect().getWidth()>300)
            box15.changeDirection();
        if ( box16.getRect().getX()<85||box16.getRect().getX()+box16.getRect().getWidth()>183)
            box16.changeDirection();
        if ( box17.getRect().getX()<85||box17.getRect().getX()+box17.getRect().getWidth()>183)
            box17.changeDirection();
        if (box2.getRect().getX() < 195 || box2.getRect().getX() + box2.getRect().getWidth() > 413|| box2.getRect().getY()<80||box2.getRect().getY()>151)
            box2.changeDirectionCircle();
        if (box3.getRect().getX() < 195 || box3.getRect().getX() + box3.getRect().getWidth() > 413|| box3.getRect().getY()<80||box3.getRect().getY()>151)
            box3.changeDirectionCircle();
        if (box4.getRect().getX() < 195 || box4.getRect().getX() + box4.getRect().getWidth() > 413|| box4.getRect().getY()<80||box4.getRect().getY()>151)
            box4.changeDirectionCircle();
        if (box8.getRect().getX() < 10 || box8.getRect().getX() + box8.getRect().getWidth() > 178|| box8.getRect().getY()<80||box8.getRect().getY()>151)
            box8.changeDirectionCircle();
        if (box9.getRect().getX() < 10 || box9.getRect().getX() + box9.getRect().getWidth() > 178|| box9.getRect().getY()<80||box9.getRect().getY()>151)
            box9.changeDirectionCircle();
        if (box10.getRect().getX() < 10 || box10.getRect().getX() + box10.getRect().getWidth() > 178|| box10.getRect().getY()<80||box10.getRect().getY()>151)
            box10.changeDirectionCircle();
        if (box5.getRect().getX() < 90 || box5.getRect().getX() + box5.getRect().getWidth() > 295|| box5.getRect().getY()<400||box5.getRect().getY()>471)
            box5.changeDirectionCircle();
        if (box6.getRect().getX() < 90 || box6.getRect().getX() + box6.getRect().getWidth() > 295|| box6.getRect().getY()<400||box6.getRect().getY()>471)
            box6.changeDirectionCircle();
        if (box7.getRect().getX() < 90 || box7.getRect().getX() + box7.getRect().getWidth() > 295|| box7.getRect().getY()<400||box7.getRect().getY()>471)
            box7.changeDirectionCircle();
        //System.out.println( " wall 1"+wall1.getRect().getX() +" h "+wall1.getRect());
        if (player.getRect().intersects(box15.getRect()) ||player.getRect().intersects(box16.getRect()) ||player.getRect().intersects(box17.getRect()) ||player.getRect().intersects(box13.getRect()) ||player.getRect().intersects(box14.getRect()) ||player.getRect().intersects(box12.getRect()) ||player.getRect().intersects(box11.getRect()) ||player.getRect().intersects(box1.getRect()) || player.getRect().intersects(box2.getRect()) || player.getRect().intersects(box3.getRect()) || player.getRect().intersects(box4.getRect()) || player.getRect().intersects(box5.getRect()) || player.getRect().intersects(box6.getRect())|| player.getRect().intersects(box7.getRect())|| player.getRect().intersects(box8.getRect())||player.getRect().intersects(box9.getRect())||player.getRect().intersects(box10.getRect()))
        {
            player.setLives(player.getLives() -1);
            System.out.println(player.getLives());
            player.reset(10,10);
            if(player.getLives()==0)
            {
                clip.stop();
             //   player.setLives(player.getLives()+1);
                player.reset(10,10);
                setVisible(false);
                new GameBase(new lose(500,600),"Set Panel Size", 500,600);
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

        /*g.fillRect(10,450,30,30);
        g.fillRect(10,470,80,20);
        g.fillRect(90,450,30,40);*/
        g.setColor(Color.GREEN);
        g.fillRect(0,500-50,50,50);
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,50,50);
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
        g.fillRect((int)box9.getRect().getX(),
                (int)box9.getRect().getY(),
                (int)box9.getRect().getWidth(),
                (int)box9.getRect().getHeight());
        g.fillRect((int)box10.getRect().getX(),
                (int)box10.getRect().getY(),
               (int)box10.getRect().getWidth(),
                (int)box10.getRect().getHeight());
        g.fillRect((int)box11.getRect().getX(),
                (int)box11.getRect().getY(),
                (int)box11.getRect().getWidth(),
                (int)box11.getRect().getHeight());
        g.fillRect((int)box12.getRect().getX(),
                (int)box12.getRect().getY(),
                (int)box12.getRect().getWidth(),
                (int)box12.getRect().getHeight());
        g.fillRect((int)box13.getRect().getX(),
                (int)box13.getRect().getY(),
                (int)box13.getRect().getWidth(),
                (int)box13.getRect().getHeight());
        g.fillRect((int)box14.getRect().getX(),
                (int)box14.getRect().getY(),
                (int)box14.getRect().getWidth(),
                (int)box14.getRect().getHeight());
        g.fillRect((int)box15.getRect().getX(),
                (int)box15.getRect().getY(),
                (int)box15.getRect().getWidth(),
                (int)box15.getRect().getHeight());
        g.fillRect((int)box16.getRect().getX(),
                (int)box16.getRect().getY(),
                (int)box16.getRect().getWidth(),
                (int)box16.getRect().getHeight());
        g.fillRect((int)box17.getRect().getX(),
                (int)box17.getRect().getY(),
                (int)box17.getRect().getWidth(),
                (int)box17.getRect().getHeight());
        g.setColor(Color.BLUE);//CHANGE TO BROWN LATER
        g.fillRect((int)player.getRect().getX(),
                (int)player.getRect().getY(),
                (int)player.getRect().getWidth(),
                (int)player.getRect().getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(0,500,500,100);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Sans Serif",Font.BOLD,20));
        g.drawString("Level 3       Lives: "+player.getLives(),150,550);}
    public void run() {
        long startTime=System.nanoTime();
        long updatesDone=0;
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
            player.reset((int)end.getX()+30,(int)end.getY());
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