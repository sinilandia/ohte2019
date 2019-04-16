/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Didiraidthat;

/**
 *
 * @author siniliu
 */
public class Gym {
    private int id;
    private String name;
    private Boolean EX;

    public Gym(String name) {
        this.name = name;
    }

    public Gym(int id, String name, Boolean EX) {
        this.id = id;
        this.name = name;
        this.EX = EX;
    }
    
    public String getName() {
        return name;
    }
    
   
}
