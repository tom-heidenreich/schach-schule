public class Brett {
    private final Figur[][] feld;
    String[] aufstellung = {"r", "n", "b", "q", "k", "b", "n", "r"};

    public Brett() {
        feld = new Figur[8][8];
        feldFuellen();
    }







    private void feldFuellen() {
        for (int k = 0; k < 8; k++) {
             feld[7][k] = new Figur(k, 7, aufstellung[k], "b");
             feld[0][k] = new Figur(k, 0, aufstellung[k], "w");
             feld[1][k] = new Figur(k, 1, "p", "w");
             feld[6][k] = new Figur(k, 6, "p", "b");

        }
    }

    public void move(Figur figur, int x, int y) {      
       if (feld[x][y] == null) {
           feld[x][y] = feld[figur.getX()][figur.getY()];
       }
        
        
    }


}