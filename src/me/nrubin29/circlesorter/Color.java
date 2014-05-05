package me.nrubin29.circlesorter;

public enum Color {

    ORANGE(Orientation.HORIZONTAL),
    TURQUOISE(Orientation.HORIZONTAL),
    PURPLE(Orientation.HORIZONTAL),

    BLUE(Orientation.VERTICAL),
    RED(Orientation.VERTICAL),
    GREEN(Orientation.VERTICAL),
    YELLOW(Orientation.VERTICAL);

    private final Orientation o;

    private Color(Orientation o) {
        this.o = o;
    }

    public Orientation getOrientation() {
        return o;
    }
}