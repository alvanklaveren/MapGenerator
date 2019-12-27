
import Terrain.ETerrainMap;
import Terrain.MapAlgorithms;
import Terrain.TerrainMap;

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

        TerrainMap background = ETerrainMap.SEA.generateMap(width, height);
        TerrainMap map = ETerrainMap.DETAILED_FOREST.generateMap(width, height);
        TerrainMap clouds = ETerrainMap.CLOUDS.generateMap(width, height);

        int[][] image = MapAlgorithms.replaceZeroAlpha(map.getImage(), background.getImage());

        //image = MapAlgorithms.blend(image, clouds.getImage());

        return image;

        //        Terrain.TerrainMap terrainMap2 = terrainMap.clone();
        //        terrainMap2.setGradientStart(Color.BLUE);
        //        terrainMap2.setGradientEnd(Color.GREEN);
        //        return Terrain.MapAlgorithms.blendImages(terrainMap, terrainMap2, terrainMap.getNoise());
    }

    static void drawImage(BufferedImage bi, int[][] image){
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                int colorValue = (int) image[x][y];
                bi.setRGB(x, y, colorValue);
            }
        }
    }

}
