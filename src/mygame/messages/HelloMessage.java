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
@Serializable(id = 1)
public class HelloMessage extends MyAbstractMessage {

    public HelloMessage() {
    }

    public HelloMessage(int clientNode) {
        this.clientID = clientNode;
    }
}
