/* Mundo fica no mundo */
package mundo;

/* Imports das classes instanciadas:
 * Todas as classes de pessoas 
 */
import pessoas.*;
/* Hospital */
import hospital.Hospital;
/* Formar o array de PessoaSaudavel e PessoaDoente */
import java.util.ArrayList;
/* Classe para randomizar a primeira posição */
import java.util.Random;
/**
 * Classe que representa o Mundo, com as pessoas,
 * hospitais e vírus.
 * @author aless
 */
public class Mundo {

    /**
     * Arrays de pessoas que serão colocadas na matriz 
     */
    private ArrayList <PessoaSaudavel> pessoaSaudavel;
    private ArrayList <PessoaDoente> pessoaDoente;

    /**
     * Hospital que também vai fazer parte do mundo 
     */
    private Hospital hospital;

    /**
     * Matriz onde todos os elementos estarão situados 
     */
    public int mapa[][];

    /** 
     * Classe para randomizar as posições iniciais das pessoas 
     */
    public Random rand;

    /**
     * Contador das mortes 
     */
    private int mortes;
    
    /**
     * Construct da classe, que recebe o numero de pessoas saudáveis da simulação,
     * a instância do novo vírus e o tempo atual para o primeiro infectado.
     * @param pessoas pessoas saudáveis para iniciar
     * @param virus Para as pessoas doentes
     * @param Vida tempo atual do programa para as pessoas doentes
     */
    public Mundo(int pessoas,Virus virus,double Vida){

        /* Instancia de PessoaSaudavel e PessoaDoente como ArrayList */
        pessoaSaudavel = new ArrayList<>();
        pessoaDoente = new ArrayList<>();
        /* Instancia da classe Random */
        rand =new Random();

        /* While para adicionar ao array pessoaSaudavel 100 pessoas ou 10,
         * dependendo da quantidade inserida pelo usuário 
         */
        int n=0;
        while (n<pessoas){
            /* Adiciona ao array */
            pessoaSaudavel.add(new PessoaSaudavel(rand.nextInt(30),rand.nextInt(60),2));
            n++;
        }

        /* Adicão de uma pessoaDoente, tanto no caso de 100 pessoas como em 10
         * diminui o range de aparição do doente, para ele não aparecer já em um hospital 
         */
        pessoaDoente.add(new PessoaDoente(rand.nextInt(30 -15)+15,rand.nextInt(45),3,virus,Vida));
    }

    /**
     * Método que adicionará à matriz mapa todos os valores como:
     * 1: limites
     * 2: pessoas saudáveis
     * 3: pessoas doentes
     * 5: hospital 
     * Além de chamar os métodos responsáveis por verificar:
     * A contaminação caso pessoa saudável tenha sobreposição com 
     * pessoa doente;
     * Morte de pessoas doentes com mais de 30 segundos;
     * A cura caso pessoa doente entre no hospital.
     * @param virus Para adicionar a cada novo infectado em Contaminação()
     * @param tempoAtual Tempo para adicionar a cada infectado
     */
    public void desenhaMundo(Virus virus,double tempoAtual){ 
        /* Adiciona o tamanho da matriz */
        this.mapa = new int[30][60];

        /* Instancia o hospital */
        hospital = new Hospital();
        
        /* for dentro de for para percorrer toda a matriz e,
         * adicionar os valores 1 para os limites e 5 para hospital 
         */

        /* i<comprimento de x */
        for(int i = 0; i<mapa.length;i++){
            /* j<comprimento de y */
            for(int j=0; j <mapa[i].length;j++){

                /* Se for 0, ou o (comprimento-1), valor na matriz = 1 */
                if (i==0 ||i==(mapa.length-1) ||j==0 ||j==(mapa[i].length-1)){
                    this.mapa[i][j]= 1;
                }

                /* Se estiver nessas posições de i e nos limites do j = nº 
                 * da cor do hospital, portanto 5.
                 * Resolvi fazer dois if para nao ficar um só extenso, facilitando
                 * o meu entendimento se fosse modificar alguma coisa. 
                 */
                else if(i==3||i==4||i==5||i==6||i==7){                    
                    if(46<=j && j<=52||7<=j && j<=13){

                        /* mapa[i][j]=5 */
                        this.mapa[i][j]= hospital.getCor(); 
                    }
                }

                /* Deve  fazer a mesma coisa do outro if, só foi dividido para 
                 * facilitar nas minhas modificações
                 */
                else if(i==23||i==24||i==25||i==26||i==27){
                    if(46<=j && j<=52){

                        /* mapa[i][j]=5 */
                        this.mapa[i][j]= hospital.getCor(); 
                    }
                }
                /* No restante ele preenche com número 0 */
                else{
                    this.mapa[i][j]= 0;
                }
            }
        }
        /* Chama o método contaminação que verifica a posição de todos os infectados e
         * não infectados 
         */
        this.contaminacao(virus,tempoAtual); 

        /* Chama o método cura, que verifica se um doente entrou no hospital*/
        this.curaContaminacao();

        /* Chama o método morte, que verifica o tempo de todos os infectados.
         * Aqueles com tempo de vida = 30 s, morrem 
         */
        this.morte(tempoAtual);

        /* Laço de repetição para adicionar as pessoas saudáveis e movê-las */
        int n;
        for(n=0;n<pessoaSaudavel.size();n++){
            /* Método mover para cada um do array */
            pessoaSaudavel.get(n).Mover();
            /* Posição será igual a 2 */
            mapa[pessoaSaudavel.get(n).getX()][pessoaSaudavel.get(n).getY()]=pessoaSaudavel.get(n).getCor();
        }

        /* Laço de repetição para adicionar as pessoas doentes e movê-las */
        for(n=0;n<pessoaDoente.size();n++){
            /* Método mover para cada um do array */
            pessoaDoente.get(n).Mover();
            /* Posição será igual a 3 */
            mapa[pessoaDoente.get(n).getX()][pessoaDoente.get(n).getY()]=pessoaDoente.get(n).getCor();
        }
        
        
    }
    
    /**
     * Método para printar colorido a matriz dependendo dos numeros:<br>
     * 1 - Branco <br>
     * 2 - Azul<br>
     * 3 - Amarelo<br>
     * 5 - Verde e Magenta, pensei em colocar vermelho mas nao sei se 
     * foi colocado magenta por daltonismo, então coloquei magenta também 
     */
    public void colorirMundo(){
        /* Reseta cores e fundos magenta,
         * verde, amarelo, azul e branco 
         */
        final String RESETAR = "\u001B[0m";
        final String verdeBG= "\u001B[42m";
        final String amareloBG= "\u001B[43m";
        final String azulBG = "\u001B[44m";
        final String magentaBG= "\u001B[45m";
        final String brancoBG = "\u001B[47m";

        /* Laço de repetição para printar a matriz como cores */
        int i,j;
        for (i=0;i<mapa.length;i++){
                for (j=0;j<mapa[i].length;j++){
                    switch (mapa[i][j]) {
                        case 1:
                            /* primeiro caso do switch pois se uma pessoa sobrepoe aparece a pessoa */
                            System.out.printf(brancoBG+" "+RESETAR);
                            break;
                        
                        /* Caso seja uma pessoa Saudável, azul */
                        case 2:
                            System.out.printf(azulBG+" "+RESETAR);
                            break;

                        /* Caso seja um doente, amarelo */
                        case 3:
                            System.out.printf(amareloBG+" "+RESETAR);
                            break;

                        /* Com numero 5, print do Hospital */
                        case 5:
                            /* Print da cruz do Hospital */
                            if (i==4 && j==10||i==4 && j==49||i==6 && j==10||i==6 && j==49
                                    ||i==24 && j==49||i==26 && j==49){
                                System.out.printf(magentaBG+" "+RESETAR);
                            }
                            /* Print da cruz também, dividido em dois para facilitar o entendimento */
                            else if(i==5 && 8<=j && j<=12||i==5 && 47<=j && j<=51
                                        ||i==25 && 47<=j && j<=51){
                                System.out.printf(magentaBG+" "+RESETAR);
                            }
                            /* Se não estiver nas posições da parte magenta, printa verde */
                            else{
                                System.out.printf(verdeBG+" "+RESETAR);
                            }   
                            break;

                        /* O restante da matriz, que é 0, sem cor */
                        default:
                            System.out.printf(" ");
                            break;
                    }
                }
                /* Após cada linha o for pula linha */
                System.out.print("\n");
                
            }
    }
    
    /**
     * Método que vai contaminar as pessoas saudaveis, 
     * caso elas sobrepõem algum doente. 
     * @param virus Para adicionar a cada novo doente
     * @param tempoVida Tempo atual do programa para adicionar
     * ao tempo de vida de cada Doente
     */
    public void contaminacao(Virus virus,double tempoVida){

        /* Esse laço de repetição percorre tanto o array pessoaSaudavel como
         * o pessoaDoente 
         */
        int i,j;
        /* i<comprimento do array pessoaSaudavel */
        for(i=0;i<pessoaSaudavel.size();i++){
            /* j<comprimento do array pessoaDoente */
            for(j=0;j<pessoaDoente.size();j++){
                /* Uso do try-catch é mais para quando ele para a execução e relata um erro
                 * que não encontra o index que justamente eu excluo no if abaixo 
                 */
                try{
                    /* Se a pessoaSaudavel sobrepõe pessoaDoente, pessoa saudável é 
                     *removida e adicionada a pessoaDoente 
                     */
                    /* Se x e y iguais, nova pessoa doente, e remoção do saudável */
                    if (pessoaSaudavel.get(i).getX()==pessoaDoente.get(j).getX()){
                        if (pessoaSaudavel.get(i).getY()==pessoaDoente.get(j).getY()){
                            pessoaDoente.add(new PessoaDoente(pessoaSaudavel.get(i).getX(),pessoaSaudavel.get(i).getY(),3,virus,tempoVida));
                            pessoaSaudavel.remove(i);
                        }
                    }
                /* O tratamento do erro é só ignorá-lo para nao mostrar ao usuário */
                }catch(Exception e){
                    ;
                }
            }    
        }
    }

    /**
     * Método para curar os doentes que entrarem nos hospitais 
     */
    public void curaContaminacao(){
        /* laço de repetição para percorrer todo o array de doentes */
        int i;
        for(i=0;i<pessoaDoente.size();i++){

            /* Recebe as posições x e y de cada doente */
            int x = pessoaDoente.get(i).getX();
            int y = pessoaDoente.get(i).getY();      
            
            /* Se estiver nas posições dos hospitais de cima 
             * a pessoa doente é retirada do array, e colocada no 
             * array pessoaSaudavel
             */
            if(3<=x && x<=7){
                if(7<=y && y<=13|| 46<=y && y<=52){
                    pessoaSaudavel.add(new PessoaSaudavel(x,y,2));
                    pessoaDoente.remove(i);
                }   
            }
            /* Se estiver nas posições do hospital embaixo
             * a pessoa doente é retirada do array, e colocada no
             * array pessoaSaudavel
             */
            else if (23<=x && x<=27){
                if(46<=y && y<=52){
                    pessoaSaudavel.add(new PessoaSaudavel(x,y,2));
                    pessoaDoente.remove(i);
                }
            /* O restante dos doentes permanecem doentes */
            }else{
                ;
            }
        }
    }
    
    /**
     * Este método recebe o Array de pessoas doentes e 
     * avalia se a pessoa tem mais do que 30s de vida,
     * se sim ela morre.
     * @param tempoVida tempo atual do programa
     * 
     */
    public void morte(double tempoVida){
        /** 
         * Se há pessoas doentes ocorre a verificação 
         */
        int i; 
        if (!pessoaDoente.isEmpty()){
            /**
             * i<comprimento do array de doentes 
             */
            for(i=0;i<pessoaDoente.size();i++){

                /** 
                 * variável recebe a vida inicial de cada doente 
                 */
                double vidaTotal = pessoaDoente.get(i).getTempoVida();

                /** 
                 * Se a vida inicial do doente + 30 segundos é
                 * menor ou igual ao tempo atual 
                 */
                if((vidaTotal+30)<=tempoVida){
                    /** 
                     * Pessoa removida e adicionado 1 as mortes 
                     */
                    pessoaDoente.remove(i);
                    this.mortes+=1;
                
                /**
                 * Se o tempo de vida não chegou ao limite o doente 
                 * contínua vivo e pode se recuperar no hospital 
                 */
                }else{
                    ;
                }                
            }
        /**
         * Se não há nenhum doente, só encerra esse método 
         */  
        }else{
            ;
        }
    }

    /**
     * Getter do array PessoaSaudavel 
     * @return Retorna o array de Pessoas saudáveis
     */
    public ArrayList<PessoaSaudavel> getPessoaSaudavel() {
        return pessoaSaudavel;
    }

    /** 
     * Getter do array PessoaDoente 
     * @return Retorna o array de Pessoas doentes
     */
    public ArrayList<PessoaDoente> getPessoaDoente() {
        return pessoaDoente;
    }

    /** 
     * Getter para retornar número total de mortos
     * @return Retorna o número total de mortos
     */
    public int getMortes() {
        return mortes;
    }
    
}
