package drawrougelikeecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import drawrougelikeecs.components.MapComponent;
import drawrougelikeecs.components.PlayerComponent;
import drawrougelikeecs.components.PositionComponent;
import drawrougelikeecs.components.RenderableComponent;
import drawrougelikeecs.systems.MovementSystem;
import drawrougelikeecs.systems.RenderSystem;

public class App {
    public static final int width = 800;
    public static final int height = 500;

    public static void main(String[] args) {
        Engine engine = new Engine();

        MovementSystem movementSystem = new MovementSystem();
        RenderSystem renderSystem = new RenderSystem();

        engine.addSystem(movementSystem);
        engine.addSystem(renderSystem);

        Map map = new Map(80, 50, 6, 1, 6);
        map.add(new MapComponent());
        engine.addEntity(map);

        Entity player = engine.createEntity();
        player.add(new PlayerComponent("Player"));
        player.add(new PositionComponent(40, 25));
        player.add(new RenderableComponent('@'));
        engine.addEntity(player);

        Draw.init(width, height);
        Draw.enableDoubleBuffering(true);

        while (true){
            engine.update(1/60f);
            Draw.syncToFrameRate();
            Draw.clearScreen();
        }
    }
}
