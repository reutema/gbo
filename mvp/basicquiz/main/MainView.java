package gui.mvp.basicquiz.main;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainView extends BorderPane{
	
	private MainPresenter mainPresenter;
	
	private HBox topArea;
	private HBox bottomArea;

	public MainView(MainPresenter mainPresenter) {

		this.mainPresenter = mainPresenter;
		
		this.topArea = new HBox();
		this.bottomArea = new HBox();
		
		
		Button startButton = new Button("Quiz starten!");
		Button contQuizButton = new Button("Quiz fortsetzen!");
		Button overviewButton = new Button("Überblick!");
		
		startButton.setId("start");
		contQuizButton.setId("continue");
		overviewButton.setId("overview");
		
		startButton.setOnAction(e-> mainPresenter.startNewQuiz());
		contQuizButton.setOnAction(e-> mainPresenter.continueQuiz());
		overviewButton.setOnAction(e->mainPresenter.overviewView());
		
		addToTopArea(startButton,contQuizButton,overviewButton);
		
		
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
}
