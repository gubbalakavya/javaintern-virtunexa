
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;





public class FlashCard{
	String question;
	String answer;

    public FlashCard(String q, String a) {
        question = q;
        answer = a;
    }
    public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
