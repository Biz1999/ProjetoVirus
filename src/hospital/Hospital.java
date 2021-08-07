/**
 * Pacote hospital, só contém essa classe 
 */
package hospital;

/**
 * Classe que representa cada Hospital no Mundo
 * @author aless
 */
public class Hospital {

    /**
     * cor que será o numero 5 para hospitais 
     */
    private int cor;
    
    /** 
     * Construct ao instanciar um hospital,
     * adiciona o valor 5 à cor do hospital
     */
    public Hospital(){
        this.cor = 5;
    }

    /**
     * Getter da cor 
     * @return Retorna a cor do hospital
     */ 
    public int getCor() {
        return cor;
    }
}
