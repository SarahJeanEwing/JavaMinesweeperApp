public class Cell {
    private boolean bomb;
    private boolean revealed;
    private int bombNearby;
    
    public Cell(boolean bomb) {
        this.bomb = bomb;
        revealed = false;
        bombNearby = 0;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public int getBombNearby() {
        return bombNearby;
    }


    public void setBombNearby(int bombNearby) {
        this.bombNearby = bombNearby;
    }
}
