package app.dao;

import app.model.Customers;
import app.model.Offices;

import java.math.BigDecimal;
import java.util.Set;


public interface OfficeDao {

    boolean insertOffice(Offices offices);

    boolean updateOffice(Offices offices);

    boolean deleteOffice(BigDecimal id);

    public Offices findOfficeById(BigDecimal id) ;

    public Set <Offices> getAllOffices ();
}
