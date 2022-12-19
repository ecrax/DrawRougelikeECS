package drawrougelikeecs.util;

public class Vector2 {
    public int x;
    public int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 add(Vector2 x, Vector2 y){
        return new Vector2(x.x + y.x, x.y + y.y);
    }
}
