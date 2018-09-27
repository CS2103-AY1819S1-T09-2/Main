package seedu.address.model.person;

/**
 * Represents a Card inside a Deck in Anakin.
 */

public class Card {
    private String question;
    private String answer;
    private int rate;

    public Card (String qns, String ans) {
        question = qns;
        answer = ans;
        rate = 0;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void editQuestion(String newQuestion) {
        question = newQuestion;
    }

    public void editAnswer(String newAnswer) {
        answer = newAnswer;
    }

    @Override
    public boolean equals(Object other) {
        // TODO: Implement proper equals method
        return other == this;
    }

    @Override
    public String toString() {
        return "Question: " + question + "\n\rAnswer: " + answer;
    }
}
