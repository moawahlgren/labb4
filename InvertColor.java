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
public class InvertColor implements ManipulatePic {
    
    @Override 
    public int[][] processImage(int[][] pixelMatrix) {
        
        //Varje pixel kommer att ha ett färgvärde, och vi vill sätta
        //att nya pixeln på denna plats ska ha färgvärdet
        // 255 - gamla färgvärdet 
        //Sedan returnera ny pixelmatris 
        
        int[][] newMatrix = null; 
        
        for (int i=0; i<pixelMatrix.length; i++) {
            for (int j=0; j<pixelMatrix[0].length; j++) {
                newMatrix[i][j] = 255 - pixelMatrix[i][j]; 
            }
        }
        
        return newMatrix; 
    }
    
}
