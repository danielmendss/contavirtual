import java.util.Scanner;

public class contaVirtual {
    public static void main(String [] args) {

        String titular = "João Carlos Ferreira";
        String categoriaConta = "Premium";
        double saldoAtual = 5289.75;
        int escolha = 0;

        System.out.println("#########################");
        System.out.println("\nCliente: " + titular);
        System.out.println("Tipo da Conta: " + categoriaConta);
        System.out.println("Saldo disponível: " + saldoAtual);
        System.out.println("\n#########################");

        String menu = """
                ** Selecione uma ação **
                1 - Verificar saldo
                2 - Enviar dinheiro
                3 - Adicionar fundos
                4 - Finalizar operação
                
                """;
        Scanner entrada = new Scanner(System.in);

        if (escolha == 1){
            System.out.println("Seu saldo atual é " + saldoAtual);
        } else if (escolha == 2) {
            System.out.println("Informe o valor a ser enviado:");
            double valor = entrada.nextDouble();
            if (valor > saldoAtual) {
                System.out.println("Saldo insuficiente para a transação.");
            } else {
                saldoAtual -= valor;
                System.out.println("Transação realizada. Saldo atualizado: " + saldoAtual);
            }
        } else if (escolha == 3) {
            System.out.println("Digite o valor a ser adicionado:");
            double valor = entrada.nextDouble();
            saldoAtual += valor;
            System.out.println("Depósito efetuado. Novo saldo: " + saldoAtual);
        } else if (escolha != 4) {
            System.out.println("Opção inválida! Por favor, tente novamente.");
        }
    }
}
