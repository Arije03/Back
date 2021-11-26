/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import lombok.*;

/**
 *
 * @author makhlouf
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long code;
    private String wording;
    private double purchasePrice;
    private double cellPrice;
    private int stockQuantity;
    private int alertStockQuantity;
    private boolean hasExpirationDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expirationDate;
    @Enumerated(EnumType.STRING)
    @ManyToOne
    private Units unit;
    @ManyToOne
    private Suppliers supplier;
    
}
