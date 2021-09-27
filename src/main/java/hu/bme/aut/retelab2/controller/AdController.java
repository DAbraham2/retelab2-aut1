package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @GetMapping
    public List<Ad> getAll(@RequestParam(defaultValue = "0") int min, @RequestParam(defaultValue = "10000000") int max) {
        return adRepository.get(min, max);
    }

    @PostMapping
    public Ad create(@RequestBody Ad data) {
        data.setId(null);
        return adRepository.save(data);
    }
}
