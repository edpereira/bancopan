package br.com.bancopan.service;

import br.com.bancopan.client.CEPClient;
import br.com.bancopan.domain.Address;
import br.com.bancopan.domain.Client;
import br.com.bancopan.repository.AddressRepository;
import br.com.bancopan.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository repository;
    @Autowired
    private CEPClient cepClient;

    public List<Address> createAddress(String clientCpf, Address address) {
        Client client = clientRepository.findById(clientCpf).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        client.addAddress(address);
        client = clientRepository.save(client);
        return client.getAddresses();
    }

    public List<Address> getAddress(String clientCpf, String cep) {
        return repository.getClientAddressByCEP(clientCpf, cep);
    }

    public Address getAddress(String cep) {
        return cepClient.getCep(cep);
    }

    public Address updateClientAddress(String clientCpf, String cep, Address address) {
        Client client = clientRepository.findById(clientCpf).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        List<Address> addresses = repository.getClientAddressByCEP(clientCpf, cep);
        Assert.isTrue(addresses.size() > 0, "Client or Address Invalid");
        address.setId(addresses.get(0).getId());
        client.addAddress(address);
        return repository.save(address);
    }

}
