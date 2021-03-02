package gui.mvp.undoredoquiz.main;

import gui.mvp.undoredoquiz.model.Model;
import gui.mvp.undoredoquiz.model.Question;

public class QuestionActionChange extends QuestionActionBase{
		
	private Question oldQuestion,newQuestion;
	
	public QuestionActionChange(Model model, Question oldQuestion, Question newQuestion) {
		super(model);
		this.oldQuestion = oldQuestion;
		this.newQuestion = newQuestion;
	}

	@Override
	public void undo() {
		model.changeQuestion(newQuestion, oldQuestion);
		
	}

	@Override
	public void redo() {
		model.changeQuestion(oldQuestion, newQuestion);
	}

}
