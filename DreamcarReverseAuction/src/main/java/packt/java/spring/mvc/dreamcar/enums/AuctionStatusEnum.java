package packt.java.spring.mvc.dreamcar.enums;

public enum AuctionStatusEnum {

	Started(1), Ended(2);

	private int id;

	public int getId() {
		return this.id;
	}

	public static AuctionStatusEnum getType(int id) {
		switch (id) {
		case 1:
			return AuctionStatusEnum.Started;
		case 2:
			return AuctionStatusEnum.Ended;
		default:
			break;
		}

		return null;
	}

	private AuctionStatusEnum(int id) {
		this.id = id;
	}

}
