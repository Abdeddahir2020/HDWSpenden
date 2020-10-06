package de.hdw.repository.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import de.hdw.model.Spender;
import de.hdw.repository.SpenderRepository;

public class SpenderRepositoryImpl implements SpenderRepository{
	
	private static SessionFactory factory; 
	
	@Override
	public Set<String> getAllIds() {
		List<String> allSpender = new ArrayList<>();
		factory = new Configuration().configure().buildSessionFactory();
		 Session session = null;
		 Transaction tx = null;
		 session = factory.openSession();
	        try {
	        	tx = session.beginTransaction();
	        	allSpender= session.createQuery("SELECT spd.spender_iban FROM spender spd").list();
	        	
	           tx.commit();
	        } catch (HibernateException e) {
	           if (tx!=null) tx.rollback();
	           e.printStackTrace(); 
	        } finally {
	           session.close(); 
	        }
	        return new HashSet<String>(allSpender);
	}

	@Override
	public List<Spender> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Spender> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Spender> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Spender> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Spender> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Spender> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Spender getOne(Long id) {
		Spender spender = null;
		factory = new Configuration().configure().buildSessionFactory();
		Session session = null;
		Transaction tx = null;
		session = factory.openSession();
	    try {
        	tx = session.beginTransaction();
        	spender= (Spender) session.createQuery("SELECT spd.spender_iban FROM spender spd WHERE spd.spender_iban = id");
        	
           tx.commit();
        } catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return spender;
	}

	@Override
	public <S extends Spender> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Spender> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Spender> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Spender> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Spender> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Spender entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Spender> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Spender> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Spender> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Spender> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Spender> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

}
