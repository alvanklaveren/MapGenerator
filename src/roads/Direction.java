package roads;

public class Direction{

    private ECompass compass, initialCompass;

    public Direction(int stepX, int stepY){
        this.compass = ECompass.getBySteps(stepX, stepY);
        this.initialCompass = compass;
    }

    public Direction() {
        init();
        this.initialCompass = compass;
    }

    public ECompass getCompass(){
        return compass;
    }

    public void setCompass(ECompass compass){
        this.compass = compass;
    }

    public void setInitialCompass(ECompass initialCompass){
        this.initialCompass = initialCompass;
    }

    public void setStepX(int stepX){
        compass = ECompass.getBySteps(stepX, compass.stepY());
    }

    public void setStepY(int stepY){
        compass = ECompass.getBySteps(compass.stepX(), stepY);
    }

    public void init(){

        compass = ECompass.STAND_STILL;

        while (compass == ECompass.STAND_STILL) {
            compass = ECompass.getBySteps(getRandomStep(false), getRandomStep(false));
        }
    }

    public Direction nextDirection(){

        Direction newStartDirection = new Direction();

        newStartDirection.setInitialCompass(initialCompass);

        // if the original direction was not moving in either the X or Y, then leave that step at 0
        if(initialCompass.stepY == 0){
            newStartDirection.setStepX(initialCompass.stepX()); // if Y doesn't move, then X should !!
        } else {
            if (newStartDirection.getCompass().stepX() == -initialCompass.stepX()) {
                newStartDirection.setCompass(ECompass.getBySteps(initialCompass.stepX(), newStartDirection.getCompass().stepY()));
            }
        }


        if(initialCompass.stepX == 0){
            newStartDirection.setStepY(initialCompass.stepY()); // if X doesn't move, then Y should !!
        } else {
            if (newStartDirection.getCompass().stepY() == -initialCompass.stepY()) {
                newStartDirection.setCompass(ECompass.getBySteps(newStartDirection.getCompass().stepX(), initialCompass.stepX()));
            }
        }

        return newStartDirection;
    }

    private int getRandomStep(boolean forceValue){

        double random = Math.random()*100;
        int step = 0;

        if(forceValue){
            if (random <= 50) {
                step = -1;
            } else {
                step = 1;
            }

        } else {
            if (random <= 34) {
                step = -1;
            } else if (random >= 67) {
                step = 1;
            } else {
                step = 0;
            }
        }

        return step;
    }

}
