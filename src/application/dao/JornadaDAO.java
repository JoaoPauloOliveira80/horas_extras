package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import application.model.HoraExtras;

public class JornadaDAO {

    public static List<HoraExtras> getAllHoraExtras() {
        List<HoraExtras> horaExtrasList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Cria a conexão
            connection = ConnectionDB.create();
            
            

            // Define a consulta SQL
            String sql = "SELECT startJornada, endJornada, startAlmoco, endAlmoco FROM JornadasTrabalho";

            // Prepara a declaração SQL
            statement = connection.prepareStatement(sql);

            // Executa a consulta
            resultSet = statement.executeQuery();

            // Processa os resultados
            while (resultSet.next()) {
                // Obtém os campos da tabela
                Timestamp startJornadaTimestamp = resultSet.getTimestamp("startJornada");
                Timestamp endJornadaTimestamp = resultSet.getTimestamp("endJornada");
                Timestamp startAlmocoTimestamp = resultSet.getTimestamp("startAlmoco");
                Timestamp endAlmocoTimestamp = resultSet.getTimestamp("endAlmoco");

                // Converte os Timestamps para LocalDateTime
                LocalDateTime startJornada = startJornadaTimestamp.toLocalDateTime();
                LocalDateTime endJornada = endJornadaTimestamp.toLocalDateTime();
                LocalDateTime startRefeicao = startAlmocoTimestamp.toLocalDateTime();
                LocalDateTime endRefeicao = endAlmocoTimestamp.toLocalDateTime();

                // Cria um objeto HoraExtras e adiciona à lista
                HoraExtras horaExtras = new HoraExtras(startJornada, endJornada, startRefeicao, endRefeicao);
                horaExtrasList.add(horaExtras);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha os recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return horaExtrasList;
    }

    // Adicione outros métodos conforme necessário
}
