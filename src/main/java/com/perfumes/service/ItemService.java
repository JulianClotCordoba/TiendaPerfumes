package com.perfumes.service;

import com.perfumes.domain.FamiliaOlfativa;
import com.perfumes.domain.Item;
import com.perfumes.domain.Marca;
import com.perfumes.repository.FamiliaOlfativaRepository;
import com.perfumes.repository.ItemRepository;
import com.perfumes.repository.MarcaRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private FamiliaOlfativaRepository familiaRepository;

    @Transactional(readOnly = true)
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Item getItem(Long idItem) {
        return itemRepository.findById(idItem).orElse(null);
    }

    public List<Item> getItemsFiltrados(String genero, Long marcaId, Long familiaId) {
        return itemRepository.findAll().stream()
                .filter(item
                        -> (genero == null || genero.isEmpty() || item.getGenero().equalsIgnoreCase(genero)))
                .filter(item
                        -> (marcaId == null || (item.getMarca() != null && item.getMarca().getIdMarca().equals(marcaId))))
                .filter(item
                        -> (familiaId == null || (item.getFamiliaOlfativa() != null && item.getFamiliaOlfativa().getIdFamilia().equals(familiaId))))
                .collect(Collectors.toList());
    }

    public List<String> getGeneros() {
        return Arrays.asList("HOMBRE", "MUJER", "UNISEX");
    }

    public List<Marca> getMarcas() {
        return marcaRepository.findAll();
    }

    public List<FamiliaOlfativa> getFamilias() {
        return familiaRepository.findAll();
    }
}
