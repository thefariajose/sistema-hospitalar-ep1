package main.hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.utils.ArquivoUtils;

public class Paciente {
    private String nome;
    private long cpf;
    private int idade;
    private boolean possuiPlanoSaude;
    private double descontoPlano;

    private List<Consulta> historicoConsultas;
    private List<Internacao> historicoInternacoes;

    public Paciente(String nome, long cpf, int idade, boolean possuiPlanoSaude, double descontoPlano) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.possuiPlanoSaude = possuiPlanoSaude;
        this.descontoPlano = possuiPlanoSaude ? descontoPlano : 0.0;
        this.historicoConsultas = new ArrayList<>();
        this.historicoInternacoes = new ArrayList<>();
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

    public List<Consulta> getHistoricoConsultas() { 
        return historicoConsultas; 
    }
    
    public List<Internacao> getHistoricoInternacoes() { 
        return historicoInternacoes; 
    }

    public void adicionarConsulta(Consulta consulta) {
        historicoConsultas.add(consulta);
    }

    public void adicionarInternacao(Internacao internacao) {
        historicoInternacoes.add(internacao);
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

    public static void cadastrarPaciente(Scanner scanner, ArrayList<Paciente> pacientes) {
        System.out.print("Digite o nome do paciente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF do paciente: ");
        long cpf = scanner.nextLong();
        scanner.nextLine(); // consumir quebra de linha

        System.out.print("Digite a idade do paciente: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // consumir quebra de linha

        System.out.print("O paciente possui plano de saúde? (S/N): ");
        String resposta = scanner.nextLine();
    
        boolean possuiPlanoSaude = false;
        double descontoPlano = 0.0;

        if (resposta.equalsIgnoreCase("S")) {
            possuiPlanoSaude = true;
            descontoPlano = 0.2;
        }

        Paciente novoPaciente = new Paciente(nome, cpf, idade, possuiPlanoSaude, descontoPlano);
        pacientes.add(novoPaciente);
        System.out.println("Paciente cadastrado com sucesso!");

        // salvar pacientes em CSV
        List<String> linhasPacientes = new ArrayList<>();
        for (Paciente p : pacientes) {
            linhasPacientes.add(p.toCSV());
        }

        ArquivoUtils.salvarEmCSV("docs/pacientes.csv", linhasPacientes);
    }

}