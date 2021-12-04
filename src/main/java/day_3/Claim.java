package day_3;

public class Claim {

    private final int id;
    private final int leftPadding;
    private final int topPadding;
    private final int width;
    private final int height;

    public Claim(int id, int leftPadding, int topPadding, int width, int height) {
        this.id = id;
        this.leftPadding = leftPadding;
        this.topPadding = topPadding;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public int getLeftPadding() {
        return leftPadding;
    }

    public int getTopPadding() {
        return topPadding;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Claim{" +
            "id=" + id +
            ", leftPadding=" + leftPadding +
            ", topPadding=" + topPadding +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
