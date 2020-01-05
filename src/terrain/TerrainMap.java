package terrain;


import palette.AbstractColorPalette;
import palette.EColorPalette;
import roads.AbstractRoad;

import java.awt.Color;

public class TerrainMap {

    private float[][] heightMap;
    private int alpha;
    private int octave;
    private int level;

    private int[][] backgroundImage;
    private int[][] roadImage;
    private AbstractRoad road;

    private AbstractColorPalette colorPalette = null;


    public TerrainMap(int width, int height, int alpha, int octave, int level){

        float[][] baseNoise = TerrainUtils.generateWhiteNoise(width, height);
        this.heightMap = TerrainUtils.GeneratePerlinNoise(baseNoise, octave);
        this.alpha = alpha;
        this.octave = octave;
        this.level = level;
    }

    public void setColorPalette(AbstractColorPalette colorPalette){ this.colorPalette = colorPalette; }

    public int getPixel(int x, int y){

        Color c = (colorPalette == null) ? EColorPalette.Default.generate(this).getColor(heightMap[y][x]) : colorPalette.getColor(heightMap[y][x]);
        return Math.round(c.getRGB());
    }

    public float[][] getHeightMap(){
        return heightMap;
    }

    public void setHeightMap(float[][] heightMap){
        this.heightMap = heightMap;
    }

    public int getHeight(){
        return heightMap.length;
    }

    public int getWidth(){
        return heightMap[0].length;
    }

    public int[][] getImage(){

        int[][] image = new int[getHeight()][getWidth()];

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                image[y][x] = getPixel(x, y);
            }
        }

        image = (roadImage == null) ? image : TerrainUtils.replaceZeroAlpha(roadImage, image);
        image = (backgroundImage == null) ? image : TerrainUtils.replaceZeroAlpha(image, backgroundImage);

        return image;
    }

    /**
     * Blends two images together, based on color and transparancy (for instance used for Clouds)
     * @param eTerrainMap
     * @return
     */
    public int[][] blend(ETerrainMap eTerrainMap) {

        TerrainMap clouds = eTerrainMap.generateMap(getHeight(), getWidth());
        return TerrainUtils.blend(getImage(), clouds.getImage());
    }

    /**
     * a background can be used (picture or whatever, in this case its a terrain map) to populate all pixels that
     * have full transparancy (alpha=0). This is obviously done in the palette used for this terrain
     *
     * @param eTerrainMap
     */
    public TerrainMap setBackGround(ETerrainMap eTerrainMap){

        this.backgroundImage = eTerrainMap.generateMap(getHeight(), getWidth()).getImage();
        return this;
    }

    /**
     * roads can be added using a Road object. Roads contain a grid of black transparent color, with roads.
     * We can replace the transparent black with the values on the terrain map
     *
     * @param road - a child class extending from class AbstractRoad
     */
    public TerrainMap setRoad(AbstractRoad road){

        this.roadImage = road.generate();
        return this;
    }

    public TerrainMap setRoad(AbstractRoad road, int numberOfRoads, int roadLength){

        this.roadImage = road.generate(numberOfRoads, roadLength);
        return this;
    }

}
