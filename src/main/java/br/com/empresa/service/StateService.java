package br.com.empresa.service;

import br.com.empresa.client.StateClient;
import br.com.empresa.domain.State;
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
    private StateClient stateClient;

    @Cacheable(value = "ordered-states")
    public List<State> getOrderedStates() {
        List<State> states = stateClient.getStates();
        return sortStates(states);
    }

    public Object getCities(String siglaEstado) {
        List<State> states = stateClient.getStates();
        states = states.stream().filter(estado -> estado.getSigla().equals(siglaEstado)).collect(Collectors.toList());
        Assert.isTrue(states.size() == 1, "State invalid");
        return stateClient.getCities(states.get(0).getId());
    }

    private List<State> sortStates(List<State> states) {
        List<State> copy = new ArrayList<>(states);
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
