package ecopontos;

public class Ecoponto {

	private String nome;
	private Pessoa pessoa;
	private Endereco endereco;
	private String[] residuos;
	
	public String pegaResiduos() {
		if(this.residuos != null) {
		String todos=residuos[0];
		for( int i = 1; i< residuos.length;i++) {
			todos = todos+", "+residuos[i];
		}
		return todos;
		}
		else return null;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String[] getResiduos() {
		return residuos;
	}
	public void setResiduos(String[] residuos) {
		this.residuos = residuos;
	}
	
	
	
}
