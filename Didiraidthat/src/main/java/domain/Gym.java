package domain;

/**
 * Java class for Gym
 */
public class Gym {
    private int id;
    private String name;
    private boolean ex;

    public Gym(String name, boolean ex) {
        this.name = name;
        this.ex = ex;
    }

    public Gym(int id, String name, boolean ex) {
        this.id = id;
        this.name = name;
        this.ex = ex;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isEx() {
        return ex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEx(boolean ex) {
        this.ex = ex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        
        final Gym other = (Gym) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "Name: " + name + "\tid: " + id + "\tEX: " + ex + "\n";
    }
}
