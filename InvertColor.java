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

public class InvertColor implements ManipulatePic {
    
    @Override 
    public int[][] processImage(int[][] pixelMatrix) {
        
        //Varje pixel kommer att ha ett färgvärde, och vi vill sätta
        //att nya pixeln på denna plats ska ha färgvärdet
        // 255 - gamla färgvärdet 
        //Sedan returnera ny pixelmatris 
 
        int[][] newMatrix = new int[pixelMatrix.length][pixelMatrix[0].length];
        
        for (int i=0; i<pixelMatrix.length; i++) {
            for (int j=0; j<pixelMatrix[0].length; j++) {
                int aValue = ((pixelMatrix[i][j] >> 24) & 0xff); 
                int rValue = ((pixelMatrix[i][j] >> 16) & 0xff); 
                int gValue = ((pixelMatrix[i][j] >> 8) & 0xff); 
                int bValue = (pixelMatrix[i][j] & 0xff); 
                
                int red = 255 - rValue; 
                int green = 255 - gValue; 
                int blue = 255 - bValue; 
                
                int color = (aValue << 24) | (red << 16) | (green << 8) | blue; 
                
                newMatrix[i][j] = color;  
            }
        }
        return newMatrix; 
    }
    
}
