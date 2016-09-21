package gui;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by samule on 20/09/16.
 */
public class VideoPlayerController implements Initializable, ControlledScreen{

    private MasterController _myParentScreensController;

    @FXML
    private MediaView _mediaView;
    private MediaPlayer _mediaPlayer;
    private Media _media;

    @FXML
    private Slider _volumeSlider;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("./resources/play.mp4");
        _media = new Media(file.toURI().toString());
        _mediaPlayer = new MediaPlayer(_media);
        System.out.println("could compile Media player");
        _mediaView.setMediaPlayer(_mediaPlayer);
        _volumeSlider.setValue(_mediaPlayer.getVolume() * 100);
        _volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                _mediaPlayer.setVolume(_volumeSlider.getValue() / 100);
            }
        });

    }

    public void play(ActionEvent event){
        _mediaPlayer.play();
        _mediaPlayer.setRate(1);
    }

    public void pause(ActionEvent event){
        _mediaPlayer.pause();
    }

    public void fastforward(ActionEvent event){
        _mediaPlayer.setRate(2);
    }

    public void slowDown(ActionEvent event){
        _mediaPlayer.setRate(0.5);
    }

    public void reload(ActionEvent event){
        _mediaPlayer.seek(_mediaPlayer.getStartTime());
        _mediaPlayer.play();
        _mediaPlayer.setRate(1);
    }

    public void returnToPostQuizScreen(ActionEvent event){
        _mediaPlayer.stop();
        _myParentScreensController.setScreen(Main.Screen.POSTQUIZ);
    }



    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentScreensController = screenParent;
    }

    @Override
    public void setup() {

    }
}
