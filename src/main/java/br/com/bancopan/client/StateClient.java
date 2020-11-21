package br.com.bancopan.client;

import br.com.bancopan.domain.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateClient {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${ibge.url}")
    private String ibgeUrl;

    @Cacheable(value = "states")
    public List<State> getStates() {
        ResponseEntity<State[]> response = restTemplate.getForEntity(ibgeUrl, State[].class);
        Assert.isTrue(response.getStatusCode().equals(HttpStatus.OK), "Service unavailable, try later.");
        return Arrays.stream(response.getBody()).collect(Collectors.toList());
    }

    @Cacheable(value = "cities", key = "#id")
    public Object getCities(String id) {
        String ibgeMunicipiosUrl = ibgeUrl + id + "/municipios";
        ResponseEntity<Object> response = restTemplate.getForEntity(ibgeMunicipiosUrl, Object.class);
        Assert.isTrue(response.getStatusCode().equals(HttpStatus.OK), "Service unavailable, try later.");
        return response.getBody();
    }

}
