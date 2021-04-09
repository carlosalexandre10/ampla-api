package br.com.ampla.marca.utils.enums;

import br.com.ampla.marca.utils.enums.config.DescricaoRecuperavel;
import br.com.ampla.marca.utils.enums.config.RecuperadorEnumPorValor;
import br.com.ampla.marca.utils.enums.config.ValorEnumInvalidoException;
import br.com.ampla.marca.utils.enums.config.ValorRecuperavel;

public enum RolesEnum implements ValorRecuperavel, DescricaoRecuperavel {

	ROLE_USER(1, "ROLE_USER"), ROLE_ADMIN(2, "ROLE_ADMIN");

	private final int valor;
	private final String descricao;

	RolesEnum(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	@Override
	public String getValor() {
		return String.valueOf(this.valor);
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

	@Override
	public String getDescricaoMaiscula() {
		return RecuperadorEnumPorValor.getDescricaoMaiscula(this);
	}

	@Override
	public String getValorComDescricao() {
		return RecuperadorEnumPorValor.retornarValorComDescricao(this);
	}

	public String getDescricaoConcatenadoComCodigo() {
		return String.format("%s - %s", this.valor, this.descricao);
	}

	public static RolesEnum fromCodigo(Integer codigo) throws ValorEnumInvalidoException {
		return RecuperadorEnumPorValor.fromValue(values(), codigo);
	}

	@Override
	public String getValorComDescricaoMaiscula() {
		// TODO Auto-generated method stub
		return null;
	}
}
