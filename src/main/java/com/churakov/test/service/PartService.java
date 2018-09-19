package com.churakov.test.service;

import com.churakov.test.dao.Filter;
import com.churakov.test.dao.PageHelper;
import com.churakov.test.model.Part;

public interface PartService {
    public PageHelper<Part> listParts(int page, Filter filter, String name);
    public void addPart(Part p);
    public void removePart(int id);
    public void updatePart(Part p);
    public Part getPartById(int id);
    public Part getPartByName(String str);
    public int getComputersCount();


}
