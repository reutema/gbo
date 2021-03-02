package gui.mvp.basicquiz.overview;


import gui.mvp.basicquiz.model.Question;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class OverviewView extends VBox{
	
	private OverviewPresenter overviewPresenter;
	
	private ListView<Question> scoreList;

	public OverviewView(OverviewPresenter overviewPresenter, ObservableList<Question> list) {
		
		this.overviewPresenter = overviewPresenter;
		
		Label overviewLabel = new Label("Übersicht");
		scoreList = new ListView<>(list);
		Button deleteButton = new Button("Ergebnisse löschen");
		
		
		scoreList.setId("overviewList");
		deleteButton.setId("deleteHistory");
				
		deleteButton.setOnAction(e->overviewPresenter.removeResults());
		
		
		this.getChildren().addAll(overviewLabel,scoreList,deleteButton);
		
	}
	
	public void refresh() {

		scoreList.refresh();
	}

	

	
	
}
