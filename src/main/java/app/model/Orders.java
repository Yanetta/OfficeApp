package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ORDERS", schema = "MA_STUDENT")
public class Orders implements java.io.Serializable {
    @Id
    @Column(name = "ORDER_NUM")
    private BigDecimal order_num;

    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE")
    private Date order_date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST")
    private Customers customers;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REP")
    private Salesreps salesreps;

    @Column(name = "MFR")
    private String mfr;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT")
    private Products products;

    @Column(name = "Qty")
    private BigDecimal qty;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public Orders() {
    }

    public Orders(BigDecimal order_num, Date order_date, Customers customers, Salesreps salesreps, String mfr, Products products, BigDecimal qty, BigDecimal amount) {
        this.order_num = order_num;
        this.order_date = order_date;
        this.customers = customers;
        this.salesreps = salesreps;
        this.mfr = mfr;
        this.products = products;
        this.qty = qty;
        this.amount = amount;
    }

    public BigDecimal getOrder_num() {
        return order_num;
    }

    public void setOrder_num(BigDecimal order_num) {
        this.order_num = order_num;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Salesreps getSalesreps() {
        return salesreps;
    }

    public void setSalesreps(Salesreps salesreps) {
        this.salesreps = salesreps;
    }

    public String getMfr() {
        return mfr;
    }

    public void setMfr(String mfr) {
        this.mfr = mfr;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "\n"+"Orders{" +
                "order_num=" + order_num +
                ", order_date=" + order_date +
                ", customers=" + customers +
                ", salesreps=" + salesreps +
                ", mfr='" + mfr + '\'' +
                ", products=" + products +
                ", qty=" + qty +
                ", amount=" + amount +
                '}';
    }
}


