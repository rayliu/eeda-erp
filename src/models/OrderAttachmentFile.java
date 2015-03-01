package models;

import com.jfinal.plugin.activerecord.Model;

public class OrderAttachmentFile extends Model<OrderAttachmentFile> {
	
	public static final String OTFRT_TYPE_RETURN = "RETURN";
	
	public static final OrderAttachmentFile dao = new OrderAttachmentFile();
}
