package com.example.exercicio2.controllers;

import java.util.List;

import com.example.exercicio2.Models.ProdutoModel;
import com.example.exercicio2.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> criarProduto(@RequestBody ProdutoModel produto) {
        ProdutoModel novoProduto = produtoService.criarProduto(produto);
        return ResponseEntity.status(201).body(novoProduto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> buscarProduto(@PathVariable Long id) {

        ProdutoModel produto = produtoService.buscarPorId(id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {

        ProdutoModel produto = produtoService.buscarPorId(id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}