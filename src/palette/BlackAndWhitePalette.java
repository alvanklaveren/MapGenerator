package palette;

import terrain.TerrainMap;

import java.awt.Color;

public class BlackAndWhitePalette extends AbstractColorPalette {

    public BlackAndWhitePalette(TerrainMap terrainMap){
        super(terrainMap);
    };

    public Color getColor(float noise){

        int maxLevel = 2;
        int modifier = (int) Math.floor(noise * maxLevel);

        return modifier == 0 ? Color.BLACK : Color.WHITE;
    }

}
