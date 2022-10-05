package eleicao;

import java.util.Objects;

public class Eleitor {
	private String cpf;
	private String nome;
	private String status;

	public Eleitor(String cpf, String nome) {
		if (cpf == null || nome == null) {
			throw new NullPointerException("Nome ou cpf nulos");
		}
		if (cpf.isBlank() || nome.isBlank()) {
			throw new IllegalArgumentException("Nome ou cpf inválidos");
		}
		this.cpf = cpf;
		this.nome = nome;
		this.status = "VOTO NÃO DEPOSITADO";

	}

	@Override
	public String toString() {
		return this.cpf + " - " + this.nome + " - " + this.status;
	}

	public String getStatus() {
		return this.status;

	}

	public void setStatus(String status) {
		if (status == null) {
			throw new NullPointerException("STATUS NULO");
		} else if (status.isBlank()) {
			throw new IllegalArgumentException("STATUS INVÁLIDO");
		}
		this.status = status;
	}

	public String getCpf() {
		return this.cpf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eleitor other = (Eleitor) obj;
		return Objects.equals(cpf, other.cpf);
	}

}
