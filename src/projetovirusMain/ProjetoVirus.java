/* Pacote contendo o Main */
package projetovirusMain;
/* Import de pacotes importantes, como o Scanner e as classes de mundo(Mundo, Virus) */
import mundo.*;
import java.util.Scanner;
/**
 * Classe ProjetoVirus que representa o main,
 * onde será instanciado o Mundo()
 * @author aless
 */

/* ProjetoVirus extende Thread para conseguir aplicar o delay no print */
public class ProjetoVirus extends Thread{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Instancia da classe Scanner */
        Scanner input = new Scanner(System.in);
        
        /**
         * Uso do ANSI ESCAPE para letras e a quantidade de pessoas em cada grupo
         * Reseta o ANSI ESCAPE 
         */
        final String RESETAR = "\u001B[0m";
        /**
         * Letra vermelha 
         */
        final String redFG= "\u001B[31m";
        /**
         * Letra amarela
         */
        final String amareloFG= "\u001B[33m";
        /**
         * Fundo amarelo 
         */
        final String amareloBG= "\u001B[43m";
        /**
         * Letra ciano 
         */
        final String cianoFG = "\u001B[36m";
        /**
         * Fundo azul 
         */
        final String azulBG = "\u001B[44m";
        
        /**
         * Seleção de opção com 10 ou 100 pessoas 
         */
        int numeroSimulacao;
        System.out.printf("\nSimulação com "+amareloFG+"10"+RESETAR+" ou "+amareloFG +"100"+RESETAR + " pessoas: ");
        numeroSimulacao = Integer.parseInt(input.nextLine());
        
        /**
         * Salva o tempo inicial, tanto para o contador como para os infectados 
         */
        long inicioPrograma = System.currentTimeMillis();
        
        /**
         * Instancia do novo Virus 
         */
        Virus corona = new Virus();
        
        /**
         * Instancia do mundo com um construtor que passa:<br>
         * numero de pessoas da simulação, o novo virus, e o tempo de inicio de vida
         * do primeiro infectado. 
         */
        Mundo mundo = new Mundo(numeroSimulacao,corona,0);
        
        /**
         * Uso do try-catch para identificar caso aja erro no while e
         * para uso do Thread para gerar delay no while 
         */
        try{   
            /* Enquanto não houver erro executa a simulação */
            while(true){
                /* Acima foi salvo o tempo inicial, agora o atual para tirar a diferença */
                long tempoAtual = System.currentTimeMillis();

                /* Diferença do tempo final e inicial, dividido por 1000, que 
                 * basicamente é o tempo do while, adicionado como double 
                 */
                double tempo = (tempoAtual-inicioPrograma)/1000F;

                /**
                 * Uso do método desenhaMundo, onde é passado o virus para novos infectados e,
                 * o tempo de simulação a partir do construct, ou seja do primeiro infectado
                 */
                mundo.desenhaMundo(corona,tempo);

                /* Desenho 'COVID-19', ficou um pouco confuso, pelos escapes do \ */
                System.out.println("\n==================================================================\n"+redFG
                            +        "  ====   ====  ===     ===  ====   ====           ==     ====\n"+redFG
                            +        " ||     ||  ||  \\\\     //    ||    ||  \\\\      //  ||  ||   ||\n"+redFG
                            +        " ||     ||  ||   \\\\   //     ||    ||   \\\\ ===     ||  ||   ||\n"+redFG
                            +        " ||     ||  ||    \\\\ //      ||    ||   //         ||    ===//\n"+redFG
                            +        "  ====   ====      \\ /      ====   ====           ====     //\n"+RESETAR
                            +         "===================================================================\n");
                
                /* Print do tempo atual da simulação, forçando int para não aparecer como double */
                System.out.println("Tempo de Simulação: "+((int)tempo) + " segundos\n");

                /* Print da situação das pessoas na simulação(saudáveis,doentes e mortos) */
                System.out.println(azulBG+" "+RESETAR+cianoFG+" Pessoas Saudáveis: "+mundo.getPessoaSaudavel().size()+RESETAR+
                "       "+amareloBG+ " "+RESETAR+ amareloFG+" Pessoas Doentes: "+mundo.getPessoaDoente().size()+RESETAR+
                        "     Mortes: "+mundo.getMortes());
                
                /* Print do total de pessoas vivas */
                System.out.println("Total de Pessoas: " +(mundo.getPessoaSaudavel().size()+mundo.getPessoaDoente().size()) +" pessoas\n" );
                
                /* Método colorir para printar a matriz com as cores */
                mundo.colorirMundo();
                
                /* for para ocupar o restante do terminal, tornando mais suave a transição do while */
                int k;
                for(k=0;k<7;k++){
                    System.out.println("");
                }
                /* Delay para tornar a simulação fluida */
                Thread.sleep(110);    
            }
        }
        /* Recebe o erro caso aconteça, e printa no terminal */
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }

        /* fechamento do input */
        input.close();
    }
    
}