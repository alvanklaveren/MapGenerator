package terrain;

import palette.*;
import java.awt.*;


public enum ETerrainMap {

    BlackAndWhite(Color.BLACK, Color.WHITE, 255, 10, 2, EColorPalette.BlackAndWhite),
    Cartoon(Color.BLUE, Color.WHITE, 255, 10, 5, EColorPalette.Cartoon),
    DetailedArctic(Color.BLUE, Color.WHITE, 255, 10, 0, EColorPalette.TransparentSea),
    DetailedForest(Color.BLACK, Color.GREEN, 255, 10, 0, EColorPalette.TransparentSea),
    DetailedDesert(Color.DARK_GRAY, new Color(200,154,102), 255, 10, 0, EColorPalette.TransparentSea),
    SimpleArctic(Color.BLUE, Color.WHITE, 255, 11, 10, EColorPalette.FixedBlueSea),
    SimpleForest(Color.GREEN, Color.WHITE, 255, 10, 10, EColorPalette.FixedBlueSea),
    SimpleDesert( new Color(200,154,102), new Color(251,225,182), 255, 10, 10, EColorPalette.FixedBlueSea),
    Clouds(Color.BLACK, Color.WHITE, 255, 8, 5, EColorPalette.Default),
    Sea(new Color(20,20,100), Color.BLUE, 255, 8, 0, EColorPalette.Default),
    Realistic(Color.BLACK, Color.WHITE, 255, 10, 5, EColorPalette.Realistic),
    ;

    Color gradientStart;
    Color gradientEnd;
    int alpha;
    int octave;
    int level;
    EColorPalette eColorPalette;

    ETerrainMap(Color gradientStart, Color gradientEnd, int alpha, int octave, int level, EColorPalette eColorPalette) {
        this.gradientStart = gradientStart;
        this.gradientEnd = gradientEnd;
        this.alpha = alpha;
        this.octave = octave;
        this.level = level;
        this.eColorPalette = eColorPalette;
    }

    public TerrainMap generateMap(int width, int height) {
        TerrainMap map = new TerrainMap(width, height, alpha, octave, level);

        if(eColorPalette != null) {
            AbstractColorPalette colorPalette = eColorPalette.generate(map, gradientStart, gradientEnd);
            map.setColorPalette(colorPalette);
        }

        return map;
    }

}