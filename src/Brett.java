public class Brett {
    private final Figur[][] feld;

    public Brett() {
        feld = new Figur[8][8];
        feldFuellen();
    }

    private void feldFuellen() {
        
    }

    public void move(Figur figur, int x, int y,) {      
       if (feld[x][y] == null) {
           feld[x][y] == feld[figur.getPositionX()][figur.getPositionY()]
       }
        
        
    }

}
