package gui.mvp.editquiz.editor;

import java.util.Optional;

import gui.mvp.editquiz.model.Question;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditorView extends VBox  {
	
	private EditorPresenter editorPresenter;
	
	private ListView<Question> listView;
	private Alert notSelectedAlert;
	private Alert deleteAlert;
	private EditorDialog editorDialog;

	public EditorView(EditorPresenter editorPresenter, ObservableList<Question> questionList) {
		this.editorPresenter = editorPresenter;

		createNotSelectedAlert();
		createDeleteAlert();
		editorDialog = new EditorDialog(this);
		
		Label headerLabel = new Label("Editor");
		listView = new ListView(questionList);
		HBox buttonBox = new HBox();
		Button addButton = new Button("Frage hinzufügen");
		Button editButton = new Button("Frage editieren");
		Button deleteButton = new Button("Frage l\u00f6schen");
		
		
		listView.setId("editorList");
		addButton.setId("addQuestion");
		editButton.setId("editQuestion");
		deleteButton.setId("deleteQuestion");
		
		
		addButton.setOnAction(e->showCreateDialog());
		editButton.setOnAction(e->showChangeDialog());
		deleteButton.setOnAction(e->editorPresenter.deleteQuestion());
		
		buttonBox.getChildren().addAll(addButton,editButton,deleteButton);
		this.getChildren().addAll(headerLabel,listView,buttonBox);
		
	}
	
	public Question selectedQuestion() {
		return listView.getSelectionModel().getSelectedItem();
	}
	
	private void createNotSelectedAlert() {
		this.notSelectedAlert = new Alert(AlertType.INFORMATION);
		notSelectedAlert.setTitle("Information");
		notSelectedAlert.setContentText("Es muss eine Frage ausgew\u00e4hlt werden!");
	}
	
	public boolean showNotSelectedAlert() {
		Optional<ButtonType> buttons = notSelectedAlert.showAndWait();
		
		return buttons.isPresent() && (buttons.get() == ButtonType.OK);
	}
	
	private void createDeleteAlert() {
		this.deleteAlert = new Alert(AlertType.CONFIRMATION);
		deleteAlert.setTitle("Frage");
		deleteAlert.setContentText("Soll diese Frage wirklich gel\u00f6scht werden?");
	}

	public boolean showDeleteAlert() {
		Optional<ButtonType> buttons = deleteAlert.showAndWait();
		
		return buttons.isPresent() && (buttons.get() == ButtonType.OK || buttons.get() != ButtonType.CANCEL);
	}
	
	public void showChangeDialog() {
		Question question = selectedQuestion();
		if(question == null) {
			showNotSelectedAlert();
		}else {
			// set mode of Dialog and fill it with data
			editorDialog.setChangeMode(question.getQuestion(),question.getPossibleAnswers(),question.getCorrectAnswered());
			// wait for change
			Optional<ButtonType> buttons = editorDialog.showAndWait();
			
			if(buttons.isPresent() && buttons.get().getButtonData() == ButtonData.APPLY) {
				editorDialog.processData();
				editorPresenter.changeQuestion(question, editorDialog.getQuestion(), editorDialog.getPossibleAnswers(), editorDialog.getIndexOfCorrectAnswer());
			}
		}
	}
	
	public void showCreateDialog() {
		editorDialog.setCreateMode();
		Optional<ButtonType> buttons = editorDialog.showAndWait();
		
		if(buttons.isPresent() && buttons.get().getButtonData() == ButtonData.APPLY) {
			editorDialog.processData();
			editorPresenter.createQuestion(editorDialog.getQuestion(), editorDialog.getPossibleAnswers(), editorDialog.getIndexOfCorrectAnswer());
		}
	}


}
