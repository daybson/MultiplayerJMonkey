package mygame.messages;

import mygame.messages.MyAbstractMessage;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * Baseado nos Tutoriais: https://jmonkeyengine.github.io/wiki/jme3.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/advanced/networking.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/beginner/hello_collision.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/beginner/hello_input_system.html
 */
@Serializable(id = 2)
public class MoveMessage extends MyAbstractMessage {

    private float x, y, z;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setPositon(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MoveMessage() {
    } // empty default constructor

    public MoveMessage(int clientId, float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.clientID = clientId;
    } // empty default constructor
}
