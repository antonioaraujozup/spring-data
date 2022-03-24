package br.com.alura.spring.data.orm;

import java.math.BigDecimal;

public interface FuncionarioProjecao {
    Long getId();
    String getNome();
    BigDecimal getSalario();
}
