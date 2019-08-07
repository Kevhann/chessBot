/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author kevin
 */
public enum Colour {
    WHITE("white"), BLACK("black");
    private final String colour;
    
    Colour(String colour) {
        this.colour = colour;
    }
    
    public String getColour() {
        return colour;
    }
}
