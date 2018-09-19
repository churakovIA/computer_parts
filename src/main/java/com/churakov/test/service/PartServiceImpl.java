package com.churakov.test.service;

import com.churakov.test.dao.Filter;
import com.churakov.test.dao.PageHelper;
import com.churakov.test.dao.PartDAO;
import com.churakov.test.model.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartDAO partDAO;

    @Transactional(readOnly = true)
    public PageHelper<Part> listParts(int page, Filter filter, String name) {
        return this.partDAO.listParts(page, filter, name);
    }

    @Transactional
    public void addPart(Part p) {
        this.partDAO.addPart(p);
    }

    @Transactional
    public void removePart(int id) {
        this.partDAO.removePart(id);
    }

    @Transactional
    public void updatePart(Part p) {
        this.partDAO.updatePart(p);
    }

    @Transactional(readOnly = true)
    public Part getPartById(int id) {
        return this.partDAO.getPartById(id);
    }

    @Transactional
    public Part getPartByName(String str) {
        return this.partDAO.getPartByName(str);
    }

    @Transactional(readOnly = true)
    public int getComputersCount() {
        return this.partDAO.getComputersCount();
    }
}
