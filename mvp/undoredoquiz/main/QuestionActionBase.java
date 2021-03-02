package gui.mvp.undoredoquiz.main;

import gui.mvp.undoredoquiz.model.Model;

public abstract class QuestionActionBase implements UndoableRedoableAction{
	
	protected Model model;
	
	
	public QuestionActionBase(Model model) {
		this.model = model;
	}
	
	
}

	
