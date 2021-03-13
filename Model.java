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
    private final InvertColor invertcolor; 
    //private final EditContrast editcontrast; 
    private final Blur blur; 
    //private final Histogram histogram; 
    
    public Model() {
        invertcolor = new InvertColor(); 
        //editcontrast = new EditContrast(int[][] matrix, int level, int window); 
        blur = new Blur(); 
        //histogram = new Histogram(); 
    }
    
    public int[][] invertColor(int[][] matrix) {
        return invertcolor.processImage(matrix);
    }
    
    public int[][] blurPic(int[][] matrix) {
        return blur.processImage(matrix); 
    }
    
    public int[][] editContrast(int[][] matrix, int level, int window) {
        return null; 
    }
    
    
}
