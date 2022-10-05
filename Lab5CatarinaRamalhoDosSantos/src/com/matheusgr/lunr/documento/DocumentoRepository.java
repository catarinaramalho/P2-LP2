package com.matheusgr.lunr.documento;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
/**
 * Repositório de documentos. O repositório pode ter operações simples de busca,
 * mas a lógica de ranking, limitação e ordenação deve ficar em outra entidade.
 * 
 * O ID de um documento é único.
 */
class DocumentoRepository {
	
	private Map<String, Documento> documentos;
	private ValidadorDocumentos validador;

	/**
	 * Construção padrão do repositório de documentos.
	 */
	DocumentoRepository() {
		this.documentos = new HashMap<>();
		this.validador = new ValidadorDocumentos();
	}

	/**
	 * Adiciona o documento. O documento é validado para garantir a consistência do
	 * documento (sem termos e id vazios).
	 * 
	 * @param d Documento a ser adicionado.
	 */
	void adiciona(Documento d) {
		this.validador.validacao(d.getId(), d.getTexto());
		this.documentos.put(d.getId(),d);
	}

	/**
	 * Recupera um documento do repositório.
	 * 
	 * @param id ID do documento.
	 * @return Documento, caso exista.
	 */
	Optional<Documento> recupera(String id) {
		Documento doc = null;
		this.validador.validacao(id);
		doc = this.documentos.get(id);
		return Optional.ofNullable(doc);
	}

	/**
	 * Retorna o total de documentos cadastrados.
	 * 
	 * @return O total de documentos cadastrados.
	 */
	int totalDocumentos() {
		return this.documentos.size();
	}

	/**
	 * Realiza uma busca pelos termos.
	 * 
	 * @param termo Termo a ser buscado.
	 * @return Conjunto de documentos com o termo.
	 */
	public Set<Documento> busca(String termo) {
		Set<Documento> documentos = new HashSet<>();
		for (String key: this.documentos.keySet()) {
			String[] TextosDocumento = this.documentos.get(key).getTexto();
			for (int i = 0; i < TextosDocumento.length; i++) {
				if (TextosDocumento[i].equals(termo)) {
					documentos.add(this.documentos.get(key));
					break;
				}
			}		
		}
		return documentos;
	}
	
	/**
	 * Realiza uma busca apenas os documentos que possuem todos os metadados indicados.
	 * 
	 * @param metadados Metadados a serem buscados.
	 * @return Conjunto de documentos que possuem todos os metadados indicados.
	 */
	public Set<Documento> busca(Map<String, String> metadados) {
		Set<Documento> documentos = new HashSet<>();
		for (String key: this.documentos.keySet()) {
			boolean  diferente = false;
			for (String metadado: metadados.keySet()) {
				if(!metadados.get(metadado).equals(this.documentos.get(key).getMetadados().get(metadado))) {
					diferente = true;
					break;
				}
			} if (!diferente) {
				documentos.add(this.documentos.get(key));
			}
		}
		return documentos;
	}
}
