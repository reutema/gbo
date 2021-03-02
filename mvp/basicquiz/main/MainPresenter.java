package gui.mvp.basicquiz.main;

import gui.mvp.basicquiz.game.QuizPresenter;
import gui.mvp.basicquiz.model.Model;
import gui.mvp.basicquiz.overview.OverviewPresenter;

public class MainPresenter {

	private Model model;
	private MainView mainView;
	private OverviewPresenter overviewPresenter;
	private QuizPresenter quizPresenter;
	
	public MainPresenter(Model model) {
		
		this.model = model;
		this.mainView = new MainView(this);
		
		this.overviewPresenter = new OverviewPresenter(this,model);
		this.quizPresenter = new QuizPresenter(this,model);
		
		continueQuiz();
	}
	
	
	public MainView getView() {
		return mainView;
	}


	public void startNewQuiz() {
		continueQuiz();
		quizPresenter.startNewQuiz();
		
	}
	
	public void continueQuiz() {
		mainView.setCenter(quizPresenter.getView());
	}


	public void overviewView() {
		mainView.setCenter(overviewPresenter.getView());	
	}
	
}
