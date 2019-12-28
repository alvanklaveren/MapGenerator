package terrain;

import java.awt.*;
import java.util.Random;

public class TerrainUtils {

    public static float[][] generateWhiteNoise(int width, int height)
    {
        Random random = new Random(0); //Seed to 0 for testing
        random.setSeed(System.currentTimeMillis());
        float[][] noise = new float[height][width];

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                noise[j][i] = (float) random.nextDouble() % 1;
            }
        }

        return noise;
    }

    public static float[][] GenerateSmoothNoise(float[][] baseNoise, int octave)
    {
        int width = baseNoise.length;
        int height = baseNoise[0].length;

        float[][] smoothNoise = new float[height][width];

        int samplePeriod = 1 << octave; // calculates 2 ^ k
        float sampleFrequency = 1.0f / samplePeriod;

        for (int i = 0; i < width; i++)
        {
            //calculate the horizontal sampling indices
            int sample_i0 = (i / samplePeriod) * samplePeriod;
            int sample_i1 = (sample_i0 + samplePeriod) % width; //wrap around
            float horizontal_blend = (i - sample_i0) * sampleFrequency;

            for (int j = 0; j < height; j++)
            {
                //calculate the vertical sampling indices
                int sample_j0 = (j / samplePeriod) * samplePeriod;
                int sample_j1 = (sample_j0 + samplePeriod) % height; //wrap around
                float vertical_blend = (j - sample_j0) * sampleFrequency;

                //blend the top two corners
                float top = interpolate(baseNoise[sample_i0][sample_j0],
                        baseNoise[sample_i1][sample_j0], horizontal_blend);

                //blend the bottom two corners
                float bottom = interpolate(baseNoise[sample_i0][sample_j1],
                        baseNoise[sample_i1][sample_j1], horizontal_blend);

                //final blend
                smoothNoise[j][i] = interpolate(top, bottom, vertical_blend);
            }
        }

        return smoothNoise;
    }

    public static float[][] GeneratePerlinNoise(float[][] baseNoise, int octaveCount)
    {
        int width = baseNoise.length;
        int height = baseNoise[0].length;

        float[][][] smoothNoise = new float[octaveCount][][]; //an array of 2D arrays containing

        float persistance = 0.5f;

        //generate smooth noise
        for (int i = 0; i < octaveCount; i++) {
            smoothNoise[i] = GenerateSmoothNoise(baseNoise, i);
        }

        float[][] perlinNoise = new float[height][width];
        float amplitude = 1.0f;
        float totalAmplitude = 0.0f;

        //blend noise together
        for (int octave = octaveCount - 1; octave >= 0; octave--) {

            amplitude *= persistance;
            totalAmplitude += amplitude;

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    perlinNoise[j][i] += smoothNoise[octave][j][i] * amplitude;
                }
            }
        }

        //normalisation
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                perlinNoise[j][i] /= totalAmplitude;
            }
        }

        return perlinNoise;
    }

    public static float interpolate(float x0, float x1, float alpha) {
        return x0 * (1 - alpha) + alpha * x1;
    }

    public static float[][] blendImages(TerrainMap map1, TerrainMap map2, float[][] noise)
    {
        int width = map1.getHeight();
        int height = map1.getWidth();

        float[][] image = new float[height][width];

        int alpha = 255;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image[y][x] = interpolate(map1.getPixel(x,y), map2.getPixel(x,y), noise[y][x]);
            }
        }

        return image;
    }

    public static int[][] blend(int[][] image1, int[][] image2) {

        if(image1.length != image2.length || image1[0].length != image2[0].length){
            throw new RuntimeException("image sizes do not match");
        }

        int[][] image = new int[image1.length][image1[0].length];

        for(int y=0; y < image.length -1; y++){
            for(int x=0; x < image[0].length -1; x++){
                image[y][x] = blend(image1[y][x], image2[y][x]);
            }
        }

        return image;
    }

    public static int blend(int i0, int i1) {
        Color c0 = new Color(i0, true);
        Color c1 = new Color(i1, true);

        Color c = blend(c0, c1);
        return c.getRGB();
    }

    public static Color blend(Color c0, Color c1) {
        double totalAlpha = c0.getAlpha() + c1.getAlpha();
        double weight0 = c0.getAlpha() / totalAlpha;
        double weight1 = c1.getAlpha() / totalAlpha;

        double r = weight0 * c0.getRed() + weight1 * c1.getRed();
        double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
        double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
        double a = Math.max(c0.getAlpha(), c1.getAlpha());

        return new Color((int) r, (int) g, (int) b, (int) a);
    }

    public static int[][] replaceZeroAlpha(int[][] image1, int[][] image2) {

        if(image1.length != image2.length || image1[0].length != image2[0].length){
            throw new RuntimeException("image sizes do not match");
        }

        int[][] image = new int[image1.length][image1[0].length];

        for(int y=0; y < image.length -1; y++){
            for(int x=0; x < image[0].length -1; x++){
                image[y][x] = replaceZeroAlpha(image1[y][x], image2[y][x]);
            }
        }

        return image;
    }

    public static int replaceZeroAlpha(int i0, int i1) {
        Color c0 = new Color(i0, true);
        Color c1 = new Color(i1, true);

        Color c = replaceZeroAlpha(c0, c1);
        return c.getRGB();
    }


    public static Color replaceZeroAlpha(Color c0, Color c1) {
        return c0.getAlpha() == 0 ? c1 : c0;
    }

}

