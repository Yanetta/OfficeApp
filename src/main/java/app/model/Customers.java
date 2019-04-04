package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS", schema = "MA_STUDENT")
public class Customers implements java.io.Serializable {
    @Id
    @Column(name = "CUST_NUM")
    private BigDecimal cust_num;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_REP")
    private Salesreps salesreps;

    @Column(name = "CREDIT_LIMIT")
    private BigDecimal credit_limit;

    @Column(name = "COMPANY")
    private String company;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customers")
    private Set<Orders> ordersSet = new HashSet<Orders>(0);


    public Customers() {
    }

    public Customers(BigDecimal cust_num) {
        this.cust_num = cust_num;
    }

    public Customers(BigDecimal cust_num, Salesreps salesreps, BigDecimal credit_limit, String company) {
        this.cust_num = cust_num;
        this.salesreps = salesreps;
        this.credit_limit = credit_limit;
        this.company = company;

    }

    public BigDecimal getCust_num() {
        return cust_num;
    }

    public void setCust_num(BigDecimal cust_num) {
        this.cust_num = cust_num;
    }

    public Salesreps getSalesreps() {
        return salesreps;
    }

    public void setSalesreps(Salesreps salesreps) {
        this.salesreps = salesreps;
    }

    public BigDecimal getCredit_limit() {
        return credit_limit;
    }

    public void setCredit_limit(BigDecimal credit_limit) {
        this.credit_limit = credit_limit;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    @Override
    public String toString() {
        return "\n"+ "Customers{" +
                "cust_num=" + cust_num +
               //", cust_rep=" + salesreps +
                ", credit_limit=" + credit_limit +
                ", company='" + company + '\'' +
//               ", orders=" + ordersSet +
                '}' ;
    }
}
