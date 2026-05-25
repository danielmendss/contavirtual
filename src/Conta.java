public class Conta {
    private String titular;
    private String categoriaConta;
    private double saldo;
    private String agencia;
    private String numero;

    public Conta(String titular, String categoriaConta, double saldo, String agencia, String numero) {
        this.titular = titular;
        this.categoriaConta = categoriaConta;
        this.saldo = saldo;
        this.agencia = agencia;
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCategoriaConta() {
        return categoriaConta;
    }

    public void setCategoriaConta(String categoriaConta) {
        this.categoriaConta = categoriaConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Saca um valor da conta. Retorna true se sucesso, false caso saldo insuficiente ou valor inválido.
     */
    public synchronized boolean sacar(double valor) {
        if (valor <= 0) {
            return false;
        }
        if (valor > saldo) {
            return false;
        }
        saldo -= valor;
        return true;
    }

    /**
     * Deposita um valor na conta. Retorna true se sucesso (valor > 0).
     */
    public synchronized boolean depositar(double valor) {
        if (valor <= 0) {
            return false;
        }
        saldo += valor;
        return true;
    }

    @Override
    public String toString() {
        return "Titular: " + titular + " | Conta: " + numero + " | Agência: " + agencia + " | Tipo: " + categoriaConta + " | Saldo: " + saldo;
    }
}
