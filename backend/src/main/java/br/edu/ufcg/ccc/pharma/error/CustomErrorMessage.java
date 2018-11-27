package br.edu.ufcg.ccc.pharma.error;

public class CustomErrorMessage {
    private String errorMessage;

    public CustomErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public CustomErrorMessage() {}

    public String getErrorMessage() {
        return errorMessage;
    }
}
