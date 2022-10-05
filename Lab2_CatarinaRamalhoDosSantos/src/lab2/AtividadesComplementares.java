package lab2;

/**
 * A classe AtividadesComplementares é uma representação das atividades
 * extra-curriculares, com dados sobre o tempo de projetos, estágios e cursos
 * para a obtenção de créditos.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 *
 */
public class AtividadesComplementares {
	/**
	 * horasEstagio é um atributo que comporta um array e seus valores serão as
	 * horas dedicadas a cada estágio pelo aluno, essas horas são do tipo inteiro.
	 */
	private int[] horasEstagio;
	/**
	 * mesesProjeto é um atributo que comporta um array e seus valores serão os
	 * meses dedicados a cada projeto pelo aluno, os meses são do tipo inteiro.
	 */
	private int[] mesesProjeto;
	/**
	 * horasCurso é um atributo do tipo double que representa as horas dedicadas em
	 * cursos pelo aluno e que são acumulativas.
	 */
	private double horasCurso;
	/**
	 * indexHorasEstagio é um atributo do tipo inteiro utilizado pela classe para
	 * armazenar o tamanho de valores efetivos no array de horasEstagio para
	 * auxiliar na manipulação deles.
	 */
	private int indexHorasEstagio;
	/**
	 * indexMesesProjeto é um atributo do tipo inteiro utilizado pela classe para
	 * armazenar o tamanho de valores efetivos no array de mesesProjeto para
	 * auxiliar na manipulação deles.
	 */
	private int indexMesesProjeto;

	/**
	 * O construtor AtividadesComplementares(), sem parâmetros, constrói a
	 * representação das atividades complementares do aluno, inicializando os
	 * atributos horasCurso, indexHorasEstagio e indexMesesProjetos com zero e
	 * incializando os arrays de horasEstagio e mesesProjeto, com tamanho 9 e 16,
	 * repectivamente, porque é o máximo de estágios e de projetos que um aluno pode
	 * fazer.
	 */
	public AtividadesComplementares() {
		this.horasEstagio = new int[9];
		this.mesesProjeto = new int[16];
		this.horasCurso = 0.0;
		this.indexHorasEstagio = 0;
		this.indexMesesProjeto = 0;

	}

	/**
	 * O método adicionarEstagio(int horasEstagio), sem retorno, é responsável por
	 * adicionar a quantidade de horas de um estágio passada no parâmetro,
	 * adicionando-a no array horasEstagio, extamente na posição de
	 * indexHorasEstagio (que armazena o tamanho preenchido do array). Depois da
	 * adição, atualiza o tamanho desse index.
	 * 
	 * @param horasEstagio do tipo inteiro que representa a quantidade de horas de
	 *                     um estágio a serem adicionadas no array.
	 */
	public void adicionarEstagio(int horasEstagio) {
		if (this.indexHorasEstagio < 9) {
			this.horasEstagio[this.indexHorasEstagio] = horasEstagio;
		}
		this.indexHorasEstagio++;

	}

	/**
	 * O método adicionarProjeto(int mesesProjeto), sem retorno, é responsável por
	 * adicionar a quantidade de meses de um projeto passada no parâmetro,
	 * adicionando-a no array mesesProjeto, extamente na posição de
	 * indexMesesProjeto (que armazena o tamanho preenchido do array). Depois da
	 * adição, atualiza o tamanho desse index.
	 * 
	 * @param mesesProjeto do tipo inteiro que representa a quantidade de meses de
	 *                     um projeto a serem adicionadas no array.
	 */
	public void adicionarProjeto(int mesesProjeto) {
		if (this.indexMesesProjeto < 16) {
			this.mesesProjeto[this.indexMesesProjeto] = mesesProjeto;
		}
		this.indexMesesProjeto++;
	}

	/**
	 * O método adicionarCurso(double horasCurso), sem retorno, é responsável por
	 * adicionar a quantidade de horas de um Curso passada no parâmetro,
	 * adicionando-a no atributo horasCurso, acumulando-as.
	 * 
	 * @param horasCurso do tipo double que representa a quantidade acumulada de
	 *                   horas dedicadas pelo aluno em cursos.
	 */
	public void adicionarCurso(double horasCurso) {
		this.horasCurso += horasCurso;

	}

	/**
	 * O método creditosEstagio(), sem parâmetros, é uma função que calcula os
	 * créditos de estágio com a condição de que a cada 300 horas de estágio, o
	 * aluno acumula 5 créditos. Para isso, percorre o array de horasEstagio até o
	 * tamanho dele preenchido com valores efetivos (armazenado no atributo
	 * indexHorasEstagio), somando esses a quantidade de horas de cada estágio, para
	 * depois calcular o crédito e retorná-lo. Por ser um método de uso exclusivo da
	 * classe, é privado.
	 * 
	 * @return creditosEstagio, do tipo inteiro, que representa os créditos de
	 *         estágio.
	 */
	private int creditosEstagio() {
		int creditosEstagio = 0;
		int somaHorasEstagio = 0;
		for (int i = 0; i < this.indexHorasEstagio; i++) {
			somaHorasEstagio += this.horasEstagio[i];
		}
		creditosEstagio = ((somaHorasEstagio / 300) * 5);
		return creditosEstagio;

	}

	/**
	 * O método creditosCurso(), sem parâmetros, é uma função que calcula os
	 * créditos de um curso, no qual a cada 30 horas acumuladas de curso, o aluno
	 * recebe 1 crédito. Por ser um método de uso exclusivo da classe, é privado.
	 * 
	 * @return creditosCurso, do tipo inteiro, que representa a quantidade de
	 *         créditos de curso.
	 */
	private int creditosCurso() {
		int creditosCurso = ((int) (this.horasCurso / 30) * 1);
		return creditosCurso;

	}

	/**
	 * O método creditosProjeto(), sem parâmetros, é uma função que calcula os
	 * créditos de Projeto com a condição de que a cada 3 meses de projeto, o aluno
	 * acumula 2 créditos. Para isso, percorre o array de mesesProjeto até o tamanho
	 * dele preenchido com valores efetivos (armazenado no atributo
	 * indexMesesProjeto), somando esses a quantidade de meses de cada projeto, para
	 * depois calcular o crédito e retorná-lo. Por ser um método de uso exclusivo da
	 * classe, é privado.
	 * 
	 * @return creditosProjeto, do tipo inteiro, que representa os créditos de
	 *         Projeto.
	 */
	private int creditosProjeto() {
		int creditosProjeto = 0;
		int somaMesesProjeto = 0;
		for (int i = 0; i < this.indexMesesProjeto; i++) {
			somaMesesProjeto += this.mesesProjeto[i];
		}
		creditosProjeto = ((somaMesesProjeto / 3) * 2);
		return creditosProjeto;
	}

	/**
	 * O método contaCreditos(), sem parâmetros, é uma função que retorna o valor de
	 * créditos acumulados pelo aluno em estágios, projetos e cursos. Para isso,
	 * soma os valores retornados das funções da própria classe: creditosCurso(),
	 * creditosProjeto(), creditosEstagio().
	 * 
	 * @return creditos, do tipo inteiro, que representa os créditos acumulados em
	 *         atividades complementares pelo aluno.
	 */
	public int contaCreditos() {
		int creditos = 0;
		creditos = creditosProjeto() + creditosCurso() + creditosEstagio();
		return creditos;

	}

	/**
	 * O método pegaAtividades(), sem parâmetros, é uma função responsável por
	 * retornar um array de String, com o valor de horas de cada estágio, o valor de
	 * meses de cada projeto, o valor de horas acumuladas por curso, e os créditos
	 * acumulados por cada um. Para isso, é definido um array com o tamanho de
	 * valores preenchidos do array horasEstagio + do tamanho de valores preenchidos
	 * array MesesProjeto + as 4 posições (horas de curso, créditos de estágio,
	 * créditos de projeto e créditos de curso). Em seguida, percorre o array de
	 * estágios e de projetos para pegar os valores de horas e de meses de cada um,
	 * e por fim, pega as horas de curso e os créditos dessas três atividades
	 * complementares, calculadas por suas respectivas funções de calcular créditos
	 * da própria classe.
	 * 
	 * @return atividades, um array com valores do tipo String, que representa
	 *         informações da classe: as horas de estágio de cada estágio, os meses
	 *         de projeto de cada projeto, as horas acumuladas de curso, e os
	 *         créditos de cada atividade complementar.
	 */
	public String[] pegaAtividades() {

		String[] atividades = new String[this.indexHorasEstagio + this.indexMesesProjeto + 4];
		int posicao = 0;
		for (int i = 0; i < this.indexHorasEstagio; i++) {
			atividades[posicao] = "Estagio " + this.horasEstagio[i];
			posicao++;
		}

		for (int i = 0; i < this.indexMesesProjeto; i++) {
			atividades[posicao] = "Projeto " + this.mesesProjeto[i];
			posicao++;
		}
		atividades[posicao] = "Cursos " + this.horasCurso;
		atividades[posicao + 1] = "Creditos_Estagio " + creditosEstagio();
		atividades[posicao + 2] = "Creditos_Projeto " + creditosProjeto();
		atividades[posicao + 3] = "Creditos_Cursos " + creditosCurso();

		return atividades;
	}

}
