package application.model;
import java.time.LocalDateTime;

public class HoraExtras {
    private LocalDateTime startJornada;
    private LocalDateTime endJornada;
    private LocalDateTime startRefeicao;
    private LocalDateTime endRefeicao;



    public HoraExtras(LocalDateTime startJornada, LocalDateTime endJornada, LocalDateTime startRefeicao,
                      LocalDateTime endRefeicao) {
        this.startJornada = startJornada;
        this.endJornada = endJornada;
        this.startRefeicao = startRefeicao;
        this.endRefeicao = endRefeicao;
    }

   
    
    public LocalDateTime getStartJornada() {
        return startJornada;
    }

    public void setStartJornada(LocalDateTime startJornada) {
        this.startJornada = startJornada;
    }

    public LocalDateTime getEndJornada() {
        return endJornada;
    }

    public void setEndJornada(LocalDateTime endJornada) {
        this.endJornada = endJornada;
    }

    public LocalDateTime getStartRefeicao() {
        return startRefeicao;
    }

    public void setStartRefeicao(LocalDateTime startRefeicao) {
        this.startRefeicao = startRefeicao;
    }

    public LocalDateTime getEndRefeicao() {
        return endRefeicao;
    }

    public void setEndRefeicao(LocalDateTime endRefeicao) {
        this.endRefeicao = endRefeicao;
    }
}

