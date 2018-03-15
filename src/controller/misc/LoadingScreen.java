package controller.misc;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class LoadingScreen {

    @FXML
    private StackPane root;

    @FXML
    private ImageView bannerImage;

    @FXML
    private ImageView marisa_parfait_image;

    @FXML
    private ImageView loading_image;

    @FXML
    private HBox textContainer;

    @FXML
    void initialize() {
        marisa_parfait_image.fitHeightProperty().bind(root.heightProperty().multiply(0.45));
        bannerImage.fitWidthProperty().bind(root.widthProperty().multiply(0.9));
        loading_image.fitHeightProperty().bind(root.widthProperty().multiply(0.09));
        textContainer.maxWidthProperty().bind(root.widthProperty().multiply(0.6));
    }
}
