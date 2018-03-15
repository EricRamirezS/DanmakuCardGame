package object.scene;

import com.sun.istack.internal.NotNull;
import controller.game.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 23-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public class MainScene extends Scene {

	public static boolean showingInGameOption = false;
	private static AnchorPane root = new AnchorPane();
	private static Pane optionPane = null;

	/**
	 * Constructs a scene consisting of a root, with a dimension of width and
	 * height, and specifies whether a depth buffer is created for this scene.
	 * <p>
	 * A scene with only 2D shapes and without any 3D transforms does not need a
	 * depth buffer.
	 **/
	public MainScene() {
		super(root, 1024, 768);
		root.getStylesheets().addAll(
				"css/combo-box.css",
				"css/spinner.css",
				"css/radio-button.css",
				"css/check-box.css",
				"css/tab-pane.css",
				"css/button.css",
				"css/custom-id.css"
		);
		root.setId("root");
		isDepthBuffer();
	}

	/**
	 * Sets a new Parent replacing any other children in the root of the MainScene.
	 *
	 * @param pane new Parent
	 */
	public static void setScene(@NotNull Parent pane) {
		root.getChildren().clear();
		AnchorPane.setTopAnchor(pane, 0D);
		AnchorPane.setRightAnchor(pane, 0D);
		AnchorPane.setBottomAnchor(pane, 0D);
		AnchorPane.setLeftAnchor(pane, 0D);
		root.getChildren().add(pane);
	}

	/**
	 * Sets a new Parent replacing any other children in the root of the MainScene.
	 *
	 * @param pane         new Parent
	 * @param topAnchor    the offset from the top of the root pane
	 * @param rightAnchor  the offset from the right of the root pane
	 * @param bottomAnchor the offset from the bottom of the root pane
	 * @param leftAnchor   the offset from the left of the root pane
	 */
	public static void setScene(@NotNull Parent pane, double topAnchor, double rightAnchor, double bottomAnchor, double leftAnchor) {
		root.getChildren().clear();
		AnchorPane.setTopAnchor(pane, topAnchor);
		AnchorPane.setRightAnchor(pane, rightAnchor);
		AnchorPane.setBottomAnchor(pane, bottomAnchor);
		AnchorPane.setLeftAnchor(pane, leftAnchor);
		root.getChildren().add(pane);
	}

	public static void displayOptionMenu(@NotNull KeyEvent keyEvent) {
		if (GameController.gameOnGoing) {
			if (keyEvent.getCode() == KeyCode.ESCAPE) {
				if (showingInGameOption) {
					root.getChildren().remove(optionPane);
					showingInGameOption = false;
				} else {
					try {
						optionPane = FXMLLoader.load(MainScene.class.getResource("/controller/config/OptionPane.fxml"));
						root.getChildren().add(optionPane);
						AnchorPane.setTopAnchor(optionPane, 0D);
						AnchorPane.setRightAnchor(optionPane, 0D);
						AnchorPane.setBottomAnchor(optionPane, 0D);
						AnchorPane.setLeftAnchor(optionPane, 0D);
						showingInGameOption = true;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
