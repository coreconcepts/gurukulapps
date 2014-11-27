package com.guru.event;



import java.lang.annotation.Annotation;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Driver;

import javax.sql.DataSource;

import common.DataBean;
import common.DataRepository;

@Repository
public class EventRepository implements DataRepository {

	@Override
	public boolean add(DataBean dataBean) {
		try {
			com.mysql.jdbc.Driver driver = new Driver();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql  = "insert into gurukul.user(userId, role, location, firstname, lastname, password, securityphrase) "
				+ " values(?,?, ?, ?, ?, ?, ?)";
		int returnedValue = jdbcTemplate.update(sql, new Object[] { "aditya.a","branchmanager", 3, "aditya", "chaudhary", "pass1234", "thisissecure"});
		if(returnedValue>0)
			return true;
		
		return false;
	}

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	   
	}