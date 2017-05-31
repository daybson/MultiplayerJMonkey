/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

/**
 *
 * @author m127391
 */
public class Player {

    public Player(int ClientID, ColorRGBA Color, Vector3f Pos) {
        this.ClientID = ClientID;
        this.Color = Color;
        this.Pos = Pos;
        this.Name = "PLAYER_" + ClientID;
    }

    public int ClientID;
    public ColorRGBA Color;
    public Vector3f Pos;
    public String Name;
}
