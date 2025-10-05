import java.util.ArrayList;
import java.util.List;

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

}
