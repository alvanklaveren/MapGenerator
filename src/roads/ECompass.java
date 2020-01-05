package roads;

enum ECompass{
    STAND_STILL (0,0),
    NORTH ( 0,-1),
    SOUTH (0, 1),
    EAST (1, 0),
    WEST (-1, 0),
    NORTHEAST (1,1),
    NORTHWEST(-1, 1),
    SOUTHEAST (1,-1),
    SOUTHWEST(-1, -1);

    int stepX, stepY;

    ECompass(int stepX, int stepY){
        this.stepX = stepX;
        this.stepY = stepY;
    }

    public int stepX(){ return stepX; }
    public int stepY(){ return stepY; }

    public static ECompass getBySteps(int x, int y){

        for (ECompass c : ECompass.values()){

            if(c.stepX == x && c.stepY == y){
                return c;
            }
        }

        return STAND_STILL;
    }

}
