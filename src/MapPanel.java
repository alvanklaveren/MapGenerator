
import roads.SandRoad;
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

        TerrainMap map = ETerrainMap.Realistic.generateMap(width, height);
        //map.setBackGround(ETerrainMap.Sea);

        SandRoad roads = new SandRoad(map.getHeightMap());


        map.setRoad(roads, 3, 2000);

        return map.getImage();
        //return ETerrainMap.BlackAndWhite.generateMap(width, height).blend(ETerrainMap.Clouds);
        //return ETerrainMap.Realistic.generateMap(width, height).blend(ETerrainMap.Clouds);
        //return ETerrainMap.DetailedArctic.generateMap(width, height).setBackGround(ETerrainMap.Sea).getImage();

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
