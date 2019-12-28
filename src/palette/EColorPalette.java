package palette;

import terrain.TerrainMap;

import java.awt.*;

public enum EColorPalette {
    Default (0),
    BlackAndWhite (1),
    Cartoon (2),
    FixedBlueSea(3),
    TransparentSea(4),
    Realistic(5),
    ;

    int id;

    EColorPalette(int id){
        this.id = id;
    }

    public AbstractColorPalette generate(TerrainMap terrainMap){
        return generate(terrainMap, null, null);
    }

    public AbstractColorPalette generate(TerrainMap terrainMap, Color gradientStart, Color gradientEnd){

        switch(id) {
            case 0:
                return new DefaultPalette(terrainMap, gradientStart, gradientEnd);
            case 1:
                return new BlackAndWhitePalette(terrainMap);
            case 2:
                return new CartoonPalette(terrainMap);
            case 3:
                return new FixedSeaPalette(terrainMap, gradientStart, gradientEnd);
            case 4:
                return new TransparentSeaPalette(terrainMap, gradientStart, gradientEnd);
            case 5:
                return new RealisticPalette(terrainMap);
            default:
                return null;
        }
    }
}
