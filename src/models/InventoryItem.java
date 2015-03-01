package models;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class InventoryItem extends Model<InventoryItem> {
    public static final InventoryItem dao = new InventoryItem();
}
