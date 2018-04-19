package object.scene;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 15-03-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public class Pos {

	public final double x;
	public final double y;

	public Pos(double x, double y){
		this.x=x;
		this.y=y;
	}

	public Pos(@NotNull Node node){
		Bounds bounds = node.getBoundsInLocal();
		Bounds screenBounds = node.localToScene(bounds);
		double x = screenBounds.getMinX();
		double y = screenBounds.getMinY();
		this.x=x;
		this.y=y;
	}

	@Override
	public String toString() {
        return "Pos{ " + x + 'x' + y + " }";
	}
}
