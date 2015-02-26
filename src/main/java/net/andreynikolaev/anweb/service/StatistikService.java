/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.service;

import java.io.Serializable;

import net.andreynikolaev.anweb.DAO.StatistikDAO;
import net.andreynikolaev.anweb.db.Statistik;
import net.andreynikolaev.anweb.dbutil.EntityDAO;
import net.andreynikolaev.anweb.dbutil.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andrey
 */
@Service("statistikService")
@Component
@Transactional
public class StatistikService extends EntityService<Statistik> implements  Serializable{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    StatistikDAO statistikDAO;
    
    @Override
    public EntityDAO getEntityDAO() {
        return statistikDAO;
    }

    @Override
    public void setEntityDAO(EntityDAO entityDAO) {
        statistikDAO = (StatistikDAO) entityDAO;
    }
    
}
