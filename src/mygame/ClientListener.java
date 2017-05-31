package mygame;

import mygame.messages.HelloMessage;
import mygame.messages.MoveMessage;
import mygame.messages.ColorMessage;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.scene.Spatial;
import java.util.Random;
import java.util.concurrent.Callable;
import mygame.messages.MyAbstractMessage;
import mygame.messages.PlayerConnectedMessage;

/**
 *
 * Baseado nos Tutoriais: https://jmonkeyengine.github.io/wiki/jme3.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/advanced/networking.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/beginner/hello_collision.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/beginner/hello_input_system.html
 */
public class ClientListener implements MessageListener<Client> {

    @Override
    public void messageReceived(Client source, Message message) {

        if (message instanceof HelloMessage) {
            final Client c = source;

//1 - recebe o ID do servidor
            HelloMessage helloMessage = (HelloMessage) message;
            app.ClientID = helloMessage.getClientID();
            System.out.println("ID recebido: '" + helloMessage.getClientID());

        } else if (message instanceof PlayerConnectedMessage) {

//1 - recebe o sinal para instanciar um novo player
            final PlayerConnectedMessage pcm = (PlayerConnectedMessage) message;

            if (app.getRootNode().getChild("PLAYER_" + pcm.getClientID()) != null) {
                System.out.println("PLAYER_" + pcm.getClientID() + " já existe");
                return;
            }

            System.out.println("Novo player conectado: '" + pcm.getClientID());

            //3 - instancia um novo personagem (cubo) na tela
            app.enqueue(new Callable() {
                @Override
                public Void call() {
                    Random r = new Random();
                    app.adicionaJogador(String.valueOf(pcm.getClientID()), new Vector3f(r.nextInt(2), r.nextInt(2), -2), pcm.getColor());
                    return null;
                }
            });

        } else if (message instanceof ColorMessage) {
            final ColorMessage colorMessage = (ColorMessage) message;

            app.enqueue(new Callable() {
                @Override
                public Void call() {
                    Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
                    mat.setColor("Color", colorMessage.getColor());
                    app.getRootNode().getChild("PLAYER_" + colorMessage.getClientID()).setMaterial(mat);
                    return null;
                }
            });

        } else if (message instanceof MoveMessage) {
            final MoveMessage moveMessage = (MoveMessage) message;

            if (app.getRootNode().getChild("PLAYER_" + moveMessage.getClientID()) != null) {
                System.out.println("PLAYER_" + moveMessage.getClientID() + " já existe");
                return;
            }
            
            app.enqueue(new Callable() {
                @Override
                public Void call() {
                    Spatial node = app.getRootNode().getChild("PLAYER_" + moveMessage.getClientID());
                    Vector3f v = node.getLocalTranslation();
                    node.setLocalTranslation(v.x + moveMessage.getX(), v.getY() + moveMessage.getZ(), v.z + moveMessage.getZ());
                    app.myCam.setLocation(new Vector3f(node.getLocalTranslation().x, node.getLocalTranslation().y + 1, node.getLocalTranslation().z + 2));
                    return null;
                }
            });
        }
    }

    public ClientListener() {

    }

    private ClientMain app;

    public ClientListener(ClientMain app) {
        this.app = app;
    }

}
