package org.green.crud1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class newTeacherDao {
	
	@Autowired
	private JdbcTemplate jTmp;
	
	
	// bean 객체로 지정하지 아니하고 
	// spel를 사용하여 직접 properties에서 불러옴
	
	@Value("#{sql['teacher.insert']")
	private String insert;
	
	@Value("#{sql['teacher.getAll']")
	private String getAll;
	
	
	
	public int insert(Teacher t){
	
		return jTmp.update(insert, t.getName(), t.getAge(), t.getSubject());
	}
		
	public List<Teacher> getAll(){
		
//		String sql = "SELECT * FROM teacher ORDER BY id DESC";
		return jTmp.query(getAll, new TeacherMapper());
//		return jTmp.query(sql, new TeacherMapper(), ? ,?, ? );
		
		
	}
	
	
	private class TeacherMapper implements RowMapper<Teacher> {

		
		// 루프 돌때마다 할일을 정의
		@Override
		public Teacher mapRow(ResultSet rs, int arg1) throws SQLException {
			
			return new Teacher(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getInt("age"),
					rs.getString("subject")					
					);
		}
		
		
	}

}
