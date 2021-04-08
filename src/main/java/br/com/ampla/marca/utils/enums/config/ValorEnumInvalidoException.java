package br.com.ampla.marca.utils.enums.config;

public class ValorEnumInvalidoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private final static String MESSAGEM_ERROR_VALOR_NAO_EXISTE = "O valor %s não existe na enum %s";
	  private final static String MENSAGEM_ERROR_ARRAY_VAZIO = "Nenhum valor foi passado para o método.";

	  /**
	   * <p>
	   *   Sobrecarga de {@link #ValorEnumInvalidoException(Class, String)}.
	   * </p>
	   *
	   * @param clazz         enum.
	   * @param valorInvalido valor inteiro inválido.
	   */
	  ValorEnumInvalidoException(Class<? extends ValorRecuperavel> clazz, int valorInvalido) {
	    this(clazz, String.valueOf(valorInvalido));
	  }

	  /**
	   * Retorna a exception imprimindo o valor parametrizado {@link #MESSAGEM_ERROR_VALOR_NAO_EXISTE}, conforme
	   * os parâmetros do método.
	   *
	   * @param clazz         enum.
	   * @param valorInvalido valor em String inválido.
	   */
	  ValorEnumInvalidoException(Class<? extends ValorRecuperavel> clazz, String valorInvalido) {
	    super(String.format(MESSAGEM_ERROR_VALOR_NAO_EXISTE, valorInvalido, clazz.getSimpleName()));
	  }

	  /**
	   * Retorna a exception imprimindo {@link #MENSAGEM_ERROR_ARRAY_VAZIO}.
	   */
	  ValorEnumInvalidoException() {
	    super(MENSAGEM_ERROR_ARRAY_VAZIO);
	  }
}
