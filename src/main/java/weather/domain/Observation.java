package weather.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Observation {
	
	int id;
	
	@NotNull
	@Min(1)
	@Max(5)
	int city_id;
	
	@NotNull
	@Min(-60)
	@Max(80)
	double temp;
	
	Timestamp timestamp;
	
	public Observation(int id, int city_id, double temp, Timestamp timestamp) {
		super();
		this.id = id;
		this.city_id = city_id;
		this.temp = temp;
		this.timestamp = timestamp;
	}
	
	public Observation() {
		super();
		this.id = 0;
		this.city_id = 0;
		this.temp = 0.0;
		this.timestamp = null;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Observation [id=" + id + ", city_id=" + city_id + ", temp=" + temp + ", timestamp=" + timestamp + "]";
	}

}
