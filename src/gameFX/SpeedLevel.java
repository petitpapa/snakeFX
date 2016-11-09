package gameFX;

public enum SpeedLevel {
	SLOW(2),
	MEDIUM(5),
	FAST(15),
	EXTRA(30);
	
	private final int SpeedLevel;
	
	private SpeedLevel(int speed) {
		this.SpeedLevel = speed;
	}
	public  int getSpeedLevel() {
		return SpeedLevel;
	}
}
