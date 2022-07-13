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
        // up, left
        for(int i = 0; i < 8; i++) {
            int localX = x + i;
            int localY = y + i;
            if(localX < 0 || localX > 7 || localY < 0 || localY > 7) break;
            if(feld[localY][localX] != null) break;
            moves[localX][localY] = true;
        }
        // up, right
        for(int i = 0; i < 8; i++) {
            int localX = x - i;
            int localY = y + i;
            if(localX < 0 || localX > 7 || localY < 0 || localY > 7) break;
            if(feld[localY][localX] != null) break;
            moves[localX][localY] = true;
        }
        // down, left
        for(int i = 0; i < 8; i++) {
            int localX = x + i;
            int localY = y - i;
            if(localX < 0 || localX > 7 || localY < 0 || localY > 7) break;
            if(feld[localY][localX] != null) break;
            moves[localX][localY] = true;
        }
        // down, right
        for(int i = 0; i < 8; i++) {
            int localX = x - i;
            int localY = y - i;
            if(localX < 0 || localX > 7 || localY < 0 || localY > 7) break;
            if(feld[localY][localX] != null) break;
            moves[localX][localY] = true;
        }
        return moves;
    }

    // if type is Springer
    private boolean[][] springer(Figur[][] feld, int x, int y) {
        boolean[][] moves = new boolean[8][8];
        for(int i = -2; i < 3; i++) {
            for(int j = -2; j < 3; j++) {
                int localX = x + i;
                int localY = y + j;
                if(localX < 8 && localX >= 0 && localY < 8 && localY >= 0) {
                    if(i == -2 && (j == -1 || j == 1)) {
                        moves[localX][localY] = true;
                    } else if(i == -1 && (j == -2 || j == 2)) {
                        moves[localX][localY] = true;
                    } else if(i == 1 && (j == -2 || j == 2)) {
                        moves[localX][localY] = true;
                    } else if(i == 2 && (j == -1 || j == 1)) {
                        moves[localX][localY] = true;
                    }
                }
            }
        }
        return moves;
    }

    // if type is turm
    private boolean[][] turm(Figur[][] feld, int x, int y) {
        System.out.println("x: " + x + " y: " + y);
        boolean[][] moves = new boolean[8][8];
        // right
        for (int i = 1; i < 8; i++) {
            int localX = x + i;
            if(localX > 7 || localX < 0) break;
            if(feld[y][localX] != null && feld[y][localX].farbe == farbe) break;
            if(feld[y][localX-1] != null && feld[y][localX-1].farbe != farbe) break;
            moves[localX][y] = true;
        }
        // left
        for (int i = 1; i < 8; i++) {
            int localX = x - i;
            if(localX > 7 || localX < 0) break;
            if(feld[y][localX] != null && feld[y][localX].farbe == farbe) break;
            if(feld[y][localX+1] != null && feld[y][localX+1].farbe != farbe) break;
            moves[localX][y] = true;
        }
        // up
        for (int i = 1; i < 8; i++) {
            int localY = y - i;
            if(localY > 7 || localY < 0) break;
            if(feld[localY][x] != null && feld[localY][x].farbe == farbe) break;
            if(feld[localY+1][x] != null && feld[localY+1][x].farbe != farbe) break;
            moves[x][localY] = true;
        }
        // down
        for (int i = 1; i < 8; i++) {
            int localY = y + i;
            if(localY > 7 || localY < 0) break;
            if(feld[localY][x] != null && feld[localY][x].farbe == farbe) break;
            if(feld[localY-1][x] != null && feld[localY-1][x].farbe != farbe) break;
            moves[x][localY] = true;
        }
        return moves;
    }

    // if type is dame
    private boolean[][] dame(Figur[][] feld, int x, int y) {
        boolean[][] moves = new boolean[8][8];

        boolean[][] läufer = läufer(feld, x, y);
        boolean[][] turm = turm(feld, x, y);

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(läufer[i][j] || turm[i][j]) {
                    moves[i][j] = true;
                }
            }
        }

        return moves;
    }

    // if type is könig
    private boolean[][] könig(Figur[][] feld, int x, int y) {
        boolean[][] moves = new boolean[8][8];
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                int localX = x + i;
                int localY = y + j;
                if(localX > 7 || localX < 0 || localY > 7 || localY < 0) break;
                moves[localX][localY] = true;
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
