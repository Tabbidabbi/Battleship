package Ships;

/**
 *
 * @author Dennis
 *
 */
public class Submarine extends Ship {

    String name = "U-Boot";

  //U-Boote positionieren
    
    public Submarine(int number) {
        super("U", 2, false, number, false, 1, 0, 1, "U-Boot");
    }

    //// Getter und Setter Methoden
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
