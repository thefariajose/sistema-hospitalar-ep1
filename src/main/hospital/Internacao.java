package main.hospital;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Internacao {
    private Paciente paciente;
    private Medico medicoResponsavel;
    private int numeroQuarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public Internacao(Paciente paciente, Medico medicoResponsavel, int numeroQuarto, LocalDate dataEntrada) {
        this.paciente = paciente;
        this.medicoResponsavel = medicoResponsavel;
        this.numeroQuarto = numeroQuarto;
        this.dataEntrada = dataEntrada;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getResumo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String entrada = dataEntrada.format(formatter);
        String saida = (dataSaida != null) ? dataSaida.format(formatter) : "Ainda internado";
        return paciente.getNome() + " | Medico: " + medicoResponsavel.getNome() + 
               " | Quarto: " + numeroQuarto + 
               " | Entrada: " + entrada + 
               " | Saída: " + saida;
    }

    public static void internarOuDarAlta(Scanner scanner, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Internacao> internacoes) {
        System.out.println("\n=== Menu de Internacao ===");
        System.out.println("1. Internar paciente");
        System.out.println("2. Dar alta a paciente");
        System.out.print("Escolha uma opcao: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1 -> internarPaciente(scanner, pacientes, medicos, internacoes);
            case 2 -> darAltaPaciente(scanner, internacoes);
            default -> System.out.println("Opcao invalida!");
        }
    }

    public static void internarPaciente(Scanner scanner, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Internacao> internacoes) {
        if (pacientes.isEmpty() || medicos.isEmpty()) {
            System.out.println("E preciso ter pelo menos um paciente e um medico cadastrados para internacao.");
            return;
        }

        // Escolher paciente
        System.out.println("\nPacientes cadastrados:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println(i + " - " + pacientes.get(i).getNome());
        }
        System.out.print("Escolha o numero do paciente: ");
        int indicePaciente = scanner.nextInt();
        scanner.nextLine();
        Paciente paciente = pacientes.get(indicePaciente);

        // Escolher médico
        System.out.println("\nMedicos cadastrados:");
        for (int i = 0; i < medicos.size(); i++) {
            System.out.println(i + " - " + medicos.get(i).getNome() + " (" + medicos.get(i).getEspecialidade() + ")");
        }
        System.out.print("Escolha o numero do medico responsavel: ");
        int indiceMedico = scanner.nextInt();
        scanner.nextLine();
        Medico medico = medicos.get(indiceMedico);

        // Definir quarto
        System.out.print("Digite o numero do quarto: ");
        int quarto = scanner.nextInt();
        scanner.nextLine();

        // Data de entrada
        LocalDate dataEntrada = LocalDate.now();

        Internacao internacao = new Internacao(paciente, medico, quarto, dataEntrada);
        internacoes.add(internacao);

        System.out.println("Internacao registrada para o paciente " + paciente.getNome() + " no quarto " + quarto);

        paciente.adicionarInternacao(new Internacao(paciente, medico, quarto, dataEntrada));
    }

    public static void darAltaPaciente(Scanner scanner, ArrayList<Internacao> internacoes) {
        ArrayList<Internacao> abertas = new ArrayList<>();
        for (Internacao i : internacoes) {
            if (i.getDataSaida() == null) {
                abertas.add(i);
            }
        }

        if (abertas.isEmpty()) {
            System.out.println("Nenhum paciente internado no momento.");
            return;
        }

        System.out.println("\nPacientes internados:");
        for (int i = 0; i < abertas.size(); i++) {
            System.out.println(i + " - " + abertas.get(i).getPaciente().getNome() + 
                           " (Quarto " + abertas.get(i).getNumeroQuarto() + ")");
    }
        System.out.print("Escolha o numero do paciente para dar alta: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        Internacao internacao = abertas.get(indice);
        internacao.setDataSaida(LocalDate.now());

        System.out.println("Alta registrada para " + internacao.getPaciente().getNome() +
                       " (Quarto " + internacao.getNumeroQuarto() + ")");
    }
}
