package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;


/**
 * Created by samule on 13/09/16.
 */
public class levelController implements ControlledScreen {

    ObservableList<String> _quizTypeList = FXCollections.observableArrayList("New Quiz","Revision Quiz");

    private ScreensController _myParentScreensController;

    @FXML
    private ChoiceBox<String> _quizType;

    @FXML
    private void  initialize(){

        _quizType.setItems(_quizTypeList);
        _quizType.setValue("New Quiz");
    }

    private String getChoice(ChoiceBox<String> _quizType){
        String quizType = _quizType.getValue();
        return quizType;
    }

    public void enterNewQuiz(){
        if( getChoice(_quizType).equals("Revision Quiz")){
            QuizScreenController.set_isRevision(true);
        }
        else if( getChoice(_quizType).equals("New Quiz")){
            QuizScreenController.set_isRevision(false);
        }
        _myParentScreensController.setScreen(Main.quizScreenID);
    }
    //nyes
    @Override
    public void setScreenParent(ScreensController screenParent) {
        _myParentScreensController = screenParent;
    }
}