package app.service;

import app.dao.OfficeDao;
import app.model.Offices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class OfficeServiceImpl implements OfficeService {
    private static final Logger LOG = LogManager.getLogger(OfficeServiceImpl.class);
    @Autowired
    private OfficeDao officeDao;

    @Override
    public void insertOffice(Offices offices) {
        LOG.debug("insertOffice starts", offices);
        officeDao.insertOffice(offices);
        LOG.debug("insertOffice is successful");
    }

    @Override
    public void updateOffice(Offices offices) {
        LOG.debug("updateOffice starts");
        officeDao.updateOffice(offices);
        LOG.debug("updateOffice is successful");
    }

    @Override
    public void deleteOffice(BigDecimal id) {
        LOG.debug("updateOffice starts, id={}", id);
        officeDao.deleteOffice(id);
        LOG.debug("updateOffice is successful");
    }

    @Override
    public Offices findOfficeById(BigDecimal id) {
        LOG.debug("findOfficeById starts");
        Offices offices = officeDao.findOfficeById(id);
        LOG.debug("findOfficeById is successful");
        return offices;
    }

    @Override
    public Set<Offices> getAllOffices() {
        LOG.debug("getAllOffices starts");
        Set<Offices> officesSet = new HashSet<>(officeDao.getAllOffices());
        LOG.debug("getAllOffices is successful");
        return officesSet;
    }
}

