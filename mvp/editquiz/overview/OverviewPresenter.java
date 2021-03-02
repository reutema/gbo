package gui.mvp.editquiz.overview;

import gui.mvp.editquiz.main.MainPresenter;
import gui.mvp.editquiz.model.Model;

public class OverviewPresenter {
	
	private Model model;
	private MainPresenter mainPresenter;
	
	private OverviewView overviewView;

	public OverviewPresenter(MainPresenter mainPresenter, Model model) {
		this.model = model;
		this.mainPresenter = mainPresenter;
		
		this.overviewView = new OverviewView(this,model.getScoreList());
	}
	
	public OverviewView getView() {
		return overviewView;
	}
	
	public void removeResults() {
		model.removeScores();
		overviewView.refresh();
	}
	
}
