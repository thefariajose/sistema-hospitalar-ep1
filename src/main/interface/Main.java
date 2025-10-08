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
                case 4 -> Internacao.internarOuDarAlta(scanner, pacientes, medicos, internacoes);
                case 5 -> exibirRelatorios(pacientes, medicos, internacoes);
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida, tente novamente!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirRelatorios(ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Internacao> internacoes) {
        System.out.println("\nRelatório de Pacientes:");
        for (Paciente p : pacientes) {
            System.out.println("- " + p.getNome() + " | CPF: " + p.getCpf() + " | Idade: " + p.getIdade());
        }

        System.out.println("\nRelatório de Médicos:");
        for (Medico m : medicos) {
            System.out.println("- " + m.getNome() + " | CRM: " + m.getCrm() + " | Especialidade: " + m.getEspecialidade());
        }

        System.out.println("\nRelatório de Internações:");
        for (Internacao i : internacoes) {
            System.out.println("- " + i.getResumo());
        }

    }

}
