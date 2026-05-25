import java.util.HashMap;
import java.util.Map;

public class Banco {
    private String nome;
    private Map<String, Conta> contas = new HashMap<String, Conta>();

    public Banco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarConta(Conta conta) {
        if (conta != null && conta.getNumero() != null) {
            contas.put(conta.getNumero(), conta);
        }
    }

    public Conta buscarConta(String numero) {
        if (numero == null) {
            return null;
        }
        return contas.get(numero);
    }

    /**
     * Realiza transferência entre duas contas identificadas pelos números.
     * Retorna true se a transferência ocorreu com sucesso.
     */
    public boolean transferir(String deNumero, String paraNumero, double valor) {
        if (deNumero == null || paraNumero == null || deNumero.equals(paraNumero)) {
            return false;
        }
        Conta origem = buscarConta(deNumero);
        Conta destino = buscarConta(paraNumero);
        if (origem == null || destino == null) {
            return false;
        }
        synchronized (this) {
            boolean sacou = origem.sacar(valor);
            if (!sacou) {
                return false;
            }
            boolean depositou = destino.depositar(valor);
            if (!depositou) {
                origem.depositar(valor);
                return false;
            }
            return true;
        }
    }
}
