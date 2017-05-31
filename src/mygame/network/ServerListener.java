package mygame.network;

import mygame.messages.HelloMessage;
import mygame.messages.MoveMessage;
import mygame.messages.ColorMessage;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Server;
import mygame.messages.PlayerConnectedMessage;

/**
 *
 * Baseado nos Tutoriais: https://jmonkeyengine.github.io/wiki/jme3.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/advanced/networking.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/beginner/hello_collision.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/beginner/hello_input_system.html
 */
public class ServerListener implements MessageListener<HostedConnection> {

    @Override
    public void messageReceived(HostedConnection source, Message message) {

        //if (message instanceof PlayerConnectedMessage) {
            //PlayerConnectedMessage pcm = (PlayerConnectedMessage) message;
            //source.getServer().broadcast(new PlayerConnectedMessage(pcm.getClientID(), pcm.getColor(), pcm.getPos()));
            //System.out.println("Nova mensagem de instancia de player");
            
        //} else 
        if (message instanceof ColorMessage) {
            ColorMessage colorMessage = (ColorMessage) message;

            Server s = source.getServer();
            s.broadcast(new ColorMessage(colorMessage.getClientID(), colorMessage.getColor()));

        } else if (message instanceof MoveMessage) {
            MoveMessage moveMessage = (MoveMessage) message;

            Server s = source.getServer();
            s.broadcast(moveMessage.setReliable(false));
        }
    }
}
