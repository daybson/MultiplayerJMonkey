package mygame.network;

import mygame.messages.HelloMessage;
import mygame.messages.MoveMessage;
import mygame.messages.ColorMessage;
import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mygame.Globals;
import mygame.Player;
import mygame.messages.PlayerConnectedMessage;

/**
 *
 * Baseado nos Tutoriais: https://jmonkeyengine.github.io/wiki/jme3.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/advanced/networking.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/beginner/hello_collision.html
 * |--https://jmonkeyengine.github.io/wiki/jme3/beginner/hello_input_system.html
 */
public class ServerMain extends SimpleApplication implements ConnectionListener {

    //ATRIBUTOS GLOBAIS
    Server myServer;
    List<Player> clients;
    int connectionsOLD = -1;
    int connections = 0;

    public static void main(String[] args) {
        //Algumas placas de som não suportam a configuracao
        //padrao do AudioRenderer e ao iniciar aplicacao
        //aparece o seguinte erro: org.lwjgl.openal.OpenALException:Invalid Device
        //A configuração de som abaixo resolveu esse erro
        AppSettings s = new AppSettings(true);
        s.setAudioRenderer(AppSettings.LWJGL_OPENAL);
        ServerMain app = new ServerMain();
        app.setSettings(s);
        app.start(JmeContext.Type.Headless);
    }

    @Override
    public void connectionAdded(Server server, HostedConnection client) {
        Random r = new Random();

        //1 - informa todos clientes para instanciarem o novo player
        Player p = new Player(client.getId(), ColorRGBA.randomColor(), new Vector3f(r.nextInt(2), r.nextInt(2), -2));

        //2 - adiciona cliente na lista de clientes
        this.clients.add(p);

        //3 - cria a mensagem de broadcast
        //PlayerConnectedMessage pcm = new PlayerConnectedMessage(p.ClientID, p.Color, p.Pos);
        for (int i = 0; i < clients.size(); i++) {
            PlayerConnectedMessage current = new PlayerConnectedMessage(clients.get(i).ClientID, clients.get(i).Color, clients.get(i).Pos);
            this.myServer.broadcast(current);
        }
    }

    @Override
    public void connectionRemoved(Server server, HostedConnection client) {
        //Encontra id do cliente a ser removido e remove, caso encontrado
        int index = -1;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).ClientID == client.getId()) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            this.clients.remove(index);
            System.out.println("Cliente #" + client.getId() + ":" + client.getAddress() + " desconectado!");
        }
    }

    @Override
    public void simpleInitApp() {
        Serializer.registerClass(HelloMessage.class);
        Serializer.registerClass(ColorMessage.class);
        Serializer.registerClass(MoveMessage.class);
        Serializer.registerClass(PlayerConnectedMessage.class);

        try {
            myServer = Network.createServer(Globals.NAME, Globals.VERSION, Globals.DEFAULT_PORT, Globals.DEFAULT_PORT);
            myServer.start();
        } catch (IOException ex) {
        }

        this.clients = new ArrayList<>();
        myServer.addConnectionListener(this);
        myServer.addMessageListener(new ServerListener(), HelloMessage.class, ColorMessage.class, MoveMessage.class);
    }

    @Override
    public void update() {
        connections = myServer.getConnections().size();
        if (connections != connectionsOLD) {
            System.out.println("Conexoes no Servidor: " + connections);
            connectionsOLD = connections;
        }
    }

    @Override
    public void destroy() {
        try {
            myServer.close();
            this.clients = null;
        } catch (Exception ex) {
        }
        super.destroy();
    }
}
