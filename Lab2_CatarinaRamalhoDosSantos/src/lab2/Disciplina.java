package lab2;

import java.util.Arrays;

/**
 * A classe Disciplina é uma representação da disciplina do estudante, com
 * informações sobre as 4 notas de avalição, com aprovação para média maior ou
 * igual a sete e com definição de nome e horas de estudo.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 *
 */
public class Disciplina {
	/**
	 * nomeDisciplina é um atributo do tipo String que representa o nome da
	 * disciplina.
	 */
	private String nomeDisciplina;
	/**
	 * horasEstudo é um atributo do tipo inteiro que representa as horas de estudo
	 * para aquela disciplina.
	 */
	private int horasEstudo;
	/**
	 * notas é um atributo que comporta um array e seus valores serão as notas das
	 * avaliações feitas na disciplina, as notas são em números decimais, por isso o
	 * tipo double.
	 */
	private double[] notas;

	/**
	 * O construtor Disciplina(String nome Disciplina) constrói a representação da
	 * disciplina do aluno, inicialiazando os atributos: nomeDisciplina da classe
	 * com o nome da disciplina passado no parâmetro, o atributo horasEstudo com 0 e
	 * o atributo notas, definindo o array com tamanho 4.
	 * 
	 * @param nomeDisciplina do tipo String que representa o nome da disciplina a
	 *                       ser atribuída.
	 */
	public Disciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
		this.horasEstudo = 0;
		this.notas = new double[4];
	}

	/**
	 * O método cadastraHoras(int horas), sem retorno, cadastra as horas de estudo
	 * passadas no parâmetro, adicionando-as no atributo horasEstudo da classe.
	 * 
	 * @param horas do tipo inteiro e que representa a quantidade de horas de
	 *              estudo.
	 */
	public void cadastraHoras(int horas) {
		this.horasEstudo += horas;

	}

	/**
	 * O método cadastraNota(int nota, double valorNota), sem retorno, cadastra o
	 * valor da nota passada pelo parâmetro na posição da identificação da nota (1,
	 * 2, 3 ou 4), também passada no parâmetro, no array notas da classe.
	 * 
	 * @param nota      do tipo inteiro representa a identificação para saber qual a
	 *                  nota.
	 * @param valorNota do tipo double representa o valor da nota a ser cadastrado.
	 */
	public void cadastraNota(int nota, double valorNota) {
		if (nota <= 4 && nota >= 1) {
			this.notas[nota - 1] = valorNota;
		}
	}

	/**
	 * O método calculaMedia(), sem parâmetros, é de uso exclusivo da classe, por
	 * isso é privado. É responsável por calcular e retornar a média aritmética das
	 * notas contidas no array de notas da classe. Para isso, soma todos os valores
	 * desse array em um laço de repetição, para depois, dividir por 4.
	 * 
	 * @return media, do tipo double, que representa a média obtida com as 4 notas.
	 */
	private double calculaMedia() {
		double soma = 0.0;
		for (int i = 0; i < this.notas.length; i++) {
			soma += this.notas[i];
		}
		double media = (soma / 4);
		return media;
	}

	/**
	 * O método aprovado(), sem parâmetros, retorna um valor lógico (tipo boolean),
	 * verdadeiro ou falso, para a aprovação do aluno. É responsável por verificar
	 * se o aluno foi, ou não, aprovado na disciplina. Ele é aprovado quando sua
	 * média é >= 7.
	 * 
	 * @return true (verdadeiro) ou false (falso), após a verificação se o aluno
	 *         foi, ou não, aprovado.
	 */
	public boolean aprovado() {
		if (calculaMedia() >= 7.0) {
			return true;
		}
		return false;
	}

	/**
	 * /** O método toStrig(), sem parâmetros, retorna uma string no seguinte
	 * formato "Nome da Diciplina Horas de estudo Média Array das notas", com os
	 * devidos atributos da classe. Ex.: "Programação 1 4 7.0 [7.0, 7.0, 6.0, 8.0]".
	 * 
	 * @return uma String com a representação com as informações da disciplina.
	 */
	@Override
	public String toString() {
		return this.nomeDisciplina + " " + this.horasEstudo + " " + calculaMedia() + " " + Arrays.toString(notas);
	}
}
