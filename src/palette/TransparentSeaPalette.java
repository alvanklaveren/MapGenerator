package palette;

import terrain.TerrainMap;

import java.awt.Color;

/**
 * The transparent sea palette allows you to select a background
 * and use that to populate the pixels in the sea-part of the map
 */
public class TransparentSeaPalette extends AbstractColorPalette {

    private AbstractColorPalette terrainPalette;

    public TransparentSeaPalette(TerrainMap terrainMap){

        super(terrainMap);
        terrainPalette = EColorPalette.Default.generate(terrainMap);
    };

    public Color getColor(float noise){

        int maxLevel = 2;
        int modifier = (int) Math.floor(noise * maxLevel);

        return modifier == 0 ? new Color(0, 0, 0, 0 /*fully transparent*/) : terrainPalette.getColor(noise);
    }
}
