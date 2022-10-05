package com.matheusgr.apresentacao;

import com.matheusgr.lunr.documento.Documento;

/**
 * Apresentacão Caixa Alta é responsável pela apresentação de todo um documento
 * em caixa alta, implementando a interface Apresentacao.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 */
public class ApresentacaoCaixaAlta implements Apresentacao {
	/**
	 * documento é o documento referenciado para a apresentação.
	 */
	private Documento documento;

	/**
	 * Inicializa a apresentação de um documento referenciado para a apresentação em
	 * caixa alta.
	 * 
	 * @param documento Documento a ser utilizado.
	 */
	public ApresentacaoCaixaAlta(Documento documento) {
		this.documento = documento;
	}

	/**
	 * Sobrescreve o método de apresenta() da interface, operando sobre o original
	 * do documento e transformando cada linha para caixa alta.
	 * 
	 * @return apresentacao em formato textual em caixa alta.
	 */
	@Override
	public String apresenta() {
		String[] linhas = this.documento.getOriginal().split("\n");
		String apresentacao = "";
		for (int i = 0; i < linhas.length; i++) {
			apresentacao += linhas[i].toUpperCase() + "\n";
		}
		return apresentacao.trim();
	}

}
