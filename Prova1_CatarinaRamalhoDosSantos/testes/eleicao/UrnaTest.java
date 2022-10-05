package eleicao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UrnaTest {

	private Urna urnaBase;

	@BeforeEach
	void preparaUrna() {
		this.urnaBase = new Urna(10, 4);
	}

	// Testes de Representação Textual da Urna
	@Test
	void testRepresentacaoTextual() {
		assertEquals(
				"Quantidade de Eleitores Permitidos: 10 - Quantidade de Candidatos Permitidos: 4 - NÃO INICIADA - Quantidade de Votos Depositados: 0",
				this.urnaBase.toString());

	}

	// Testes de Adicionar Eleitor
	@Test
	void testAdicionaEleitorComUrnaEmAndamento() {
		this.urnaBase.setStatus("EM ANDAMENTO");
		assertEquals("Fora do Período de Cadastro", this.urnaBase.AdicionaEleitor("111.111.111-11", "Catarina"));
	}

	@Test
	void testAdicionaEleitorComUrnaEncerrada() {
		this.urnaBase.setStatus("ENCERRADA");
		assertEquals("Fora do Período de Cadastro", this.urnaBase.AdicionaEleitor("111.111.111-11", "Catarina"));
	}

	@Test
	void testAdicionaEleitorComNomeNulo() {
		try {
			this.urnaBase.AdicionaEleitor("111.111.111-11", null);
			fail("Era esperada uma exceção ao passar nome nulo");
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testAdicionaEleitorComNomeVazio() {
		try {
			this.urnaBase.AdicionaEleitor("111.111.111-11", "");
			fail("Era esperada uma exceção ao passar nome vazio");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testAdicionaEleitorComCpfNulo() {
		try {
			this.urnaBase.AdicionaEleitor(null, "Catarina");
			fail("Era esperada uma exceção ao passar cpf nulo");
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testAdicionaEleitorComCpfVazio() {
		try {
			this.urnaBase.AdicionaEleitor("", "Catarina");
			fail("Era esperada uma exceção ao passar cpf vazio");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testAdicionaEleitorComCpfRepetido() {
		assertEquals("Eleitor Cadastrado corretamente", this.urnaBase.AdicionaEleitor("111.111.111-11", "Catarina"));
		assertEquals("CPF já cadastrado - Eleitor não adicionado",
				this.urnaBase.AdicionaEleitor("111.111.111-11", "Gaudencio"));
	}

	@Test
	void testAdicionaEleitorExcedido() {
		this.urnaBase.AdicionaEleitor("156.156.156-01", "A");
		this.urnaBase.AdicionaEleitor("156.151.156-02", "B");
		this.urnaBase.AdicionaEleitor("156.152.156-03", "C");
		this.urnaBase.AdicionaEleitor("156.153.156-04", "D");
		this.urnaBase.AdicionaEleitor("156.156.156-05", "E");
		this.urnaBase.AdicionaEleitor("156.156.156-06", "F");
		this.urnaBase.AdicionaEleitor("156.156.156-07", "G");
		this.urnaBase.AdicionaEleitor("156.156.156-08", "H");
		this.urnaBase.AdicionaEleitor("156.156.156-09", "I");
		this.urnaBase.AdicionaEleitor("156.156.156-10", "J");
		assertEquals("Eleitor Não Cadastrado", this.urnaBase.AdicionaEleitor("111.111.111-11", "Catarina"));
	}

	// Testes de Adicionar Candidato
	@Test
	void testAdicionaCandidatoComUrnaEmAndamento() {
		this.urnaBase.setStatus("EM ANDAMENTO");
		assertEquals(-1, this.urnaBase.AdicionaCandidato("Catarina"));
	}

	@Test
	void testAdicionaCandidatoComUrnaEncerrada() {
		this.urnaBase.setStatus("ENCERRADA");
		assertEquals(-1, this.urnaBase.AdicionaCandidato("Catarina"));
	}

	@Test
	void testAdicionaCandidatoComNomeNulo() {
		try {
			this.urnaBase.AdicionaCandidato(null);
			fail("Era esperada uma exceção ao passar nome nulo");
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testAdicionaCandidatoComNomeVazio() {
		try {
			this.urnaBase.AdicionaCandidato("");
			fail("Era esperada uma exceção ao passar nome vazio");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testAdicionaCandidatoExcedido() {
		this.urnaBase.AdicionaCandidato("A");
		this.urnaBase.AdicionaCandidato("B");
		this.urnaBase.AdicionaCandidato("C");
		this.urnaBase.AdicionaCandidato("D");

		assertEquals(-1, this.urnaBase.AdicionaCandidato("Catarina"));
	}

	@Test
	void testAdicionaCandidatoRepetido() {
		this.urnaBase.AdicionaCandidato("A");
		this.urnaBase.AdicionaCandidato("B");
		this.urnaBase.AdicionaCandidato("Catarina");
		assertEquals(-1, this.urnaBase.AdicionaCandidato("Catarina"));
	}

	@Test
	void testAdicionaCandidato() {
		this.urnaBase.AdicionaCandidato("A");
		this.urnaBase.AdicionaCandidato("B");
		this.urnaBase.AdicionaCandidato("D");

		assertEquals(3, this.urnaBase.AdicionaCandidato("Catarina"));
	}

	// Testar o método encerrarVotacao()
	@Test
	void testEncerrarVotacao() {
		this.urnaBase.setStatus("EM ANDAMENTO");
		this.urnaBase.encerrarVotacao();
		assertEquals("ENCERRADA", this.urnaBase.getStatus());
	}

	@Test
	void testEncerrarVotacaoStatusErrado() {
		this.urnaBase.encerrarVotacao();
		assertNotEquals("ENCERRADA", this.urnaBase.getStatus());
	}

	// Testes de iniciar a votação
	@Test
	void testIniciarVotacaoComUrnaEmAndamento() {
		this.urnaBase.setStatus("EM ANDAMENTO");
		this.urnaBase.iniciarVotacao();
		assertEquals("Não pode iniciar a votação", this.urnaBase.iniciarVotacao());
	}

	@Test
	void testIniciarVotacaoComUrnaEncerrada() {
		this.urnaBase.setStatus("ENCERRADA");
		assertEquals("Não pode iniciar a votação", this.urnaBase.iniciarVotacao());
	}
	

}
