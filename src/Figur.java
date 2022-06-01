public abstract class Figur {
//create variables
    protected int x;
    protected int y;
    protected String type;
    protected String player;
    //create constructor
    public Figur(int x, int y, String type, String player) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.player = player;

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
    public String getType() {
        return type;
    }

    //give player
    public String getPlayer() {
        return player;
    }

}
