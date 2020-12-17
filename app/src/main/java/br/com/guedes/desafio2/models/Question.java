package br.com.guedes.desafio2.models;

import android.content.Intent;

public class Question {
    private String pergunta;
    private Boolean resposta;
    private Integer respostaUser;

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public Boolean getResposta() {
        return resposta;
    }

    public void setResposta(Boolean resposta) {
        this.resposta = resposta;
    }

    public Integer getRespostaUser() {
        return respostaUser;
    }

    public void setRespostaUser(int respostaUser) {
        this.respostaUser = respostaUser;
    }
}
