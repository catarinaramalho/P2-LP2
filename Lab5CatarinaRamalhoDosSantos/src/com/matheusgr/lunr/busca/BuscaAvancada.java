package com.matheusgr.lunr.busca;

import java.util.HashMap;
import java.util.Map;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

/**
 * BuscaAvancada é responsável pela busca avançada do sistema, mediante os
 * metadados indicados, implementando a interface Busca.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 */
public class BuscaAvancada implements BuscaInterface {
	/**
	 * metadados é uma mapa de metadados do documento.
	 */
	private Map<String, String> metadados;

	/**
	 * Inicializa a busca avançada, inicializando a validação de metadados e os
	 * metadados a serem referenciados na busca.
	 * 
	 * @param metadado Metadados a serem referenciados.
	 */
	public BuscaAvancada(Map<String, String> metadados) {
		(new ValidadorBusca()).valida(metadados);
		this.metadados = metadados;
	}

	/**
	 * Sobrescreve o método de busca() da interface, buscando todos os documentos
	 * que possuem todos os metadados passados no parâmetro.
	 * 
	 * @param ds Documento Service responsável pelas operações básicas do sistema.
	 * @return respostaDocumento Um mapa de documentos compatíveis com a busca.
	 */
	@Override
	public Map<Documento, Integer> busca(DocumentoService ds) {
		Map<Documento, Integer> respostaDocumento = new HashMap<>();
		for (String metadado : this.metadados.keySet()) {
			if (this.metadados.get(metadado).isBlank()) {
				continue;
			}
			for (Documento d : ds.busca(this.metadados)) {
				respostaDocumento.put(d, respostaDocumento.getOrDefault(d, 0) + 1);
			}
		}
		return respostaDocumento;
	}

	/**
	 * Sobrescreve o método de descreveConculta() da interface, mostrando os
	 * metadados da busca.
	 * 
	 * @return resultado Uma matriz que descreve a consulta dos metadados da busca.
	 */
	@Override
	public String[][] descreveConsulta() {
		String[][] resultado = new String[this.metadados.size()][];
		int i = 0;
		for (String metadado : this.metadados.keySet()) {
			resultado[i] = new String[] { "METADADO " + (i + 1) + " " + metadado, this.metadados.get(metadado) };
			i++;
		}
		return resultado;
	}
}
