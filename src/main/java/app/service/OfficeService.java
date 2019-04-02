package app.service;

import app.model.Offices;

import java.math.BigDecimal;
import java.util.Set;

public interface OfficeService {

    void insertOffice(Offices offices);

    void updateOffice(Offices offices);

    void deleteOffice(BigDecimal id);

    public Offices findOfficeById(BigDecimal id) ;

    public Set<Offices> getAllOffices ();
}
