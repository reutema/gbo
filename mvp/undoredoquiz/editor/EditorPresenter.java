package gui.mvp.undoredoquiz.editor;

import java.util.Arrays;

import gui.mvp.undoredoquiz.main.MainPresenter;
import gui.mvp.undoredoquiz.main.QuestionActionAdd;
import gui.mvp.undoredoquiz.main.QuestionActionChange;
import gui.mvp.undoredoquiz.main.QuestionActionDelete;
import gui.mvp.undoredoquiz.main.UndoRedoManager;
import gui.mvp.undoredoquiz.model.Model;
import gui.mvp.undoredoquiz.model.Question;


public class EditorPresenter {
	
	private Model model;
	
	private EditorView editorView;
	
	private MainPresenter mainPresenter;

	private UndoRedoManager urManager;
	
	public EditorPresenter(Model model,MainPresenter mainPresenter, UndoRedoManager urManager) {
		this.model = model;
		this.mainPresenter = mainPresenter;
		this.urManager = urManager;
		
		this.editorView = new EditorView(this,model.getScoreList());
	}

	public EditorView getView() {
		return editorView;
	}
	
	public boolean isQuestionSelected() {
		if(editorView.selectedQuestion() == null) {
			editorView.showNotSelectedAlert();
			return false;
		}
		return true;
	}
	
	public void deleteQuestion() {
		if(isQuestionSelected()) {
			if(editorView.showDeleteAlert()) {
				Question question = editorView.selectedQuestion();
				int position = model.getIndexOf(question);
				model.deleteQuestion(question);
				
				// undo redo action
				QuestionActionDelete action = new QuestionActionDelete(model, question, position);
				urManager.addAction(action);
				mainPresenter.refreshUndoRedo();
			}
		}
		
	}

	public void createQuestion(String question, String[] possibleAnswers, int indexOfCorrectAnswer) {
		
		if(!question.isBlank() && !hasEmptyContent(possibleAnswers)) {
			Question newQuestion = new Question(question, possibleAnswers, indexOfCorrectAnswer);
			model.addQuestion(newQuestion);
			
			// undo redo action
			QuestionActionAdd action = new QuestionActionAdd(model, newQuestion);
			urManager.addAction(action);
			mainPresenter.refreshUndoRedo();
		}
	}

	public void changeQuestion(Question question,String questionText, String[] possibleAnswers, int indexOfCorrectAnswer) {
		if(question != null) {
			Question q = new Question(questionText,	possibleAnswers,indexOfCorrectAnswer);
			
			model.changeQuestion(question,q);
					
			// undo redo action#
			QuestionActionChange action = new QuestionActionChange(model, question, q);
			urManager.addAction(action);
			mainPresenter.refreshUndoRedo();
			
		}else {
			editorView.showNotSelectedAlert();
		}
		
	}
	
	public boolean hasEmptyContent(String[] possibleAnswers) {
		
		if(possibleAnswers == null || possibleAnswers.length <= 0) {
			return true;
		}	
		
		for( String s : possibleAnswers) {
			
			if(s == null || s.isBlank()) {
				return true;
			}
		}
		
		return false;
	}
	
	
}
