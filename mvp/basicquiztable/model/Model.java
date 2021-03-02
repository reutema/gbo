package gui.mvp.basicquiztable.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	
	private ObservableList<Question> scoreList;
	private int index;
	private Question dummy = new Question("dummy", new String[] {"dummy"}, 0);

	public Model() {
		this.scoreList = FXCollections.observableArrayList();;
		reset();
	}
	
	public void addQuestion(Question q) {
		this.scoreList.add(q);
	}

	
	public Question getNext() {
		Question q = scoreList.get(++index);
		return q;
	}
	
	public Question getCurrent() {
		return scoreList.get(index);
	}
	
	public int currentIndex() {
		return index;
	}
	
	public int numberOfQuestions() {
		return scoreList.size();
	}

	public void reset() {
		index = 0;
	}

	public void removeScores() {
		for(Question q : scoreList) {
			System.out.println(q);
			q.resetScores();
			System.out.println(q);
		}
		scoreList.add(dummy);
		scoreList.remove(dummy);
	}
	
	public ObservableList<Question> getScoreList(){
		
		return scoreList;
	}
}


