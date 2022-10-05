package com.matheusgr.lunr;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * SimilaridadeTest é responsável pelos testes de similaridade, herda a
 * BaseTest.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 *
 */
class SimilaridadeTest extends BaseTest {
	/**
	 * Testa a similaridade entre documentos, de 20% (0.2).
	 */
	@Test
	void testSimilaridade() {
		documentoController.adicionaDocumentoTxt("1", "Uma casa feliz é uma casa bonita");
		documentoController.adicionaDocumentoTxt("2", "Um dia feliz é um bom dia");
		assertEquals(similaridadeController.similaridade("1", "2"), (0.2));
	}

	/**
	 * Testa a falta similaridade entre documentos, de 0%(0.0).
	 */
	@Test
	void testSemSimilaridade() {
		documentoController.adicionaDocumentoTxt("1", "Uma casa feliz é uma casa bonita");
		documentoController.adicionaDocumentoTxt("2", "Stranger Things 4");
		assertEquals(similaridadeController.similaridade("1", "2"), (0.0));
	}

	/**
	 * Testa a similaridade total entre documentos, de 100%(1.0).
	 */
	@Test
	void testTotalSimilaridade() {
		documentoController.adicionaDocumentoTxt("1", "Uma casa feliz é uma casa bonita");
		documentoController.adicionaDocumentoTxt("2", "Uma casa feliz é uma casa bonita");
		assertEquals(similaridadeController.similaridade("1", "2"), (1.0));
	}

}
