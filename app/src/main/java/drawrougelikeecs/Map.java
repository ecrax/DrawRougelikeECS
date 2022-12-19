package drawrougelikeecs;

import com.badlogic.ashley.core.Entity;
import drawrougelikeecs.util.Rect;
import drawrougelikeecs.util.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Map extends Entity {

    public final int width;
    public final int height;

    public final TileType[] tiles;
    public final ArrayList<Rect> rooms;
    public final int maxRooms;
    public final int minRoomSize;
    public final int maxRoomSize;

    public Map(int width, int height, int maxRooms, int minRoomSize, int maxRoomSize) {
        this.width = width;
        this.height = height;
        this.maxRooms = maxRooms;
        this.minRoomSize = minRoomSize;
        this.maxRoomSize = maxRoomSize;

        tiles = new TileType[width * height];
        Arrays.fill(tiles, TileType.Wall);

        rooms = new ArrayList<>();

        Random r = new Random();
        for (int i = 0; i < maxRooms; i++) {
            int w = r.nextInt(minRoomSize, maxRoomSize);
            int h = r.nextInt(minRoomSize, maxRoomSize);
            int x = r.nextInt(1, this.width - w - 1) - 1;
            int y = r.nextInt(1, this.height - h - 1) - 1;

            Rect newRoom = new Rect(x, y, w, h);
            boolean ok = true;
            for (Rect otherRoom : rooms) {
                if (newRoom.intersect(otherRoom)) {
                    ok = false;
                }
            }

            if (ok) {
                this.applyRoomToMap(newRoom);

                if (!this.rooms.isEmpty()) {
                    Vector2 newPoint = newRoom.center();
                    Vector2 prevPoint = this.rooms.get(this.rooms.size() - 1).center();

                    if (r.nextBoolean()) {
                        this.applyHorizontalTunnel(prevPoint.x, newPoint.x, prevPoint.y);
                        this.applyVerticalTunnel(prevPoint.y, newPoint.y, newPoint.x);
                    } else {
                        this.applyVerticalTunnel(prevPoint.y, newPoint.y, prevPoint.x);
                        this.applyHorizontalTunnel(prevPoint.x, newPoint.x, newPoint.y);
                    }
                }

                this.rooms.add(newRoom);
            }
        }
    }

    public void fillWithWalls() {
        Arrays.fill(tiles, TileType.Wall);
    }

    public int pointToIndex(Vector2 v) {
        return (v.y * this.width) + v.x;
    }

    public int pointToIndex(int x, int y) {
        return (y * this.width) + x;
    }

    public void applyRoomToMap(Rect room) {
        for (int y = room.y1 + 1; y <= room.y2; y++) {
            for (int x = room.x1 + 1; x <= room.x2; x++) {
                int idx = this.pointToIndex(x, y);
                this.tiles[idx] = TileType.Floor;
            }
        }
    }

    public void applyVerticalTunnel(int y1, int y2, int x) {
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
            int idx = this.pointToIndex(x, y);
            if (idx > 0 && idx < this.height * this.width) this.tiles[idx] = TileType.Floor;
        }
    }

    public void applyHorizontalTunnel(int x1, int x2, int y) {
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            int idx = this.pointToIndex(x, y);
            if (idx > 0 && idx < this.height * this.width) this.tiles[idx] = TileType.Floor;
        }
    }

//        public void drawMap() {
//            int x = 0;
//            int y = 0;
//
//            for (TileType tile : this.tiles) {
//                switch (tile) {
//                    case Wall -> Draw.setColor(255, 255, 255);
//                    case Floor -> Draw.setColor(0, 0, 0);
//                }
//
//                Vector2 mapped = Util.mapToScreen(x,y, this.width, this.height);
//
//                Draw.filledRect(mapped.x, mapped.y, 11, 11);
//                x += 1;
//                if (x > this.width - 1) {
//                    x = 0;
//                    y += 1;
//                }
//            }
//        }
}
