
import terrain.ETerrainMap;
import terrain.TerrainMap;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;


public class MapPanel extends JPanel{

    private int[][] image;

    private MouseListener mouseListener;

    public MapPanel()
    {
        int width = 1024, height = 768;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        ImageIcon icon = new ImageIcon( bi );
        add( new JLabel(icon) );

        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                image = generateTerrainImage(width, height);
                drawImage(bi, image);
                revalidate();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };

        this.addMouseListener(mouseListener);

        image = generateTerrainImage(width, height);
        drawImage(bi, image);

    }

    static int[][] generateTerrainImage(int width, int height){

        //return ETerrainMap.Realistic.generateMap(width, height).getImage();
        //return ETerrainMap.Realistic.generateMap(width, height).blend(ETerrainMap.Clouds);
        return ETerrainMap.DetailedArctic.generateMap(width, height).setBackGround(ETerrainMap.Sea).getImage();

    }

    static void drawImage(BufferedImage bi, int[][] image){
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                int colorValue = image[x][y];
                bi.setRGB(x, y, colorValue);
            }
        }
    }

}
