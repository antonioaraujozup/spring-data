package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long>,
        JpaSpecificationExecutor<Funcionario> {

    public List<Funcionario> findByNome(String nome);

    @Query("SELECT f FROM Funcionario f " +
            "WHERE f.nome = :nome " +
            "AND f.salario > :salario " +
            "AND f.dataContratacao = :dataContratacao")
    public List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, BigDecimal salario, LocalDate dataContratacao);

    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :dataContratacao", nativeQuery = true)
    public List<Funcionario> findDataContratacaoMaior(LocalDate dataContratacao);

    @Query(value = "select f.id, f.nome, f.salario from funcionarios f", nativeQuery = true)
    public List<FuncionarioProjecao> findFuncionarioSalario();

}
