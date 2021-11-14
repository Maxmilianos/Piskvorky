package the.max.piskvorky;

public enum Type {
    X("X"),
    O("O"),
    S("S"),
    NONE("=");

    String ch;

    Type(String c) {
        ch = c;
    }

    @Override
    public String toString() {
        return ch;
    }
}
