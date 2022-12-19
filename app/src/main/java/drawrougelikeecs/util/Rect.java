package drawrougelikeecs.util;

public class Rect {
    public final int x1;
    public final int x2;
    public final int y1;
    public final int y2;

    public Rect(int x, int y, int w, int h) {
        this.x1 = x;
        this.x2 = x + w;
        this.y1 = y;
        this.y2 = y + h;
    }

    public boolean intersect(Rect other) {
        return this.x1 <= other.x2 && this.x2 >= other.x1 && this.y1 <= other.y2 && this.y2 >= other.y1;
    }

    public Vector2 center() {
        return new Vector2((this.x1 + this.x2) / 2, (this.y1 + this.y2) / 2);
    }
}
