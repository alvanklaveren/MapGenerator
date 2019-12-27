package Terrain;

import ColorPalette.ColorPalette;

import java.awt.*;
import ColorPalette.*;

public enum ETerrainMap {

    BLACK_AND_WHITE(Color.BLACK, Color.WHITE, 255, 10, 2, null),
    CARTOON(Color.BLUE, Color.WHITE, 255, 10, 5, new CartoonPalette()),
    DETAILED_ARCTIC(Color.BLUE, Color.WHITE, 255, 10, 0, new TransparantSeaPalette()),
    DETAILED_FOREST(Color.BLACK, Color.GREEN, 255, 10, 0, new TransparantSeaPalette()),
    DETAILED_DESERT(Color.DARK_GRAY, new Color(200,154,102), 255, 10, 0, new TransparantSeaPalette()),
    SIMPLE_ARCTIC(Color.BLUE, Color.WHITE, 255, 11, 10, new FixedSeaPalette()),
    SIMPLE_FOREST(Color.GREEN, Color.WHITE, 255, 10, 10, new FixedSeaPalette()),
    SIMPLE_DESERT( new Color(200,154,102), new Color(251,225,182), 255, 10, 10, new FixedSeaPalette()),
    CLOUDS(Color.BLACK, Color.WHITE, 255, 9, 60, null),
    SEA(new Color(20,20,100), Color.BLUE, 255, 8, 0, null),
    ;

    Color gradientStart;
    Color gradientEnd;
    int alpha;
    int octave;
    int level;
    ColorPalette colorPalette;

    ETerrainMap(Color gradientStart, Color gradientEnd, int alpha, int octave, int level, ColorPalette colorPalette) {
        this.gradientStart = gradientStart;
        this.gradientEnd = gradientEnd;
        this.alpha = alpha;
        this.octave = octave;
        this.level = level;
        this.colorPalette = colorPalette;
    }

    public TerrainMap generateMap(int width, int height) {
        TerrainMap map = new TerrainMap(width, height, gradientStart, gradientEnd, alpha, octave, level);

        if(colorPalette != null) {
            map.setFixedPalette(colorPalette);
        }

        return map;
    }

}