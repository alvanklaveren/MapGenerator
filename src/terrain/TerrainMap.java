package terrain;


import palette.AbstractColorPalette;
import palette.EColorPalette;

import java.awt.Color;

public class TerrainMap {

    private float[][] noise;
    private int alpha;
    private int octave;
    private int level;

    private int[][] backgroundImage;

    private AbstractColorPalette colorPalette = null;


    public TerrainMap(int width, int height, int alpha, int octave, int level){

        float[][] baseNoise = TerrainUtils.generateWhiteNoise(width, height);
        this.noise = TerrainUtils.GeneratePerlinNoise(baseNoise, octave);
        this.alpha = alpha;
        this.octave = octave;
        this.level = level;
    }

    public void setColorPalette(AbstractColorPalette colorPalette){ this.colorPalette = colorPalette; }

    public int getPixel(int x, int y){

        Color c = (colorPalette == null) ? EColorPalette.Default.generate(this).getColor(noise[y][x]) : colorPalette.getColor(noise[y][x]);
        return Math.round(c.getRGB());
    }

    public float[][] getNoise(){
        return noise;
    }

    public void setNoise(float[][] noise){
        this.noise = noise;
    }

    public int getHeight(){
        return noise.length;
    }

    public int getWidth(){
        return noise[0].length;
    }

    public int[][] getImage(){

        int[][] image = new int[getHeight()][getWidth()];

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                image[y][x] = getPixel(x, y);
            }
        }

        return (backgroundImage == null) ? image : TerrainUtils.replaceZeroAlpha(image, backgroundImage);
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

}
