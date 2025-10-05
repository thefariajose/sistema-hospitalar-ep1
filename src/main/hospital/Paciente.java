public class Paciente {
    private String nome;
    private long cpf;
    private int idade;
    private boolean possuiPlanoSaude;
    private double descontoPlano;

    public Paciente(String nome, long cpf, int idade, boolean possuiPlanoSaude, double descontoPlano) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.possuiPlanoSaude = possuiPlanoSaude;
        this.descontoPlano = possuiPlanoSaude ? descontoPlano : 0.0;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean isPossuiPlanoSaude() { 
        return possuiPlanoSaude; 
    }

    public void setPossuiPlanoSaude(boolean possuiPlanoSaude) { 
        this.possuiPlanoSaude = possuiPlanoSaude; 
        if (!possuiPlanoSaude) this.descontoPlano = 0.0;
    }

    public double getDescontoPlano() { 
        return descontoPlano; 
    }
    
    public void setDescontoPlano(double descontoPlano) { 
        if (possuiPlanoSaude) {
            this.descontoPlano = descontoPlano; 
        }
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nome='" + nome + '\'' +
                ", cpf=" + cpf +
                ", idade=" + idade +
                ", possuiPlanoSaude=" + possuiPlanoSaude +
                (possuiPlanoSaude ? ", desconto=" + (descontoPlano * 100) + "%" : "") +
                '}';
    }

    public String toCSV() {
        return nome + ";" + cpf + ";" + idade + ";" + possuiPlanoSaude + ";" + descontoPlano;
    }

    public static Paciente fromCSV(String linha) {
        String[] dados = linha.split(";");
        String nome = dados[0];
        long cpf = Long.parseLong(dados[1]);
        int idade = Integer.parseInt(dados[2]);
        boolean possuiPlano = Boolean.parseBoolean(dados[3]);
        double desconto = Double.parseDouble(dados[4]);
        return new Paciente(nome, cpf, idade, possuiPlano, desconto);
    }

}
