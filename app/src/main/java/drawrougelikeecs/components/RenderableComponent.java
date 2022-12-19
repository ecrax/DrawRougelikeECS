package drawrougelikeecs.components;

import com.badlogic.ashley.core.Component;

public class RenderableComponent implements Component {
    public final char glyph;

    public RenderableComponent(char glyph) {
        this.glyph = glyph;
    }
}
