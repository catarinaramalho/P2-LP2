package agenda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Uma classe de testes para os métodos de Agenda.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 *
 */
class AgendaTest {
	/**
	 * agendaBase é um atributo para armazenar o objeto agenda, para o acesso a
	 * referência de seus métodos e futuro testes sobre eles.
	 */
	private Agenda agendaBase;

	/**
	 * Cria a agenda padrão antes de cada teste.
	 */
	@BeforeEach
	void preparaAgenda() {
		this.agendaBase = new Agenda();
	}

	// TESTES DE CADASTRAR

	/**
	 * Testa o cadastro de um contato por uma posição válida, do intervalo [1,100].
	 */
	@Test
	void testCadastraContato1() {
		assertEquals("CADASTRO REALIZADO",
				this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000"));

	}

	/**
	 * Testa o cadastro de um contato por uma posição válida (1), do intervalo
	 * [1,100], mas já preenchida para sobreescrever.
	 */
	@Test
	void testCadastraContato2() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals("CADASTRO REALIZADO", this.agendaBase.cadastraContato(1, "Pedro", "Silva", "(84) 98888-1111"));

	}

	/**
	 * Testa o cadastro de um contato já cadastrado.
	 */
	@Test
	void testCadastraContato3() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals("CONTATO JA CADASTRADO",
				this.agendaBase.cadastraContato(3, "Matheus", "Gaudencio", "(83) 99999-0000"));

	}

	/**
	 * Testa o cadastro de um contato por uma posição de limite do intervalo
	 * [1,100].
	 */
	@Test
	void testCadastraContato4() {
		assertEquals("CADASTRO REALIZADO",
				this.agendaBase.cadastraContato(100, "Matheus", "Gaudencio", "(83) 99999-0000"));

	}

	/**
	 * Testa o cadastro de um contato por uma posição acima do limite do intervalo
	 * [1,100].
	 */
	@Test
	void testCadastraContato5() {
		assertEquals("POSIÇÃO INVÁLIDA",
				this.agendaBase.cadastraContato(101, "Matheus", "Gaudencio", "(83) 99999-0000"));

	}

	/**
	 * Testa o cadastro de um contato por uma posição abaixo do limite do intervalo
	 * [1,100].
	 */
	@Test
	void testCadastraContato6() {
		assertEquals("POSIÇÃO INVÁLIDA", this.agendaBase.cadastraContato(0, "Matheus", "Gaudencio", "(83) 99999-0000"));

	}

	/**
	 * Testa o cadastro de um contato com telefone vazio.
	 */
	@Test
	void testCadastraContato7() {
		assertEquals("CONTATO INVALIDO", this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", ""));

	}

	/**
	 * Testa o cadastro de um contato com nome vazio.
	 */
	@Test
	void testCadastraContato8() {
		assertEquals("CONTATO INVALIDO", this.agendaBase.cadastraContato(1, "", "Gaudencio", "(83) 99999-0000"));

	}

	/**
	 * Testa o cadastro de um contato com nome nulo.
	 */
	@Test
	void testCadastraContato9() {
		assertEquals("CONTATO INVALIDO", this.agendaBase.cadastraContato(1, null, "Gaudencio", "(83) 99999-0000"));

	}

	/**
	 * Testa o cadastro de um contato com telefone nulo.
	 */
	@Test
	void testCadastraContato10() {
		assertEquals("CONTATO INVALIDO", this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", null));

	}

	/**
	 * Testa o cadastro de um contato com sobrenome vazio.
	 */
	@Test
	void testCadastraContato11() {
		assertEquals("CADASTRO REALIZADO", this.agendaBase.cadastraContato(10, "Danielle", null, "(83) 8596-8956"));

	}

	// TESTES DE EXIBIR
	/**
	 * Testa a exibição do contato cadastrado na posição 1.
	 */
	@Test
	void testExibirContato1() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n", this.agendaBase.exibirContato(1));

	}

	/**
	 * Testa a exibição do contato na posição sem cadastro.
	 */
	@Test
	void testExibirContato2() {
		assertEquals("POSIÇÃO INVÁLIDA", this.agendaBase.exibirContato(100));

	}

	/**
	 * Testa a exibição do contato na posição sem cadastro superior oa limite
	 * [1,100].
	 */
	@Test
	void testExibirContato3() {
		assertEquals("POSIÇÃO INVÁLIDA", this.agendaBase.exibirContato(101));

	}

	/**
	 * Testa a exibição do contato na posição inferior ao limite [1,100].
	 */
	@Test
	void testExibirContato4() {
		assertEquals("POSIÇÃO INVÁLIDA", this.agendaBase.exibirContato(0));

	}

	/**
	 * Testa a exibição do contato favoritado.
	 */
	@Test
	void testExibirContato5() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.adicionarFavorito(1, this.agendaBase.getContato(1));
		assertEquals("❤️ Matheus Gaudencio" + "\n" + "(83) 99999-0000\n", this.agendaBase.exibirContato(1));

	}

	/**
	 * Testa a exibição do contato com tags.
	 */
	@Test
	void testExibirContato6() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.aplicaTag(new String[] { "1" }, 1, "professor-ufcg");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n" + "professor-ufcg", this.agendaBase.exibirContato(1));

	}

	// TESTES DE ADICIONAR FAVORITOS
	/**
	 * Testa favoritar um contato em agenda.
	 */
	@Test
	void testAdicionarFavorito1() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals("CONTATO FAVORITADO NA POSIÇÃO 1!",
				this.agendaBase.adicionarFavorito(1, this.agendaBase.getContato(1)));

	}

	/**
	 * Testa favoritar um contato em agenda e uma posição limite do intervalo
	 * [1,10].
	 */
	@Test
	void testAdicionarFavorito2() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals("CONTATO FAVORITADO NA POSIÇÃO 10!",
				this.agendaBase.adicionarFavorito(10, this.agendaBase.getContato(1)));

	}

	/**
	 * Testa favoritar um contato em agenda e uma posição inferior ao limite do
	 * intervalo [1,10].
	 */
	@Test
	void testAdicionarFavorito3() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals("POSIÇÃO INVÁLIDA", this.agendaBase.adicionarFavorito(0, this.agendaBase.getContato(1)));

	}

	/**
	 * Testa favoritar um contato em agenda e uma posição superior ao limite do
	 * intervalo [1,10].
	 */
	@Test
	void testAdicionarFavorito4() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals("POSIÇÃO INVÁLIDA", this.agendaBase.adicionarFavorito(11, this.agendaBase.getContato(1)));

	}

	/**
	 * Testa favoritar um contato que já está na lista de favoritos.
	 */
	@Test
	void testAdicionarFavorito5() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.adicionarFavorito(1, this.agendaBase.getContato(1));
		assertEquals("CONTATO JÁ FAVORITADO", this.agendaBase.adicionarFavorito(1, this.agendaBase.getContato(1)));

	}

	// TESTES DE APLICAR TAGS

	/**
	 * Testa aplicar tags em mais de um contato.
	 */
	@Test
	void testAplicaTag1() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.cadastraContato(2, "Catarina", "Ramalho", "(83) 99111-0000");
		this.agendaBase.cadastraContato(3, "Monitoria", "UFCG", "(83) 88899-0000");
		this.agendaBase.aplicaTag(new String[] { "1", "2", "3" }, 1, "ufcg");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n" + "ufcg", this.agendaBase.exibirContato(1));
		assertEquals("Catarina Ramalho\n" + "(83) 99111-0000\n" + "ufcg", this.agendaBase.exibirContato(2));
		assertEquals("Monitoria UFCG\n" + "(83) 88899-0000\n" + "ufcg", this.agendaBase.exibirContato(3));

	}

	/**
	 * Testa aplicar tags em uma posição com tag já presente, para sobrescrita.
	 */
	@Test
	void testAplicaTag2() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.aplicaTag(new String[] { "1" }, 1, "ufcg");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n" + "ufcg", this.agendaBase.exibirContato(1));
		this.agendaBase.aplicaTag(new String[] { "1" }, 1, "cc");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n" + "cc", this.agendaBase.exibirContato(1));

	}

	/**
	 * Testa aplicar tags em uma posição de tag inválida, superior ao intervalo.
	 * [1,5].
	 */
	@Test
	void testAplicaTag3() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.aplicaTag(new String[] { "1" }, 1, "ufcg");
		this.agendaBase.aplicaTag(new String[] { "1" }, 6, "cc");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n" + "ufcg", this.agendaBase.exibirContato(1));

	}

	/**
	 * Testa aplicar tags em uma posição de tag inválida, inferior ao intervalo.
	 * [1,5].
	 */
	@Test
	void testAplicaTag4() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.aplicaTag(new String[] { "1" }, 1, "ufcg");
		this.agendaBase.aplicaTag(new String[] { "1" }, 0, "cc");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n" + "ufcg", this.agendaBase.exibirContato(1));

	}

	/**
	 * Testa aplicar tags em uma posição de contato inválida, inferior ao intervalo
	 * [1,100], só adiciona em posições válidas.
	 */
	@Test
	void testAplicaTag5() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.aplicaTag(new String[] { "0", "1" }, 1, "ufcg");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n" + "ufcg", this.agendaBase.exibirContato(1));
		assertEquals("POSIÇÃO INVÁLIDA", this.agendaBase.exibirContato(0));

	}

	/**
	 * Testa aplicar tags em uma posição de contato inválida, superior ao intervalo
	 * [1,100], só adiciona em posições válidas.
	 */
	@Test
	void testAplicaTag6() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.aplicaTag(new String[] { "101", "1" }, 1, "ufcg");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n" + "ufcg", this.agendaBase.exibirContato(1));
		assertEquals("POSIÇÃO INVÁLIDA", this.agendaBase.exibirContato(101));

	}

	/**
	 * Testa aplicar tags em contatos nulos, só adiciona em contatos existentes.
	 */
	@Test
	void testAplicaTag7() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.aplicaTag(new String[] { "100", "1" }, 1, "ufcg");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n" + "ufcg", this.agendaBase.exibirContato(1));
		assertEquals("POSIÇÃO INVÁLIDA", this.agendaBase.exibirContato(100));

	}

	/**
	 * Testa aplicar tags repetidas em posições diferentes.
	 */
	@Test
	void testAplicaTag8() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.aplicaTag(new String[] { "1" }, 1, "ufcg");
		this.agendaBase.aplicaTag(new String[] { "1" }, 2, "ufcg");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n" + "ufcg ufcg", this.agendaBase.exibirContato(1));

	}

	/**
	 * Testa aplicar o limite de 5 tags permitidas.
	 */
	@Test
	void testAplicaTag9() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.aplicaTag(new String[] { "1" }, 1, "ufcg");
		this.agendaBase.aplicaTag(new String[] { "1" }, 2, "cc");
		this.agendaBase.aplicaTag(new String[] { "1" }, 3, "prof");
		this.agendaBase.aplicaTag(new String[] { "1" }, 4, "p2");
		this.agendaBase.aplicaTag(new String[] { "1" }, 5, "lp2");
		assertEquals("Matheus Gaudencio\n" + "(83) 99999-0000\n" + "ufcg cc prof p2 lp2",
				this.agendaBase.exibirContato(1));

	}

	// TESTES DE LISTAR

	/**
	 * Testa a listagem dos contatos cadastrados.
	 */
	@Test
	void testListaContatos1() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.cadastraContato(2, "Catarina", "Ramalho", "(83) 99111-0000");
		this.agendaBase.cadastraContato(4, "Monitoria", "UFCG", "(83) 88899-0000");
		assertEquals("1 - Matheus Gaudencio\n" + "2 - Catarina Ramalho\n" + "4 - Monitoria UFCG",
				this.agendaBase.listaContatos());

	}

	/**
	 * Testa a listagem da agenda sem cadastros.
	 */
	@Test
	void testListaContatos2() {
		assertEquals("", this.agendaBase.listaContatos());

	}

	/**
	 * Testa a listagem dos contatos favoritos cadastrados.
	 */
	@Test
	void testListaFavoritos() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.cadastraContato(2, "Catarina", "Ramalho", "(83) 99111-0000");
		this.agendaBase.cadastraContato(4, "Monitoria", "UFCG", "(83) 88899-0000");
		this.agendaBase.adicionarFavorito(1, this.agendaBase.getContato(1));
		this.agendaBase.adicionarFavorito(2, this.agendaBase.getContato(2));
		this.agendaBase.adicionarFavorito(4, this.agendaBase.getContato(4));
		assertEquals("1 - Matheus Gaudencio\n" + "2 - Catarina Ramalho\n" + "4 - Monitoria UFCG",
				this.agendaBase.listaFavoritos());

	}

	/**
	 * Testa a listagem da agenda sem cadastros favoritados.
	 */
	@Test
	void testListaFavoritos2() {
		assertEquals("", this.agendaBase.listaFavoritos());

	}

	// TESTES DE PEGAR CONTATO

	/**
	 * Testa acessar um contato cadastrado na posição válida do intervalo [1,100]
	 */
	@Test
	void testGetContato1() {
		Contato contato = new Contato("Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals(this.agendaBase.getContato(1), contato);

	}

	/**
	 * Testa acessar um contato cadastrado na posição inválida do intervalo [1,100],
	 * inferior.
	 */
	@Test
	void testGetContato2() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals(this.agendaBase.getContato(0), null);

	}

	/**
	 * Testa acessar um contato cadastrado na posição inválida do intervalo [1,100],
	 * superior.
	 */
	@Test
	void testGetContato3() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals(this.agendaBase.getContato(101), null);

	}

	/**
	 * Testa acessar um contato cadastrado na posição válida do intervalo [1,100],
	 * porém sem contato cadastrado.
	 */
	@Test
	void testGetContato4() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals(this.agendaBase.getContato(50), null);

	}
	// TESTES DE REMOVER CONTATO

	/**
	 * Testa remover um contato cadastrado na posição válida do intervalo [1,100]
	 */
	@Test
	void testRemoverContato1() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals(this.agendaBase.removeContatos(new String[] { "1" }), true);

	}

	/**
	 * Testa remover um contato cadastrado na posição válida do limite intervalo
	 * [1,100]
	 */
	@Test
	void testRemoverContato2() {
		this.agendaBase.cadastraContato(100, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertTrue(this.agendaBase.removeContatos(new String[] { "100" }));

	}

	/**
	 * Testa remover um contato cadastrado na posição inválida inferior ao intervalo
	 * [1,100]
	 */
	@Test
	void testRemoverContato3() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertFalse(this.agendaBase.removeContatos(new String[] { "0" }));

	}

	/**
	 * Testa remover um contato cadastrado na posição inválida superior ao intervalo
	 * [1,100]
	 */
	@Test
	void testRemoverContato4() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertFalse(this.agendaBase.removeContatos(new String[] { "101" }));

	}

	/**
	 * Testa remover um contato não cadastrado
	 */
	@Test
	void testRemoverContato5() {
		assertFalse(this.agendaBase.removeContatos(new String[] { "1" }));

	}

	/**
	 * Testa remover mais de um contato cadastrado
	 */
	@Test
	void testRemoverContato6() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.cadastraContato(2, "Catarina", "Ramalho", "(83) 11999-0000");
		this.agendaBase.cadastraContato(3, "Monitoria", "UFCG", "(83) 11999-0000");
		assertEquals("1 - Matheus Gaudencio\n" + "2 - Catarina Ramalho\n" + "3 - Monitoria UFCG",
				this.agendaBase.listaContatos());
		assertTrue(this.agendaBase.removeContatos(new String[] { "1", "2", "3" }));
		assertEquals("", this.agendaBase.listaContatos());
	}

	/**
	 * Testa remover mais de um contato cadastrado, porém com uma posição inválida e
	 * parando de remover os demais imediatamente para o retorno do menu.
	 */
	@Test
	void testRemoverContato7() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.cadastraContato(2, "Catarina", "Ramalho", "(83) 11999-0000");
		this.agendaBase.cadastraContato(3, "Monitoria", "UFCG", "(83) 11999-0000");
		assertEquals("1 - Matheus Gaudencio\n" + "2 - Catarina Ramalho\n" + "3 - Monitoria UFCG",
				this.agendaBase.listaContatos());
		assertFalse(this.agendaBase.removeContatos(new String[] { "1", "2", "0", "3" }));
		assertEquals("3 - Monitoria UFCG", this.agendaBase.listaContatos());
	}

	// TESTES REMOVER FAVORITO

	/**
	 * Testa remover um contato favoritado
	 */
	@Test
	void testRemoverFavorito1() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.cadastraContato(2, "Catarina", "Ramalho", "(83) 99111-0000");
		this.agendaBase.cadastraContato(4, "Monitoria", "UFCG", "(83) 88899-0000");
		this.agendaBase.adicionarFavorito(1, this.agendaBase.getContato(1));
		this.agendaBase.adicionarFavorito(2, this.agendaBase.getContato(2));
		this.agendaBase.adicionarFavorito(4, this.agendaBase.getContato(4));
		assertEquals("1 - Matheus Gaudencio\n" + "2 - Catarina Ramalho\n" + "4 - Monitoria UFCG",
				this.agendaBase.listaFavoritos());
		this.agendaBase.removeFavorito(this.agendaBase.getContato(1));
		assertEquals("2 - Catarina Ramalho\n" + "4 - Monitoria UFCG", this.agendaBase.listaFavoritos());

	}

	/**
	 * Testa remover um contato que não é favorito
	 */
	@Test
	void testRemoverFavorito2() {
		this.agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		this.agendaBase.cadastraContato(2, "Catarina", "Ramalho", "(83) 99111-0000");
		this.agendaBase.cadastraContato(4, "Monitoria", "UFCG", "(83) 88899-0000");
		this.agendaBase.adicionarFavorito(1, this.agendaBase.getContato(1));
		this.agendaBase.adicionarFavorito(2, this.agendaBase.getContato(2));
		this.agendaBase.adicionarFavorito(4, this.agendaBase.getContato(4));
		assertEquals("1 - Matheus Gaudencio\n" + "2 - Catarina Ramalho\n" + "4 - Monitoria UFCG",
				this.agendaBase.listaFavoritos());
		this.agendaBase.removeFavorito(this.agendaBase.getContato(1));
		assertEquals("2 - Catarina Ramalho\n" + "4 - Monitoria UFCG", this.agendaBase.listaFavoritos());
		this.agendaBase.removeFavorito(this.agendaBase.getContato(1));
		assertEquals("2 - Catarina Ramalho\n" + "4 - Monitoria UFCG", this.agendaBase.listaFavoritos());

	}

}
