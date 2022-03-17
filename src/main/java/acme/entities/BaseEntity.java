package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BaseEntity extends AbstractEntity {

	//Serialisation identifier ------------------------------

	protected static final long serialVersionUID = 1L;
	
	//Attributes ---------------------------------------------
	
	@NotBlank
	@Length(max = 100)
	protected String name;
	
	@Column(unique = true)
	@Pattern(regexp ="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;
	
	@NotBlank
	@Length(max = 100)
	protected String technology;
	
	@NotBlank
	@Length(max = 255)
	protected String description;
	
	protected Money retailPrice;
	
	@URL
	protected String link;
		
		
	@AssertTrue(message="Retail price must be positive")
	private boolean isMoneyPositive() {
		return this.retailPrice.getAmount() > 0.;
	}
}
