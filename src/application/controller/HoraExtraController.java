package application.controller;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;
import application.dao.JornadaDAO;
import application.model.HoraExtras;

public class HoraExtraController {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Duration JORNADA_PADRAO = Duration.ofHours(9).plusMinutes(48);

    public void showHoraExtrasData() {
        List<HoraExtras> horaExtrasList = JornadaDAO.getAllHoraExtras();
        Duration totalHorasExtrasMes = Duration.ZERO;

        for (HoraExtras horaExtras : horaExtrasList) {
            System.out.println("Start Jornada: " + horaExtras.getStartJornada().format(formatter));
            System.out.println("End Jornada: " + horaExtras.getEndJornada().format(formatter));
            System.out.println("Start Almoco: " + horaExtras.getStartRefeicao().format(formatter));
            System.out.println("End Almoco: " + horaExtras.getEndRefeicao().format(formatter));

            // Calcular as horas extras para o dia
            Duration horasExtrasDia = calcularHorasExtras(horaExtras);

            // Adicionar as horas extras do dia ao total do mês
            totalHorasExtrasMes = totalHorasExtrasMes.plus(horasExtrasDia);

            // Exibir as horas e minutos das horas extras do dia
            long horasExtrasDiaHoras = horasExtrasDia.toHours();
            long horasExtrasDiaMinutos = horasExtrasDia.toMinutesPart();

            System.out.println(
                    "Horas Extras do Dia: " + horasExtrasDiaHoras + " horas e " + horasExtrasDiaMinutos + " minutos");
            System.out.println("-------------------------------------------------------");
        }

        // Exibir a soma total de horas extras para o mês
        long totalHorasExtrasMensalHoras = totalHorasExtrasMes.toHours();
        long totalHorasExtrasMensalMinutos = totalHorasExtrasMes.toMinutesPart();

        System.out.println("Soma Total de Horas Extras no Mês: " + totalHorasExtrasMensalHoras + " horas e "
                + totalHorasExtrasMensalMinutos + " minutos");
    }

    private Duration calcularHorasExtras(HoraExtras horaExtras) {
        // Calcular a diferença entre o tempo de trabalho e a jornada padrão
        Duration horasTrabalhadas = Duration.between(horaExtras.getStartJornada(), horaExtras.getEndJornada());

        // Calcular as horas extras considerando o tempo de almoço
        Duration horasExtras = horasTrabalhadas.minus(JORNADA_PADRAO);

        // Se as horas extras forem negativas (trabalhou menos que a jornada padrão),
        // ajustar para zero
        horasExtras = horasExtras.isNegative() ? Duration.ZERO : horasExtras;

        return horasExtras;
    }
}
