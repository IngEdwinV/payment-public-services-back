package edu.escuelaing.arem.ASE.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class RolService {
    @Autowired    
    private JdbcTemplate jdbcTemplate;

    public List<String> create(){
        return jdbcTemplate.queryForList("SELECT rol FROM ROL", String.class);
    }
}
