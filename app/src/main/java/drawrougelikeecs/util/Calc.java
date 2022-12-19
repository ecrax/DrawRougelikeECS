package drawrougelikeecs.util;

import drawrougelikeecs.App;

public class Calc {
    public static float map(float value, float rangeMin, float rangeMax, float targetMin, float targetMax) {
        return (value - rangeMin) / (rangeMax - rangeMin) * (targetMax - targetMin) + targetMin;
    }

    public static Vector2 mapToScreen(int x, int y, int mapWidth, int mapHeight) {
        int mappedX = (int) map(x, 0, mapWidth - 1, 0, App.width);
        int mappedY = (int) map(y, 0, mapHeight - 1, 0, App.height);

        return new Vector2(mappedX, mappedY);
    }
}
