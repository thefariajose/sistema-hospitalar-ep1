import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
