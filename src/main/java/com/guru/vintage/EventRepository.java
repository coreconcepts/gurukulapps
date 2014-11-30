package com.guru.vintage;
//package com.guru.event;
//
//
//
//import java.lang.annotation.Annotation;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import com.mysql.jdbc.Driver;
//
//import javax.sql.DataSource;
//
//import common.DataBean;
//import common.DataRepository;
//
//@Repository
//public class EventRepositoryCustom implements DataRepository, IEventRepository {
//
//	@Override
//	public boolean add(DataBean dataBean) {
//		try {
//			com.mysql.jdbc.Driver driver = new Driver();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String sql  = "insert into gurukul.user(userId, role, location, firstname, lastname, password, securityphrase) "
//				+ " values(?,?, ?, ?, ?, ?, ?)";
//		int returnedValue = jdbcTemplate.update(sql, new Object[] { "aditya.a","branchmanager", 3, "aditya", "chaudhary", "pass1234", "thisissecure"});
//		if(returnedValue>0)
//			return true;
//		
//		return false;
//	}
//
//	private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void delete(Long arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(Event arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(Iterable<? extends Event> arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean exists(Long arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public Iterable<Event> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<Event> findAll(Iterable<Long> arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Event findOne(Long arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends Event> S save(S arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends Event> Iterable<S> save(Iterable<S> arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Event> findByEventName(String eventName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	   
//	}