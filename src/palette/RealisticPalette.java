package palette;

import terrain.TerrainLevel;
import terrain.TerrainMap;

import java.awt.*;

public class RealisticPalette extends AbstractColorPalette {

    public RealisticPalette(TerrainMap terrainMap){
        super(terrainMap);
    };

    public Color getColor(float noise) {

        Color color = null;

        double percentage = Math.floor(noise * 100);

        double levels[] = {0.0, 50.0, 51.0, 70.0, 80.0, 85.0, 90.0, 100.0};

        TerrainLevel selectedLevel = null;
        for(int i = 0; i<levels.length -1; i++){
            if (percentage >= levels[i] && percentage < levels[i+1]) {
                selectedLevel = new TerrainLevel(i, levels[i], levels[i+1]);
                break;
            }
        }

        if (selectedLevel == null){
            throw new RuntimeException("could not derive terrain level");
        }

        double modifiedPercentage = (percentage - selectedLevel.getMinPercentage())  * selectedLevel.getRangeModifier();

        switch (selectedLevel.getLevel()) {
            case 0:
                color = getGradientColor(new Color(0,0,50), new Color(50,50, 255), modifiedPercentage, noise);
                break;
            case 1:
                color = getGradientColor(new Color(225,180,13), new Color(225,180,13), modifiedPercentage, noise);
                break;
            case 2:
                color = getGradientColor(new Color(35,100,23), new Color(50,150,53), modifiedPercentage, noise);
                break;
            case 3:
                color = getGradientColor(new Color(200,154,112), new Color(251,225,182), modifiedPercentage, noise);
                break;
            case 4:
                color = getGradientColor(new Color(251,225,182), Color.DARK_GRAY, modifiedPercentage, noise);
                break;
            case 5:
               color = getGradientColor(Color.DARK_GRAY, Color.GRAY, modifiedPercentage, noise);
               break;
            case 6:
                color = getGradientColor(Color.LIGHT_GRAY, Color.WHITE, modifiedPercentage, noise);
                break;
        }

        return color;
    }

}
