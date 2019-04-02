package app.officecontroller;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import javax.validation.Valid;

import app.service.OfficeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import app.model.Offices;

@RestController
@RequestMapping("/office")
public class OfficeController {
    private static final Logger LOG = LogManager.getLogger(OfficeController.class);

    @Autowired
    private OfficeService officeService;

    @GetMapping
    public @ResponseBody
    Set<Offices> getAllOffices() {
        LOG.debug("getAllOffices starts");
        return officeService.getAllOffices();
    }

    @PostMapping
    public void insertOffice(@Valid @RequestBody Offices officeRequest) {
        LOG.info("insertOffice starts");
        officeService.insertOffice(officeRequest);
        LOG.info("insertOffice  finished");
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Offices getOfficesById(@PathVariable("id") int id) {
        LOG.info("getOfficesById starts, id={}", id);
        Offices offices = officeService.findOfficeById(BigDecimal.valueOf(id));
        LOG.info("getOfficesById finished");
        return offices;
    }

    @DeleteMapping("/{id}")
    public void deleteOfficeById(@PathVariable("id") int id) {
        LOG.info("deleteOfficeById starts, id={}", id);
        officeService.deleteOffice(BigDecimal.valueOf(id));
        LOG.info("deleteOrderById finished");
    }

    @PutMapping("/{id}")
    public void updateOfficeById(@PathVariable("id") int id, @RequestParam("target") Integer target) {
        LOG.info("updateOfficeById starts, id={}, target = {}", id, target);
        Offices offices = officeService.findOfficeById(BigDecimal.valueOf(id));
        if (Objects.isNull(offices)) {
            LOG.warn("There is no offices with such id={}", id);
        } else {
            offices.setTarget(BigDecimal.valueOf(target));
            officeService.updateOffice(offices);
        }
        LOG.info("updateOfficeById finished");
    }

}
