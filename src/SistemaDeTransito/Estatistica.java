package SistemaDeTransito;

public class Estatistica {
	int codigoCidade; 
	String nomeCidade;
	int qtdAcidentes;

	public Estatistica(int codigoCidade, String nomeCidade, int qtdAcidentes) {
		this.codigoCidade = codigoCidade;
		this.nomeCidade = nomeCidade;
		this.qtdAcidentes = qtdAcidentes;
	}
	
	@Override
	public String toString() {
		return codigoCidade+";"+nomeCidade+";"+qtdAcidentes;
	}
}
