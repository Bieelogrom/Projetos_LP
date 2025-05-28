package SistemaDeTransito;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Principal {
	static Scanner sc = new Scanner(System.in);
	static int n = 6;
	
	public static void main(String[] args) throws Exception{
		Estatistica[] arrEstatistica = new Estatistica[10];
		
		while(true) {
			System.out.println("1. Cadastro estatística"
					+ "\n2. Consulta por quantidade de acidentes"
					+ "\n3. Consulta por estatísticas de acidentes"
					+ "\n4. Acidentes acima da média das 10 cidades"
					+ "\n9. Finaliza");
			int opc = sc.nextInt();
			switch(opc) {
			case 1:
				arrEstatistica = fCadastraEstatistica(arrEstatistica);
				break;
			case 2:
				pQtdAcidentes();
				break;
			case 3:
				pMaiorMenor();
				break;
			case 4:
				pAcima();
				break;
			case 9:
				return;
			default:
				System.out.println("Opção desconhecida!");
				break;
			}
		}
	}
	
	public static Estatistica[] fCadastraEstatistica(Estatistica[] arrEstatistica) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter("ArquivoEstatistica.txt"));
		for(int i = 0; i < n; i++) {
			System.out.println("Digite o código da cidade: ");
			int codigoCidade = sc.nextInt();
			System.out.println("Digite o nome da cidade: ");
			String nomeCidade = sc.next();
			System.out.println("Digite a quantidade de acidentes: ");
			int qtdAcidentes = sc.nextInt();
			arrEstatistica[i] = new Estatistica(codigoCidade, nomeCidade, qtdAcidentes);
			bw.write(arrEstatistica[i].toString());
			bw.newLine();
		}
		System.out.println("Gravação completa!");
		bw.close();
		return arrEstatistica;
	}
	
	public static void pQtdAcidentes() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("ArquivoEstatistica.txt"));
		for(int i = 0; i < n; i++) {
			String[] stringEstatisticas = br.readLine().split(";");
			int acidentes = Integer.parseInt(stringEstatisticas[2]);
			if(acidentes > 100  && acidentes < 500) {
				System.out.println("Cidade "+stringEstatisticas[1]+" aconteceram "+acidentes+" acidentes!");
			}
		}
		br.close();
	}
	
	public static void pMaiorMenor() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("ArquivoEstatistica.txt"));
		int maior = 0, menor = 0;
		String maiorNome = "", menorNome = "";
		for(int i = 0; i < n; i++) {
			String[] stringEstatisticas = br.readLine().split(";");
			int acidentes = Integer.parseInt(stringEstatisticas[2]);
			if(i == 0) {
				maior = acidentes;
				menor = acidentes;
				maiorNome = stringEstatisticas[1];
				menorNome = stringEstatisticas[1];
			}
			if(acidentes > maior) {
				maior = acidentes;
				maiorNome = stringEstatisticas[1];
			}
			if(acidentes < menor) {
				menor = acidentes;
				menorNome = stringEstatisticas[1];
			}
		}
		System.out.println("Maior quantidade de acidentes: "+maior+"\nem: "+maiorNome);
		System.out.println("Menor quantidade de acidentes: "+menor+"\nem: "+menorNome);
		br.close();
	}
	
	public static void pAcima() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("ArquivoEstatistica.txt"));
		int soma = 0;
		for(int i = 0; i < n; i++) {
			String[] stringEstatisticas = br.readLine().split(";");
			soma += Integer.parseInt(stringEstatisticas[2]);
		}
		int media = soma / n;
		br.close();
		
		br = new BufferedReader(new FileReader("ArquivoEstatistica.txt"));
		for(int i = 0; i < n; i++) {
			String[] stringEstatisticas = br.readLine().split(";");
			int acidentes = Integer.parseInt(stringEstatisticas[2]);
			if(acidentes > media) {
				System.out.println(stringEstatisticas[1]+" acima da média!");
			}
		}
		br.close();
	}
}
