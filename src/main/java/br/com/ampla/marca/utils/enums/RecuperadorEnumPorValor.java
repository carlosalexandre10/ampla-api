package br.com.ampla.marca.utils.enums;

import org.apache.commons.lang3.StringUtils;

public class RecuperadorEnumPorValor {
	public static <T extends Enum<?> & DescricaoRecuperavel> String getDescricaoMaiscula(T enumDominio) {
		if (enumDominio != null) {
			return enumDominio.getDescricao().toUpperCase();
		}

		return null;
	}

	public static <T extends Enum<?> & ValorRecuperavel & DescricaoRecuperavel> String retornarValorComDescricao(
			T enumDominio) {
		return String.format("%s - %s", enumDominio.getValor(), enumDominio.getDescricao());
	}


	public static <T extends Enum<?> & ValorRecuperavel> T fromValue(T[] enumsDominio, Integer valor)
			throws ValorEnumInvalidoException {
		if (valor != null) {
			return fromValue(enumsDominio, String.valueOf(valor));
		}

		return null;
	}

	public static <T extends Enum<?> & ValorRecuperavel> T fromValue(T[] enumsDominio, String value)
			throws ValorEnumInvalidoException {
		if (StringUtils.isEmpty(value)) {
			return null;
		}

		if (isArrayEnumNaoVazio(enumsDominio)) {
			return retornarValorIterandoArray(enumsDominio, value);
		}

		return null;
	}

	private static <T extends Enum<?> & ValorRecuperavel> boolean isArrayEnumNaoVazio(T[] enumsDominio)
			throws ValorEnumInvalidoException {
		if (enumsDominio.length != 0) {
			return true;
		}

		throw new ValorEnumInvalidoException();
	}

	private static <T extends ValorRecuperavel> T retornarValorIterandoArray(T[] enumsDominio, String value)
			throws ValorEnumInvalidoException {
		for (T e : enumsDominio) {
			if (value.equalsIgnoreCase(e.getValor())) {
				return e;
			}
		}

		throw new ValorEnumInvalidoException(enumsDominio[0].getClass(), value);
	}
}
