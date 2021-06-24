import espacoDeEstados.Estado;
import espacoDeEstados.Puzzle8;
import estrategiasDeBusca.cega.BuscaCega;
import estrategiasDeBusca.cega.BuscaEmLargura;
import estrategiasDeBusca.cega.BuscaEmProfundidade;
import estrategiasDeBusca.cega.BuscaEmProfundidadeLimitada;
import estrategiasDeBusca.heuristica.AStar;
import estrategiasDeBusca.heuristica.BestFirst;
import estrategiasDeBusca.heuristica.BuscaInformada;
import estrategiasDeBusca.heuristica.HillClimbing;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static espacoDeEstados.Puzzle8.TABULEIRO_ORGANIZADO;

public class Buscas {

    private static final String MENSAGEM_ERRO_MEMORIA = "Este algoritmo apresentou estouro de memória ao tentar resolver o puzzle";

    private Puzzle8 obterConfiguracaoInicial() {
        char[] cfgIni = {'3', '1', '2', '4', '7', '5', '6', ' ', '8'};


        Puzzle8 puzzleInicial = new Puzzle8();
        puzzleInicial.setEstado(cfgIni);
        puzzleInicial.setCusto(0);
        puzzleInicial.setAvaliacao(puzzleInicial.heuristica(TABULEIRO_ORGANIZADO));

        return puzzleInicial;
    }

    private Puzzle8 obterConfiguracaoFinal() {
        char[] cfgFim = {' ', '1', '2', '3', '4', '5', '6', '7', '8'};

        Puzzle8 puzzleFinal = new Puzzle8();
        puzzleFinal.setEstado(cfgFim);
        puzzleFinal.setCusto(0);
        puzzleFinal.setAvaliacao(0);

        return puzzleFinal;
    }

    public void efetuarBuscaEmLargura() {
        Instant start = Instant.now();

        BuscaCega buscaLargura = new BuscaEmLargura();
        buscaLargura.setInicio(obterConfiguracaoInicial());
        buscaLargura.setObjetivo(obterConfiguracaoFinal());

        System.out.println("Resolvendo Puzzle pela Busca  em Largura");
        try {
            buscaLargura.buscar();
        } catch (OutOfMemoryError e) {
            System.out.println(MENSAGEM_ERRO_MEMORIA);
        }
        buscaLargura.getCaminhoSolucao().forEach(System.out::println);

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        System.out.println("Tempo de execução: " + timeElapsed.toMillis() + "ms");

        System.out.println("Fim Busca  em Largura");
    }

    public void efetuarBuscaEmProfundidade() {
        Instant start = Instant.now();

        BuscaCega buscaProfundidade = new BuscaEmProfundidade();
        buscaProfundidade.setInicio(obterConfiguracaoInicial());
        buscaProfundidade.setObjetivo(obterConfiguracaoFinal());

        System.out.println("Resolvendo puzzle pela Busca  em Profundidade");
        try {
            buscaProfundidade.buscar();
        } catch (OutOfMemoryError e) {
            System.out.println(MENSAGEM_ERRO_MEMORIA);
        }

        if (buscaProfundidade.getCaminhoSolucao().isEmpty()) {
            System.out.println("Nâo foi achada uma solução");
        }

        buscaProfundidade.getCaminhoSolucao().forEach(System.out::println);

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        System.out.println("Tempo de execução: " + timeElapsed.toMillis() + "ms");

        System.out.println("Fim Busca em Profundidade");

    }

    public void efetuarBuscaEmProfundidadeLimitada(int limite) {
        Instant start = Instant.now();

        BuscaEmProfundidadeLimitada buscaProfundidadeLimitada = new BuscaEmProfundidadeLimitada();
        buscaProfundidadeLimitada.setInicio(obterConfiguracaoInicial());
        buscaProfundidadeLimitada.setObjetivo(obterConfiguracaoFinal());
        buscaProfundidadeLimitada.setLimite(limite);

        System.out.println("Resolvendo puzzle pela Busca Profundidade Limitada");
        buscaProfundidadeLimitada.buscar();

        if (buscaProfundidadeLimitada.getCaminhoSolucao().isEmpty()) {
            System.out.println("Nâo foi achada uma solução");
        }

        buscaProfundidadeLimitada.getCaminhoSolucao().forEach(System.out::println);

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        System.out.println("Tempo de execução: " + timeElapsed.toMillis() + "ms");
        System.out.println("Fim Busca Profundidade Limitada");

    }

    public void efetuarBuscaIterativa(int depth_ini, int depth_end) {
        Instant start = Instant.now();

        BuscaEmProfundidadeLimitada buscaProfundidadeLimitada = new BuscaEmProfundidadeLimitada();
        buscaProfundidadeLimitada.setInicio(obterConfiguracaoInicial());
        buscaProfundidadeLimitada.setObjetivo(obterConfiguracaoFinal());

        List<Estado<?>> path = null;

        System.out.println("Resolvendo puzzle pela Busca Iterativa");
        for (int i = depth_ini; i < depth_end; i++) {
            buscaProfundidadeLimitada.setLimite(i);
            buscaProfundidadeLimitada.buscar();
            System.out.println("Nível: " + i);
            path = buscaProfundidadeLimitada.getCaminhoSolucao();

            if (path.isEmpty()) {
                System.out.println("Não foi achada uma solução!");
            } else {
                path.forEach(System.out::println);

                path.clear();
            }
        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        System.out.println("Tempo de execução: " + timeElapsed.toMillis() + "ms");
        System.out.println("Fim Busca Iterativa");
    }

    public void efetuarBuscaAStar() {
        Instant start = Instant.now();

        System.out.println("Resolvendo puzzle pela Busca A*");

        BuscaInformada buscaAStar = new AStar();
        buscaAStar.setInicio(obterConfiguracaoInicial());
        buscaAStar.setObjetivo(obterConfiguracaoFinal());
        buscaAStar.buscar();
        buscaAStar.getCaminhoSolucao().forEach(System.out::println);

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        System.out.println("Tempo de execução: " + timeElapsed.toMillis() + "ms");

        System.out.println("Fim Busca A*");
    }

    public void efetuarBuscaHillClimbing() {
        Instant start = Instant.now();

        System.out.println("Resolvendo puzzle pela Busca Hill Climbing");

        BuscaInformada buscaHC = new HillClimbing();
        buscaHC.setInicio(obterConfiguracaoInicial());
        buscaHC.setObjetivo(obterConfiguracaoFinal());
        buscaHC.buscar();
        buscaHC.getCaminhoSolucao().forEach(System.out::println);

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        System.out.println("Tempo de execução: " + timeElapsed.toMillis() + "ms");

        System.out.println("Fim Busca Hill Climbing");

    }

    public void efetuarBuscaBesFirst() {
        Instant start = Instant.now();

        System.out.println("Resolvendo puzzle pela Busca Best First");

        BuscaInformada buscaBestFirst = new BestFirst();
        buscaBestFirst.setInicio(obterConfiguracaoInicial());
        buscaBestFirst.setObjetivo(obterConfiguracaoFinal());
        buscaBestFirst.buscar();

        buscaBestFirst.getCaminhoSolucao().forEach(System.out::println);

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        System.out.println("Tempo de execução: " + timeElapsed.toMillis() + "ms");

        System.out.println("Fim Busca Best First");
    }
}
