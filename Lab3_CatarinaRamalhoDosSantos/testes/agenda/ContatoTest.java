package agenda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

/**
 * Uma classe de testes para os métodos de Contato.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 *
 */
class ContatoTest {
	/**
	 * contatoBase é um atributo para armazenar o objeto contato, para o acesso a
	 * referência de seus métodos e futuro testes sobre eles.
	 */
	private Contato contatoBase;

	/**
	 * Cria um contato padrão antes de cada teste.
	 */
	@BeforeEach
	void preparaContatos() {
		this.contatoBase = new Contato("Matheus", "Gaudencio", "555-5551");
	}

	// TESTES DE REPRESENTAÇÃO TEXTUAL
	/**
	 * Testa o retorno do nome completo do contato.
	 */
	@Test
	void testNomeCompleto() {
		assertEquals("Matheus Gaudencio", this.contatoBase.nomeCompleto());
	}

	/**
	 * Testa a representação textual da classe.
	 */
	@Test
	void testToString() {
		assertEquals("Matheus Gaudencio\n" + "555-5551\n", this.contatoBase.toString());
	}

	/**
	 * Testa a representação textual das tags adicionadas
	 */
	@Test
	void testTagsContato1() {
		this.contatoBase.adicionaTag(1, "ufcg");
		this.contatoBase.adicionaTag(2, "cc");
		this.contatoBase.adicionaTag(3, "federal");
		this.contatoBase.adicionaTag(4, "universidade");
		this.contatoBase.adicionaTag(5, "educação");
		assertEquals("ufcg cc federal universidade educação", this.contatoBase.tagsContato());
	}

	/**
	 * Testa a representação textual de nenhuma tag adicionada
	 */
	@Test
	void testTagsContato2() {
		assertEquals("", this.contatoBase.tagsContato());
	}

	// TESTES DE ADICIONAR TAGS

	/**
	 * Testa a adição de tag em uma posição válida entre [1,5].
	 */
	@Test
	void testAdicionaTag1() {
		this.contatoBase.adicionaTag(1, "ufcg");
		assertEquals("ufcg", this.contatoBase.tagsContato());
	}

	/**
	 * Testa a adição de tag em uma posição válida entre [1,5] até seu limite de 5.
	 */
	@Test
	void testAdicionaTag2() {
		this.contatoBase.adicionaTag(1, "ufcg");
		this.contatoBase.adicionaTag(2, "cc");
		this.contatoBase.adicionaTag(3, "federal");
		this.contatoBase.adicionaTag(4, "universidade");
		this.contatoBase.adicionaTag(5, "educação");
		assertEquals("ufcg cc federal universidade educação", this.contatoBase.tagsContato());
	}

	/**
	 * Testa a adição de tag em uma posição inválida superior a [1,5], pois não
	 * adiciona.
	 */
	@Test
	void testAdicionaTag3() {
		this.contatoBase.adicionaTag(1, "ufcg");
		this.contatoBase.adicionaTag(2, "cc");
		this.contatoBase.adicionaTag(3, "federal");
		this.contatoBase.adicionaTag(4, "universidade");
		this.contatoBase.adicionaTag(5, "educação");
		this.contatoBase.adicionaTag(6, "estudante");
		assertEquals("ufcg cc federal universidade educação", this.contatoBase.tagsContato());
	}

	/**
	 * Testa a adição de tag em uma posição inválida inferior a [1,5], pois não
	 * adiciona.
	 */
	@Test
	void testAdicionaTag4() {
		this.contatoBase.adicionaTag(1, "ufcg");
		this.contatoBase.adicionaTag(2, "cc");
		this.contatoBase.adicionaTag(3, "federal");
		this.contatoBase.adicionaTag(4, "universidade");
		this.contatoBase.adicionaTag(5, "educação");
		this.contatoBase.adicionaTag(0, "estudante");
		assertEquals("ufcg cc federal universidade educação", this.contatoBase.tagsContato());
	}

	/**
	 * Testa a adição de tags repetidas.
	 */
	@Test
	void testAdicionaTag5() {
		this.contatoBase.adicionaTag(1, "ufcg");
		this.contatoBase.adicionaTag(2, "ufcg");
		this.contatoBase.adicionaTag(3, "ufcg");
		this.contatoBase.adicionaTag(4, "ufcg");
		this.contatoBase.adicionaTag(5, "ufcg");

		assertEquals("ufcg ufcg ufcg ufcg ufcg", this.contatoBase.tagsContato());
	}

	/**
	 * Testa a adição de tag com sobrescrita.
	 */
	@Test
	void testAdicionaTag6() {
		this.contatoBase.adicionaTag(1, "ufcg");
		this.contatoBase.adicionaTag(2, "ufcg");
		assertEquals("ufcg ufcg", this.contatoBase.tagsContato());
		this.contatoBase.adicionaTag(2, "cc");
		assertEquals("ufcg cc", this.contatoBase.tagsContato());
	}

	// TESTES SOBRE EQUALS

	/**
	 * Testa se dois contatos são iguais, sem o mesmo nome e sobrenome.
	 */
	@Test
	void testEquals1() {
		Contato outroContato = new Contato("Catarina", "Ramalho", "(83) 99111-0000");
		assertFalse(this.contatoBase.equals(outroContato));
	}

	/**
	 * Testa se dois contatos são iguais, com o mesmo nome e sobrenome.
	 */
	@Test
	void testEquals2() {
		Contato outroContato = new Contato("Matheus", "Gaudencio", "(83) 99111-0000");
		assertTrue(this.contatoBase.equals(outroContato));
	}

	/**
	 * Testa se dois contatos são iguais, sendo o parâmetro um objeto.
	 */
	@Test
	void testEquals3() {
		assertFalse(this.contatoBase.equals(new Object()));
	}

	/**
	 * Testa se dois contatos são iguals, um sendo nulo.
	 */
	@Test
	void testEquals4() {
		assertFalse(this.contatoBase.equals(null));
	}

	/**
	 * Testa se dois contatos são iguais, sendo os dois o mesmo contato.
	 */
	@Test
	void testEquals5() {
		assertTrue(this.contatoBase.equals(this.contatoBase));
	}

	/**
	 * Testa se dois contatos são iguais, sendo com nomes diferentes e sobrenomes
	 * iguais.
	 */
	@Test
	void testEquals6() {
		Contato outroContato = new Contato("Catarina", "Gaudencio", "(83) 99111-0000");
		assertFalse(this.contatoBase.equals(outroContato));
	}

	/**
	 * Testa se dois contatos são iguais, sendo com sobrenomes diferentes e nomes
	 * iguais.
	 */
	@Test
	void testEquals7() {
		Contato outroContato = new Contato("Matheus", "Ramalho", "(83) 99111-0000");
		assertFalse(this.contatoBase.equals(outroContato));
	}

}
