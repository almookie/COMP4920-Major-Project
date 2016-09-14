
public class Subject {
	private String name;
	private String shortcode;
	
	public Subject(String name, String shortcode) {
		this.name = name;
		this.shortcode = shortcode;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(String name) {
		return name;
	}
	
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	
	public String getShortcode(String shortcode) {
		return shortcode;
	}
	
	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (!(o instanceof Subject)) return false;
		Subject subject = (Subject) o;
		return name.equals(subject.name);
	}
		
	@Override
	public int hashCode() {
		final int result = 17;
		return 37 * result + name.hashCode();
	}	

}
