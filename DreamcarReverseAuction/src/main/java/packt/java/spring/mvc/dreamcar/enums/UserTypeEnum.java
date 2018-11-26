package packt.java.spring.mvc.dreamcar.enums;

public enum UserTypeEnum {
	AuctionOwner(1),
	Provider(2);
	
	private int id;
    public int getId() 
    { 
        return  this.id; 
    }
    
    public static UserTypeEnum getType(int id) 
    { 
    	switch(id){
    	case 1:
    		return UserTypeEnum.AuctionOwner;
		case 2:
    		return UserTypeEnum.Provider;
		default:
    		break;
    	}
    	
		return null;
    }
  
    private UserTypeEnum(int id) 
    { 
        this.id = id; 
    } 
}
