import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {

    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame frame = new JFrame("Map Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MapPanel panel = new MapPanel();
                frame.add( panel );
                frame.setLocationByPlatform( true );
                frame.pack();
                frame.setVisible( true );
            }
        });
    }
}
