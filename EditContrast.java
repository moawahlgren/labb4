/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author moawahlgren
 */
public class EditContrast implements ManipulatePic {
    
    int level, window;
    final static int MP = 255; 
    int[] lut = theLUT(level, window); 
 
    
    public EditContrast(int[][] pixelMatrix, int level, int window) {
        this.level = level; 
        this.window = window; 
    }
    
    @Override 
    public int[][] processImage(int[][] pixelMatrix) {
        int[][] newMatrix = new int[pixelMatrix.length][pixelMatrix[0].length]; 
       
        for (int i=0; i<pixelMatrix.length; i++) {
            for (int j=0; j<pixelMatrix[0].length; j++) {
                int aValue = ((pixelMatrix[i][j] >> 24) & 0xff); 
                int rValue = ((pixelMatrix[i][j] >> 16) & 0xff); 
                int gValue = ((pixelMatrix[i][j] >> 8) & 0xff); 
                int bValue = (pixelMatrix[i][j] & 0xff); 
                
                int intensity = (rValue + gValue + bValue) / 3; 
                intensity = intensity * lut[intensity];
                
                newMatrix[i][j] = (aValue << 24) | (intensity << 16) | (intensity << 8) | intensity;
            }
        }
        return newMatrix; 
    }

    public int[] theLUT(int level, int window) { 
        int a = level - window / 2; 
        int b = level + window / 2; 
        int[] array = new int[MP + 1];
        
        for (int i=a; i<b; i++) {
            float scale = (MP/window) * (i-a);
            array[i] = (int) scale;
        }
        for (int i=b+1; i<MP; i++) {
            array[i] = MP; 
        }
        
        return array;
    }
}

