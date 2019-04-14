package domain;

/**
 *
 * @author siniliu
 */
public class Gym {
    private int id;
    private String name;
    private boolean EX;

    public Gym(String name, boolean EX) {
        this.name = name;
        this.EX = EX;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isEX() {
        return EX;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEX(boolean EX) {
        this.EX = EX;
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
            
           
    
 
    
}
