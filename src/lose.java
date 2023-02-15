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

public class lose extends JPanel implements KeyListener{
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
    public lose(int panelWidth, int panelHeight)
    {
        super();
        try
        {
//            URL fileLocation= new URL("file:WR.wav");
//            question5= Applet.newAudioClip(fileLocation);
//            question5.play();
//            question5.loop();
//            Thread.sleep(3000);
            AudioInputStream ao= AudioSystem.getAudioInputStream(new File("C:\\Users\\naysa\\Desktop\\oth School\\highschool\\compsci 2\\IdeaProjects\\FlyingCoconut\\FlyingCoconut\\Music\\SNS.wav").getAbsoluteFile());
            clip= AudioSystem.getClip();
            clip.open(ao);
            clip.start();

        }
        catch(Exception ex)
        {
            System.out.println("Can not find: EIA.wav");
        }
        setSize(panelWidth, panelHeight);
        addKeyListener(this );
    }


    public void paint(Graphics g)
    {
        g.setColor(Color.pink);
        g.fillRect(0,500,500,100);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Sans Serif",Font.BOLD,40));
        g.drawString("YOU LOSE!!!",150,100);
        g.setFont(new Font("Sans Serif",Font.BOLD,20));
        g.drawString("Your options:",200,200);
        g.drawString("press the 'ESC' key if you want the L",80,250);
        g.drawString("OR",235,300);
        g.drawString("press the 'R' key to try again",125,350);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
        else if (e.getKeyCode() == KeyEvent.VK_R)
        {
            clip.stop();
            setVisible(false);
            new GameBase(new Level1(500,600),"Set Panel Size", 500,600);
        }
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