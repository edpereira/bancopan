package br.com.empresa.service;

import br.com.empresa.domain.Client;
import br.com.empresa.repository.AddressRepository;
import br.com.empresa.repository.ClientRepository;
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
