package schach;

public class Figur {

    public static enum FigurType {
        LÄUFER, SPRINGER, TURM, DAME, KÖNIG, BAUER;

        public char toKey() {
            switch (this) {
                case LÄUFER:
                    return 'b';
                case SPRINGER:
                    return 'n';
                case TURM:
                    return 'r';
                case DAME:
                    return 'q';
                case KÖNIG:
                    return 'k';
                case BAUER:
                    return 'p';
                default:
                    return ' ';
            }
        }
    }

    public static enum FigurFarbe {
        WEISS, SCHWARZ
    }

    // create variables
    protected int xalt;
    protected int yalt;
    protected FigurType type;
    protected FigurFarbe farbe;

    // create constructor
    public Figur(int x, int y, FigurType type, FigurFarbe farbe) {
        this.xalt = x;
        this.yalt = y;
        this.type = type;
        this.farbe = farbe;

    }

    // give x position
    public int getX() {
        return xalt;
    }

    // give y position
    public int getY() {
        return yalt;
    }

    // give type
    public FigurType getType() {
        return type;
    }

    // give player
    public FigurFarbe getFarbe() {
        return farbe;
    }

    // give all possible moves in a two dimensional array
    public boolean[][] getMoves(Figur[][] feld, int x, int y) {
        //if type is Läufer
        if (type == FigurType.LÄUFER) return läufer(feld, x, y); 
        //if type is Springer
        else if (type == FigurType.SPRINGER) return springer(feld, x, y);
        //if type is Turm
        else if (type == FigurType.TURM) return turm(feld, x, y);
        //if type is Dame
        else if (type == FigurType.DAME) return dame(feld, x, y);
        //if type is König
        else if (type == FigurType.KÖNIG) return könig(feld, x, y);
        //if type is Bauer
        else if (type == FigurType.BAUER) return bauer(feld, x, y);
        else return null;
    }

    // if type is läufer
    private boolean[][] läufer(Figur[][] feld, int x, int y) {
        boolean[][] moves = new boolean[8][8];
        // for every x = y if no figure is in the way and the position is in the board
        for (int i = -7; i < 7; i++) {
            if (x + i < 8 && x + i > 0 && y + i < 8 && y + i > 0 && feld[x + i][y + i] == null) {
                moves[x + i][y + i] = true;
            }
        }
        return moves;
    }

    // if type is Springer
    private boolean[][] springer(Figur[][] feld, int x, int y) {
        boolean[][] moves = new boolean[8][8];
        for (int i = -7; i < 7; i++) {
            if (x + i < 8 && x + i > 0 && y + i < 8 && y + i > 0 && feld[x + i][y + i] == null) {
                moves[x + i][y + i] = true;
            }
        }
        return moves;
    }

    // if type is turm
    private boolean[][] turm(Figur[][] feld, int x, int y) {
        boolean[][] moves = new boolean[8][8];
        // go from x and y to the right
        for (int i = 0; i + x < 7; i++) {
            if (feld[i][y] == null) {
                moves[i][y] = true;
            } else if (feld[i][y].getFarbe() != farbe) {
                moves[i][y] = true;
                break;
            } else {
                break;
            }
        }
        // go from x and y to the top
        for (int i = 0; i + y < 7; i++) {
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

    // if type is dame
    private boolean[][] dame(Figur[][] feld, int x, int y) {
        if (turm(feld, x, y) != null) {
            return turm(feld, x, y);
        } else if (läufer(feld, x, y) != null) {
            return läufer(feld, x, y);
        } else {
            return null;
        }
    }

    // if type is könig
    private boolean[][] könig(Figur[][] feld, int x, int y) {
        boolean[][] moves = new boolean[8][8];
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i < 8 && x + i > 0 && y + j < 8 && y + j > 0 && feld[x + i][y + j] == null) {
                    moves[x + i][y + j] = true;
                    return moves;
                }
            }
        }
        return moves;
    }
    
    //if type is bauer
    private boolean[][] bauer(Figur[][] feld, int x, int y) {
        boolean moves[][] = new boolean[8][8];
        if (farbe == FigurFarbe.WEISS) {
            if (feld[x][y+1] == null) {
                moves[x][y+1] = true;
                if (feld[x][y+2] == null && y == 1){
                    moves[x][y+2] = true;
                }
            }
            Figur a = feld[x-1][y+1];
            if (a != null && a.getFarbe() != farbe) {
                moves[x-1][y+1] = true;
            }
            if (feld[x+1][y+1].getFarbe() != farbe) {
                moves[x+1][y+1] = true;
            }
        }

        if (farbe == FigurFarbe.SCHWARZ) {
            if (feld[x][y-1] == null) {
                moves[x][y-1] = true;
                if (feld[x][y-2] == null && y == 7){
                    moves[x][y-2] = true;
                }
            }
            if (feld[x-1][y-1].getFarbe() != farbe) {
                moves[x-1][y-1] = true;
            }
            if (feld[x+1][y-1].getFarbe() != farbe) {
                moves[x+1][y-1] = true;
            }
        }
        return moves;
    }
}
