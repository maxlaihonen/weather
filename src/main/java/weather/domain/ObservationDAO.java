package weather.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ObservationDAO {

		@Autowired
		private JdbcTemplate jdbcTemplate;

		public JdbcTemplate getJdbcTemplate() {
			return jdbcTemplate;
		}

		public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}
		
		public void save(Observation observation) {
			String sql = "insert into observations(city_id, temp) values(?,?)";
			Object[] parameters = new Object[] { observation.getCity_id(), observation.getTemp() };
			jdbcTemplate.update(sql, parameters);

		}

		public List<Observation> findByCity(int city_id) {
			String sql = "select id, city_id, temp, date from observations where city_id = ? ORDER BY date DESC";
			Object[] parameters = new Object[] { city_id };
			RowMapper<Observation> mapper = new ObservationRowMapper();
			List<Observation> observations = jdbcTemplate.query(sql, parameters, mapper);
			return observations;
		}
		
		public List<Observation> findByCityOrderByTemperature(int city_id) {
			String sql = "select temp from observations where city_id = ? AND date >= NOW() - INTERVAL 1 DAY ORDER BY temp ASC";
			Object[] parameters = new Object[] { city_id };
			RowMapper<Observation> mapper = new ObservationTempMapper();
			List<Observation> observationsasc = jdbcTemplate.query(sql, parameters, mapper);
			return observationsasc;
			
		}

		
	}
