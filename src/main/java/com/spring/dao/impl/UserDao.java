package com.spring.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import com.spring.dao.BaseDao;
import com.spring.entity.User;

@Repository("userDao")
public class UserDao implements BaseDao<User> {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveEntity(final User t) {
		String sql = "insert into users(username,password,email,tel,address,sex) values(?,?,?,?,?,?)";
		int result = jdbcTemplate.execute(sql, new PreparedStatementCallback<Integer>() {

			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, t.getUserName());
				ps.setString(2, DigestUtils.md5DigestAsHex(t.getPassword().getBytes()));
				ps.setString(3, t.getEmail());
				ps.setString(4, t.getTel());
				ps.setString(5, t.getAddress());
				ps.setObject(6, t.getSex(), Types.CHAR);// 使用java.sql.Types中提供的常量来映射数据库中的数据类型
				return ps.executeUpdate();
			}
		});
		return result;
	}

	@Override
	public int deleteEntity(final User t) {
		String sql = "delete from users where id=?";
		int reuslt = jdbcTemplate.execute(sql, new PreparedStatementCallback<Integer>() {

			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, t.getId());
				return ps.executeUpdate();
			}
		});
		return reuslt;
	}

	@Override
	public List<User> queryAll() {
		String sql = "select id,username as userName,password,email,tel,address from users";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));// 处理不了char类型的数据
	}

	@Override
	public List<Map<String, Object>> queryMap() {
		String sql = "select * from users";
		return jdbcTemplate.query(sql, new ColumnMapRowMapper());// 将没行数据处理为map类型的数据
	}

	@Override
	public User quertyEntity(String sql, final Object...objects) {
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				for(int i=0;i<objects.length;i++){
					ps.setObject(i+1, objects[i]);
				}
			}
		}, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();//必须调用
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setTel(rs.getString("tel"));
				user.setSex(rs.getString("sex")!=null?rs.getString("sex").charAt(0):'0');
				return user;
			}
		});
	}

}
