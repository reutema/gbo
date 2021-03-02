package gui.mvp.undoredoquiz.game;



import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class QuizView extends VBox {
	
	private QuizPresenter quizPresenter;
	
	private Label question;
	private ToggleGroup toggleGroup;
	
	private VBox radioGroup;
	private Button next;

	public QuizView(QuizPresenter quizPresenter) {
		
		this.quizPresenter = quizPresenter;
		
		this.question = new Label();
		this.radioGroup = new VBox();
		this.next = new Button("=>");
		
		next.setOnAction(e-> {
			if(toggleGroup.getSelectedToggle() != null) {
				RadioButton rb = (RadioButton) toggleGroup.getSelectedToggle();
				quizPresenter.setSelectedAnswer(rb.getText());
			}
			quizPresenter.nextQuestion();
		});
		
		question.setId("question");
		this.getChildren().addAll(question,radioGroup,next);
	}
	
	
	public void setQuestion(String text) {
		this.question.setText(text);
	}
	
	public void addAnswer(String text) {
		RadioButton rb = new RadioButton(text);
		rb.setSelected(false);
		if(toggleGroup == null) {
			this.toggleGroup = new ToggleGroup();
		}
		rb.setToggleGroup(toggleGroup);
		radioGroup.getChildren().add(rb);
	}
	
	public void resetAnswers() {
		toggleGroup = null;
		radioGroup.getChildren().removeAll(radioGroup.getChildren());
	}
	
	public void setQuizView() {
		next.setDisable(false);
		radioGroup.getChildren().forEach(e->{e.setVisible(true);e.setDisable(false);});
	}
	
	public void endOfQuiz() {
		question.setText("Ende des Quiz");
		next.setDisable(true);
		radioGroup.getChildren().forEach(e->{e.setVisible(false);e.setDisable(true);});
	}
	

}
