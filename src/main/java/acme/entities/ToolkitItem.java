package acme.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToolkitItem extends AbstractEntity{

	//Serialisation identifier ------------------------------

	protected static final long serialVersionUID = 1L; 
	
	//Attributes ---------------------------------------------
	
	@NotNull
	@ManyToOne
	protected Toolkit toolkit;
	
	@NotNull
	@ManyToOne
	protected Item item;
	
	@NotNull
	@Min(value = 1,message = "Value should be greater or equal to 1")
	@Max(value = 1000,message = "Value should be less or equal to 1000")
	protected Integer units;
	
	
}
