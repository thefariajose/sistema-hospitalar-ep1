import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        return paciente.getNome() + " | Médico: " + medicoResponsavel.getNome() + 
               " | Quarto: " + numeroQuarto + 
               " | Entrada: " + entrada + 
               " | Saída: " + saida;
    }
}
