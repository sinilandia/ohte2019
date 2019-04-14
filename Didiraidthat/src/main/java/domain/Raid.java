/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author siniliu
 */
public class Raid {
    private int id;
    private String level;
    private Gym gym;
    private LocalDate date;
    private LocalTime time;
    private boolean raided;
    //add participants  and raid boss

    public Raid(String level, Gym gym, LocalDate date, LocalTime time) {
        this.level = level;
        this.gym = gym;
        this.date = date;
        this.time = time;
        this.raided = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public void setRaided(boolean raided) {
        this.raided = raided;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public Gym getGym() {
        return gym;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public boolean isRaided() {
        return raided;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Raid other = (Raid) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
