package com.matheusgr.apresentacao;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

/**
 * Componente para tratamento da lógica de negócio relativa a apresentação de
 * documentos.
 */
public class ApresentacaoService {

	private DocumentoService documentoService;

	/**
	 * Inicialização da lógica de serviço.
	 * 
	 * @param documentoService DocumentoService a ser utilizado pelo
	 *                         ApresentacaoService.
	 */
	public ApresentacaoService(DocumentoService documentoService) {
		this.documentoService = documentoService;
	}

	/**
	 * Realiza a apresentação do documento indicado.
	 * 
	 * @param docId           Documento a ser apresentado.
	 * @param tipoApresentacao modo de apresentacao a ser aplicado sobre o
	 *                         documento.
	 */
	public String apresenta(String docId, String tipoApresentacao) {
		Documento doc = this.documentoService.recuperaDocumento(docId).get();
		Apresentacao apresentacao;
		switch (tipoApresentacao.toUpperCase()) {
		case "PRIMEIRAS LINHAS":
			apresentacao = new ApresentacaoPrimeirasLinhas(doc);
			return apresentacao.apresenta();
		case "ULTIMAS LINHAS":
			apresentacao = new ApresentacaoUltimasLinhas(doc);
			return apresentacao.apresenta();
		case "CAIXA ALTA":
			apresentacao = new ApresentacaoCaixaAlta(doc);
			return apresentacao.apresenta();
		default:
			return "Tipo não compatível!";
		}

	}

}
