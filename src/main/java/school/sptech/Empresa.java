package school.sptech;

import com.sun.jdi.connect.Connector;
import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.JogoInvalidoException;
import school.sptech.exception.JogoNaoEncontradoException;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Empresa {
    private String nome;
    private List<Jogo> jogos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public Empresa(){
        this.nome = nome;
        this.jogos = jogos;
    }

    public void adicionarJogo(Jogo jogo) {
        if (jogo == null) {
            throw new JogoInvalidoException();
        } else if (jogo.getCodigo() == null || jogo.getCodigo().isEmpty()) {
            throw new JogoInvalidoException();
        } else if (jogo.getNome() == null || jogo.getNome().isEmpty()) {
            throw new JogoInvalidoException();
        } else if (jogo.getGenero() == null || jogo.getGenero().isEmpty()) {
            throw new JogoInvalidoException();
        } else if (jogo.getPreco() == null || jogo.getPreco() <= 0) {
            throw new JogoInvalidoException();
        } else if (jogo.getAvaliacao() > 5 || jogo.getAvaliacao() < 0) {
            throw new JogoInvalidoException();
        } else if (jogo.getDataLancamento() == null || jogo.getDataLancamento().isAfter(LocalDate.now())) {
            throw new JogoInvalidoException();
        } else {
            jogos.add(jogo);
        }
    }

    public Jogo buscarJogoPorCodigo(String codigo) {
        boolean jogoEncontrado = false;
        if (codigo == null || codigo.isEmpty() || codigo == "" || codigo.isBlank()) {
            throw new ArgumentoInvalidoException("Código inválido");
        }
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getCodigo() == codigo) {
                jogoEncontrado = true;
                return jogos.get(i);
            }
        }
        if(jogoEncontrado == false){
            throw new JogoNaoEncontradoException();
        }
        return null;
    }

    public void removerJogoPorCodigo(String codigo){
        boolean jogoEncontrado = false;
        if (codigo == null || codigo.isEmpty() || codigo == "" || codigo.isBlank()) {
            throw new ArgumentoInvalidoException();
        }
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getCodigo() == codigo) {
                jogos.remove(jogos.get(i));
                jogoEncontrado = true;
            }
        }
            if(!jogoEncontrado){
                throw new JogoNaoEncontradoException();
            }

    }

    public Jogo buscarJogoComMelhorAvaliacao(){
        if(jogos.isEmpty()){
            throw new JogoNaoEncontradoException();
        }
        int ultimoJogo = 0;
        for(int i = 0; i < jogos.size(); i++){
            if(jogos.get(i).getAvaliacao() > jogos.get(ultimoJogo).getAvaliacao()){
                ultimoJogo = i;
            }else if(jogos.get(i).getAvaliacao() == jogos.get(ultimoJogo).getAvaliacao()){
                if(jogos.get(i).getDataLancamento().isAfter(jogos.get(ultimoJogo).getDataLancamento())){
                    ultimoJogo = i;
                }
            }
        }
        return jogos.get(ultimoJogo);
    }

    public List<Jogo> buscarJogoPorPeriodo(LocalDate dataInicio, LocalDate dataFim){
        if(dataInicio == null || dataFim == null || dataInicio.isBefore(dataFim)){
            throw new ArgumentoInvalidoException();
        }
        List<Jogo> jogosEncontrados = null;
        for(int i = 0; i < jogos.size(); i++){
            if(jogos.get(i).getDataLancamento().equals(dataFim) || jogos.get(i).equals(dataInicio)){
                jogosEncontrados.add(jogos.get(i));
            }
        }
        return jogosEncontrados;
    }
}


