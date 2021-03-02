package gui.mvp.editquiz.editor;


import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditorDialog extends Dialog<ButtonType>{
	
	private EditorView editorView;
	
	private boolean createMode;
	
	private DialogPane dPane;
	private TextArea tArea;
	private VBox answerBox;
	private ToggleGroup toggleGroup;
	
	private ButtonType changeButton,createButton,cancelButton;
	
	private String[] possibleAnswers;
	private int indexOfCorrectAnswer;
	
	
	public EditorDialog(EditorView editorView) {
		this.editorView = editorView;
		
		this.dPane = new DialogPane();
		VBox contentBox = new VBox();
		
		Label header = new Label("Frage");
		this.tArea = new TextArea();
		Button addAnswerButton = new Button("Antwort hinzuf\u00fcgen");
		this.answerBox  = new VBox();
		this.toggleGroup = new ToggleGroup();
		this.changeButton = new ButtonType("\u00c4ndern",ButtonData.APPLY);
		this.createButton = new ButtonType("Hinzuf\u00fcgen",ButtonData.APPLY);
		this.cancelButton = new ButtonType("Abbrechen",ButtonData.CANCEL_CLOSE);
		
		tArea.setId("dialogQuestion");
		
		addAnswerButton.setOnAction(e-> addAnswer("", false));	
		
		contentBox.setPrefHeight(450);
		
		this.setTitle("Dialog");
		

		contentBox.getChildren().addAll(header,tArea,addAnswerButton,answerBox);
		this.dPane.setContent(contentBox);
		this.setDialogPane(dPane);
	}
	
	private void addAnswer(String answer, boolean isCorrectAnswer) {
		HBox hbox = new HBox();
		RadioButton rb = new RadioButton();
		TextField input = new TextField(answer);
		Button delButton = new Button("L\u00f6schen");
		
		rb.setSelected(isCorrectAnswer);
		rb.setToggleGroup(toggleGroup);
		
		delButton.setOnAction(e->deleteAnswer(hbox));


		hbox.getChildren().addAll(rb,input,delButton);
		answerBox.getChildren().add(hbox);
	}
	
	private void deleteAnswer(HBox parent) {
		RadioButton rb = (RadioButton) parent.getChildren().get(0);
		
		toggleGroup.getToggles().remove(rb);
		answerBox.getChildren().remove(parent);
		
		if(rb.isSelected()) {
			toggleGroup.getToggles().get(0).setSelected(true);
		}
	}

	public void setChangeMode(String question, String[] internPossibleAnswers, int correctAnswer) {
		
		reset();
		this.dPane.getButtonTypes().addAll(cancelButton,changeButton);
		createMode = false;
	
		this.tArea.setText(question);
		for(int i = 0; i < internPossibleAnswers.length;i++) {
			addAnswer(internPossibleAnswers[i],i==correctAnswer);
		}
		
	}
	
	public void setCreateMode() {
		reset();
		this.dPane.getButtonTypes().addAll(cancelButton,createButton);
		createMode = true;
	}

	private void reset() {
		setQuestion("");
		setPossibleAnswers(null);
		setIndexOfCorrectAnswer(0);
		
		this.dPane.getButtonTypes().removeAll(dPane.getButtonTypes());
		
		toggleGroup.getToggles().removeAll(toggleGroup.getToggles());
		
		answerBox.getChildren().removeAll(answerBox.getChildren());
	}
	
	
	public void processData() {
		int size = answerBox.getChildren().size();
		String[] possibleAnswersList = new String[size];
		int internIndexOfCorrectAnswer = 0;
		
		for(int i = 0; i < size; i++) {
			HBox parent = (HBox) answerBox.getChildren().get(i);
			RadioButton rb = (RadioButton) parent.getChildren().get(0);
			TextField tf = (TextField) parent.getChildren().get(1);

			if(!tf.getText().isBlank()) {
				possibleAnswersList[i] = (tf.getText());

			}else {
				possibleAnswersList[i] = "";
			}
			
			if(rb.isSelected()) {
				internIndexOfCorrectAnswer = i;
			}
		}

		
		this.indexOfCorrectAnswer = internIndexOfCorrectAnswer;
		this.possibleAnswers = possibleAnswersList;
		/*
		if(createMode) {
			editorView.createQuestion(tArea.getText(),possibleAnswers,indexOfCorrectAnswer);
		}else {
			editorView.changeQuestion(tArea.getText(), possibleAnswers, indexOfCorrectAnswer);
		}*/
	}
	
	
	public String getQuestion() {
		return tArea.getText();
	}
	
	private void setQuestion(String text) {
		this.tArea.setText(text);
	}

	public String[] getPossibleAnswers() {
		return possibleAnswers;
	}

	private void setPossibleAnswers(String[] possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	public int getIndexOfCorrectAnswer() {
		return indexOfCorrectAnswer;
	}

	private void setIndexOfCorrectAnswer(int indexOfCorrectAnswer) {
		this.indexOfCorrectAnswer = indexOfCorrectAnswer;
	}


}
