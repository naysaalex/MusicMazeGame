import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
public class GameBase extends JFrame
{
    //public Player oval;
    public GameBase(JPanel p,String frameName, int panelWidth, int panelHeight) {
       super(frameName);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setResizable(false);
        pack();

        //oval= new Player(new Rectangle(getX(),getY(),15,15));
        Insets frameInsets = getInsets();
        int frameWidth = panelWidth
                + (frameInsets.left + frameInsets.right);
        int frameHeight = panelHeight
                + (frameInsets.top + frameInsets.bottom);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setLayout(null);
        add(p);
//        if(p.endgame&&p.win)
//        {
//            Level2 r = new Level2(panelWidth, panelHeight);
//            Insets frameInsets1 = getInsets();
//            int frameWidth1= panelWidth
//                    + (frameInsets1.left + frameInsets1.right);
//            int frameHeight1 = panelHeight
//                    + (frameInsets1.top + frameInsets1.bottom);
//            setPreferredSize(new Dimension(frameWidth1, frameHeight1));
//            setLayout(null);
//            add(r);
//        }
        pack();
        setVisible(true);
    }
    public void dispose()
    {
        dispose();
    }

}