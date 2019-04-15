package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "OFFICES", schema = "MA_STUDENT")
public class Offices implements java.io.Serializable {
    @Id
    @Column(name = "OFFICE")
    private BigDecimal office;

    @Column(name = "REGION")
    private String region;

    @Column(name = "CITY")
    private String city;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MGR")
    private Salesreps salesreps;

    @Column(name = "TARGET")
    private BigDecimal target;

    @Column(name = "SALES")
    private BigDecimal sales;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offices")
    private Set<Salesreps> salesrepsSet = new HashSet<Salesreps>(0);


    public Offices() {
    }

    public Offices(BigDecimal office) {
        this.office = office;
    }

    public Offices(BigDecimal office, String region, String city, Salesreps mgr, BigDecimal target, BigDecimal sales) {
        this.office = office;
        this.region = region;
        this.city = city;
        this.salesreps = mgr;
        this.target = target;
        this.sales = sales;
    }

    public BigDecimal getOffice() {
        return office;
    }

    public void setOffice(BigDecimal office) {
        this.office = office;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Salesreps getSalesreps() {
        return salesreps;
    }

    public void setSalesreps(Salesreps mgr) {
        this.salesreps = mgr;
    }

    public BigDecimal getTarget() {
        return target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public Set<Salesreps> getSalesrepsSet() {
        return salesrepsSet;
    }

    public void setSalesrepsSet(Set<Salesreps> salesreps) {
        this.salesrepsSet = salesreps;
    }

    @Override
    public String toString() {
        return "\n"+"Offices{" +
                "office=" + office +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", mgr=" + salesreps +
                ", target=" + target +
                ", sales=" + sales +
                '}';
    }
}
