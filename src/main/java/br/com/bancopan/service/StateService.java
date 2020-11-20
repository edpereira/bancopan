package br.com.bancopan.service;

import br.com.bancopan.client.StateClient;
import br.com.bancopan.domain.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {

    private static final String SP = "SP";
    private static final String RJ = "RJ";

    @Autowired
    private StateClient estadoClient;

    @Cacheable(value = "estados-ordenados")
    public List<State> getEstados() {
        List<State> estados = estadoClient.getEstados();
        return orderEstados(estados);
    }

    public Object getMunicipios(String siglaEstado) {
        List<State> estados = estadoClient.getEstados();
        estados = estados.stream().filter(estado -> estado.getSigla().equals(siglaEstado)).collect(Collectors.toList());
        Assert.isTrue(estados.size() == 1, "State invalid");
        return estadoClient.getMunicipios(estados.get(0).getId());
    }

    private List<State> orderEstados(List<State> estados) {
        List<State> copy = new ArrayList<>(estados);
        Collections.sort(copy, Comparator.comparing(State::getNome));
        List<State> response = new ArrayList<>();
        State sp = copy.remove(25);
        State rj = copy.remove(20);
        response.add(0, sp);
        response.add(1, rj);
        response.addAll(2, copy);
        return response;
    }
}
