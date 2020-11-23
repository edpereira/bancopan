package br.com.empresa;

import br.com.empresa.client.CEPClient;
import br.com.empresa.domain.Address;
import br.com.empresa.repository.AddressRepository;
import br.com.empresa.repository.ClientRepository;
import br.com.empresa.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressService service;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private AddressRepository repository;
    @Mock
    private CEPClient cepClient;

    @Test(expected = IllegalArgumentException.class)
    public void updateClientAddressShouldThrowIllegalArgumentExceptionWhenClientCpfNotFound() {
        String clientCpf = "39150548875";
        Mockito.doReturn(Optional.empty()).when(clientRepository).findById(clientCpf);
        service.updateClientAddress(clientCpf, "", new Address());
    }
}
