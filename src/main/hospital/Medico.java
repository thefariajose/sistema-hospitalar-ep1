import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.utils.ArquivoUtils;

public class Medico {
    private String nome;
    private String especialidade;
    private int crm;
    private double custoConsulta;
    private List<Consulta> agenda;

    public Medico(String nome, String especialidade, int crm, double custoConsulta) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
        this.custoConsulta = custoConsulta;
        this.agenda = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public double getCustoConsulta() {
        return custoConsulta;
    }

    public void setCustoConsulta(double custoConsulta) {
        this.custoConsulta = custoConsulta;
    }

    public List<Consulta> getAgenda() {
        return agenda;
    }

    public void adicionarConsulta(Consulta consulta) {
        agenda.add(consulta);
    }

    @Override
    public String toString() {
        return "Médico{" +
                "nome='" + nome + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", CRM=" + crm +
                ", custoConsulta=" + custoConsulta +
                ", consultasAgendadas=" + agenda.size() +
                '}';
    }

    public String toCSV() {
    return nome + ";" + especialidade + ";" + crm + ";" + custoConsulta;
}

    public static Medico fromCSV(String linha) {
        String[] dados = linha.split(";");
        String nome = dados[0];
        String especialidade = dados[1];
        int crm = Integer.parseInt(dados[2]);
        double custo = Double.parseDouble(dados[3]);
        return new Medico(nome, especialidade, crm, custo);
    }

    public static void cadastrarMedico(Scanner scanner, ArrayList<Medico> medicos) {
        System.out.print("Digite o nome do médico: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a especialidade do médico: ");
        String especialidade = scanner.nextLine();

        System.out.print("Digite o CRM do médico: ");
        int crm = scanner.nextInt();

        System.out.print("Digite o custo da consulta: ");
        double custoConsulta = scanner.nextDouble();
        scanner.nextLine();

        medicos.add(new Medico(nome, especialidade, crm, custoConsulta));
        System.out.println("Médico cadastrado com sucesso!");

        // Salvar médicos
        List<String> linhasMedicos = new ArrayList<>();
        for (Medico m : medicos) {
            linhasMedicos.add(m.toCSV());
        }
        ArquivoUtils.salvarEmCSV("docs/medicos.csv", linhasMedicos);
    }


}
