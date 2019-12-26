package ColorPalette;

import java.awt.*;

public class FixedSeaPalette extends ColorPalette{

    private int alpha = 255;
    private int level = 2;


    public Color getColor(float noise){
        int modifier = (int) Math.floor(noise * level);

        Color color = null;

        switch(modifier) {
            case 0:
                color = Color.BLUE;
                break;
        }

        return color;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setAlpha(int alpha){
        this.alpha = alpha;
    }
}
