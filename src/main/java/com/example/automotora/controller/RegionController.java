package com.example.automotora.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.automotora.model.Region;
import com.example.automotora.service.RegionService;

@RestController
@RequestMapping("/api/v1/regiones")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegiones() {
        List<Region> regiones = regionService.getAllRegiones();
        return ResponseEntity.ok(regiones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Integer id) {
        Region region = regionService.getRegionById(id);
        if (region != null) {
            return ResponseEntity.ok(region);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Region> getRegionByNombre(@RequestParam String nombre) {
        Optional<Region> region = regionService.getRegionByNombre(nombre);
        return region.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        Region nuevaRegion = regionService.saveRegion(region);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRegion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable Integer id, @RequestBody Region region) {
        Region regionActualizada = regionService.updateRegion(id, region);
        if (regionActualizada != null) {
            return ResponseEntity.ok(regionActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Region> patchRegion(@PathVariable Integer id, @RequestBody Region region) {
        Region regionActualizada = regionService.patchRegion(id, region);
        if (regionActualizada != null) {
            return ResponseEntity.ok(regionActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Integer id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}

