import java.util.Scanner;

public class ContaVirtual {
    public static void main(String[] args) {
        // Criar banco e contas de exemplo
        Banco banco = new Banco("Banco Exemplo");
        Conta contaOrigem = new Conta("João Carlos Ferreira", "Premium", 5289.75, "0001", "100200");
        Conta contaDestino = new Conta("Maria Silva", "Standard", 1500.00, "0001", "200300");
        banco.adicionarConta(contaOrigem);
        banco.adicionarConta(contaDestino);

        Scanner entrada = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n#########################");
            System.out.println("Cliente: " + contaOrigem.getTitular());
            System.out.println("Tipo da Conta: " + contaOrigem.getCategoriaConta());
            System.out.printf("Saldo disponível: %.2f\n", contaOrigem.getSaldo());
            System.out.println("#########################\n");

            System.out.println("Selecione uma ação:");
            System.out.println(" 1 - Verificar saldo");
            System.out.println(" 2 - Enviar dinheiro");
            System.out.println(" 3 - Adicionar fundos");
            System.out.println(" 4 - Exibir detalhes da conta");
            System.out.println(" 5 - Finalizar operação");
            System.out.print("Escolha: ");

            int escolha = -1;
            if (entrada.hasNextInt()) {
                escolha = entrada.nextInt();
                entrada.nextLine(); 
            } else {
                System.out.println("Entrada inválida. Informe um número entre 1 e 5.");
                entrada.nextLine();
                continue;
            }

            switch (escolha) {
                case 1:
                    System.out.printf("Seu saldo atual é: %.2f\n", contaOrigem.getSaldo());
                    break;
                case 2:
                    System.out.print("Informe o número da conta destino: ");
                    String numeroDestino = entrada.nextLine().trim();
                    Conta destino = banco.buscarConta(numeroDestino);
                    if (destino == null) {
                        System.out.println("Conta destino não encontrada. Confira o número e tente novamente.");
                        break;
                    }
                    System.out.print("Informe o valor a ser enviado: ");
                    double valorEnvio = 0;
                    try {
                        String linha = entrada.nextLine().trim();
                        valorEnvio = Double.parseDouble(linha);
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido. Operação cancelada.");
                        break;
                    }
                    if (valorEnvio <= 0) {
                        System.out.println("Informe um valor positivo.");
                        break;
                    }
                    boolean enviado = banco.transferir(contaOrigem.getNumero(), numeroDestino, valorEnvio);
                    if (enviado) {
                        System.out.printf("Transação realizada. Saldo atualizado: %.2f\n", contaOrigem.getSaldo());
                    } else {
                        System.out.println("Falha na transferência. Verifique saldo e dados e tente novamente.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o valor a ser adicionado: ");
                    double valor = 0;
                    try {
                        String linha = entrada.nextLine().trim();
                        valor = Double.parseDouble(linha);
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido. Depósito cancelado.");
                        break;
                    }
                    if (valor <= 0) {
                        
                        System.out.println("Informe um valor positivo para depósito.");
                        break;
                    }
                    boolean depositou = contaOrigem.depositar(valor);
                    if (depositou) {
                        
                        System.out.printf("Depósito efetuado. Novo saldo: %.2f\n", contaOrigem.getSaldo());
                    } else {
                        System.out.println("Falha ao depositar. Verifique o valor informado.");
                    }
                    
                    break;
                case 4:
                    System.out.println(contaOrigem.toString());
                    
                    break;
                case 5:
                    System.out.println("Encerrando sessão atual. Agradecemos a preferência");
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, tente novamente.");
            }
        }

        entrada.close();
    }
}
