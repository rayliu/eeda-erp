package models;



import com.jfinal.plugin.activerecord.Model;
@SuppressWarnings("serial")
public class ReturnOrder extends Model<ReturnOrder> {
	public static final String Delivery_Order="delivery";
	public static final String Depart_Order="depart";
	public static final ReturnOrder dao = new ReturnOrder();
}