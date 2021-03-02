package gui.mvp.undoredoquiz.game;

import gui.mvp.undoredoquiz.main.MainPresenter;
import gui.mvp.undoredoquiz.model.Model;
import gui.mvp.undoredoquiz.model.Question;
import javafx.scene.control.Toggle;

public class QuizPresenter {
	
	private Model model;
	
	private MainPresenter mainPresenter;
	
	private QuizView quizView;
	

	public QuizPresenter(MainPresenter mainPresenter, Model model) {
		this.model = model;
		this.mainPresenter = mainPresenter;
		
		this.quizView = new QuizView(this);
	
		showQuestion(model.getCurrent());
	}
	
	public QuizView getView() {
		return quizView;
	}
	
	public void nextQuestion() {
		if(model.currentIndex() + 1 < model.numberOfQuestions()) {
			Question q = model.getNext();
			
			showQuestion(q);
		}else {
			quizView.endOfQuiz();
		}
		
	}
	
	public void currentQuestion() {
		if(model.currentIndex() < model.numberOfQuestions()) {
			Question q = model.getCurrent();
			
			showQuestion(q);
		}else {
			quizView.endOfQuiz();
		}
		
	}
	
	public void showQuestion(Question q) {
		quizView.setQuestion(q.getQuestion());
		
		quizView.resetAnswers();
		for(String pAnswer : q.getPossibleAnswers()) {
			quizView.addAnswer(pAnswer);
		}
	}

	public void startNewQuiz() {
		quizView.setQuizView();
		model.reset();
		currentQuestion();
		
	}

	public void setSelectedAnswer(String selectedAnswer) {
		Question q = model.getCurrent();
		q.answered(selectedAnswer);
	
	}

}
