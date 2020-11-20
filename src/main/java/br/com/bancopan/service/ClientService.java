package br.com.bancopan.service;

import br.com.bancopan.domain.Client;
import br.com.bancopan.repository.AddressRepository;
import br.com.bancopan.repository.ClientRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientByCPF(String cpf) {
        return clientRepository.findById(cpf).orElseThrow(IllegalArgumentException::new);
    }
}
