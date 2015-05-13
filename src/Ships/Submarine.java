package Ships;

/**
 *
 * @author Dennis
 *
 */
public class Submarine extends Ship {

    String name = "Submarines";

    public Submarine(int number) {
        super((char) 83, 2, false, number, false, 1, 0, 1, "Submarine");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
