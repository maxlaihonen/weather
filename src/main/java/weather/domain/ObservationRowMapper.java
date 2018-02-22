package weather.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ObservationRowMapper implements RowMapper<Observation> {

	public Observation mapRow(ResultSet rs, int rowNum) throws SQLException {

		Observation observation = new Observation();
		observation.setId(rs.getInt("id"));
		observation.setCity_id(rs.getInt("city_id"));
		observation.setTemp(rs.getDouble("temp"));
		observation.setTimestamp(rs.getTimestamp("date"));
		return observation;
	}

}
