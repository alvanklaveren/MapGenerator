package terrain;

/**
 * Helper class to store range of percentages of map over which a certain (gradient) color has to be used
 */
public class TerrainLevel {
    private int level;
    private double minPercentage, maxPercentage;

    public TerrainLevel(int level, double minPercentage, double maxPercentage) {

        this.level = level;
        this.minPercentage = minPercentage;
        this.maxPercentage = maxPercentage;
    }

    public int getLevel(){ return level; }

    public double getMinPercentage() {
        return minPercentage;
    }

    public double getMaxPercentage() {
        return maxPercentage;
    }

    public double getRangeModifier(){
        return (100 / (getMaxPercentage() - getMinPercentage()));
    }

}
