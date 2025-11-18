package com.example.automotora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Comuna;
import com.example.automotora.model.Region;
import com.example.automotora.repository.ComunaRepository;
import com.example.automotora.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    public List<Region> getAllRegiones() {
        return regionRepository.findAll();
    }

    public Region getRegionById(Integer id) {
        return regionRepository.findById(id).orElse(null);
    }

    public Optional<Region> getRegionByNombre(String nombre) {
        return regionRepository.findByNombre(nombre);
    }

    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    public Region updateRegion(Integer id, Region regionActualizada) {
        Region region = regionRepository.findById(id).orElse(null);
        if (region != null) {
            region.setNombre(regionActualizada.getNombre());
            return regionRepository.save(region);
        }
        return null;
    }

    public Region patchRegion(Integer id, Region regionParcial) {
        Region region = regionRepository.findById(id).orElse(null);
        if (region != null) {
            if (regionParcial.getNombre() != null) {
                region.setNombre(regionParcial.getNombre());
            }
            return regionRepository.save(region);
        }
        return null;
    }

    public void deleteRegion(Integer id) {
        List<Comuna> comunas = comunaRepository.findByRegionId(id);
        comunaRepository.deleteAll(comunas);
        regionRepository.deleteById(id);
    }
}
