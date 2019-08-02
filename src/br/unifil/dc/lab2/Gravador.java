package br.unifil.dc.lab2;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Write a description of class Gravador here.
 *
 * @author Matheus de campos
 * * @version
 */
public class Gravador
{
    public Gravador() {
        this.seqGravacoes = new ArrayList<Transparencia>();
    }

    public void gravarLista(List<Integer> lista, String nome) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        ListaGravada gravacao = new ListaGravada(copia, cores, nome);
        seqGravacoes.add(gravacao);
    }

    public void gravarIndiceDestacado(List<Integer> lista, int i, String nome) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.YELLOW);
        ListaGravada gravacao = new ListaGravada(copia, cores, nome);
        seqGravacoes.add(gravacao);
    }

    public void gravarComparacaoSimples(List<Integer> lista, int i, int j) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.PINK);
        cores.set(j, Color.PINK);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Comparação");
        seqGravacoes.add(gravacao);
    }

    public void gravarPosTrocas(List<Integer> lista, int i, int j) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.YELLOW);
        cores.set(j, Color.YELLOW);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Pós-troca");
        seqGravacoes.add(gravacao);
    }

    public ListIterator<Transparencia> getFilme() {
        return seqGravacoes.listIterator();
    }

    private static List<Color> novaListaColors(int numElems) {
        ArrayList<Color> lista = new ArrayList<>(numElems);
        for (; numElems > 0; numElems--) lista.add(null);
        return lista;
    }

    private List<Transparencia> seqGravacoes;

    public void gravarIndiceBuscaBinaria(List<Integer> valores, int menor, int maior, int medio) {
    }

    public void gravarIndiceMarcado(List<Integer> valores, int menor) {
    }

    public void gravarComparacaoComMarcacao(List<Integer> valores, int i, int j, int menor) {
    }
}
