package gui.mvp.editquiz.main;

import gui.mvp.editquiz.editor.EditorPresenter;
import gui.mvp.editquiz.game.QuizPresenter;
import gui.mvp.editquiz.model.Model;
import gui.mvp.editquiz.overview.OverviewPresenter;

public class MainPresenter {

	private Model model;
	private MainView mainView;
	private OverviewPresenter overviewPresenter;
	private QuizPresenter quizPresenter;
	private EditorPresenter editorPresenter;
	
	public MainPresenter(Model model) {
		
		this.model = model;
		this.mainView = new MainView(this);
		
		this.overviewPresenter = new OverviewPresenter(this,model);
		this.quizPresenter = new QuizPresenter(this,model);
		this.editorPresenter = new EditorPresenter(this.model);
		
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
	
}
