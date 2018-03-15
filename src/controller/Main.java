package controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import object.scene.MainScene;

import java.util.concurrent.Semaphore;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final KeyCombination kb = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);

		primaryStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.DEAD_SEMIVOICED_SOUND, KeyCombination.META_DOWN));
		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (kb.match(e) || e.getCode() == KeyCode.F11) primaryStage.setFullScreen(!primaryStage.isFullScreen());
		});
		primaryStage.setFullScreenExitHint("");

		primaryStage.setScene(new MainScene());
		primaryStage.setMinWidth(640);
		primaryStage.setMinHeight(480);
		primaryStage.setMaximized(true);
		primaryStage.show();
		MainScene.setScene(FXMLLoader.load(getClass().getResource("/controller/MainScreen.fxml")), 0, 42, 0, 42);
	}

}
