package ColorPalette;

import java.awt.*;

public class BlackAndWhitePalette extends ColorPalette{

    public Color getColor(float noise){
        int fixedLevel = 2;
        int modifier = (int) Math.floor(noise * fixedLevel);

        Color color = null;

        switch(modifier) {
            case 0:
                color = Color.BLACK;
                break;
            case 1:
                color = Color.WHITE;
                break;
        }

        return color;
    }

}
