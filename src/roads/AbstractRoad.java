package roads;

import java.awt.*;

public abstract class AbstractRoad {

    protected float[][] heightMap;
    protected int[][] roadMap;
    protected int width, height;
    protected int numberOfRoads, roadLength;

    public AbstractRoad(float[][] heightMap) {
        this(heightMap, 0,0);
    }

    public AbstractRoad(float[][] heightMap, int numberOfRoads, int roadLength) {
        init(heightMap, numberOfRoads, roadLength);
    }

    private void init(float[][] heightMap, int numberOfRoads, int roadLength){

        this.numberOfRoads = numberOfRoads;
        this.roadLength = roadLength;
        this.heightMap = heightMap;

        width = heightMap[0].length;
        height = heightMap.length;

        roadMap = new int[height][width];

        // prepopulate the entire (road)map with completely transparent pixels
        int transparentBlack = new Color(0, 0, 0, 0).getRGB();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                roadMap[y][x] = transparentBlack;
            }
        }
    }
    public int[][] generate(){
        if(numberOfRoads <= 0){ throw new RuntimeException("number Of Roads is 0 or less"); }
        if(roadLength <= 0){ throw new RuntimeException("Road length is 0 or less"); }
        return generate(numberOfRoads, roadLength);
    }

    public int[][] generate(int noRoads, int length){

        for(int num = 0; num < noRoads; num++) {

            int x = (int) Math.round(Math.random() * width);
            int y = (int) Math.round(Math.random() * height);

            Direction startDirection = new Direction();

            for (int l = 0; l < length; l++) {

                Direction nextDirection = startDirection.nextDirection();
                while (!isValidDirection(nextDirection)) {
                    nextDirection = startDirection.nextDirection();
                }
                startDirection = nextDirection;

                x += startDirection.getCompass().stepX();
                y += startDirection.getCompass().stepY();

                Color secondaryColor = getSecondaryColor();
                if(secondaryColor != null) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        for (int i = x - 1; i <= x + 1; i++) {
                            // only apply secondary color when original color is black (no color yet).
                            if (isValidPixel(i,j) && roadMap[j][i] == 0) {
                                setRoadMapXY(i, j, secondaryColor);
                            }
                        }
                    }
                }

                setRoadMapXY(x,y, getColor());
            }
        }

        return roadMap;
    }

    private void setRoadMapXY(int x, int y, Color color){

        if (x >= width || y >= height) { return; }
        if (x < 0 || y < 0) { return; }

        roadMap[y][x] = color.getRGB();
    }

    private boolean isValidPixel(int x, int y){
        if (x >= width || y >= height) { return false; }
        if (x < 0 || y < 0) { return false; }
        return true;
    }

    /**
     * The below getColor and getSecondaryColor return the colors of the road.
     * getSecondaryColor is optional, in case you do not want an extra line (color) showing
     *
     * @return
     */
    protected abstract Color getColor();
    protected abstract Color getSecondaryColor();

    /**
     * If there are any conditions that do not allow a road to be drawn in the given
     * direction, then this function has to be overridden in the child class.
     * For instance, a sandroad may try to avoid mountains
     *
     */
    protected boolean isValidDirection(Direction direction){
        return true;
    };
}