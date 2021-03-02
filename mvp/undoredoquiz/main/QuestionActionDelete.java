package gui.mvp.undoredoquiz.main;

import gui.mvp.undoredoquiz.model.Model;
import gui.mvp.undoredoquiz.model.Question;

public class QuestionActionDelete extends QuestionActionBase{
	
	private Question question;
	
	private int position;
	
	public QuestionActionDelete(Model model, Question question, int position) {
		super(model);
		this.question = question;
		this.position = position;
	}

	@Override
	public void undo() {
		model.insertQuestion(position,question);
		
	}

	@Override
	public void redo() {
		model.deleteQuestion(question);
	}

}
