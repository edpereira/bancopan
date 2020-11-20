package br.com.bancopan.repository;

import br.com.bancopan.domain.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface AddressRepository extends CrudRepository<Address, BigInteger> {

    @Query("select a from Address a where a.client.cpf = :cpf and a.cep = :cep")
    List<Address> getClientAddressByCEP(@Param("cpf") String clientCpf, @Param("cep") String cep);

}
