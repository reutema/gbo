package gui.mvp.undoredoquiz.main;

import gui.mvp.undoredoquiz.editor.EditorPresenter;
import gui.mvp.undoredoquiz.game.QuizPresenter;
import gui.mvp.undoredoquiz.model.Model;
import gui.mvp.undoredoquiz.overview.OverviewPresenter;

public class MainPresenter {

	// Model
	private Model model;
	
	// View
	private MainView mainView;
	
	// Presenter
	private OverviewPresenter overviewPresenter;
	private QuizPresenter quizPresenter;
	private EditorPresenter editorPresenter;
	private UndoRedoManager urManager;
	
	
	
	public MainPresenter(Model model) {
		
		this.model = model;
		this.urManager = new UndoRedoManager();
		
		this.mainView = new MainView(this);
		
		this.overviewPresenter = new OverviewPresenter(this,model);
		this.quizPresenter = new QuizPresenter(this,model);
		this.editorPresenter = new EditorPresenter(this.model,this,urManager);
		
		continueQuiz();
	}
	
	
	public MainView getView() {
		return mainView;
	}


	public void startNewQuiz() {
		mainView.enableContinueButton();
		continueQuiz();
		quizPresenter.startNewQuiz();
	}
	
	public void continueQuiz() {
		mainView.setCenter(quizPresenter.getView());
	}


	public void overviewView() {
		mainView.setCenter(overviewPresenter.getView());	
	}


	public void editView() {
		mainView.disableContinueButton();
		mainView.setCenter(editorPresenter.getView());
	}


	public void undo() {
		editView();
		urManager.undo();
		undoable();
		redoable();
	}


	public void redo() {
		editView();
		urManager.redo();
		redoable();
		undoable();
	}
	
	public void undoable() {
		if(urManager.canUndo()) {
			mainView.enableUndoButton();
		}else {
			mainView.disableUndoButton();
		}
	}
	
	public void redoable() {
		if(urManager.canRedo()) {
			mainView.enableRedoButton();
		}else {
			mainView.disableRedoButton();
		}
	}
	
	public void refreshUndoRedo() {
		undoable();
		redoable();
	}
}
