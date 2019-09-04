/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import util.enumConfi.Impressoras;
import util.enumConfi.QualidadeImpressao;
import util.enumConfi.StatusMensagem;

/**
 *
 * @author Marisergio
 */
public class MensagemImprimir extends Mensagem implements Serializable{
    public String conteudo;
    public Integer numeroCopias;
    public Impressoras modeloImpressora; 
    public Boolean frenteVerso;
    public QualidadeImpressao qualidade;
}
