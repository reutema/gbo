package gui.mvp.undoredoquiz.main;

import gui.mvp.undoredoquiz.model.Model;
import gui.mvp.undoredoquiz.model.Question;

public class QuestionActionAdd extends QuestionActionBase{
	
	private Question question;
	
	
	public QuestionActionAdd(Model model, Question question) {
		super(model);
		this.question = question;
	}

	@Override
	public void undo() {
		model.deleteQuestion(question);
	}

	@Override
	public void redo() {
		model.addQuestion(question);
	}

}
