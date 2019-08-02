package br.unifil.dc.lab2;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;


public class AnimadorAlgoritmos extends JFrame {

    /**
     * O construtor do aplicativo AnimadorAlgoritmos. É aqui que todos os elementos da interface gráfica são
     * construídos, configurados e posicionados. Por enquanto, aqui também é feito o tratamento de
     * eventos (input de usuário através de mouse e teclado), utilizando métodos anônimos (lambda).
     *
     * @see javax.swing.JFrame
     */
    public AnimadorAlgoritmos() {

        // Cria e configura botões
        btnCarregar = new JButton("Carregar");
        btnCarregar.addActionListener((e) -> onBtnCarregarPressionado());

        btnProx = new JButton("Prox");
        btnProx.setEnabled(false);
        btnProx.addActionListener((e) -> onBtnProxPressionado());

        btnAnt = new JButton("Ant");
        btnAnt.setEnabled(false);
        btnAnt.addActionListener((e) -> onBtnAntPressionado());

        // Cria e configura o campo de seleção de algoritmos animados
        OpcaoAlgoritmo algsAnimados[] = {
        OpcaoAlgoritmo.LISTA_ESTATICA,
        OpcaoAlgoritmo.BUSCA_BINARIA, OpcaoAlgoritmo.BUSCA_SEQUENCIAL,
        OpcaoAlgoritmo.ORDENACAO_BOLHA, OpcaoAlgoritmo.ORDENACAO_SELECAO,
        OpcaoAlgoritmo.ORDENACAO_INSERCAO, OpcaoAlgoritmo.ORDENACAO_MERGESORT,
        OpcaoAlgoritmo.ORDENACAO_QUICKSORT};
        boxListaAlgoritmos = new JComboBox<OpcaoAlgoritmo>(algsAnimados);
        boxListaAlgoritmos.addItemListener((ItemEvent e) -> onSlctOpcaoAlgoritmo((OpcaoAlgoritmo) e.getItem()));

        final int COMPRIMENTO_ENTRADA_LISTA = 20;
        txfEntradaValores = new JTextField("", COMPRIMENTO_ENTRADA_LISTA);

        final int COMPRIMENTO_ENTRADA_CHAVE_BUSCA = 3;
        txfEntradaChaveBusca = new JTextField("", COMPRIMENTO_ENTRADA_CHAVE_BUSCA);

        // Campo para abrigar e organizar os botões e campos de entrada
        JPanel pnlBotoes = new JPanel(new FlowLayout());
        pnlBotoes.add(new JLabel("Valores:"));
        pnlBotoes.add(txfEntradaValores);
        pnlBotoes.add(new JLabel("Chave:"));
        pnlBotoes.add(txfEntradaChaveBusca);
        pnlBotoes.add(boxListaAlgoritmos);
        pnlBotoes.add(btnCarregar);
        pnlBotoes.add(btnAnt);
        pnlBotoes.add(btnProx);

        // Cria e configura a tela do desenhista
        tela = new Tocador();
        tela.setPreferredSize(new Dimension(800, 600));

        // Container que organiza o posicionamento dos botões e da tela
        Container organizacao = getContentPane();
        organizacao.setLayout(new BorderLayout());
        organizacao.add(tela, BorderLayout.CENTER);
        organizacao.add(pnlBotoes, BorderLayout.SOUTH);

        // Configurações de comportamento do aplicativo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AnimadorAlgoritmos - Lab. Comp. II");
        pack();
        setVisible(true);
        requestFocus();
    }

    /**
     * Event listeners a partir daqui, tratadores de ações do usuário.
     */

    public void onBtnCarregarPressionado() {
        // Verifica o desenho escolhido no ComboBox e repassa à tela para pintura
        List<Integer> valores = textoParaLista(txfEntradaValores.getText());
        OpcaoAlgoritmo algoritmo = (OpcaoAlgoritmo) boxListaAlgoritmos.getSelectedItem();
        JOptionPane.showMessageDialog(null,"Valores carregados com sucesso");

        Gravador novoFilme = null;
        switch (algoritmo) {

            case LISTA_ESTATICA:
                novoFilme = AlgoritmosAnimados.listaEstatica(valores);
                break;

            case BUSCA_SEQUENCIAL:
                int chave = Integer.parseInt(txfEntradaChaveBusca.getText());
                novoFilme = AlgoritmosAnimados.buscaSequencial(valores, chave);
                break;

            case ORDENACAO_BOLHA:
                novoFilme = AlgoritmosAnimados.ordenarPorBolha(valores);
                break;

            case ORDENACAO_INSERCAO:
                novoFilme = AlgoritmosAnimados.ordenarPorInsercao(valores);
                break;

            case ORDENACAO_SELECAO:
                novoFilme = AlgoritmosAnimados.ordenarPorSelecao(valores);
                break;

            case ORDENACAO_MERGESORT:
                novoFilme = AlgoritmosAnimados.ordenarPorMergesort(valores);
                break;

            case BUSCA_BINARIA:
                chave = Integer.parseInt(txfEntradaChaveBusca.getText());
                novoFilme = AlgoritmosAnimados.buscaBinaria(valores, chave);
                break;


            default:
                throw new UnsupportedOperationException("Funcionalidade ainda não implementada pelo aluno");
        }


        if (novoFilme != null) {
            tela.carregarFilme(novoFilme.getFilme());
            btnProx.setEnabled(true);
            btnAnt.setEnabled(true);
        }

        onBtnProxPressionado();
    }

    public void onBtnProxPressionado() {
        tela.avancarFilme();
        tela.repaint();
        requestFocusInWindow();
    }

    public void onBtnAntPressionado() {
        tela.voltarFilme();
        tela.repaint();
        requestFocusInWindow();
    }

    public void onSlctOpcaoAlgoritmo(OpcaoAlgoritmo alg) {
        if (alg == OpcaoAlgoritmo.BUSCA_SEQUENCIAL || alg == OpcaoAlgoritmo.BUSCA_BINARIA) {
            txfEntradaChaveBusca.setEnabled(true);
        } else {
            txfEntradaChaveBusca.setEnabled(false);
        }
    }


    /**
     * Ponto de início do programa. Simplesmente informa que é um aplicativo gráfico e passa o
     * controle para o construtor do método. Utiliza uma segunda thread para isso.
     *
     * @param args Argumentos recebidos da linha de comando.
     */
    public static void main(String[] args) {
        // Código que inicializa o aplicativo gráfico
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AnimadorAlgoritmos();
            }
        });
    }

    private enum OpcaoAlgoritmo {
        LISTA_ESTATICA("Lista estática"),
        BUSCA_SEQUENCIAL("Busca sequencial"),
        BUSCA_BINARIA("Busca binária"),
        ORDENACAO_BOLHA("Bolha"),
        ORDENACAO_SELECAO("Seleção"),
        ORDENACAO_INSERCAO("Inserção"),
        ORDENACAO_MERGESORT("Mergesort"),
        ORDENACAO_QUICKSORT("Quicksort");

        OpcaoAlgoritmo(String repr) {
            this.repr = repr;
        }

        @Override
        public String toString() {
            return this.repr;
        }

        private final String repr;
    }

    /**
     * Método auxiliar para a classe que converte para uma lista de Integers uma sequencia de
     * valores em String, separados por ",". Valores não numéricos são considerados como 0.
     *
     * @param textoValores String com valores separados por ",".
     * @return Lista de inteiros de acordo com a String de entrada.
     */
    private static List<Integer> textoParaLista(String textoValores) {
        String[] numerosTxt = textoValores.split(",");
        List<Integer> lista = new ArrayList<>(numerosTxt.length);

        for (String numTxt : numerosTxt) {
            try {
                lista.add(Integer.valueOf(numTxt));
            } catch (NumberFormatException nfe) {
                String entradaErrada = nfe.getMessage();
                int posIni = entradaErrada.indexOf("\"") + 1;
                int posFim = entradaErrada.lastIndexOf("\"");
                entradaErrada = entradaErrada.substring(posIni, posFim);

                System.err.println("Ignorando entrada '" + entradaErrada + "'. Utilizado valor 0.");
                lista.add(0);
            }
        }

        return lista;
    }


    private Tocador tela;
    private JButton btnCarregar, btnProx, btnAnt;
    private JComboBox<OpcaoAlgoritmo> boxListaAlgoritmos;
    private JTextField txfEntradaValores;
    private JTextField txfEntradaChaveBusca;
}