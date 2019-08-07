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
public class IllegalMoveException extends Exception {
    String message;

    public IllegalMoveException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
    
    
    
}
