import java.util.Arrays;
import java.util.Scanner;

public final class MenuBuscas {

    public static void create() {
        montarMenuBuscar();
        Scanner scanner = new Scanner(System.in);

        int opcaoEscolhida = scanner.nextInt();

        OpcoesBusca.values()[--opcaoEscolhida].buscar();

        create();
    }

    private static void montarMenuBuscar() {

        StringBuilder sb = new StringBuilder();

        sb.append("\t------- MENU BUSCAS -------\n");
        sb.append("\tESCOLHA UMA DAS OPÇÕES ABAIXO\n");

        Arrays.stream(OpcoesBusca.values()).forEach(opcoesBusca -> {
            sb.append("\t" + opcoesBusca.getCodigo() + " - " + opcoesBusca.getOpcao() + "\n");
        });

        sb.append("\tOpção escolhida: \n");

        System.out.println(sb.toString());
    }
}
