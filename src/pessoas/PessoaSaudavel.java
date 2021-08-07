/**
 * Toda Pessoa pertence a pessoas 
 */
package pessoas;

/* Import da classe Random, para randomizar a posição do Mover() */
import java.util.Random;
/**
 * Classe representa as pessoas saudáveis
 * no mundo, sem as limitações das pessoas
 * doentes.
 * @author aless
 */

/* Classe PessoaSaudavel é filha de Pessoa */
public class PessoaSaudavel extends Pessoa{

    /**
     * Construct de toda instância
     * @param x Recebe a posição X para super()
     * @param y Recebe a posição Y para super()
     * @param cor Recebe a cor de cada PessoaSaudável, será 2
     */
    public PessoaSaudavel(int x,int y,int cor) {
        super(x, y, cor);
    }

    /**
     * Getter do super cor (sobreposição)
     * @return valor de cor dessa PessoaSaudavel
     */
    @Override
    public int getCor() {
        return super.getCor();
    }

    /**
     * Getter do super Y (sobreposição)
     * @return valor de Y dessa PessoaSaudavel
     */
    @Override
    public int getY() {
        return super.getY();
    }

    /**
     * Getter do super X (sobreposição)
     * @return valor de X dessa PessoaSaudavel
     */
    @Override
    public int getX() {
        return super.getX();
    }

    /**
     * Uso do método Mover, obrigatório às filhas(sobreposição) 
     */
    @Override
    public void Mover() {
        /* Método do movimento de X */
        moverX();
        /* Método do movimento de Y */
        moverY();
        
    }

    /**
     * Método para mover em X a pessoa 
     */
    public void moverX(){
        /* Instância da classe Random */
        Random rand =new Random();
        /* Valor int randomizado de (-1,1),ou seja, será 0,1,-1 */
        int x = rand.nextInt(2 + 1)-1;

        /* Tratamento do valor caso as pessoas estejam no limite da matriz,
         * e recebam valores como -1 ou 1 
         */

        /* Se x randomizado = -1, e a posição atual = 0,
         * a pessoa passa para o outro lado da matriz 
         */
        if (x==-1 && this.getX()==0){
            super.setX(29);
        }

        /* Se x randomizado = 1, e a posição atual = 29,
         * a pessoa passa para o outro lado da matriz 
         */
        else if(x==1 && this.getX()==29){
            /* Nesse caso como o super é += entao soma -29
             * para ser 0. 
             */
            super.setX(-29);
        }

        /* O restante faz a adição normal */
        else{
            super.setX(x);
        }
    }

    /**
     * Método para mover em Y a pessoa 
     */
    public void moverY(){
        /* Instância da classe Random */
        Random rand =new Random();
        /* Valor int randomizado de (-1,1),ou seja, será 0,1,-1 */
        int y =rand.nextInt(2 + 1) - 1;

        /* Assim como em X o tratamento do Y é igual. 
         * Se y randomizado = -1, e a posição atual = 0,
         * a pessoa passa para o outro lado da matriz 
         */
        if (y==-1 && this.getY()==0){
            super.setY(59);
        }

        /* Se y randomizado = 1, e a posição atual = 59,
         * a pessoa passa para o outro lado da matriz 
         */
        else if(y==1 && this.getY()==59){
            super.setY(-59);
        }

        /* Restante faz adição normal */
        else{
            super.setY(y);
        }
    }
}
