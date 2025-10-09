package main.hospital;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime horario;

    public Consulta(Paciente paciente, Medico medico, LocalDateTime horario) {
        this.paciente = paciente;
        this.medico = medico;
        this.horario = horario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public String getHorarioFormatado() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return horario.format(formatter);
    }

    public static void agendarConsulta(Scanner scanner, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos) {
        
        //Se paciente está vazio ou médico está vazio
        if (pacientes.isEmpty() || medicos.isEmpty()) {
            System.out.println("É preciso ter pelo menos um paciente e um médico cadastrados para agendar.");
            return;
        }

        // Escolher paciente
        System.out.println("\nPacientes cadastrados:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println(i + " - " + pacientes.get(i).getNome());
        }
        System.out.print("Escolha o número do paciente: ");
        int indicePaciente = scanner.nextInt();
        scanner.nextLine();
        Paciente paciente = pacientes.get(indicePaciente);

        // escolher médico
        System.out.println("\nMédicos cadastrados:");
        for (int i = 0; i < medicos.size(); i++) {
            System.out.println(i + " - " + medicos.get(i).getNome() + " (" + medicos.get(i).getEspecialidade() + ")");
        }
        System.out.print("Escolha o número do médico: ");
        int indiceMedico = scanner.nextInt();
        scanner.nextLine();
        Medico medico = medicos.get(indiceMedico);

        // escolher data/hora
        System.out.print("Digite a data e hora da consulta (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime horario = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        // criar consulta
        Consulta consulta = new Consulta(paciente, medico, horario);
        medico.adicionarConsulta(consulta);

        System.out.println("Consulta agendada: " + paciente.getNome() + " com Dr(a). " + medico.getNome() + " em " + consulta.getHorarioFormatado());

        paciente.adicionarConsulta(new Consulta(paciente, medico,horario));
    }
}
