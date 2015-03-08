package ma.exampl.imagineapp.model;

public class Preference {
	// ==================================================================================

	private int id;
	private String preferenceName;
	private String PreferenceValue;

	// ==================================================================================

	public Preference() {
		super();
		// TODO Auto-generated constructor stub
	}

	// ==================================================================================

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// ==================================================================================

	public String getPreferenceName() {
		return preferenceName;
	}

	public void setPreferenceName(String preferenceName) {
		this.preferenceName = preferenceName;
	}

	// ==================================================================================

	public String getPreferenceValue() {
		return PreferenceValue;
	}

	public void setPreferenceValue(String preferenceValue) {
		PreferenceValue = preferenceValue;
	}
	// ==================================================================================

}
