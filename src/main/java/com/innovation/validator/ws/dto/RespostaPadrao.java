package com.innovation.validator.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespostaPadrao<T> {

    private boolean valido;
    private T conteudo;
    private String codigo;
    private String mensagem;
    private List<String> motivos;
    private List<String> motivosTecnicos;
    private String status;
    private OffsetDateTime timestamp;

    public void invalidar() {
        this.valido = false;
    }

    public void addMensagem(String mensagem) {
        this.motivos.add(mensagem);
    }

    /**
     * Adiciona uma mensagem e inválida o status
     *
     * @param erro -> Erros
     */
    public void addErro(String erro) {
        invalidar();
        addMensagem(erro);
    }

    /**
     * Adiciona uma Lista de Mensagens de erro e invalida o status
     *
     * @param erros -> Erros
     */
    public void addErros(List<String> erros) {
        invalidar();
        erros.forEach(this::addMensagem);
    }
}