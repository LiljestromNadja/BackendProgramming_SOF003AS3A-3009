package hhBackEnd.week2.domain;

public class Plant {
	
	private String name, watering, light;
	
	
	public Plant(String name, String watering, String light) {
		super();
		this.name = name;
		this.watering = watering;
		this.light = light;
	}
	
	

	public Plant() {
		super();
	}
	
	public Plant(String name) {
		super();
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWatering() {
		return watering;
	}

	public void setWatering(String watering) {
		this.watering = watering;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	@Override
	public String toString() {
		return "Plant [name=" + name + ", watering=" + watering + ", light=" + light + "]";
	}


	
	
	
	
	

}
