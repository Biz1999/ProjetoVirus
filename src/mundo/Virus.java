/* O vírus faz parte do mundo */
package mundo;

/**
 * Classe que representa o Vírus
 * que infestará o mundo
 * @author aless
 */
public class Virus {
    /**
     * String de todos os infectados 
     */
    private final String infeccao;
    
    /**
     * Construct ao instanciar o vírus<br> 
     * Adiciona valor a String infecção
     */
    public Virus(){
        this.infeccao = "VOCE ESTÁ INFECTADO!";
    }
}
