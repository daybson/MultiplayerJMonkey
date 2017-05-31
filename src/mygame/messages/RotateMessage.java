package mygame.messages;

import com.jme3.network.serializing.Serializable;

@Serializable(id = 5)
public class RotateMessage extends MyAbstractMessage {

    private float angle;

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public RotateMessage() {
    } // empty default constructor

    public RotateMessage(int clientId, float angle) {
        this.angle = angle;
        this.clientID = clientId;
    }
}
