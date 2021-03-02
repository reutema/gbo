package gui.mvp.basicquiztable.overview;

import gui.mvp.basicquiztable.main.MainPresenter;
import gui.mvp.basicquiztable.model.Model;

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
