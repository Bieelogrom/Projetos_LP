package SistemaDeVotacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Principal {
	static Scanner sc = new Scanner(System.in);
	static int n = 5;
	
	public static void main(String[] args) throws Exception {
		Votacao[] arrVotacao = new Votacao[n];
		
		while(true) {
			System.out.println("1. Carregar seção/Número Eleitor"
					+ "\n2. Classificar por seção"
					+ "\n3. Gravar registros"
					+ "\n4. Mostrar indicadores"
					+ "\n9. Finalizar");
			int opc = sc.nextInt();
			switch(opc) {
			case 1:
				arrVotacao = fCadastraVotacao(arrVotacao);
				break;
			case 2:
				arrVotacao = fClassifica(arrVotacao);
				break;
			case 3:
				arrVotacao = fGravaVotacao(arrVotacao);
				break;
			case 4:
				menuEstatisticas(arrVotacao);
			case 9:
				break;
			}
		}
	}
	
	private static void menuEstatisticas(Votacao[] arrVotacao) throws Exception{
		int opc;
		while(true) {
			System.out.println("1. Quantidade de eleitores por seção"
					+ "\n2. Seção com maior e menor número de eleitores"
					+ "\n3. Quantidade de votos por candidato"
					+ "\n4. 10 primeiros colocados(nro cand e qtd votos)"
					+ "\n9. Finaliza consulta");
			opc = sc.nextInt();
			switch(opc) {
			case 1:
				quantidadeDeEleitoresPorSecao(arrVotacao);
				break;
			case 2:
				maiorEMenor(arrVotacao);
			case 9:
				return;
			}
		}
	}

	private static void maiorEMenor(Votacao[] arrVotacao) {
		// TODO Auto-generated method stub
		
	}

	private static void quantidadeDeEleitoresPorSecao(Votacao[] arrVotacao) throws Exception{
		for(int i = 0; i < n; i++) {
			int cont = 1;
			for(int j = i + 1; j < n; j++) {
				if(arrVotacao[i].numeroSecao == arrVotacao[j].numeroSecao) {
					cont++;
				}
			}
			if(cont > 1) {
				System.out.println("Seção "+arrVotacao[i].numeroSecao+" tem "+cont+" eleitores.");
			}
		}
	}

	private static Votacao[] fGravaVotacao(Votacao[] arrVotacao) throws Exception {
		BufferedWriter bf = new BufferedWriter(new FileWriter("Votacao.txt"));
		for(int i = 0; i < n; i++) {
			bf.write(arrVotacao[i].numeroSecao+";"+arrVotacao[i].numeroCandidato);
			bf.newLine();
		}
		bf.close();
		return arrVotacao;
	}

	private static Votacao[] fClassifica(Votacao[] arrVotacao){
		boolean trocado;
		for(int i = 0; i < n - 1; i++) {
			trocado = false;
			for(int j = 0; j < n - 1; j++) {
				if(arrVotacao[j].numeroSecao > arrVotacao[j+1].numeroSecao) {
					Votacao votacaoTemp = arrVotacao[j];
					arrVotacao[j] = arrVotacao[j+1];
					arrVotacao[j+1] = votacaoTemp;
					trocado = true;
				}
			}
			if(!trocado) break;
		}
		System.out.println("Dados classificados!");
		return arrVotacao;
	}

	private static Votacao[] fCadastraVotacao(Votacao[] arrVotacao){
		int numeroSecao, numeroDoCandidato;
		for(int i = 0; i < n; i++) {
			do {
				System.out.print("Digite o número da seção: ");
				numeroSecao = sc.nextInt();
				if(numeroSecao < 0 || numeroSecao > 10) {
					System.out.println("Digite novamente!");
				}
			}while(numeroSecao < 0 || numeroSecao > 10);
			do {
				System.out.print("Digite o número do condidato: ");
				numeroDoCandidato = sc.nextInt();
				if(numeroDoCandidato < 0 || numeroDoCandidato > 300) {
					System.out.println("Digite novamente!");
				}
			}while(numeroDoCandidato < 0 || numeroDoCandidato > 300);
			arrVotacao[i] = new Votacao(numeroSecao, numeroDoCandidato);
		}
		return arrVotacao;
	}
	
	
}
