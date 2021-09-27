package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PutMapping
    public ResponseEntity<Ad> update(@RequestBody Ad T) {
        try {
            return ResponseEntity.ok(adRepository.update(T));
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("{tag}")
    public List<Ad> getWithTag(@PathVariable String tag) {
        List<Ad> ads = adRepository.getAll();
        List<Ad> res = new ArrayList<>();

        ads.forEach(ad -> {
            if (ad.getTags().contains(tag)) res.add(ad);
        });

        return res;
    }
}
