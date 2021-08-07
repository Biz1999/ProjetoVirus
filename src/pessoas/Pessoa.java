/**
 * Toda pessoa faz parte de pessoas 
 */
package pessoas;

/**
 * Classe abstrata que representa cada pessoa,
 * sem diferença entre saudáveis e doente,
 * todas sem exceção estarão no Mundo.
 * @author aless
 */

/* A classe Pessoa jamais será instanciada, por isso abstract */
public abstract class Pessoa {
    /**
     * Atributos de posição e cor da pessoa, 
     * privados como no diagrama 
     */
    private int x;
    private int y;
    private int cor;

    /**
     * Já que a classe é abstrata, esse construct deve estar
     * nas classes filhas, como super() 
     * @param x posição X da pessoa
     * @param y posição Y da pessoa
     * @param cor Cor da Pessoa, dependendo se é Saudável ou Doente
     */
    public Pessoa(int x, int y, int cor) {
        this.x = x;
        this.y = y;
        this.cor = cor;
    }

    /**
     * Getter do X 
     * @return valor de X dessa Pessoa
     */
    public int getX() {
        return x;
    }

    /**
     * Setter do X, ao invés de apenas substituir ele, 
     * vai somar ao valor existente 
     * @param x Posição a ser somada ao this.x
     */
    public void setX(int x) {
        this.x += x;
    }

    /** 
     * Getter do Y 
     * @return valor de Y dessa Pessoa
     */
    public int getY() {
        return y;
    }

    /**
     * Setter do Y, ao invés de apenas substituir ele, 
     * vai somar ao valor existente 
     * @param y Posição a ser somada ao this.y
     */
    public void setY(int y) {
        this.y += y;
    }

    /**
     * Recebe a cor dessa Pessoa 
     * @return valor de cor dessa Pessoa
     */
    public int getCor() {
        return cor;
    }

    /** 
     * Força as classes filhas a implementar o método Mover 
     */
    public abstract void Mover();
    
    
}
