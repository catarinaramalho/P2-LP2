package com.matheusgr.lunr;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * ApresentacaoTest é responsável pelos testes de Apresentação, herda a
 * BaseTest.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 *
 */
class ApresentacaoTest extends BaseTest {

	/**
	 * Testa a apresentação de um documento em caixa alta.
	 */
	@Test
	void testApresentacaoCaixaAlta() {
		documentoController.adicionaDocumentoTxt("1",
				"Despencados de voos cansativos\nComplicados e pensativos\nMachucados após tantos crivos\nAmuados, reflexivos\nE dá-lhe antidepressivos");
		assertEquals(apresentacaoController.apresenta("1", "CAIXA ALTA"),
				("DESPENCADOS DE VOOS CANSATIVOS\nCOMPLICADOS E PENSATIVOS\nMACHUCADOS APÓS TANTOS CRIVOS\nAMUADOS, REFLEXIVOS\nE DÁ-LHE ANTIDEPRESSIVOS"));
	}

	/**
	 * Testa a apresentação das 5 primeiras linhas de um documento.
	 */
	@Test
	void testApresentacaoPrimeirasLinhas() {
		documentoController.adicionaDocumentoTxt("1",
				"Despencados de voos cansativos\nComplicados e pensativos\nMachucados após tantos crivos\nAmuados, reflexivos\nE dá-lhe antidepressivos\nAcanhados entre discos e livros");
		assertEquals(apresentacaoController.apresenta("1", "Primeiras Linhas"),
				("Despencados de voos cansativos\nComplicados e pensativos\nMachucados após tantos crivos\nAmuados, reflexivos\nE dá-lhe antidepressivos"));
	}

	/**
	 * Testa a apresentação das primeiras linhas de um documento com menos de 5
	 * linhas.
	 */
	@Test
	void testApresentacaoPrimeirasLinhasTextosComMenosDeCincoLinhas() {
		documentoController.adicionaDocumentoTxt("1", "Despencados de voos cansativos");
		assertEquals(apresentacaoController.apresenta("1", "Primeiras Linhas"), ("Despencados de voos cansativos"));
	}

	/**
	 * Testa a apresentação das 5 últimas linhas de um documento.
	 */
	@Test
	void testApresentacaoUltimasLinhas() {
		documentoController.adicionaDocumentoTxt("1",
				"Despencados de voos cansativos\nComplicados e pensativos\nMachucados após tantos crivos\nAmuados, reflexivos\nE dá-lhe antidepressivos\nAcanhados entre discos e livros");
		assertEquals(apresentacaoController.apresenta("1", "Ultimas Linhas"),
				"Complicados e pensativos\nMachucados após tantos crivos\nAmuados, reflexivos\nE dá-lhe antidepressivos\nAcanhados entre discos e livros");
	}

	/**
	 * Testa a apresentação das 5 últimas linhas de um documento com menos de 5
	 * linhas.
	 */
	@Test
	void testApresentacaoPrimeirasUltimasTextosComMenosDeCincoLinhas() {
		documentoController.adicionaDocumentoTxt("1", "Despencados de voos cansativos");
		assertEquals(apresentacaoController.apresenta("1", "ULTIMAS Linhas"), ("Despencados de voos cansativos"));
	}

	/**
	 * Testa a apresentação com o tipo não compatível de apresentação.
	 */
	@Test
	void testApresentacaoTipoNaoCompativel() {
		documentoController.adicionaDocumentoTxt("1", "Despencados de voos cansativos");
		assertEquals(apresentacaoController.apresenta("1", "Só Vogais"), ("Tipo não compatível!"));
	}

}