package br.com.alura.spring.data.specification;

import br.com.alura.spring.data.orm.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SpecificationFuncionario {

    public static Specification<Funcionario> nome(String nome) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
    }

    public static Specification<Funcionario> cpf(String cpf) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("cpf"), cpf));
    }

    public static Specification<Funcionario> salario(BigDecimal salario) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("salario"), salario));
    }

    public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao));
    }

}
