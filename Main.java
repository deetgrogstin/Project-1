import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private final String[] messages = {"She/Her", "He/Him", "They/Them"};
    private final Random random = new Random();

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        primaryStage.setTitle("Random Pronoun Generator"); // Updated title

        Label pronounsLabel = new Label("My pronouns today are..."); // Set the text to "My pronouns today are..."
        pronounsLabel.setStyle("-fx-font-size: 20;"); // Set font size
        pronounsLabel.setOnMouseClicked(event -> handlePronounsLabelClick(pronounsLabel, root));

        HBox hbox = new HBox(pronounsLabel);
        hbox.setAlignment(Pos.CENTER);

        root.getChildren().add(hbox);

        Scene scene = new Scene(root, 500, 400);

        // Apply custom CSS styling to set the title bar color
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);

        Text successMessage = new Text("");
        successMessage.setFont(Font.font("Verdana", 30)); // Set the font to Verdana with size 30
        successMessage.setVisible(false);
        root.getChildren().add(successMessage);

        // Set the initial click handler
        pronounsLabel.setOnMouseClicked(event -> handlePronounsLabelClick(pronounsLabel, root));

        // Center the window on the screen
        centerOnScreen(primaryStage);

        primaryStage.show();
    }

    private void centerOnScreen(Stage stage) {
        Screen screen = Screen.getPrimary();
        double width = screen.getBounds().getWidth();
        double height = screen.getBounds().getHeight();

        stage.setX((width - stage.getWidth()) / 2);
        stage.setY((height - stage.getHeight()) / 2);
    }

    private void handlePronounsLabelClick(Label pronounsLabel, StackPane root) {
        System.out.println("Pronouns label clicked"); // Debugging output

        // Hide the pronouns label
        pronounsLabel.setVisible(false);

        // Choose a random message from the array
        String randomMessage = messages[random.nextInt(messages.length)];

        // Display the random message
        Text successMessage = (Text) root.getChildren().get(root.getChildren().size() - 1);
        successMessage.setText(randomMessage);
        successMessage.setVisible(true);

        // Create and show the "Retry" button
        Button retryButton = new Button("Retry");
        retryButton.setOnAction(event -> handleRetryClick(pronounsLabel, root));
        HBox retryBox = new HBox(retryButton);
        retryBox.setAlignment(Pos.BOTTOM_RIGHT);
        retryBox.setTranslateX(-10); // Adjust the horizontal position
        retryBox.setTranslateY(-10); // Adjust the vertical position
        root.getChildren().add(retryBox);
    }

    private void handleRetryClick(Label pronounsLabel, StackPane root) {
        System.out.println("Retry button clicked"); // Debugging output

        // Show the pronouns label again
        pronounsLabel.setVisible(true);

        // Hide the message
        root.getChildren().get(root.getChildren().size() - 2).setVisible(false); // Index adjusted for the Retry button

        // Remove the Retry button
        root.getChildren().remove(root.getChildren().size() - 1);

        // Set a new click handler to handle the next click on the pronouns label
        root.setOnMouseClicked(event -> handlePronounsLabelClick(pronounsLabel, root));
    }
}