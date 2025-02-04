package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.Services.SecretGenerator;
import hu.bme.aut.retelab2.domain.Ad;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class AdRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save(Ad data) {
        data.setCreationDate(new Date(System.currentTimeMillis()));
        data.setSecret(SecretGenerator.generate());
        return em.merge(data);
    }

    public List<Ad> get(int min, int max) {
        List<Ad> res = em.createQuery("SELECT n FROM Ad n WHERE n.price BETWEEN ?1 AND ?2", Ad.class)
                .setParameter(1, min)
                .setParameter(2, max)
                .getResultList();

        res.forEach(ad -> ad.setSecret(""));

        return res;
    }

    public Ad getById(Long id) {
        return em.find(Ad.class, id);
    }

    @Transactional
    public Ad update(Ad T) throws IllegalAccessException {
        Ad r = getById(T.getId());

        if (r.getSecret().equals(T.getSecret())) {
            return em.merge(T);
        } else throw new IllegalAccessException();
    }

    public List<Ad> getAll() {
        return em.createQuery("SELECT n FROM Ad n", Ad.class).getResultList();
    }

    @Transactional
    public void deleteById(long Id) {
        Ad ad = getById(Id);

        em.remove(ad);
    }
}
