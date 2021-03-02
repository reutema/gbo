package gui.mvp.editquiz.editor;

import java.util.Arrays;

import gui.mvp.editquiz.model.Model;
import gui.mvp.editquiz.model.Question;

public class EditorPresenter {
	
	private Model model;
	
	private EditorView editorView;

	public EditorPresenter(Model model) {
		this.model = model;
		
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
				model.deleteQuestion(editorView.selectedQuestion());
			}
		}
		model.printAll();
	}

	public void createQuestion(String question, String[] possibleAnswers, int indexOfCorrectAnswer) {
		
		if(!question.isBlank() && !hasEmptyContent(possibleAnswers)) {
			
			model.addQuestion(new Question(question, possibleAnswers, indexOfCorrectAnswer));
			model.printAll();
		}
	}

	public void changeQuestion(Question question,String questionText, String[] possibleAnswers, int indexOfCorrectAnswer) {
		if(question != null) {
			System.out.println(possibleAnswers.length);
			System.out.println(Arrays.toString(possibleAnswers));
			Question q = new Question(questionText,	possibleAnswers,indexOfCorrectAnswer);
			
			model.changeQuestion(question,q);
					
		}else {
			editorView.showNotSelectedAlert();
		}
		
		model.printAll();
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
