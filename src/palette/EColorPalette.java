package palette;

import terrain.TerrainMap;

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

        switch(id) {
            case 0:
                return new DefaultPalette(terrainMap);
            case 1:
                return new BlackAndWhitePalette(terrainMap);
            case 2:
                return new CartoonPalette(terrainMap);
            case 3:
                return new FixedSeaPalette(terrainMap);
            case 4:
                return new TransparentSeaPalette(terrainMap);
            case 5:
                return new RealisticPalette(terrainMap);
            default:
                return null;
        }
    }
}
