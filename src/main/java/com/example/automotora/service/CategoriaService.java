package com.example.automotora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Categoria;
import com.example.automotora.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Optional<Categoria> getCategoriaByNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Integer id, Categoria categoriaActualizada) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria != null) {
            categoria.setNombre(categoriaActualizada.getNombre());
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    public Categoria patchCategoria(Integer id, Categoria categoriaParcial) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria != null) {
            if (categoriaParcial.getNombre() != null) {
                categoria.setNombre(categoriaParcial.getNombre());
            }
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    public void deleteCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
