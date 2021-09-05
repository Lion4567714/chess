public interface Piece {
    boolean color = false;
    int x = 0;
    int y = 0;
    int value = 0;
    char symbol = ' ';

    void setX(int x);
    void setY(int y);
    int getX();
    int getY();
    boolean getColor();
    char getSymbol();

    boolean[][] getPossibleMoves(Piece[][] board);
}

class Pawn implements Piece {
    boolean color;
    int x;
    int y;
    int value = 1;
    char symbol = 'p';

    public Pawn(boolean color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getColor() {
        return this.color;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public boolean[][] getPossibleMoves(Piece[][] board) {
        boolean[][] possibleMoves = new boolean[8][8];

        if (!color) {
            // Once forward
            if (y == 1 && board[x][y + 1] == null) {
                possibleMoves[x][y + 1] = true;

                // Twice forward
                if (board[x][y + 2] == null) {
                    possibleMoves[x][y + 2] = true;
                }
            }

            // Attack left
            if (x != 0 && board[x - 1][y + 1] != null) {
                possibleMoves[x - 1][y + 1] = true;
            }

            // Attack right
            if (x != 7 && board[x + 1][y + 1] != null) {
                possibleMoves[x + 1][y + 1] = true;
            }
        } else {
            if (y == 6 && board[x][y - 1] == null) {
                possibleMoves[x][y - 1] = true;

                if (board[x][y - 2] == null) {
                    possibleMoves[x][y - 2] = true;
                }
            }

            if (x != 0 && board[x - 1][y - 1] != null) {
                possibleMoves[x - 1][y - 1] = true;
            }

            if (x != 7 && board[x + 1][y - 1] != null) {
                possibleMoves[x + 1][y - 1] = true;
            }
        }

        return possibleMoves;
    }
}

class Rook implements Piece {
    boolean color;
    int x;
    int y;
    int value = 5;
    char symbol = 'R';

    public Rook(boolean color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getColor() {
        return this.color;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public boolean[][] getPossibleMoves(Piece[][] board) {
        boolean[][] possibleMoves = new boolean[8][8];

        for (int i = x + 1; i < 8; i++) {
            if (board[i][y] == null) {
                possibleMoves[i][y] = true;
            } else if (board[i][y].color != this.color) {
                possibleMoves[i][y] = true;
                break;
            } else {
                break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            if (board[i][y] == null) {
                possibleMoves[i][y] = true;
            } else if (board[i][y].color != this.color) {
                possibleMoves[i][y] = true;
                break;
            } else {
                break;
            }
        }

        for (int i = y + 1; y < 8; y++) {
            if (board[x][i] == null) {
                possibleMoves[x][i] = true;
            } else if (board[x][i].color != this.color) {
                possibleMoves[x][i] = true;
                break;
            } else {
                break;
            }
        }

        for (int i = y - 1; y >= 0; y--) {
            if (board[x][i] == null) {
                possibleMoves[x][i] = true;
            } else if (board[x][i].color != this.color) {
                possibleMoves[x][i] = true;
                break;
            } else {
                break;
            }
        }

        return possibleMoves;
    }
}

class Knight implements Piece {
    boolean color;
    int x;
    int y;
    int value = 3;
    char symbol = 'N';

    public Knight(boolean color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getColor() {
        return this.color;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public boolean[][] getPossibleMoves(Piece[][] board) {
        boolean[][] possibleMoves = new boolean[8][8];

        try {
            if (board[x + 1][y + 2].color != this.color) {
                possibleMoves[x + 1][y + 2] = true;
            }
        } catch (Exception ignored) {};

        try {
            if (board[x + 2][y + 1].color != this.color) {
                possibleMoves[x + 2][y + 1] = true;
            }
        } catch (Exception ignored) {};

        try {
            if (board[x + 2][y - 1].color != this.color) {
                possibleMoves[x + 2][y - 1] = true;
            }
        } catch (Exception ignored) {};

        try {
            if (board[x + 1][y - 2].color != this.color) {
                possibleMoves[x + 1][y - 2] = true;
            }
        } catch (Exception ignored) {};

        try {
            if (board[x - 1][y - 2].color != this.color) {
                possibleMoves[x - 1][y - 2] = true;
            }
        } catch (Exception ignored) {};

        try {
            if (board[x - 2][y - 1].color != this.color) {
                possibleMoves[x - 2][y - 1] = true;
            }
        } catch (Exception ignored) {};

        try {
            if (board[x - 2][y + 1].color != this.color) {
                possibleMoves[x - 2][y + 1] = true;
            }
        } catch (Exception ignored) {};

        try {
            if (board[x - 1][y + 2].color != this.color) {
                possibleMoves[x - 1][y + 2] = true;
            }
        } catch (Exception ignored) {};

        return possibleMoves;
    }

}

class Bishop implements Piece {
    boolean color;
    int x;
    int y;
    int value = 3;
    char symbol = 'B';

    public Bishop(boolean color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getColor() {
        return this.color;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public boolean[][] getPossibleMoves(Piece[][] board) {
        boolean[][] possibleMoves = new boolean[8][8];
        boolean a = true;
        boolean b = true;
        boolean c = true;
        boolean d = true;

        for (int i = 1; i < 8; i++) {
            if (!a && !b && !c && !d) {
                break;
            }

            if (a) {
                if (x + i < 7 && y + i < 7) {
                    if (board[x + i][y + i] != null) {
                        if (board[x + i][y + i].color != this.color) {
                            possibleMoves[x + i][y + i] = true;
                        } else {
                            a = false;
                        }
                    } else {
                        possibleMoves[x + i][y + i] = true;
                    }
                }
            }

            if (b) {
                if (x + i < 7 && y - i >= 0) {
                    if (board[x + i][y - i] != null) {
                        if (board[x + i][y - i].color != this.color) {
                            possibleMoves[x + i][y - i] = true;
                        } else {
                            b = false;
                        }
                    } else {
                        possibleMoves[x + i][y - i] = true;
                    }
                }
            }

            if (c) {
                if (x - i >= 0 && y - i >= 0) {
                    if (board[x - i][y - i] != null) {
                        if (board[x - i][y - i].color != this.color) {
                            possibleMoves[x - i][y - i] = true;
                        } else {
                            c = false;
                        }
                    } else {
                        possibleMoves[x - i][y - i] = true;
                    }
                }
            }

            if (d) {
                if (x - i >= 0 && y + i < 7) {
                    if (board[x - i][y + i] != null) {
                        if (board[x - i][y + i].color != this.color) {
                            possibleMoves[x - i][y + i] = true;
                        } else {
                            d = false;
                        }
                    } else {
                        possibleMoves[x - i][y + i] = true;
                    }
                }
            }
        }

        return possibleMoves;
    }
}

class Queen implements Piece {
    boolean color;
    int x;
    int y;
    int value = 8;
    char symbol = 'Q';

    public Queen(boolean color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getColor() {
        return this.color;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public boolean[][] getPossibleMoves(Piece[][] board) {
        boolean[][] possibleMoves = new boolean[8][8];
        boolean a = true;
        boolean b = true;
        boolean c = true;
        boolean d = true;

        for (int i = 1; i < 8; i++) {
            if (!a && !b && !c && !d) {
                break;
            }

            if (a) {
                if (x + i < 7 && y + i < 7) {
                    if (board[x + i][y + i] != null) {
                        if (board[x + i][y + i].color != this.color) {
                            possibleMoves[x + i][y + i] = true;
                        } else {
                            a = false;
                        }
                    } else {
                        possibleMoves[x + i][y + i] = true;
                    }
                }
            }

            if (b) {
                if (x + i < 7 && y - i >= 0) {
                    if (board[x + i][y - i] != null) {
                        if (board[x + i][y - i].color != this.color) {
                            possibleMoves[x + i][y - i] = true;
                        } else {
                            b = false;
                        }
                    } else {
                        possibleMoves[x + i][y - i] = true;
                    }
                }
            }

            if (c) {
                if (x - i >= 0 && y - i >= 0) {
                    if (board[x - i][y - i] != null) {
                        if (board[x - i][y - i].color != this.color) {
                            possibleMoves[x - i][y - i] = true;
                        } else {
                            c = false;
                        }
                    } else {
                        possibleMoves[x - i][y - i] = true;
                    }
                }
            }

            if (d) {
                if (x - i >= 0 && y + i < 7) {
                    if (board[x - i][y + i] != null) {
                        if (board[x - i][y + i].color != this.color) {
                            possibleMoves[x - i][y + i] = true;
                        } else {
                            d = false;
                        }
                    } else {
                        possibleMoves[x - i][y + i] = true;
                    }
                }
            }
        }

        for (int i = x + 1; i < 8; i++) {
            if (board[i][y] == null) {
                possibleMoves[i][y] = true;
            } else if (board[i][y].color != this.color) {
                possibleMoves[i][y] = true;
                break;
            } else {
                break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            if (board[i][y] == null) {
                possibleMoves[i][y] = true;
            } else if (board[i][y].color != this.color) {
                possibleMoves[i][y] = true;
                break;
            } else {
                break;
            }
        }

        for (int i = y + 1; y < 8; y++) {
            if (board[x][i] == null) {
                possibleMoves[x][i] = true;
            } else if (board[x][i].color != this.color) {
                possibleMoves[x][i] = true;
                break;
            } else {
                break;
            }
        }

        for (int i = y - 1; y >= 0; y--) {
            if (board[x][i] == null) {
                possibleMoves[x][i] = true;
            } else if (board[x][i].color != this.color) {
                possibleMoves[x][i] = true;
                break;
            } else {
                break;
            }
        }

        return possibleMoves;
    }
}

class King implements Piece {
    boolean color;
    int x;
    int y;
    int value = 100;
    char symbol = 'K';

    public King(boolean color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getColor() {
        return this.color;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public boolean[][] getPossibleMoves(Piece[][] board) {
        boolean[][] possibleMoves = new boolean[8][8];

        if (x > 0) {
            if (board[x - 1][y] == null || board[x - 1][y].color != this.color) {
                possibleMoves[x - 1][y] = true;
            }

            if (y > 0 && board[x - 1][y - 1] == null || board[x - 1][y - 1].color != this.color) {
                possibleMoves[x - 1][y - 1] = true;
            }

            if (y < 7 && board[x - 1][y + 1] == null || board[x - 1][y + 1].color != this.color) {
                possibleMoves[x - 1][y + 1] = true;
            }
        }

        if (x < 7) {
            if (board[x + 1][y] == null || board[x + 1][y].color != this.color) {
                possibleMoves[x + 1][y] = true;
            }

            if (y > 0 && board[x + 1][y - 1] == null || board[x + 1][y - 1].color != this.color) {
                possibleMoves[x + 1][y - 1] = true;
            }

            if (y < 7 && board[x + 1][y + 1] == null || board[x + 1][y + 1].color != this.color) {
                possibleMoves[x + 1][y + 1] = true;
            }
        }

        if (y > 0 && board[x][y - 1] == null || board[x][y - 1].color != this.color) {
            possibleMoves[x][y - 1] = true;
        }

        if (y < 7 && board[x][y + 1] == null || board[x][y + 1].color != this.color) {
            possibleMoves[x][y + 1] = true;
        }

        return possibleMoves;
    }
}