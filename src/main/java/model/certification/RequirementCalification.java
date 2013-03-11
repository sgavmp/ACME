package model.certification;

import javax.persistence.Embeddable;

@Embeddable
public class RequirementCalification extends Requirement {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Double calificationNum;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------

	public RequirementCalification() {
		super();
	}

	public RequirementCalification(Double calificationNum, String name,
			String description) {
		super(name, description);
		this.calificationNum = calificationNum;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public Double getCalificationNum() {
		return calificationNum;
	}

	public void setCalificationNum(Double calificationNum) {
		this.calificationNum = calificationNum;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((calificationNum == null) ? 0 : calificationNum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequirementCalification other = (RequirementCalification) obj;
		if (calificationNum == null) {
			if (other.calificationNum != null)
				return false;
		} else if (!calificationNum.equals(other.calificationNum))
			return false;
		return true;
	}

}
