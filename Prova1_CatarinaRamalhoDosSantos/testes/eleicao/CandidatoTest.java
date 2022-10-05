package eleicao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CandidatoTest {

	private Candidato candidatoBase;

	@BeforeEach
	void preparaCandidato() {
		this.candidatoBase = new Candidato(0, "Catarina");
	}

	// Testes de Criação de Candidato

	@Test
	void testCandidatoNomeNulo() {
		try {
			this.candidatoBase = new Candidato(1, null);
			fail("Era esperada uma exceção ao passar nome nulo");
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void tesCandidatoNomeVazio() {
		try {
			this.candidatoBase = new Candidato(1, "");
			fail("Era esperada uma exceção ao passar nome nulo");
		} catch (IllegalArgumentException iae) {

		}
	}

	// Testes Representação Textual
	@Test
	void testRepresentacaoTextual() {
		assertEquals("0 - Catarina - Quantidade de Votos Recebidos: 0", this.candidatoBase.toString());

	}

	// Testes de Acesso ao Nome
	@Test
	void testGetNome() {
		assertEquals("Catarina", this.candidatoBase.getNome());

	}

	// Testes de registar votos
	@Test
	void testRegistrarVoto() {
		this.candidatoBase.registraVoto();
		assertEquals("0 - Catarina - Quantidade de Votos Recebidos: 1", this.candidatoBase.toString());
		this.candidatoBase.registraVoto();
		assertEquals("0 - Catarina - Quantidade de Votos Recebidos: 2", this.candidatoBase.toString());
	}

	// Testes de zerar votos
	@Test
	void testZerarVotos() {
		this.candidatoBase.registraVoto();
		assertEquals("0 - Catarina - Quantidade de Votos Recebidos: 1", this.candidatoBase.toString());
		this.candidatoBase.registraVoto();
		assertEquals("0 - Catarina - Quantidade de Votos Recebidos: 2", this.candidatoBase.toString());
		this.candidatoBase.zeraQuantidadeVotosRecebidos();
		assertEquals("0 - Catarina - Quantidade de Votos Recebidos: 0", this.candidatoBase.toString());

	}

	// Testes do método equals()
	@Test
	void testEqualsNomesIguais() {
		Candidato outroCandidato = new Candidato(1, "Catarina");
		assertTrue(this.candidatoBase.equals(outroCandidato));
	}

	@Test
	void testEqualsNomesDiferentes() {
		Candidato outroCandidato = new Candidato(1, "Carolina");
		assertFalse(this.candidatoBase.equals(outroCandidato));
	}

	@Test
	void testEqualsNull() {
		assertFalse(this.candidatoBase.equals(null));
	}

	@Test
	void testEqualsObjetosDiferentes() {
		assertFalse(this.candidatoBase.equals(new Object()));
	}

	@Test
	void testEqualsObjetosIguais() {
		assertTrue(this.candidatoBase.equals(this.candidatoBase));
	}

	// Testes do método hashCode()
	@Test
	void testHashCodeNomesIguais() {
		Candidato outroCandidato = new Candidato(1, "Catarina");
		assertEquals(this.candidatoBase.hashCode(), outroCandidato.hashCode());
	}

	@Test
	void testHashCodeNomesDiferentes() {
		Candidato outroCandidato = new Candidato(1, "Carolina");
		assertNotEquals(this.candidatoBase.hashCode(), outroCandidato.hashCode());
	}

	// Testar Acessar a quantidade de votos
	@Test
	void testGetQuantidadeVotosRecebidos() {
		assertEquals(0, this.candidatoBase.getQuantidadeVotosRecebidos());
	}

}
