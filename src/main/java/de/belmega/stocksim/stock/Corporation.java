package de.belmega.stocksim.stock;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Corporation {

    @Id
    private String id;

    private String name;


}
