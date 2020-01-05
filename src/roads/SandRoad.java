package roads;

import java.awt.*;

public class SandRoad extends AbstractRoad {

    public SandRoad(float[][] heightMap){
        super(heightMap);
    }

    protected Color getColor(){
        return new Color(200, 140, 50);
    }

    protected Color getSecondaryColor(){
        return new Color(100, 50, 10);
    }

}
