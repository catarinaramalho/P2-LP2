package com.matheusgr.lunr;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.matheusgr.lunr.busca.BuscaAvancada;
import com.matheusgr.lunr.documento.DocumentoDTO;
/**
 * BuscaTest é responsável pelos testes de busca
 *
 */
class BuscaTest extends BaseTest {
	/**
	 * Testa termo ausente
	 */
	@Test
	void testAusente() {
		DocumentoDTO[] busca = this.buscaController.busca(new String[] { "ABCDEFGHI", "JKLMNOPQRST" });
		assertEquals(0, busca.length, "Sem resultados de busca");
	}

	/**
	 * Testa termo único
	 */
	@Test
	void testTermoUnico() {
		DocumentoDTO[] busca = this.buscaController.busca(new String[] { "public" });
		assertEquals(1, busca.length, "Apenas 1 resultado");
		assertEquals(JAVA_ID, busca[0].getId(), "Arquivo java");
	}

	/**
	 * Testa termo comum
	 */
	@Test
	void testTermoComum() {
		DocumentoDTO[] busca = this.buscaController.busca(new String[] { "use" });
		assertEquals(4, busca.length, "Todos os documentos");
		Set<String> ids = Stream.of(busca).map(DocumentoDTO::getId).collect(Collectors.toSet());
		Set<String> expectedIds = Stream.of(new String[] { TEXTO1_ID, TEXTO2_ID, HTML_ID, JAVA_ID })
				.collect(Collectors.toSet());
		assertEquals(expectedIds, ids);
	}

	/**
	 * Testa o termo comum e termo raro
	 */
	@Test
	void testTermoComumETermoRaro() {
		DocumentoDTO[] busca = this.buscaController.busca(new String[] { "use", "public" });
		assertEquals(4, busca.length, "Todos os documentos");
		Set<String> ids = Stream.of(busca).map(DocumentoDTO::getId).collect(Collectors.toSet());
		Set<String> expectedIds = Stream.of(new String[] { TEXTO1_ID, TEXTO2_ID, HTML_ID, JAVA_ID })
				.collect(Collectors.toSet());
		assertEquals(expectedIds, ids);
		assertEquals(JAVA_ID, busca[0].getId(), "Arquivo java");
	}

	/**
	 * Testa o Histórico de Busca
	 */
	@Test
	void testHistoricoDeBusca() {
		this.buscaController.busca(new String[] { "public" });
		this.buscaController.busca(new String[] { "use" });
		this.buscaController.busca(new String[] { "use", "public" });

		String[][] historicoDepuracao = this.buscaController.recuperaHistoricoDepuracao(0);
		String[] historicoIds = this.buscaController.recuperaHistoricoIds(0);
		assertEquals(1, historicoDepuracao.length);
		assertArrayEquals(new String[] { "TERMO 1", "public" }, historicoDepuracao[0]);
		assertArrayEquals(new String[] { JAVA_ID }, historicoIds);

		historicoDepuracao = this.buscaController.recuperaHistoricoDepuracao(1);
		historicoIds = this.buscaController.recuperaHistoricoIds(1);
		assertEquals(1, historicoDepuracao.length);
		assertArrayEquals(new String[] { "TERMO 1", "use" }, historicoDepuracao[0]);
		assertEquals(4, historicoIds.length);

		historicoDepuracao = this.buscaController.recuperaHistoricoDepuracao(2);
		historicoIds = this.buscaController.recuperaHistoricoIds(2);
		assertEquals(2, historicoDepuracao.length);
		assertArrayEquals(new String[] { "TERMO 1", "use" }, historicoDepuracao[0]);
		assertArrayEquals(new String[] { "TERMO 2", "public" }, historicoDepuracao[1]);

	}
	
	// TESTES PARA BUSCA AVANÇADA (FUNCIONALIDADE ADICIONADA)
	/**
	 * Testa a Busca Avançada
	 */
	@Test
	void testBuscaAvancada() {
		Map<String, String> metadadosBuscados = new HashMap<>();
		metadadosBuscados.put("TIPO", "txt");
		DocumentoDTO[] busca = this.buscaController.busca(metadadosBuscados);
		assertEquals(2, busca.length, "Todos os documentos de texto");

		Set<String> ids = Stream.of(busca).map(DocumentoDTO::getId).collect(Collectors.toSet());
		Set<String> expectedIds = Stream.of(new String[] { TEXTO1_ID, TEXTO2_ID }).collect(Collectors.toSet());
		assertEquals(expectedIds, ids);
	}

	/**
	 * Testa a Busca Avançada com 2 metadados
	 */
	@Test
	void testBuscaAvancadaDoisMetadados() {
		Map<String, String> metadadosBuscados = new HashMap<>();
		metadadosBuscados.put("TIPO", "txt");
		metadadosBuscados.put("LINHAS", "1");
		DocumentoDTO[] busca = this.buscaController.busca(metadadosBuscados);
		assertEquals(1, busca.length, "Todos os documentos de texto");
		assertEquals(TEXTO1_ID, busca[0].getId());
	}

	/**
	 * Testa a Busca Avançada com um metadado null
	 */
	@Test
	void testBuscaAvancadaMetadadoNull() {
		Map<String, String> metadadosBuscados = null;
		try {
			this.buscaController.busca(metadadosBuscados);
		} catch (NullPointerException npe) {
		}
	}

	/**
	 * Testa a Busca Avançada com um metadado vazio
	 */
	@Test
	void testBuscaAvancadaMetadadoVazio() {
		Map<String, String> metadadosBuscados = new HashMap<>();
		try {
			this.buscaController.busca(metadadosBuscados);
		} catch (IllegalArgumentException iae) {
		}
	}

	/**
	 * Testa a Busca Avançada com 2 metadados, sendo um vazio
	 */
	@Test
	void testBuscaAvancadaDoisMetadadosComUmMetadadoVazio() {
		Map<String, String> metadadosBuscados = new HashMap<>();
		metadadosBuscados.put("TIPO", " ");
		metadadosBuscados.put("LINHAS", "1");
		DocumentoDTO[] busca = this.buscaController.busca(metadadosBuscados);
		assertEquals(0, busca.length, "Todos os documentos de texto");
	}

	/**
	 * Testa o método Descreve Consulta na busca Avançada
	 */
	@Test
	void testDescreveConsultaBuscaAvancada() {
		Map<String, String> metadadosBuscados = new HashMap<>();
		metadadosBuscados.put("TIPO", "txt");
		metadadosBuscados.put("LINHAS", "1");
		BuscaAvancada buscaAvancada = new BuscaAvancada(metadadosBuscados);
		String[][] resultado = { { "METADADO 1 LINHAS", "1" }, { "METADADO 2 TIPO", "txt" } };
		assertTrue(Arrays.deepEquals(buscaAvancada.descreveConsulta(), resultado));

	}

}
