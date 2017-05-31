/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.messages;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author DAYBSON
 */
@Serializable(id = 4)
public class PlayerConnectedMessage extends MyAbstractMessage {

    ColorRGBA color = ColorRGBA.White;
    Vector3f pos;

    public String getName() {
        return name;
    }
    String name;

    public Vector3f getPos() {
        return pos;
    }

    public ColorRGBA getColor() {
        return color;
    }

    public PlayerConnectedMessage() {
    }

    public PlayerConnectedMessage(int clientID, ColorRGBA c, Vector3f pos) {
        this.clientID = clientID;
        this.name = "PLAYER_" + clientID;
        this.color = c;
        this.pos = pos;
    }

}
