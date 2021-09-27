package hu.bme.aut.retelab2.Services;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrashService {

    @Autowired
    private AdRepository adRepository;

    @Scheduled(fixedDelay = 6000)
    public void ThrowTrash() {
        List<Ad> r = adRepository.getAll();

        r.forEach(ad -> {
            if (ad.getExpireDate() != null && ad.getExpireDate().isAfter(LocalDateTime.now())) {
                adRepository.deleteById(ad.getId());
            }
        });
    }
}
