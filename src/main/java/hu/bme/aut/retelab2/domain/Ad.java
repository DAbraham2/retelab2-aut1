package hu.bme.aut.retelab2.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Ad {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private int price;

    private Date creationDate;

    private String secret;

    @ElementCollection
    @CollectionTable(
            name = "TAG"
    )
    private List<String> tags;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
