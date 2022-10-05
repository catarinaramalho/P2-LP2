package agenda;

import java.util.Objects;

/**
 * Um Contato possui nome, sobrenome, telefone e pode conter uma lista de até 5
 * tags identificatórias.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 *
 */
public class Contato {
	/**
	 * nome é um atributo do tipo String que define o nome do contato.
	 */
	private String nome;
	/**
	 * sobrenome é um atributo do tipo String que define o sobrenome do contato.
	 */
	private String sobrenome;
	/**
	 * telefone é um atributo do tipo String que define o telefone do contato.
	 */
	private String telefone;
	/**
	 * tags é um atributo que comporta um array do tipo String que define as tags do
	 * contato.
	 */
	private String[] tags;

	/**
	 * Cria um contato a partir do nome, sobrenome e telefone, incializando a lista
	 * de tags de tamanho 5.
	 * 
	 * @param nome      do tipo String que representa o nome do contato.
	 * @param sobrenome do tipo String que representa o sobrenome do contato.
	 * @param telefone  do tipo String que representa o telefone do contato.
	 */
	public Contato(String nome, String sobrenome, String telefone) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.tags = new String[5];

	}

	/**
	 * Adiciona uma Tag, do tipo String, na posição passada no parâmetro que deve
	 * estar apenas no intervalo [1,5] para ser adicionada na lista de tags do
	 * contato.
	 * 
	 * @param posicao do tipo inteiro que indica a posição na qual deve ser
	 *                adicionada a tag.
	 * @param tag     do tipo String que representa a tag a ser adicionada na lista
	 *                de tagas do contato.
	 */
	public void adicionaTag(int posicao, String tag) {
		if ((posicao) >= 1 && (posicao) <= 5) {
			this.tags[posicao - 1] = tag;
		}
	}

	/**
	 * Retorna a representação textual do nome do contato, formado por seu nome e
	 * sobrenome.
	 * 
	 * @return uma String contendo o nome o o sobrenome do contato.
	 */
	public String nomeCompleto() {
		return this.nome + " " + this.sobrenome;
	}

	/**
	 * Acessa e retorna uma representação textual das tags cadastradas na lista de
	 * tags de contatos.
	 * 
	 * @return uma String com as tags cadastradas na lista de tags do contato.
	 */
	public String tagsContato() {
		String tagsCadastradas = "";
		for (int i = 0; i < this.tags.length; i++) {
			if (this.tags[i] != null) {
				tagsCadastradas += this.tags[i] + " ";
			}
		}
		return tagsCadastradas.trim();
	}

	/**
	 * Forma e retorna uma representação textual do contato, com o nome, o sobrenome
	 * (ambos já retornados no método nomeCompleto()), o telefone e a representação
	 * textual das tags retornada no método tagsContato().
	 * 
	 * @return uma representação textual dos atributos de contato.
	 */
	@Override
	public String toString() {
		return nomeCompleto() + "\n" + this.telefone + "\n" + this.tagsContato();
	}

	/**
	 * Compara a igualdade de Contatos que possuem o mesmo nome e sobrenome.
	 * 
	 * @param obj que é o objeto a ser comparado.
	 * @return um valor do tipo boolean para igualdade entre objetos.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(sobrenome, other.sobrenome);
	}

}
