/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author siniliu
 */
public class Raid {
    private int id;
    private Gym gym;
    private String level;
    private LocalDate date;
    private LocalTime time;

    public Raid(Gym gym, String level, String hours, String minutes) {
        this.level = level;
        this.gym = gym;
        this.date = LocalDate.now();
        String timeAsString = hours + ":" + minutes + ":00"; 
        this.time = LocalTime.parse(timeAsString, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public Raid(int id, Gym gym, String level, LocalDate date, LocalTime time) {
        this.id = id;
        this.level = level;
        this.gym = gym;
        this.date = date;
        this.time = time;
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

    @Override
    public String toString() {
        return "Raid id: " + id + "\tgym: " + gym + "\tlevel: " + level + "\t" 
                + date + "\t" + time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
