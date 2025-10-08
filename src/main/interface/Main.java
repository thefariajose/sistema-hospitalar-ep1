import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import main.utils.ArquivoUtils;

public class Main {
    public static void main(String[] args) {
        // Arraylist de pacientes
        ArrayList<Paciente> pacientes = new ArrayList<>();
        // Arraylist de médicos
        ArrayList<Medico> medicos = new ArrayList<>();
        // Arraylist de internações
        ArrayList<Internacao> internacoes = new ArrayList<>();

        // Carregar pacientes e médicos salvos
        for (String linha : ArquivoUtils.carregarDeCSV("docs/pacientes.csv")) {
            pacientes.add(Paciente.fromCSV(linha));
        }
        for (String linha : ArquivoUtils.carregarDeCSV("docs/medicos.csv")) {
            medicos.add(Medico.fromCSV(linha));
        }

        Scanner scanner = new Scanner(System.in);

        int opcao;
        //do para fazer o código rodar até o usuário sair do sistema
        do {
            System.out.println("\nBem-vindo ao sistema do Hospital do Gama!");
            System.out.println("*****************************************");
            System.out.println("1. Cadastro de paciente");
            System.out.println("2. Cadastro de médico");
            System.out.println("3. Agendar uma consulta");
            System.out.println("4. Internação de paciente");
            System.out.println("5. Exibir relatórios");
            System.out.println("0. Sair");
            System.out.println("*****************************************");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> Paciente.cadastrarPaciente(scanner, pacientes);
                case 2 -> Medico.cadastrarMedico(scanner, medicos);
                case 3 -> Consulta.agendarConsulta(scanner, pacientes, medicos);
                case 4 -> internarOuDarAlta(scanner, pacientes, medicos, internacoes);
                case 5 -> exibirRelatorios(pacientes, medicos, internacoes);
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida, tente novamente!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    //Primeira função: Cadastro de pacientes


    //Segunda função: Cadastro de médicos
    
    //Terceira função: Exibição de relatórios
    private static void exibirRelatorios(ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Internacao> internacoes) {
        System.out.println("\nRelatório de Pacientes:");
        for (Paciente p : pacientes) {
            System.out.println("- " + p.getNome() + " | CPF: " + p.getCpf() + " | Idade: " + p.getIdade());
        }

        System.out.println("\nRelatório de Médicos:");
        for (Medico m : medicos) {
            System.out.println("- " + m.getNome() + " | CRM: " + m.getCrm() + " | Especialidade: " + m.getEspecialidade());
        }

        System.out.println("\n📋 Relatório de Internações:");
        for (Internacao i : internacoes) {
            System.out.println("- " + i.getResumo());
        }

    }

    //Quarta função: Agendamento de consultas
    

    private static void internarOuDarAlta(Scanner scanner, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Internacao> internacoes) {
        System.out.println("\n=== Menu de Internação ===");
        System.out.println("1. Internar paciente");
        System.out.println("2. Dar alta a paciente");
        System.out.print("Escolha uma opção: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1 -> internarPaciente(scanner, pacientes, medicos, internacoes);
            case 2 -> darAltaPaciente(scanner, internacoes);
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void internarPaciente(Scanner scanner, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Internacao> internacoes) {
        if (pacientes.isEmpty() || medicos.isEmpty()) {
            System.out.println("É preciso ter pelo menos um paciente e um médico cadastrados para internação.");
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

        // Escolher médico
        System.out.println("\nMédicos cadastrados:");
        for (int i = 0; i < medicos.size(); i++) {
            System.out.println(i + " - " + medicos.get(i).getNome() + " (" + medicos.get(i).getEspecialidade() + ")");
        }
        System.out.print("Escolha o número do médico responsável: ");
        int indiceMedico = scanner.nextInt();
        scanner.nextLine();
        Medico medico = medicos.get(indiceMedico);

        // Definir quarto
        System.out.print("Digite o número do quarto: ");
        int quarto = scanner.nextInt();
        scanner.nextLine();

        // Data de entrada
        LocalDate dataEntrada = LocalDate.now();

        Internacao internacao = new Internacao(paciente, medico, quarto, dataEntrada);
        internacoes.add(internacao);

        System.out.println("Internação registrada para o paciente " + paciente.getNome() + " no quarto " + quarto);
}

private static void darAltaPaciente(Scanner scanner, ArrayList<Internacao> internacoes) {
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
        System.out.print("Escolha o número do paciente para dar alta: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        Internacao internacao = abertas.get(indice);
        internacao.setDataSaida(LocalDate.now());

        System.out.println("Alta registrada para " + internacao.getPaciente().getNome() +
                       " (Quarto " + internacao.getNumeroQuarto() + ")");
    }



}
