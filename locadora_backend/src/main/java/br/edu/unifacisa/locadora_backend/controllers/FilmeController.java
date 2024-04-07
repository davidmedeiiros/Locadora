package br.edu.unifacisa.locadora_backend.controllers;

import br.edu.unifacisa.locadora_backend.entities.Filme;
import br.edu.unifacisa.locadora_backend.repositories.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    @Autowired
    private FilmeRepository repository;

    @GetMapping
    public List<Filme> listarTodosOsFilmes() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Filme> listarFilmePorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerFilmePorId(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public Filme adicionarFilme(@RequestBody Filme novoFilme) {
        return repository.save(novoFilme);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Filme> alterarFilmePorId(@PathVariable Long id, @RequestBody Filme novoFilme) {
        try {
            Filme filmeAntigo = repository.findById(id).get();

            filmeAntigo.setTitulo(novoFilme.getTitulo());
            filmeAntigo.setCategoria(novoFilme.getCategoria());
            filmeAntigo.setAno(novoFilme.getAno());
            return new ResponseEntity<>(repository.save(filmeAntigo), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
