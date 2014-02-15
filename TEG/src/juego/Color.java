/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.util.logging.Logger;

/**
 *
 * @author heril
 */
public class Color {
    
    private String color;
    private int idColor;

    public Color() {
    }

    public Color(String color, int idColor) {
        this.color = color;
        this.idColor = idColor;
    }

    public String getColor() {
        return color;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Color) {
            Color another = (Color) obj;
            if (another.idColor == this.idColor) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    
    
}
