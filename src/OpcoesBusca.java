import java.util.Scanner;

public enum OpcoesBusca {

    BUSCA_LARGURA(1, "Busca em Largura") {
        @Override
        public void buscar() {
            Buscas busca = new Buscas();
            busca.efetuarBuscaEmLargura();
        }
    },
    BUSCA_PROFUNDIDADE(2, "Busca em Profundidade") {
        @Override
        public void buscar() {
            Buscas busca = new Buscas();
            busca.efetuarBuscaEmProfundidade();
        }
    },
    BUSCA_PROFUNDIDADE_LIMITADA(3, "Busca em Profundidade Limitada") {
        @Override
        public void buscar() {
            System.out.println("Informe o limite:");
            Scanner scanner = new Scanner(System.in);
            int limite = scanner.nextInt();

            Buscas busca = new Buscas();
            busca.efetuarBuscaEmProfundidadeLimitada(limite);
        }
    },
    BUSCA_INTERATIVA(4, "Busca Profundidade Iterativa") {
        @Override
        public void buscar() {
            System.out.println("Informe o inicio:");
            Scanner scanner = new Scanner(System.in);
            int inicio = scanner.nextInt();

            System.out.println("Informe o fim:");
            int fim = scanner.nextInt();

            Buscas busca = new Buscas();
            busca.efetuarBuscaIterativa(inicio, fim);
        }
    },
    BUSCA_ASTAR(5, "Busca A*") {
        @Override
        public void buscar() {
            Buscas busca = new Buscas();
            busca.efetuarBuscaAStar();
        }
    },
    BUSCA_HILL_CLIMBING(6, "Busca Hill Climbing") {
        @Override
        public void buscar() {
            Buscas busca = new Buscas();
            busca.efetuarBuscaHillClimbing();
        }
    },
    BUSCA_BEST_FIRST(7, "Busca Best First") {
        @Override
        public void buscar() {
            Buscas busca = new Buscas();
            busca.efetuarBuscaBesFirst();
        }
    };


    private int codigo;
    private String opcao;

    OpcoesBusca(int codigo, String descricao) {
        this.codigo = codigo;
        this.opcao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getOpcao() {
        return opcao;
    }

    public abstract void buscar();
}
