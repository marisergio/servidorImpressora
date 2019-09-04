
package util;

import java.io.Serializable;
import util.enumConfi.Impressoras;
import util.enumConfi.Operacao;
import util.enumConfi.QualidadeImpressao;
import util.enumConfi.StatusMensagem;

public class Mensagem implements Serializable{
    
    public Operacao operacao;
    public StatusMensagem statusMensagem;
    
}
