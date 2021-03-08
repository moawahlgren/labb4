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
public class Model {
    
    InvertColor invertColor = new InvertColor(); 
    
    public int[][] invertColor(int[][] matrix) {
        return invertColor.processImage(matrix);
    }
    
    
}