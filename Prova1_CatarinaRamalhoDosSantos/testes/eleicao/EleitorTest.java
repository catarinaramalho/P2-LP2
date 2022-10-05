package eleicao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EleitorTest {
	private Eleitor eleitorBase;

	@BeforeEach
	void preparaEleitor() {
		this.eleitorBase = new Eleitor("111.111.111-11", "Catarina");
	}

	// Testes de Criação de Eleitor

	@Test
	void testEleitorNomeNulo() {
		try {
			this.eleitorBase = new Eleitor("111.111.111-11", null);
			fail("Era esperada uma exceção ao passar nome nulo");
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testEleitorNomeVazio() {
		try {
			this.eleitorBase = new Eleitor("111.111.111-11", "");
			fail("Era esperada uma exceção ao passar nome nulo");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testEleitorCpfNulo() {
		try {
			this.eleitorBase = new Eleitor(null, "Catarina");
			fail("Era esperada uma exceção ao passar nome nulo");
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testEleitorCpfVazio() {
		try {
			this.eleitorBase = new Eleitor("", "Catarina");
			fail("Era esperada uma exceção ao passar nome nulo");
		} catch (IllegalArgumentException iae) {

		}
	}

	// Testes de Representação Textual
	@Test
	void testRepresentacaoTextual() {
		assertEquals("111.111.111-11 - Catarina - VOTO NÃO DEPOSITADO", this.eleitorBase.toString());

	}

	// Testes de Alterar e Acessar o Status

	@Test
	void testSetStatus() {
		this.eleitorBase.setStatus("VOTO DEPOSITADO");
		assertEquals("111.111.111-11 - Catarina - VOTO DEPOSITADO", this.eleitorBase.toString());

	}

	@Test
	void testSetStatusNulo() {
		try {
			this.eleitorBase.setStatus(null);
			fail("Era esperada uma exceção ao passar status nulo");
		} catch (NullPointerException npe) {

		}
	}

	@Test
	void testSetStatusVazio() {
		try {
			this.eleitorBase.setStatus("");
			fail("Era esperada uma exceção ao passar status vazio");
		} catch (IllegalArgumentException iae) {

		}
	}

	@Test
	void testGetStatus() {
		this.eleitorBase.setStatus("VOTO DEPOSITADO");
		assertEquals("VOTO DEPOSITADO", this.eleitorBase.getStatus());

	}

	// Testes Acessar CPF
	@Test
	void testGetCpf() {
		assertEquals("111.111.111-11", this.eleitorBase.getCpf());

	}

	// Testes do método equals()
	@Test
	void testEqualsCpfIguais() {
		Eleitor outroEleitor = new Eleitor("111.111.111-11", "Gaudencio");
		assertTrue(this.eleitorBase.equals(outroEleitor));
	}

	@Test
	void testEqualsCpfDiferentes() {
		Eleitor outroEleitor = new Eleitor("111.111.111-12", "Gaudencio");
		assertFalse(this.eleitorBase.equals(outroEleitor));
	}

	@Test
	void testEqualsNull() {
		assertFalse(this.eleitorBase.equals(null));
	}

	@Test
	void testEqualsObjetosDiferentes() {
		assertFalse(this.eleitorBase.equals(new Object()));
	}

	@Test
	void testEqualsObjetosIguais() {
		assertTrue(this.eleitorBase.equals(this.eleitorBase));
	}

	// Testes do método hashCode()
	@Test
	void testHashCodeCpfIguais() {
		Eleitor outroEleitor = new Eleitor("111.111.111-11", "Gaudencio");
		assertEquals(this.eleitorBase.hashCode(), outroEleitor.hashCode());
	}

	@Test
	void testHashCodeCPFDiferentes() {
		Eleitor outroEleitor = new Eleitor("111.111.111-21", "Gaudencio");
		assertNotEquals(this.eleitorBase.hashCode(), outroEleitor.hashCode());
	}

}
