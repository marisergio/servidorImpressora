package servidor1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Mensagem;
import util.MensagemImprimir;
import util.enumConfi.Operacao;
import util.enumConfi.StatusMensagem;

public class Servidor1 implements Serializable {

    private ServerSocket servidor;

    private void criarServerSocket(int porta) throws IOException {
        this.servidor = new ServerSocket(porta);
    }

    public static void main(String[] args) throws ClassNotFoundException {

        Servidor1 server = new Servidor1();

        try {
            //criando o servidor
            server.criarServerSocket(5555);

            //aguardando a conexao
            System.out.println("SERVIDOR:: AGUARDANDO REQUISIÇÃO");

            Socket soquete = server.servidor.accept();
            System.out.println("SERVIDOR:: CONEXÃO ACEITA");

            //criando os streams
            ObjectOutputStream saida = new ObjectOutputStream(soquete.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(soquete.getInputStream());
            Mensagem mensagem;
            Mensagem resposta = new Mensagem();
            MensagemImprimir impressao;
            Integer contadorMensagem = 1;
            do {
                mensagem = (Mensagem) entrada.readObject();
                //lendo a mensagem enviado
                System.out.println("\n\nSERVIDOR:: MENSAGEM: "+contadorMensagem);
                System.out.println("SERVIDOR:: OPERAÇÃO: "+mensagem.operacao);
                
                resposta.statusMensagem = StatusMensagem.OK;

                //respondendo ao cliente
                saida.writeObject(resposta);
                saida.flush();

                if (mensagem.operacao.equals(Operacao.IMPRIMIR)) {
                    //lendo a mensagem enviado
                    impressao = (MensagemImprimir) entrada.readObject();
                    System.out.println("SERVIDOR:: CONTEUDO: ");
                    System.out.println(impressao.conteudo);

                    resposta.statusMensagem = StatusMensagem.OK;

                    //respondendo ao cliente
                    saida.writeObject(resposta);
                    saida.flush();
                }
                contadorMensagem++;
            } while (mensagem.operacao != Operacao.SAIR);
            //fechando os streams e a conexao
            
            entrada.close();
            saida.close();
            soquete.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
