package weather.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ObservationTempMapper implements RowMapper<Observation> {

	public Observation mapRow(ResultSet rs, int rowNum) throws SQLException {

		Observation observation = new Observation();
		observation.setTemp(rs.getDouble("temp"));
		return observation;
	}

}
