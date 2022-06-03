package schach;
public class Figur {

    public static enum FigurType {
        LÄUFER, SPRINGER, TURM, DAME, KÖNIG, BAUER;

        public char toKey() {
            switch(this) {
                case LÄUFER: return 'b';
                case SPRINGER: return 'k';
                case TURM: return 'r';
                case DAME: return 'q';
                case KÖNIG: return 'k';
                case BAUER: return 'p';
                default: return ' ';
            }
        }
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
    //give all possible moves in a two dimensional array
    public boolean[][] getMoves(Figur[][] feld) {
        boolean[][] moves = new boolean[8][8];
        //if type is läufer
        if (type == FigurType.LÄUFER)return boolean[][] läufer(feld)); 
        //if type is Springer
        else if (type == FigurType.SPRINGER) return boolean[][] springer(feld);
        //if type is Turm
        else if (type == FigurType.TURM) return boolean[][] turm(feld);
        //if type is Dame
        else if (type == FigurType.DAME) return boolean[][] dame(feld);
        //if type is König
        else if (type == FigurType.KÖNIG) return boolean[][] könig(feld);
        //if type is Bauern
        else if (type == FigurType.BAUER) return boolean[][] bauer(feld);
        else return null;
    }

    //if type is läufer
    private boolean[][] läufer(Figur[][] feld) {
        boolean[][] moves = new boolean[8][8];
        //for every x = y if no figure is in the way and the position is in the board
        for (int i = -7; i < 7; i++) {
            if (x + i < 8 && x + i > 0 && y + i < 8 && y + i > 0 && feld[x + i][y + i] == null) {
                moves[x + i][y + i] = true;
            }
        }
        return moves;
    }

    //if type is turm
    private boolean[][] turm(Figur[][] feld) {
        boolean[][] moves = new boolean[8][8];
        //go from x and y to the right
        for(int i = x + 1; i + x < 8; i++) {
            if (feld[i][y] == null) {
                moves[i][y] = true;
            } else if (feld[i][y].getFarbe() != farbe) {
                moves[i][y] = true;
                break;
            } else {
                break;
            }
        }
        //go from x and y to the left
        for(int i = x - 1; i + x > 0; i--) {
            if (feld[i][y] == null) {
                moves[i][y] = true;
            } else if (feld[i][y].getFarbe() != farbe) {
                moves[i][y] = true;
                break;
            } else {
                break;
            }
        }
        //go from x and y to the top
        for(int i = y + 1; i + y < 8; i++) {
            if (feld[x][i] == null) {
                moves[x][i] = true;
            } else if (feld[x][i].getFarbe() != farbe) {
                moves[x][i] = true;
                break;
            } else {
                break;
            }
        }
        //go from x and y to the bottom
        for(int i = y - 1; i + y > 0; i--) {
            if (feld[x][i] == null) {
                moves[x][i] = true;
            } else if (feld[x][i].getFarbe() != farbe) {
                moves[x][i] = true;
                break;
            } else {
                break;
            }
        }
        return moves;
    }
}
