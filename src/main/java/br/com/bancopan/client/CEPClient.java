package br.com.bancopan.client;

import br.com.bancopan.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Component
public class CEPClient {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${viacep.url}")
    private String baseUrl;

    public Address getCep(String cep) {
        String url = baseUrl + cep + "/json";
        ResponseEntity<Address> reponse = restTemplate.getForEntity(url, Address.class);
        Address address = reponse.getBody();
        Assert.notNull(address.getCep(), "Invalid CEP");
        return address;
    }
}
