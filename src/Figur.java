public class Figur {

    public static enum FigurType {
        LÄUFER, SPRINGER, TURM, DAME, KÖNIG, BAUER
    }

    public static enum FigurFarbe {
        WEISS, SCHWARZ
    }

//create variables
    protected int x;
    protected int y;
    protected FigurType type;
    protected FigurFarbe farbe;
    //create constructor
    public Figur(int x, int y, FigurType type, FigurFarbe farbe) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.farbe = farbe;

    }

    //give x position
    public int getX() {
        return x;
    }

    //give y position
    public int getY() {
        return y;
    }

    //give type
    public FigurType getType() {
        return type;
    }

    //give player
    public FigurFarbe getFarbe() {
        return farbe;
    }

}
