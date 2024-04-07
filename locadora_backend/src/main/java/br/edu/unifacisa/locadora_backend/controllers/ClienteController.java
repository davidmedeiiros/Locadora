package br.edu.unifacisa.locadora_backend.controllers;

import br.edu.unifacisa.locadora_backend.entities.Cliente;
import br.edu.unifacisa.locadora_backend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public List<Cliente> listarTodosOsClientes() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerClientePorId(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public Cliente adicionarCliente(@RequestBody Cliente novoCliente) {
        return repository.save(novoCliente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> alterarClientePorId(@PathVariable Long id, @RequestBody Cliente novoCliente) {
        try {
            Cliente clienteAntigo = repository.findById(id).get();

            clienteAntigo.setNome(novoCliente.getNome());
            clienteAntigo.setCpf(novoCliente.getCpf());
            return new ResponseEntity<>(repository.save(clienteAntigo), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
