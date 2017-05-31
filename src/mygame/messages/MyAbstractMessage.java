/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author DAYBSON
 */
@Serializable(id = 0)
public class MyAbstractMessage extends AbstractMessage {

    protected int clientID;

    public int getClientID() {
        return clientID;
    }

    public void setClientId(int clientID) {
        this.clientID = clientID;
    }

}
