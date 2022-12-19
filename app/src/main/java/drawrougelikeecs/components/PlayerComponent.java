package drawrougelikeecs.components;

import com.badlogic.ashley.core.Component;

public class PlayerComponent implements Component {
    public final String name;

    public PlayerComponent(String name) {
        this.name = name;
    }
}
