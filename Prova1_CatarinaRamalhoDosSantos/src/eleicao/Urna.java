package eleicao;

public class Urna {
	private int quantidadeVotos;
	private String status;
	private Eleitor[] eleitores;
	private Candidato[] candidatos;

	public Urna(int quantidadeEleitoresPermitidos, int quantidadeCandidatosPermitidos) {
		this.quantidadeVotos = 0;
		this.status = "NÃO INICIADA";
		this.eleitores = new Eleitor[quantidadeEleitoresPermitidos];
		this.candidatos = new Candidato[quantidadeCandidatosPermitidos];
	}

	public int getQuantidadeVotos() {
		return this.quantidadeVotos;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Quantidade de Eleitores Permitidos: " + this.eleitores.length
				+ " - Quantidade de Candidatos Permitidos: " + this.candidatos.length + " - " + this.status
				+ " - Quantidade de Votos Depositados: " + this.quantidadeVotos;
	}

	public String AdicionaEleitor(String cpf, String nome) {
		if (this.status == "NÃO INICIADA") {
			if (cpf == null || nome == null) {
				throw new NullPointerException("CPF ou Nome nulos");
			}
			if (cpf.isBlank() || nome.isBlank()) {
				throw new IllegalArgumentException("CPF ou Nome inválidos");
			} else {
				Eleitor eleitor = new Eleitor(cpf, nome);
				for (int i = 0; i < this.eleitores.length; i++) {
					if (eleitor.equals(this.eleitores[i])) {
						return "CPF já cadastrado - Eleitor não adicionado";
					} else if (this.eleitores[i] == null) {
						this.eleitores[i] = eleitor;
						return "Eleitor Cadastrado corretamente";

					}
				}
				return "Eleitor Não Cadastrado";
			}
		}
		return "Fora do Período de Cadastro";
	}

	public int AdicionaCandidato(String nome) {
		if (this.status == "NÃO INICIADA") {
			if (nome == null) {
				throw new NullPointerException("Nome nulo");
			} else if (nome.isBlank()) {
				throw new IllegalArgumentException("Nome inválido");
			} else {
				for (int i = 0; i < this.candidatos.length; i++) {
					Candidato candidato = new Candidato(i, nome);
					if (candidato.equals(this.candidatos[i])) {
						return -1;
					} else if (this.candidatos[i] == null) {
						this.candidatos[i] = candidato;
						return i;
					}
				}
			}
		}
		return -1;
	}

	private String listarCandidatos() {
		String listagem = "";
		for (int i = 0; i < this.candidatos.length; i++) {
			if (this.candidatos[i] != null) {
				listagem += this.candidatos[i].toString() + "\n";
			}
		}
		return listagem;
	}

	private String listarEleitores() {
		String listagem = "";
		for (int i = 0; i < this.eleitores.length; i++) {
			if (this.eleitores[i] != null) {
				listagem += this.eleitores[i].toString() + "\n";
			}

		}
		return listagem;
	}

	public String iniciarVotacao() {
		if (this.status == "NÃO INICIADA") {
			for (int i = 0; i < this.candidatos.length; i++) {
				if (this.candidatos[i] != null) {
					this.candidatos[i].zeraQuantidadeVotosRecebidos();
				} else {
					break;
				}
			}
			for (int i = 0; i < this.eleitores.length; i++) {
				if (this.eleitores[i] != null) {
					this.eleitores[i].setStatus("VOTO NÃO DEPOSITADO");

				} else {
					break;
				}
			}
			this.quantidadeVotos = 0;
			this.status = "EM ANDAMENTO";
			String relatorio = "—----------------- Urna —-----------------\n" + this.toString()
					+ "—----------------- Candidatos —-----------------\n" + this.listarCandidatos()
					+ "—----------------- Eleitores  —-----------------\n" + this.listarEleitores();
			return relatorio;
		}
		return "Não pode iniciar a votação";
	}

	public String registrarVoto(String cpfEleitor, String nomeCandidato) {
		if (this.status == "EM ANDAMENTO") {
			for (int i = 0; i < this.eleitores.length; i++) {
				if (this.eleitores[i] != null) {
					if (cpfEleitor.equals(this.eleitores[i].getCpf())) {
						if (this.eleitores[i].getStatus() == "VOTO NÃO DEPOSITADO") {
							for (int j = 0; j < this.candidatos.length; j++) {
								if (this.candidatos[j] != null) {
									if (nomeCandidato.equals(this.candidatos[j].getNome())) {
										this.candidatos[j].registraVoto();
										this.eleitores[i].setStatus("VOTO DEPOSITADO");
										this.quantidadeVotos++;
										return "Voto cadastrado corretamente";
									}
								} else {
									return "Candidato não cadastrado - Voto não Cadastrado";
								}
							}
						} else {
							return "Eleitor já votou anteriormente - Voto desconsiderado";
						}

					}
				} else {
					return "Eleitor não cadastrado - Voto não Cadastrado";
				}
			}
		}
		return "Fora do período de votação";
	}

	private int eleitoresCadastrados() {
		int eleitoresCadastrados = 0;
		for (int i = 0; i < this.eleitores.length; i++) {
			if (this.eleitores[i] != null) {
				eleitoresCadastrados++;
			}
		}
		return eleitoresCadastrados;
	}

	public int listarQuantidadeEleitoresFaltando() {
		return (this.eleitoresCadastrados() - this.quantidadeVotos);
	}

	public void encerrarVotacao() {
		if (this.status == "EM ANDAMENTO") {
			this.status = "ENCERRADA";
		}
	}

	public String informarVencedor() {
		Candidato candidato = new Candidato(1, "candidato");
		int maiorNumeroVotos = 0;
		if (this.status == "ENCERRADA") {
			for (int i = 0; i < this.candidatos.length; i++) {
				if (this.candidatos[i] != null) {
					if (this.candidatos[i].getQuantidadeVotosRecebidos() > maiorNumeroVotos) {
						candidato = this.candidatos[i];
						maiorNumeroVotos = this.candidatos[i].getQuantidadeVotosRecebidos();

					}
				}
			}
			int votosValidos = (maiorNumeroVotos * 100) / eleitoresCadastrados();
			return candidato.getNome() + " " + votosValidos;
		}
		return "Votação ainda não foi encerrada";
	}

	public String emitirBoletim() {
		String relatorio = "—----------------- Urna —-----------------\n" + this.toString()
				+ "—----------------- Candidatos —-----------------\n" + this.listarCandidatos();
		return relatorio;
	}

}
