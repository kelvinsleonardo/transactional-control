package br.com.kelvinsantiago.transactionalcontrol.repository;

import br.com.kelvinsantiago.transactionalcontrol.model.Balance;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface BalanceRepository extends PagingAndSortingRepository<Balance, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Balance> findById(long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Balance> findByName(String name);

    @Query(value = "SELECT * FROM tb_balance WHERE id = :id FOR UPDATE", nativeQuery = true)
    Optional<Balance> findByIdLock(@Param("id") long id);

    @Query(value = "SELECT * FROM tb_balance WHERE name = :name FOR UPDATE", nativeQuery = true)
    Optional<Balance> findByNameLock(@Param("name") String name);


}
