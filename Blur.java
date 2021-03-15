/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author SaraRoempke & MoaWahlgren
 */
public class Blur implements ManipulatePic {
    
    @Override 
    public int[][] processImage(int[][] pixelMatrix) {

        int blurry = 0;
        while(blurry < 10 ) {
            for (int i=0; i<pixelMatrix.length-1; i++) {
                for (int j=0; j<pixelMatrix[0].length-1; j++) {

                    int alpha = ((pixelMatrix[i][j] >> 24) & 0xff);

                    int[] surroundingPixels = new int[4];  
                    surroundingPixels[0] = pixelMatrix[i][j]; 
                    surroundingPixels[1] = pixelMatrix[i+1][j+1];
                    surroundingPixels[2] = pixelMatrix[i+1][j];
                    surroundingPixels[3] = pixelMatrix[i][j+1]; 

                    int red = getRedOfPixels(surroundingPixels); 
                    int green = getGreenOfPixels(surroundingPixels); 
                    int blue = getBlueOfPixels(surroundingPixels); 

                    pixelMatrix[i][j] = (alpha << 24) | (red << 16) | (green << 8) | blue;

                    //Vi vill låta färgen av pixeln vara medelvärdet av färgvärdet av tre pixlar runtomkring 
                    //För varje pixel, hämta färgvärden och sätt medel på pixel 
                }
            }

            for (int i=pixelMatrix.length-1; i>0; i--) {
                for (int j=pixelMatrix[0].length-1; j>0; j--) {

                    int alpha = ((pixelMatrix[i][j] >> 24) & 0xff);

                    int[] surroundingPixels = new int[4];  
                    surroundingPixels[0] = pixelMatrix[i][j]; 
                    surroundingPixels[1] = pixelMatrix[i-1][j-1];
                    surroundingPixels[2] = pixelMatrix[i-1][j];
                    surroundingPixels[3] = pixelMatrix[i][j-1]; 

                    int red = getRedOfPixels(surroundingPixels); 
                    int green = getGreenOfPixels(surroundingPixels); 
                    int blue = getBlueOfPixels(surroundingPixels); 

                    pixelMatrix[i][j] = (alpha << 24) | (red << 16) | (green << 8) | blue;
                }
            }
        blurry ++; 
        }
        return pixelMatrix; 
    }
    
    private int getRedOfPixels(int[] surroundingPixels) {
        int red = 0;    
        for (int i=0; i<surroundingPixels.length; i++) {
            int pixel = surroundingPixels[i];
            int value = ((pixel >> 16) & 0xff); 
            red += value;
        }
        return (int)(red/4); 
    } 
    
    private int getGreenOfPixels(int[] surroundingPixels) {
        int green = 0; 
        for (int i=0; i<surroundingPixels.length; i++) {
            int pixel = surroundingPixels[i];
            int value = ((pixel >> 8) & 0xff); 
            green += value;
        }  
        return (int)(green/4); 
    }
    
        private int getBlueOfPixels(int[] surroundingPixels) {
        int blue = 0; 
        for (int i=0; i<surroundingPixels.length; i++) {
            int pixel = surroundingPixels[i];
            int value = (pixel & 0xff); 
            blue += value;
        }  
        return (int)(blue/4); 
    }
        
}

