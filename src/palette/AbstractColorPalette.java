package palette;

import terrain.TerrainMap;

import java.awt.*;

public abstract class AbstractColorPalette {

    protected int mapWidth, mapHeight;
    protected Color gradientStart, gradientEnd;

    public AbstractColorPalette(TerrainMap terrainMap, Color gradientStart, Color gradientEnd){

        this.mapWidth = terrainMap.getHeight();
        this.mapHeight = terrainMap.getWidth();
        this.gradientStart = gradientStart;
        this.gradientEnd = gradientEnd;
    };

    public AbstractColorPalette(TerrainMap terrainMap){

        this.mapWidth = terrainMap.getHeight();
        this.mapHeight = terrainMap.getWidth();
    };

    public abstract Color getColor(float noise);

    protected Color getGradientColor(Color gradientStart, Color gradientEnd, double percentage, float noise){

        if(percentage >= 1) { percentage = percentage / 100; }

        if(percentage <= 0.0){ percentage = 0.01; }

        float u = 1 - (noise * (float) percentage);

        Color color = new Color((int) ((gradientStart.getRed() * u + gradientEnd.getRed() * noise * percentage)),
                (int) ((gradientStart.getGreen() * u + gradientEnd.getGreen() * noise * percentage)),
                (int) ((gradientStart.getBlue() * u + gradientEnd.getBlue() * noise * percentage))
        );

        return color;
    }

}
