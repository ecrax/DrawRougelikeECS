package drawrougelikeecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import drawrougelikeecs.Draw;
import drawrougelikeecs.components.PositionComponent;
import drawrougelikeecs.components.RenderableComponent;
import drawrougelikeecs.util.Calc;
import drawrougelikeecs.util.Vector2;

public class RenderSystem extends EntitySystem {
    // Render every Entity with a Renderable component

    public ImmutableArray<Entity> entities;

    private final ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<RenderableComponent> rm
            = ComponentMapper.getFor(RenderableComponent.class);

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, RenderableComponent.class).get());
        System.out.println("RenderSystem added to engine");
    }

    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {
            RenderableComponent rc = rm.get(entity);
            PositionComponent pc = pm.get(entity);
            Draw.setColor(0, 0, 0);
            Vector2 pos = Calc.mapToScreen(pc.x, pc.y, 80, 50);
            Draw.text(pos.x, pos.y, "" + rc.glyph, 40);
            //Draw.filledRect(pc.x, pc.y, 2, 2);
        }
    }
}
