package palette;

import terrain.TerrainMap;

import java.awt.*;

public class DefaultPalette extends AbstractColorPalette {

    int level, alpha;

    public DefaultPalette(TerrainMap terrainMap){

        super(terrainMap);
        level = 0;
        alpha = 255;

    };

    public DefaultPalette(TerrainMap terrainMap, Color gradientStart, Color gradientEnd){

        super(terrainMap);
        level = 0;
        alpha = 255;
        this.gradientStart = gradientStart;
        this.gradientEnd = gradientEnd;
    }

    public Color getColor(float noise) {

        float u = 1 - noise;

        Color color = new Color((int) (gradientStart.getRed() * u + gradientEnd.getRed() * noise),
                (int) (gradientStart.getGreen() * u + gradientEnd.getGreen() * noise),
                (int) (gradientStart.getBlue() * u + gradientEnd.getBlue() * noise),
                alpha
        );

        if (level != 0) {
            int modifier = (int) Math.floor(noise * level);

            // black is to black.. so in that case change modifier to 1 to get a little bit of color
            modifier = (modifier == 0) ? 1 : modifier;

            color = new Color((color.getRed() * modifier) / level, (color.getGreen() * modifier) / level, (color.getBlue() * modifier / level), alpha);
        }

        return color;
    }
}
