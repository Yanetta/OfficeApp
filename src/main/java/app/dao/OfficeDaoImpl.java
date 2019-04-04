package app.dao;

import app.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private Session session;

    private SessionFactory sessionFactory;

    private static final Logger LOG = LogManager.getLogger(OfficeDaoImpl.class);

    @Autowired
    public OfficeDaoImpl(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Set<Offices> getAllOffices() {
        LOG.debug("getAllOffices starts");
        Set<Offices> officesSet = new HashSet<>();
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Offices");
            officesSet = new HashSet<Offices>(query.getResultList());
            //session.createQuery("FROM Orders", O.class).list());
            session.getTransaction().commit();
            LOG.info("getAllOffices is successfull!");
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                LOG.info("transaction is rolled back");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return officesSet;
    }

    @Override
    public boolean insertOffice(Offices offices) {
        LOG.debug("insertOffice starts");
        boolean isSuccessfull = false;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(offices);
            session.getTransaction().commit();
            isSuccessfull = true;
            LOG.info("insertOffice is Successfull!");
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                LOG.info("transaction is rolled back");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return isSuccessfull;
    }

    @Override
    public Offices findOfficeById(BigDecimal id) {
        LOG.debug("findOfficeById starts");
        Offices offices = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            offices = session.get(Offices.class, id);
            session.getTransaction().commit();
            LOG.info("findOfficeById is successfull!");
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                LOG.info("transaction is rolled back");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return offices;
    }

    @Override
    public boolean updateOffice(Offices offices) {
        LOG.debug("updateOffice starts");
        boolean isSuccessful = false;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(offices);
            session.getTransaction().commit();
            isSuccessful = true;
            LOG.info("updateOffice is successful!");
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                LOG.info("transaction is rolled back");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return isSuccessful;
    }

    @Override
    public boolean deleteOffice(BigDecimal id) {
        LOG.debug("deleteOffice starts");
        boolean isSuccessful = false;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(session.get(Offices.class, id));
            session.getTransaction().commit();
            isSuccessful = true;
            LOG.info("deleteOffice is successful!");
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                LOG.info("transaction is rolled back");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return isSuccessful;
    }
}
