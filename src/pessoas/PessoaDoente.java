/* Toda Pessoa pertence a pessoas */
package pessoas;

/* Import da classe Virus, ja que este é um infectado */
import mundo.Virus;

/* Import da classe Random, para randomizar a posição do Mover() */
import java.util.Random;

/**
 * Classe representa pessoas doentes
 * no Mundo, com distinção das saudáveis pois,
 * estão infectadas e com tempo limite de vida
 * @author aless
 */

/* Classe PessoaDoente é filha de Pessoa */
public class PessoaDoente extends Pessoa{
    
    /**
     * Todo infectado contém o Vírus 
     */
    private Virus virus;

    /**
     * Todo infectado tem 30s de vida para entrar em um hospital,
     * valor recebido do main pela instância em mundo
     */
    private double tempoVida;
    
    /**
     * Construct de toda instância,
     * recebe os valores e repassa ao super, além<br> 
     * do Tempo de início da vida e o vírus
     * @param x posição x de PessoaDoente para o super()
     * @param y posição y de PessoaDoente para o super()
     * @param cor cor de PessoaDoente para o super()
     * @param virus Vírus para o infectado
     * @param tempoVida tempo inicial de vida
     */
    public PessoaDoente(int x,int y, int cor,Virus virus,double tempoVida) {
        super(x, y, cor);
        this.virus = virus;
        this.tempoVida = tempoVida;
    }

    /**
     * Getter do super cor (sobreposição) 
     * @return valor de cor dessa PessoaDoente
     */
    @Override
    public int getCor() {
        return super.getCor();
    }

    /**
     * Getter do super Y (sobreposição)
     * @return valor de Y dessa PessoaDoente
     */
    @Override
    public int getY() {
        return super.getY();
    }

    /**
     * Getter do super X (sobreposição) 
     * @return valor de X dessa PessoaDoente
     */
    @Override
    public int getX() {
        return super.getX();
    }

    /**
     * Getter do Tempo inicial da infecção,para comparar com o tempo atual
     * @return Tempo inicial dessa PessoaDoente
     */
    public double getTempoVida() {
        return tempoVida;
    }

    /**
     * Setter do tempo de vida inicial
     * @param tempoVida tempo inicial de vida do infectado
     */
    public void setTempoVida(double tempoVida) {
        this.tempoVida = tempoVida;
    }
        
    /**
     * Uso do método Mover, obrigatório às filhas (sobreposição) 
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
             * para ser 0 
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

        /* Assim como em X o tratamento do Y é igual 
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
