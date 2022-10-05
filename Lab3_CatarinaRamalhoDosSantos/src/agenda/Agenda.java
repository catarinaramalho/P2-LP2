package agenda;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100
 * contatos.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 *
 */
public class Agenda {
	/**
	 * TAMANHO_Agenda é um atributo que define o tamnho de agenda para até 100
	 * contatos.
	 */
	private static final int TAMANHO_AGENDA = 100;
	/**
	 * contatos é um array que comportará os contatos do tipo Contato na agenda.
	 */
	private Contato[] contatos;
	/**
	 * favoritos é um array que comportará os contatos favoritos do tipo Contato na
	 * agenda.
	 */
	private Contato[] favoritos;

	/**
	 * Cria uma agenda, definindo um tamanho de 100 e 10, respectivamente, para a
	 * lista de contatos e a lista de contatos favoritos da agenda.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
		this.favoritos = new Contato[10];
	}

	/**
	 * Acessa os dados de um contato específico atarvés de uma posição no intervalo
	 * [1,100].
	 * 
	 * @param posicao Posição do contato na agenda.
	 * @return Dados do contato. Null se não há contato na posição.
	 */
	public Contato getContato(int posicao) {
		if (posicao > 100 || posicao < 1) {
			return null;
		}
		return this.contatos[posicao - 1];
	}

	/**
	 * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe
	 * sobrescreve o anterior. Para realizar o cadastro do usuário: a posição deve
	 * estar no intervalo [1,100], o nome e o telefone não podem ser nulos ou com
	 * espaços em branco, bem como não pode adicionar contato já cadastrado.
	 * 
	 * @param posicao   Posição do contato.
	 * @param nome      Nome do contato.
	 * @param sobrenome Sobrenome do contato.
	 * @param telefone  Telefone do contato.
	 * @return Uma String contendo a expressão validadora do cadastro, conforme os
	 *         critérios.
	 */
	public String cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
		if (posicao > 100 || posicao < 1) {
			return "POSIÇÃO INVÁLIDA";
		}
		if (nome == null || nome.isBlank() || telefone == null || telefone.isBlank()) {
			return "CONTATO INVALIDO";
		}
		Contato contato = new Contato(nome, sobrenome, telefone);
		for (int i = 0; i < this.contatos.length; i++) {
			if (contato.equals(this.contatos[i])) {
				return "CONTATO JA CADASTRADO";

			}
		}
		this.contatos[posicao - 1] = contato;
		return "CADASTRO REALIZADO";
	}

	/**
	 * Exibe um contatato mediante uma posição válida [1,100] e caso esse contato
	 * esteja na lista de favoritos, acrescenta um coração contato. Caso contrário,
	 * retorna que o usuário digitou uma posição inválida que não há contato.
	 * 
	 * @param posicao Posição do contato.
	 * @return Uma String contendo a expressão com os dados do contato ou null
	 *         quando não há contato naquela posição.
	 */
	public String exibirContato(int posicao) {
		if (posicao > 100 || posicao < 1) {
			return "POSIÇÃO INVÁLIDA";
		}
		Contato contato = this.getContato(posicao);
		if (contato == null) {
			return "POSIÇÃO INVÁLIDA";
		}
		String dadosContato = (contato.toString());
		for (int i = 0; i < this.favoritos.length; i++) {
			if (contato.equals(favoritos[i])) {
				return "❤️ " + dadosContato;
			}

		}
		return dadosContato;
	}

	/**
	 * Favorita um contato em uma posição. Um contato favoritado em uma posição que
	 * já existe, sobrescreve o anterior. Não se pode favoritar um contato já
	 * existente na lista de favoritos da agenda.
	 * 
	 * @param posicao Posição do contato a ser favoritado.
	 * @param contato Contato a ser favoritado.
	 * @return String com uma expressão se o contato foi ou não favoritado, conforme
	 *         os critérios e null quando é um contato já cadastrado.
	 */
	public String adicionarFavorito(int posicao, Contato contato) {
		if (posicao > 10 || posicao < 1) {
			return "POSIÇÃO INVÁLIDA";
		}
		for (int i = 0; i < this.favoritos.length; i++) {
			if (contato.equals(this.favoritos[i])) {
				return "CONTATO JÁ FAVORITADO";
			}
		}
		this.favoritos[posicao - 1] = contato;
		return "CONTATO FAVORITADO NA POSIÇÃO " + posicao + "!";
	}

	/**
	 * Aplica uma tag na posição válida da tag informada no parâmetro [1,5], nas
	 * respectivas posições de contato passados no parâmetro válidas em [1,100] e em
	 * contatos não nulos.
	 * 
	 * @param posicaoContatos que é um array de String contendo as posições dos
	 *                        contatos a serem adicionados a tag.
	 * @param posicaoTag      que é inteiro que contém a posição na qual deve ser
	 *                        adicionada a tag na lista de tags de contato.
	 * @param tag             que é uma string identificadora da taga ser aplicada.
	 */
	public void aplicaTag(String[] posicaoContatos, int posicaoTag, String tag) {
		if (posicaoTag >= 1 && posicaoTag <= 5) {
			for (int i = 0; i < posicaoContatos.length; i++) {
				if (Integer.parseInt(posicaoContatos[i]) >= 1 && Integer.parseInt(posicaoContatos[i]) <= 100) {
					Contato contato = this.getContato(Integer.parseInt(posicaoContatos[i]));
					if (contato != null) {
						contato.adicionaTag(posicaoTag, tag);
					}
				}
			}
		}
	}

	/**
	 * Remove um ou mais de um Contato na posição informada, que deve conter um
	 * contato e estar no intervalo [1,100], atribuindo valor nulo para ele. Se o
	 * contato pode ser removido, remove da lista de favoritos, caso ele seja
	 * favorito. Caso não haja contato na posição em questão, retorna false e para
	 * de remover, como solicitado na especificação.
	 * 
	 * @param posicaoContatos Array de Strings das posições dos contatos a serem removidos.
	 * @return um boolean de verdadeiro se todos os contatos foram removidos e falso
	 *         caso um não foi removido.
	 */
	public boolean removeContatos(String[] posicaoContatos) {
		for (int i = 0; i < posicaoContatos.length; i++) {
			if (Integer.parseInt(posicaoContatos[i]) >= 1 && Integer.parseInt(posicaoContatos[i]) <= 100) {
				Contato contato = this.getContato(Integer.parseInt(posicaoContatos[i]));
				if (contato != null) {
					this.removeFavorito(contato);
					this.contatos[Integer.parseInt(posicaoContatos[i]) - 1] = null;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Remove um Contato Favoritado passado pelo parâmetro da lista de favoritos da
	 * agenda.
	 * 
	 * @param contato Contato a ser removido da lista de favoritos.
	 */
	public void removeFavorito(Contato contato) {
		for (int i = 0; i < this.favoritos.length; i++) {
			if (contato.equals(this.favoritos[i])) {
				this.favoritos[i] = null;
			}
		}
	}

	/**
	 * Lista contatos da agenda, exibindo sua posição e nome completo.
	 * 
	 * @return uma String contendo a listagem de contatos.
	 */
	public String listaContatos() {
		String listaContatos = "";
		for (int i = 0; i < this.contatos.length; i++) {
			if (contatos[i] != null) {
				listaContatos += (i + 1) + " - " + this.contatos[i].nomeCompleto() + "\n";
			}
		}
		return listaContatos.trim();
	}

	/**
	 * Lista contatos favoritos da agenda, exibindo sua posição e nome completo.
	 * 
	 * @return uma String contendo a listagem de contatos favoritos.
	 */
	public String listaFavoritos() {
		String listaFavoritos = "";
		for (int i = 0; i < this.favoritos.length; i++) {
			if (this.favoritos[i] != null) {
				listaFavoritos += (i + 1) + " - " + this.favoritos[i].nomeCompleto() + "\n";
			}
		}
		return listaFavoritos.trim();
	}

}
