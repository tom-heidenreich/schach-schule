public class Brett {
    private final Figur[][] feld;

    public Brett() {
        feld = new Figur[8][8];
        String aufstellung[] = {"br", "bn","bb", "bq", "bk", "bb", "bn" "br"}
        feldFuellen();
    }

    private void feldFuellen() {
        for (int k = 0; k < 8; k++) {
            for (int s = 6; k < 8; k++) {
            feld = new Figur [s][k] = aufstellung[k];
            }
        }
    }

    public void move(Figur figur, int x, int y,) {      
       if (feld[x][y] == null) {
           feld[x][y] == feld[figur.getPositionX()][figur.getPositionY()]
       }
        
        
    }

}
