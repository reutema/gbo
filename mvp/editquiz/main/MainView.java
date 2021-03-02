package gui.mvp.editquiz.main;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainView extends BorderPane{
	
	private MainPresenter mainPresenter;
	
	private HBox topArea;
	private HBox bottomArea;
	
	private Button contQuizButton;

	public MainView(MainPresenter mainPresenter) {

		this.mainPresenter = mainPresenter;
		
		this.topArea = new HBox();
		this.bottomArea = new HBox();
		
		
		Button startButton = new Button("Quiz starten!");
		this.contQuizButton = new Button("Quiz fortsetzen!");
		Button overviewButton = new Button("Überblick!");
		Button editorButton = new Button("Quiz editieren!");
		
		startButton.setId("start");
		contQuizButton.setId("continue");
		overviewButton.setId("overview");
		editorButton.setId("editor");
		
		startButton.setOnAction(e-> mainPresenter.startNewQuiz());
		contQuizButton.setOnAction(e-> mainPresenter.continueQuiz());
		overviewButton.setOnAction(e->mainPresenter.overviewView());
		editorButton.setOnAction(e->mainPresenter.editView());
		
		addToTopArea(startButton,contQuizButton,overviewButton,editorButton);
		
		
		this.setTop(topArea);
		this.setBottom(bottomArea);
		
		this.setPrefSize(800, 500);
	}
	


	private void addToTopArea(Node... nodes) {
		topArea.getChildren().addAll(nodes);
	}
	private void addToBottomArea(Node... nodes) {
		bottomArea.getChildren().addAll(nodes);
	}
	
	public void disableContinueButton() {
		this.contQuizButton.setDisable(true);
	}
	
	public void enableContinueButton() {
		this.contQuizButton.setDisable(false);
	}
}
