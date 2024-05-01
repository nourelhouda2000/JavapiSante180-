package ApiVedio;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;


public class Video extends JFrame {
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public Video(String title, String videoUrl) {
        super(title);
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        setContentPane(mediaPlayerComponent);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Créer des boutons pour contrôler la vidéo
        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().play());

        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().pause());

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().stop());

        JSlider volumeSlider = new JSlider(0, 100);
        volumeSlider.addChangeListener(e -> mediaPlayerComponent.mediaPlayer().audio().setVolume(volumeSlider.getValue()));

        // Ajouter les boutons à la fenêtre
        JPanel controlPanel = new JPanel();
        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(stopButton);
        controlPanel.add(volumeSlider);

        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);

        // Lire la vidéo à partir de l'URL
        mediaPlayerComponent.mediaPlayer().media().play(videoUrl);
    }
}
