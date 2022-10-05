package eleicao;

import java.util.Objects;

public class Candidato {
	private int id;
	private String nome;
	private int quantidadeVotosRecebidos;

	public Candidato(int id, String nome) {
		if (nome == null) {
			throw new NullPointerException("Nome nulo");
		}
		if (nome.isBlank()) {
			throw new IllegalArgumentException("Nome inválido");
		}
		this.id = id;
		this.nome = nome;
		this.quantidadeVotosRecebidos = 0;

	}

	@Override
	public String toString() {
		return this.id + " - " + this.nome + " - Quantidade de Votos Recebidos: " + quantidadeVotosRecebidos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidato other = (Candidato) obj;
		return Objects.equals(nome, other.nome);
	}

	public void registraVoto() {
		this.quantidadeVotosRecebidos++;
	}

	public void zeraQuantidadeVotosRecebidos() {
		this.quantidadeVotosRecebidos = 0;
	}

	public String getNome() {
		return this.nome;
	}

	public int getQuantidadeVotosRecebidos() {
		return this.quantidadeVotosRecebidos;
	}

}
