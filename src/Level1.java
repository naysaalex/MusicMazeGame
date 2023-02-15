import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Level1 extends JPanel implements Runnable,KeyListener{
    int x=5;
    int y=5;
    public static final int UPS=140;
    private UDLRbox box1,box2,box3,box4,box5,box6;
    public UDLRbox player;
    private Wall wall1,wall2,wall3,wall4,wall5,wall6;
    private Rectangle end;
    private Player oval;
    private double timeBetweenUpdates=1000.0/UPS;
    private BufferedImage buffer;
    Collidable c= new Collidable(oval);
    AudioClip question5=null;
    Clip clip;
    public Level1(int panelWidth, int panelHeight)
    {
        super();
        try
        {
//            URL fileLocation= new URL("file:WR.wav");
//            question5= Applet.newAudioClip(fileLocation);
//            question5.play();
//            question5.loop();
//            Thread.sleep(3000);
            AudioInputStream ao= AudioSystem.getAudioInputStream(new File("C:\\Users\\naysa\\Desktop\\oth School\\highschool\\compsci 2\\IdeaProjects\\FlyingCoconut\\FlyingCoconut\\Music\\WR.wav").getAbsoluteFile());
            clip= AudioSystem.getClip();
            clip.open(ao);
            clip.start();

        }
        catch(Exception ex)
        {
            System.out.println("Can not find: WR.wav");
        }

        setSize(panelWidth, panelHeight);
        box1= new UDLRbox(new Rectangle(70,100,20,20),
                UDLRbox.RIGHT, Color.RED);
        box2= new UDLRbox(new Rectangle(275,200,20,20),
                UDLRbox.LEFT, Color.RED);
        box3= new UDLRbox(new Rectangle(70,300,20,20),
                UDLRbox.RIGHT, Color.RED);
        box4= new UDLRbox(new Rectangle(305,200,20,20),
                UDLRbox.RIGHT, Color.RED);
        box5= new UDLRbox(new Rectangle(getWidth()-25,300,20,20),
                UDLRbox.LEFT, Color.RED);
        box6= new UDLRbox(new Rectangle(getWidth()-25,100,20,20),
                UDLRbox.LEFT, Color.RED);
        player= new UDLRbox(new Rectangle(10,10,20,20),
                UDLRbox.MOC, Color.BLACK,5);
        setSize(500,600);
        buffer= new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        setVisible(true);
        wall1= new Wall(new Rectangle(0,0,5,500));
        wall2= new Wall(new Rectangle(0,0,500,5));
        wall3= new Wall(new Rectangle(495,0,5,500));
        wall4= new Wall(new Rectangle(0,495,500,5));
        wall5= new Wall( new Rectangle(65,0,5,450));
        wall6= new Wall(new Rectangle(300,80,5,420));
        end= new Rectangle(500-50,500-50,50,50);
        oval=new Player(new Rectangle(getX(),getY(),13,13));

        addKeyListener( this);


        Thread t=new Thread(this);
        t.start();

    }
    public void update()
    {
//        if(player.getLives()==0){
//            clip.stop();
//            player.setLives(-1);
//            setVisible(false);
//            new GameBase(new lose(500,600),"Set Panel Size", 500,600);
//
//        }
        if(player.getRect().intersects(end))
        {
            System.out.print("I'm donee");
            clip.stop();
            player.setLives(player.getLives()+1);
            player.reset(300,300);
            setVisible(false);
            //setPreferredSize(new Dimension(0, 0));

            new GameBase(new Level2(500,600,player.getLives()+1),"Set Panel Size", 500,600);

        }

        //oval.move(oval.getDirection());
            box1.update();
            box2.update();
            box3.update();
            box4.update();
            box5.update();
            box6.update();
            if (player.getDirection() == 9)
                player.up();
            if (player.getDirection() == 6)
                player.down();
            if (player.getDirection() == 7)
                player.left();
            if (player.getDirection() == 8)
                player.right();
            if (box1.getRect().getX() < 70 || box1.getRect().getX() + box1.getRect().getWidth() > 300)
                box1.changeDirection();
            if (box2.getRect().getX() < 70 || box2.getRect().getX() + box2.getRect().getWidth() > 300)
                box2.changeDirection();
            if (box3.getRect().getX() < 70 || box3.getRect().getX() + box3.getRect().getWidth() > 300)
                box3.changeDirection();
            if (box4.getRect().getX() < 305 || box4.getRect().getX() + box4.getRect().getWidth() > getWidth()-5)
                box4.changeDirection();
            if (box5.getRect().getX() < 305 || box5.getRect().getX() + box5.getRect().getWidth() > getWidth()-5)
                box5.changeDirection();
            if (box6.getRect().getX() < 305 || box6.getRect().getX() + box6.getRect().getWidth() > getWidth()-5)
                box6.changeDirection();
            //System.out.println( " wall 1"+wall1.getRect().getX() +" h "+wall1.getRect());
            if (player.getRect().intersects(box1.getRect()) || player.getRect().intersects(box2.getRect()) || player.getRect().intersects(box3.getRect()) || player.getRect().intersects(box4.getRect()) || player.getRect().intersects(box5.getRect()) || player.getRect().intersects(box6.getRect()))
                {
                    player.setLives(player.getLives() -1);
                    System.out.println(player.getLives());
                    player.reset(10,10);
                    if(player.getLives()==0)
                    {
                        clip.stop();
                       // player.setLives(player.getLives()+1);
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
                player.reset(65-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset(70,(int)player.getRect().getY());
            }
            if(player.getDirection()==9)
            {
                player.reset((int)player.getRect().getX(),(int)wall5.getRect().getY()+(int)wall5.getRect().getHeight());
            }
        }
        if(player.getRect().intersects(wall6.getRect()))
        {
            if(player.getDirection()==8)
            {
                player.reset(300-20,(int)player.getRect().getY());
            }
            if(player.getDirection()==7)
            {
                player.reset(305,(int)player.getRect().getY());
            }
            if(player.getDirection()==6)
            {
                player.reset((int)player.getRect().getX(),(int)wall6.getRect().getY()-20);
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
        /*g.fillRect(10,450,30,30);
        g.fillRect(10,470,80,20);
        g.fillRect(90,450,30,40);*/
        g.setColor(Color.GREEN);
        g.fillRect(500-50,500-50,50,50);
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
        g.setColor(Color.BLUE);//CHANGE TO BROWN LATER
        g.fillRect((int)player.getRect().getX(),
                (int)player.getRect().getY(),
                (int)player.getRect().getWidth(),
                (int)player.getRect().getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(0,500,500,100);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Sans Serif",Font.BOLD,20));
        g.drawString("Level 1       Lives: "+player.getLives(),150,550);
    }
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