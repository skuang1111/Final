package exceptions;

public class RateException extends Exception {

	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	
	private rocketDomain.RateDomainModel RateDomainModel;
	
	public RateException (rocketDomain.RateDomainModel RateDomainModel) {
		this.RateDomainModel = RateDomainModel;
	}
	
	public rocketDomain.RateDomainModel getRateDomainModel() {
		return RateDomainModel;
	}
}
