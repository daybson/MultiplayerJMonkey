package mygame.messages;

import mygame.messages.MyAbstractMessage;
import com.jme3.math.ColorRGBA;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * Baseado nos Tutoriais: https://jmonkeyengine.github.io/wiki/jme3.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/advanced/networking.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/beginner/hello_collision.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/beginner/hello_input_system.html
 */
@Serializable(id = 1)
public class ColorMessage extends MyAbstractMessage {

    ColorRGBA color = ColorRGBA.White;

    public void setColor(ColorRGBA c) {
        color = c;
    }

    public ColorRGBA getColor() {
        return color;
    }

    public ColorMessage() {
    } // empty default constructor

    public ColorMessage(int clientNode, ColorRGBA c) {
        color = c;
        this.clientID = clientNode;
    }
}
