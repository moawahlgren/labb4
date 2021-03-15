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

public class Histogram {
    
    private final int red[] = new int[256]; 
    private final int green[] = new int[256]; 
    private final int blue[] = new int[256]; 
    
    
    public int[][] processImage(int[][] pixelMatrix) {
        for (int i=0; i<256; i++) {
            red[i] = green[i] = blue[i] = 0; 
        }
        for (int i=0; i<pixelMatrix.length; i++) {
            for (int j=0; j<pixelMatrix[0].length; j++) {
                int r = ((pixelMatrix[i][j] >> 16) & 0xff); 
                int g = ((pixelMatrix[i][j] >> 8) & 0xff); 
                int b = (pixelMatrix[i][j] & 0xff); 
                 
                red[r]++; 
                green[g]++; 
                blue[b]++; 
            }
        }
        
        int[][] matrix = new int[3][256];
        matrix[0] = red; 
        matrix[1] = green;
        matrix[2] = blue; 
        
        return matrix; 
    }
}
