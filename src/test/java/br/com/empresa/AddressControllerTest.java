package br.com.empresa;

import br.com.empresa.controller.AddressController;
import br.com.empresa.domain.Address;
import br.com.empresa.service.AddressService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

    @InjectMocks
    private AddressController controller;
    @Mock
    private AddressService service;

    @Test
    public void getAddressByCEPShouldReturnStatusOkWhenCepIsValid() {
        Mockito.doReturn(new Address()).when(service).getAddress(Mockito.anyString());
        Assert.assertEquals(controller.getAddressByCEP("06755260").getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getAddressByCEPShouldReturnStatusBadRequestWhenCepIsNotValid() {
        Mockito.doThrow(new IllegalArgumentException()).when(service).getAddress(Mockito.anyString());
        Assert.assertEquals(controller.getAddressByCEP("06755261").getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
