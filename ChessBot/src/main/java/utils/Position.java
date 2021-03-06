package utils;

/**
 *
 * @author kevin
 */
public class Position {

    byte rank;
    byte file;

    public Position(byte rank, byte file) {
        this.rank = rank;
        this.file = file;
    }

    public Position(String pos) {
        this.rank = (byte) (Character.getNumericValue(pos.charAt(1)) - 1);
        this.file = (byte) (pos.charAt(0) - 97);
    }

    public int getPos() {
        return (rank * 8) + file;
    }

    public void setPos(byte rank, byte file) {
        this.rank = rank;
        this.file = file;
    }

    public void setPos(byte pos) {
        this.rank = (byte) (pos / 8);
        this.file = (byte) (pos - rank * 8);
    }

    public byte getRank() {
        return rank;
    }

    public byte getFile() {
        return file;
    }

    public void setFile(byte file) {
        this.file = file;
    }

    public void setRank(byte rank) {
        this.rank = rank;
    }
    public void setPos(Position pos) {
        this.file = pos.file;
        this.rank = pos.rank;
    }

    @Override
    public String toString() {
        return "rank: " + rank + ", file: " + file;
    }

}
