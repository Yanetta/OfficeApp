package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCTS", schema = "MA_STUDENT")
public class Products implements Serializable {

    @Id
    @Column(name = "PRODUCT_ID")
    private String product_id;

    @Column(name = "MFR_ID")
    private String mfr_id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "QTY_ON_HAND")
    private BigDecimal qty_on_hand;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private Set<Orders> ordersSet = new HashSet<Orders>(0);

    public Products() {
    }

    public Products(String product_id, String mfr_id, String description, BigDecimal price, BigDecimal qty_on_hand) {
        this.product_id = product_id;
        this.mfr_id = mfr_id;
        this.description = description;
        this.price = price;
        this.qty_on_hand = qty_on_hand;
    }

    public String getMfr_id() {
        return mfr_id;
    }

    public void setMfr_id(String mfr_id) {
        this.mfr_id = mfr_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty_on_hand() {
        return qty_on_hand;
    }

    public void setQty_on_hand(BigDecimal qty_on_hand) {
        this.qty_on_hand = qty_on_hand;
    }

    public Set<Orders> getOrdersSet() {
        return this.ordersSet;
    }

    public void setOrdersSet(Set<Orders> orders) {
        this.ordersSet = orders;
    }

    @Override
    public String toString() {
        return "\n"+"Products{" +"\n"+
                "product_id='" + product_id +
                ", "+"\n"+"mfr_id='" + mfr_id +
                ", "+"\n"+"description='" + description +
                ", "+"\n"+"price=" + price +
                ","+"\n"+"qty_on_hand=" + qty_on_hand +
                //", "+"\n"+"orders=" + ordersSet +
                '}';
    }
}


