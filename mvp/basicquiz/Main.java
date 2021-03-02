package gui.mvp.basicquiz;


import java.util.List;

import gui.mvp.basicquiz.main.MainPresenter;
import gui.mvp.basicquiz.model.Model;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		List<String> parameters = this.getParameters().getUnnamed();
		
		Model model = ModelInitializer.initModel(parameters.get(0));
		
		MainPresenter mainPresenter = new MainPresenter(model);
		
		Scene scene = new Scene(mainPresenter.getView());
			
		
		primaryStage.setTitle("Quiz");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
