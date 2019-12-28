package palette;

import terrain.TerrainMap;

import java.awt.Color;

public class FixedSeaPalette extends AbstractColorPalette {

    private AbstractColorPalette terrainPalette;

    public FixedSeaPalette(TerrainMap terrainMap){

        super(terrainMap);
        terrainPalette = EColorPalette.Default.generate(terrainMap);
    };

    public Color getColor(float noise){

        int maxLevel = 2;
        int modifier = (int) Math.floor(noise * maxLevel);

        return modifier == 0 ? Color.BLUE : terrainPalette.getColor(noise);
    }
}
