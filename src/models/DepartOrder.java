package models;

import com.jfinal.plugin.activerecord.Model;
@SuppressWarnings("serial")
public class DepartOrder extends Model<DepartOrder> {
	public static final String COMBINE_TYPE_DEPART = "DEPART";
	public static final String COMBINE_TYPE_PICKUP = "PICKUP";
	
	public static final DepartOrder dao = new DepartOrder();
}
