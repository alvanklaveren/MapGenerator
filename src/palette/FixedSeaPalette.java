package palette;

import terrain.TerrainMap;

import java.awt.Color;

public class FixedSeaPalette extends AbstractColorPalette {

    private AbstractColorPalette terrainPalette;

    public FixedSeaPalette(TerrainMap terrainMap){

        super(terrainMap);

        this.gradientStart = Color.BLACK;
        this.gradientEnd = Color.WHITE;
        terrainPalette = EColorPalette.Default.generate(terrainMap, gradientStart, gradientEnd);
    };

    public FixedSeaPalette(TerrainMap terrainMap, Color gradientStart, Color gradientEnd){

        super(terrainMap);
        this.gradientStart = gradientStart;
        this.gradientEnd = gradientEnd;
    }


    public Color getColor(float noise){

        int maxLevel = 2;
        int modifier = (int) Math.floor(noise * maxLevel);

        return modifier == 0 ? Color.BLUE : terrainPalette.getColor(noise);
    }
}
