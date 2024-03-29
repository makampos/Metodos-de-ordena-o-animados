package br.unifil.dc.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Write a description of class AlgoritmosAnimados here.
 * 
 * @author Matheus de campos
 * @version
 */
public class AlgoritmosAnimados {
    public static Gravador listaEstatica(List<Integer> valores) {
        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Valores da lista imutável");
        return anim;
    }

    public static Gravador buscaSequencial(List<Integer> valores, int chave) {
        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Inicio de busca sequencial");

        int i = 0;
        anim.gravarIndiceDestacado(valores, i, "Busca sequencial");
        while (i < valores.size() && valores.get(i) != chave) {
            i++;
            anim.gravarIndiceDestacado(valores, i, "Busca sequencial");
        }

        if (i < valores.size()) {
            anim.gravarIndiceDestacado(valores, i, "Chave encontrada");
        } else {
            anim.gravarLista(valores, "Chave não encontrada");
        }

        return anim;
    }


    public static Gravador ordenarPorBolha(List<Integer> valores) {
        Gravador anim = new Gravador();
        //grava os estado atual da lista
        anim.gravarLista(valores, "Disposição inicial");


        assert valores != null : "Lista não pode ser nula.";
//        System.out.println(valores.toString());

        boolean houveTroca;
        do {
            houveTroca = false;
            for (int i = 0; i < valores.size() - 1; i++) {
                //Grava a comparação
                anim.gravarComparacaoSimples(valores, i, i + 1);

                if (valores.get(i) > valores.get(i + 1)) {
                    trocar(valores, i, i + 1, anim);
                    houveTroca = true;
                }
            }
        } while (houveTroca);

        anim.gravarLista(valores, "Disposição final");
        return anim;
    }

    private static void trocar(List<Integer> valores, int i, int i1, Gravador anim) {
    }

    // Metodo 1

    public static Gravador buscaBinaria(List<Integer> valores, int chave) {
        Gravador anim = new Gravador(); //instanciando um objeto do tipo gravador
        anim.gravarLista(valores, "Inicio da busca Binária");

        ordenarPorSelecao(valores);

        int menor = 0;
        int maior = valores.size() - 1;
        int medio;

        while (menor <= maior) {
            medio = (menor + maior) / 2;
            anim.gravarIndiceBuscaBinaria(valores, menor, maior, medio);

            if (valores.get(medio) == chave) {
                anim.gravarIndiceDestacado(valores, medio, "Valor encontrado");
                return anim;
            } else if (valores.get(medio) < chave) {

                menor = medio + 1;
            } else if (valores.get(medio) > chave) {
                maior = medio - 1;
            }
        }
        anim.gravarLista(valores, "Chave não encontrada");
        return anim;
    }

    public static Gravador ordenarPorSelecao(List<Integer> valores){
        Gravador anim = new Gravador();
        //Gravando o estado inicial da lista
        anim.gravarLista(valores, "Disposição inicial");

        assert valores != null : "Lista não pode ser nula.";

        //System.out.println("i, menor, elementos");
        for (int i = 0; i < valores.size()-1; i++) {
            // Qual o índice do menor elemento?

            int menor = i;
            //anim.gravarIndiceDestacado(valores, menor, "Menor elemento");
            for (int j = i+1; j < valores.size(); j++) {
                anim.gravarComparacaoComMarcacao(valores, i, j, menor);

                if (valores.get(menor) > valores.get(j)) {
                    menor = j;
                    anim.gravarIndiceMarcado(valores, menor);
                }

            }

            //System.out.println(i + ", " + menor + ", " + valores);

            trocar(valores, i, menor, anim);
        }

        anim.gravarLista(valores, "Disposição final");
        return anim;
    }

    public static Gravador ordenarPorInsercao(List<Integer> valores) {
        Gravador anim = new Gravador();
        //Gravando o estado inicial da lista
        anim.gravarLista(valores, "Disposição inicial");

        assert valores != null : "Lista não pode ser nula.";

        for (int i = 1; i < valores.size(); i++) {
            int elemAtual = valores.get(i);

            int posicaoTroca;
            for (posicaoTroca = i; posicaoTroca > 0; posicaoTroca--) {

                anim.gravarComparacaoSimples(valores, posicaoTroca - 1, i); //comparação
                if (valores.get(posicaoTroca - 1) < elemAtual) {
                    break;
                }

                valores.set(posicaoTroca, valores.get(posicaoTroca - 1));
                anim.gravarPosTrocas(valores, posicaoTroca, posicaoTroca - 1);
            }


            valores.set(posicaoTroca, elemAtual);
            anim.gravarPosTrocas(valores, posicaoTroca, i);
        }

        anim.gravarLista(valores, "Disposição final");
        return anim;
    }

    public static Gravador ordenarPorMergesort(List<Integer> valores) {
        Gravador anim = new Gravador();
        //Gravando o estado inicial da lista
        anim.gravarLista(valores, "Disposição inicial");

        mergeSort(valores, 0, valores.size(), anim);

        anim.gravarLista(valores, "Disposição final");
        return anim;
    }

    private static void mergeSort(List<Integer> valores, int inicio, int fim, Gravador anim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            anim.gravarComparacaoComMarcacao(valores, inicio, fim, meio);

            mergeSort(valores, inicio, meio, anim);
            mergeSort(valores, meio + 1, fim, anim);
            merge(valores, inicio, fim, anim);
        }


    }

    private static void merge(List<Integer> valores, int inicio, int fim, Gravador anim) {
        //
        final int meio = (inicio + fim) / 2;

        int topoEsq = inicio;
        int topoDir = meio;
        anim.gravarComparacaoSimples(valores, topoEsq, topoDir);

        ArrayList<Integer> intercalados
                = new ArrayList<>(fim - inicio);

        while (topoEsq < meio && topoDir < fim) {

            anim.gravarComparacaoSimples(valores, topoEsq, topoDir);
            if (valores.get(topoEsq) < valores.get(topoDir)) {

                intercalados.add(valores.get(topoEsq));
                anim.gravarLista(intercalados, "Adicionado da esquerda");

                topoEsq++;
            } else {

                intercalados.add(valores.get(topoDir));
                anim.gravarLista(intercalados, "Adicionado da direita");

                topoDir++;
            }
        }

        while (topoEsq < meio) {
            intercalados.add(valores.get(topoEsq));
            anim.gravarIndiceMarcado(valores, topoEsq);
            anim.gravarLista(intercalados, "Adicionado a esquerda");
            topoEsq++;
        }
        while (topoDir < fim) {
            intercalados.add(valores.get(topoDir));
            anim.gravarIndiceMarcado(valores, topoDir);
            anim.gravarLista(intercalados, "Adicionado a direita");
            topoDir++;
        }

        int j = 0;
        for (int i = inicio; i < fim; i++) {
            // vals[i] = intercalados[j]
            valores.set(i, intercalados.get(j));
            anim.gravarIndiceMarcado(intercalados, j);
            anim.gravarIndiceDestacado(valores, i, "Adicionado a valores");
            j++;
        }

    }


}