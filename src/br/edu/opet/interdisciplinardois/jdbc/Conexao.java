package br.edu.opet.interdisciplinardois.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import br.edu.opet.interdisciplinardois.util.ExceptionUtil;

public class Conexao
{
    // Atributos est�ticos
    private static Connection sConexao;

    // M�todos est�ticos
    public static void main(String[] args)
    {
        System.out.println();
        System.out.println("Obtendo a conex�o");
        getConexao();

        System.out.println();
        System.out.println("Fechando a conex�o");
        desconectar();
    }

    // M�todos de classe
    public static Connection getConexao()
    {
        try
        {
            // Caso a conex�o j� exista, verifica se est� aberta e retorna essa conex�o
            if (sConexao != null)
            {
                if (!sConexao.isClosed())
                    return sConexao;
            }

            // Carregando o arquivo de configura��o do JDBC para as propriedades
            InputStream tArqEntrada = Conexao.class.getResourceAsStream("jdbc.properties");
            ;
            if (tArqEntrada == null)
                throw new IOException("Arquivo de configura��o 'jdbc.properties' n�o existe no diret�rio do pacote");
            Properties tPropriedades = new Properties();
            tPropriedades.load(tArqEntrada);
            tArqEntrada.close();

            // Recuperando as propriedades do arquivo e validando se est�o ok
            String tDriver = tPropriedades.getProperty("driver");
            if (tDriver == null || tDriver.isEmpty())
                throw new InvalidPropertiesFormatException("Propriedade 'driver' n�o existe ou em branco no arquivo 'jdbc.properties'");
            String tURL = tPropriedades.getProperty("url");
            if (tURL == null || tURL.isEmpty())
                throw new InvalidPropertiesFormatException("Propriedade 'url' n�o existe ou em branco no arquivo 'jdbc.properties'");
            String tNomeLog = tPropriedades.getProperty("log");
            String tUsuario = tPropriedades.getProperty("usuario");
            String tSenha = tPropriedades.getProperty("senha");

            // Assinalando o arquivo de log, caso ele esteja configurado
            if (tNomeLog != null && !tNomeLog.isEmpty())
            {
                PrintWriter tLog = new PrintWriter(new FileWriter(tNomeLog, true));
                DriverManager.setLogWriter(tLog);
                DriverManager.println("|-------> Iniciando log.....");
            }

            // Carregando o driver e fazendo a conex�o com o banco de dados
            DriverManager.println("|-------> Registrando o driver.....");
            Class.forName(tDriver);
            DriverManager.println("|-------> Realizando a conex�o.....");
            sConexao = DriverManager.getConnection(tURL, tUsuario, tSenha);
            DriverManager.println("|-------> Conex�o estabelecida.....");
        }
        catch (ClassNotFoundException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Classe do Driver n�o encontrada");
            System.exit(9);
        }
        catch (IOException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na cria��o do arquivo de log");
            System.exit(9);
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na conex�o com o banco de dados");
            System.exit(9);
        }
        return sConexao;
    }

    public static void desconectar()
    {
        try
        {
            // Desconectando do banco de dados
            DriverManager.println("|-------> Fechando a conex�o.....");
            sConexao.close();

            // Verificando se a desconex�o foi bem realizada
            DriverManager.println("|-------> Verificando a conex�o ap�s fechamento");
            if (sConexao.isClosed())
            {
                DriverManager.println("|-------> Conex�o fechada");
                sConexao = null;
            }
            else
            {
                DriverManager.println("|-------> Conex�o aberta");
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas no fechamento da conex�o com o banco de dados");
            System.exit(9);
        }
    }

    public static void iniciarTransacao()
    {
        try
        {
            sConexao.setAutoCommit(false);
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas no in�cio da transa��o com o banco de dados");
            System.exit(9);
        }
    }

    public static void confirmarTransacao()
    {
        try
        {
            sConexao.commit();
            sConexao.setAutoCommit(true);
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na confirma��o da transa��o no banco de dados");
            System.exit(9);
        }
    }

    public static void desfazerTransacao()
    {
        try
        {
            sConexao.rollback();
            sConexao.setAutoCommit(true);
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas para desfazer a transa��o no banco de dados");
            System.exit(9);
        }
    }
}
