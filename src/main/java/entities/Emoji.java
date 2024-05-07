package entities;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Emoji extends VBox {
    public Emoji() {
        // Charger tous les emojis disponibles
        List<String> sportEmojis = List.of(
                "⚽", "🏀", "🏈", "⚾", "🎾", "🏐", "🏉", "🎱", "🏓", "🏸", "🏒", "🏑", "🥍", "🏏", "⛳", "🏹", "🎣", "🥊", "🥋", "🎽",
                "🏃‍♂️", "🏃‍♀️", "🚴‍♂️", "🚴‍♀️", "🏊‍♂️", "🏊‍♀️", "🏋️‍♂️", "🏋️‍♀️",
                "🧘‍♂️", "🧘‍♀️", "🕉️", "🙏",
                "💃", "🕺", "🎶"
        );

        List<String> selectedEmojis = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 19; i++) {
            int randomIndex = random.nextInt(sportEmojis.size());
            selectedEmojis.add(sportEmojis.get(randomIndex));
        }

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10));
        flowPane.setHgap(10);

        for (String emoji : selectedEmojis) {
            Label emojiLabel = new Label(emoji);
            emojiLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: darkgreen;");
            flowPane.getChildren().add(emojiLabel);
        }

        getChildren().add(flowPane);
    }
}
