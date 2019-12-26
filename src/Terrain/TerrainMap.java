package Terrain;

import ColorPalette.ColorPalette;

import java.awt.*;

public class TerrainMap {

    private float[][] noise;
    private int alpha;
    private int octave;
    private int level;

    private ColorPalette colorPalette = null;

    private Color gradientStart, gradientEnd;

    public TerrainMap(int width, int height, Color gradientStart, Color gradientEnd, int alpha, int octave, int level){

        float[][] baseNoise = MapAlgorithms.generateWhiteNoise(width, height);
        this.noise = MapAlgorithms.GeneratePerlinNoise(baseNoise, octave);
        this.gradientStart = gradientStart;
        this.gradientEnd = gradientEnd;
        this.alpha = alpha;
        this.octave = octave;
        this.level = level;
    }

    public void setFixedPalette(ColorPalette colorPalette){
        this.colorPalette = colorPalette;
    }

    public int get(int x, int y){
        return Math.round(getColor(gradientStart, gradientEnd, noise[x][y]).getRGB());
    }

    public void setGradientStart(Color gradientStart){
        this.gradientStart = gradientStart;
    }

    public void setGradientEnd(Color gradientEnd){
        this.gradientEnd = gradientEnd;
    }

    public void setAlpha(int alpha){
        this.alpha = alpha;
    }

    public float[][] getNoise(){
        return noise;
    }

    public void setNoise(float[][] noise){
        this.noise = noise;
    }

    public float[][] getImage(){
        float[][] image = new float[getWidth()][getHeight()];

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                int colorValue =  get(x, y);
                image[x][y] = colorValue;
            }
        }

        return image;
    }

    public int getWidth(){
        return noise.length;
    }

    public int getHeight(){
        return noise[0].length;
    }

    private Color getColor(Color gradientStart, Color gradientEnd, float noise)
    {

        float u = 1 - noise;

        Color color = new Color((int)(gradientStart.getRed() * u + gradientEnd.getRed() * noise),
                (int)(gradientStart.getGreen() * u + gradientEnd.getGreen() * noise),
                (int)(gradientStart.getBlue() * u + gradientEnd.getBlue() * noise),
                alpha
        );

        if (level != 0) {
            int modifier = (int) Math.floor(noise * level);

            // black is to black.. so in that case change modifier to 1 to get a little bit of color
            modifier = (modifier == 0) ? 1: modifier;

            color = new Color((color.getRed() * modifier) / level, (color.getGreen() * modifier) / level, (color.getBlue() * modifier / level), alpha);
        }

        // if color palette exists, override color (mind that not all palettes return colors in cases where you want PART to be level dependent
        if (colorPalette != null) {
            Color colorFromPalette = colorPalette.getColor(noise);
            if (colorFromPalette != null) {
                color = colorFromPalette;
            }
        }

        return color;
    }

    public TerrainMap clone(){
        TerrainMap clone = new TerrainMap(getWidth(), getHeight(), gradientStart, gradientEnd, alpha, octave, level);
        clone.setNoise(getNoise());

        return clone;
    }

}
