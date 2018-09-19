package com.churakov.test.dao;

import com.churakov.test.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PartDAOImpl implements PartDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public PageHelper<Part> listParts(int pageNumber, Filter filter, String name) {

        final int PAGE_SIZE = 10;

        Session session = this.sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        //totalPages
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Part> countFrom = countQuery.from(Part.class);
        countQuery.select(criteriaBuilder.count(countFrom)).where(criteriaBuilder.and(getPredicates(criteriaBuilder, countFrom, filter, name)));
        long countResults = session.createQuery(countQuery).getSingleResult();
        long totalPages = (long) (Math.ceil(countResults / (double)PAGE_SIZE));

        //partList
        CriteriaQuery<Part> criteriaQuery = criteriaBuilder.createQuery(Part.class);
        Root<Part> from = criteriaQuery.from(Part.class);
        criteriaQuery.select(from).where(criteriaBuilder.and(getPredicates(criteriaBuilder, from, filter, name)));
        TypedQuery<Part> typedQuery = session.createQuery(criteriaQuery);

        typedQuery.setFirstResult((pageNumber - 1) * PAGE_SIZE);
        typedQuery.setMaxResults(PAGE_SIZE);

        List<Part> partList = typedQuery.getResultList();

        return new PageHelper<Part>(totalPages, partList);
        
    }

    private Predicate[] getPredicates(CriteriaBuilder cb, Root<Part> root, Filter filter, String name){
        ArrayList<Predicate> predicates = new ArrayList<Predicate>(2);

        if(filter != Filter.all) {
            predicates.add(cb.equal(root.get("need"), filter == Filter.need));
        }
        if(name!=null && name.trim().length()>0){
            predicates.add(cb.like(root.<String>get("name"), String.format("%%%s%%", name)));
        }

        return predicates.toArray(new Predicate[0]);

    }

    public void addPart(Part p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
    }

    public void removePart(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Part p = (Part) session.load(Part.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
    }

    public void updatePart(Part p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
    }

    public Part getPartById(int id) {
//        Session session = this.sessionFactory.getCurrentSession();
        Session session = this.sessionFactory.openSession();
        Part p = (Part) session.load(Part.class, new Integer(id));
        return p;
    }

    public Part getPartByName(String str) {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "from Part where name like :str";
        Query query = session.createQuery(hql);
        query.setParameter("str", "%"+str+"%");
        List results = query.list();
        if(results == null || results.size() == 0)
            return null;
        else
            return (Part)results.get(0);
    }

    public int getComputersCount() {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "SELECT min(p.count) from Part as p where p.need = true";
        Query query = session.createQuery(hql);
        List results = query.list();
        if(results == null || results.size() == 0)
            return 0;
        else
            return (Integer)results.get(0);
    }


}
