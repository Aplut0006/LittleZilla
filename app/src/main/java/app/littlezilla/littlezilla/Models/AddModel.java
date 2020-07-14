package app.littlezilla.littlezilla.Models;

public class AddModel {
    String corectAns,optionA,optionB,optionC,optionD,question,questionno;

    public AddModel(String corectAns, String optionA, String optionB, String optionC,
                    String optionD, String question, String questionno) {
        this.corectAns = corectAns;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.question = question;
        this.questionno = questionno;
    }

    public AddModel() {
    }

    public String getCorectAns() {
        return corectAns;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestionno() {
        return questionno;
    }

    public void setCorectAns(String corectAns) {
        this.corectAns = corectAns;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestionno(String questionno) {
        this.questionno = questionno;
    }
}
