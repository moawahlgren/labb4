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

public class EditContrast implements ManipulatePic {
    
    int level, window;
    final static int MP = 255; 
    int[] lut = theLUT(level, window); 
 
    
    public EditContrast(int level, int window) {
        this.level = level; 
        this.window = window; 
    }
    
    @Override 
    public int[][] processImage(int[][] pixelMatrix) {
        int[][] newMatrix = new int[pixelMatrix.length][pixelMatrix[0].length]; 
       
        for (int i=0; i<pixelMatrix.length; i++) {
            for (int j=0; j<pixelMatrix[0].length; j++) {
                int a = ((pixelMatrix[i][j] >> 24) & 0xff); 
                int r = ((pixelMatrix[i][j] >> 16) & 0xff); 
                int g = ((pixelMatrix[i][j] >> 8) & 0xff); 
                int b = (pixelMatrix[i][j] & 0xff);
                
                double greyValue = (r * 0.2126 + g * 0.7125 + b * 0.0722) / 3; 
                int intensity = (int) greyValue;
                greyValue = greyValue * lut[intensity];
                int color = (int) greyValue;
                
                newMatrix[i][j] = (a << 24) | (color << 16) | (color << 8) | color;
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
