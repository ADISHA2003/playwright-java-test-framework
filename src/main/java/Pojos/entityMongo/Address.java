package Pojos.entityMongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Address")
public class Address {
	private String address;
	private String city;
	private String pincode;
	private String country;

}
