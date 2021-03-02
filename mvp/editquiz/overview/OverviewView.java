package gui.mvp.editquiz.overview;


import gui.mvp.editquiz.model.Question;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import test.Person;

public class OverviewView extends VBox{
	
	private OverviewPresenter overviewPresenter;
	
	private TableView<Question> scoreList;

	public OverviewView(OverviewPresenter overviewPresenter, ObservableList<Question> list) {
		
		this.overviewPresenter = overviewPresenter;
		
		Label overviewLabel = new Label("Übersicht");
		Button deleteButton = new Button("Ergebnisse löschen");
		
		
		deleteButton.setId("deleteHistory");
				
		deleteButton.setOnAction(e->overviewPresenter.removeResults());
		
		initTableView(list);
		this.getChildren().addAll(overviewLabel,scoreList,deleteButton);
		
	}
	
	private void initTableView(ObservableList list) {
		scoreList = new TableView<Question>();
		scoreList.setId("overviewTable");
		scoreList.setItems(list);
		 
		 TableColumn<Question,String> questionCol = new TableColumn<Question,String>("Frage");
		 questionCol.setCellValueFactory(new PropertyValueFactory("question"));
		 questionCol.setId("questionCol");
		 
		 TableColumn<Question,Integer> totalAnswerCol = new TableColumn<Question,Integer>("Gesamtanzahl gespielt");
		 totalAnswerCol.setCellValueFactory(new PropertyValueFactory("answered"));
		 totalAnswerCol.setId("totalAnswerCol");
		 
		 TableColumn<Question,Integer> correctAnswerCol = new TableColumn<Question,Integer>("Anzahl richtig");
		 correctAnswerCol.setCellValueFactory(new PropertyValueFactory<>("correctAnswered"));
		 correctAnswerCol.setId("correctAnswerCol");
		 
		 scoreList.getColumns().setAll(questionCol, totalAnswerCol,correctAnswerCol);
		 
	}
	
	public void refresh() {

		scoreList.refresh();
	}

	

	
	
}
