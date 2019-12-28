package palette;

import terrain.TerrainMap;

import java.awt.Color;

public class CartoonPalette extends AbstractColorPalette {

    public CartoonPalette(TerrainMap terrainMap){
        super(terrainMap);
    };

    public Color getColor(float noise){

        int maxLevel = 100;
        int percentage = (int) Math.floor(noise * maxLevel);
        Color color = null;

        double levels[] = {0.0, 50.0, 51.0, 60, 70.0, 80.0, 90.0, 100.0};

        if(percentage >= levels[0] && percentage < levels[1]) {
            color = Color.BLUE;

        } else if(percentage >= levels[1] && percentage < levels[2]) {
            color = new Color(232, 206, 36); // Yellow-ish

        } else if(percentage >= levels[2] && percentage < levels[3]) {
            color = new Color(45,140,33); //GREEN-ish

        } else if(percentage >= levels[3] && percentage < levels[4]) {
            color = new Color(200,154,112);

        } else if(percentage >= levels[4] && percentage < levels[5]) {
            color = Color.GRAY;

        } else if(percentage >= levels[5] && percentage < levels[6]) {
            color = Color.LIGHT_GRAY;

        } else if(percentage >= levels[6] && percentage < levels[7]) {
            color = Color.WHITE;

        }

        return color;
    }
}
