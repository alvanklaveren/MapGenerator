package ColorPalette;

import java.awt.*;

public class CartoonPalette extends ColorPalette{

    public Color getColor(float noise){
        int fixedLevel = 20;
        int modifier = (int) Math.floor(noise * fixedLevel);

        Color color = null;

        switch(modifier) {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
                color = Color.BLUE;
                break;
            case 10:
                //color = Color.YELLOW;
                color = new Color(232,206,36);
                break;
            case 11: case 12: case 13: case 14:
                //color = Color.GREEN;
                color = new Color(45,140,33);
                break;
            case 15: case 16:
                color = Color.GRAY;
                break;
            case 17: case 18:
                color = Color.LIGHT_GRAY;
                break;
            case 19: case 20:
                color = Color.WHITE;
                break;
        }

        return color;
    }
}
