package config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import models.Category;
import models.Party;
import models.PartyAttribute;
import models.yh.profile.Carinfo;
import models.yh.profile.Contact;

import com.jfinal.plugin.c3p0.C3p0Plugin;

public class DataInitUtil {
    public static void initH2Tables(C3p0Plugin cp) {
        try {
            cp.start();
            Connection conn = cp.getDataSource().getConnection();
            Statement stmt = conn.createStatement();

            // 登陆及授权的3个表
            stmt.executeUpdate("create table if not exists office(id bigint auto_increment primary key, office_code varchar(50), office_name varchar(50), office_person varchar(50),phone varchar(255),address varchar(255),email varchar(50),type varchar(50),company_intro varchar(255),remark varchar(255),belong_office bigint,location varchar(50),abbr varchar(100),is_stop boolean);");
            stmt.executeUpdate("create table if not exists user_login(id bigint auto_increment primary key, user_name varchar(50) not null, password varchar(50) not null, password_hint varchar(255), office_id bigint, token varchar(10),c_name varchar(50),is_stop boolean, foreign key(office_id) references office(id));");
            stmt.executeUpdate("create table if not exists role(id bigint auto_increment primary key,code varchar(50), name varchar(50), p_code varchar(50), remark varchar(255));");
            stmt.executeUpdate("create table if not exists permission(id bigint auto_increment primary key, module_name varchar(50) ,code varchar(50), name varchar(50), value varchar(50), url varchar(255), remark varchar(255));");
            stmt.executeUpdate("create table if not exists user_role(id bigint auto_increment primary key, user_name varchar(50) not null, role_code varchar(255) not null, remark varchar(255));");
            stmt.executeUpdate("create table if not exists role_permission(id bigint auto_increment primary key, role_code varchar(50) not null, permission_code varchar(50) not null, remark varchar(255));");
            
            stmt.executeUpdate("create table if not exists location(id bigint auto_increment primary key, code varchar(50) not null, name varchar(50), pcode varchar(255));");
            stmt.executeUpdate("create table if not exists fin_account(id bigint auto_increment primary key, type varchar(50), currency varchar(50), bank_name varchar(50), account_no varchar(50), bank_person varchar(50),remark varchar(255), creator bigint, office_id bigint, amount double,is_stop boolean);");
            //stmt.executeUpdate("create table if not exists fin_account_item(id bigint auto_increment primary key,account_id bigint,currency varchar(50),org_name varchar(50),account_pin varchar(50),org_person varchar(50));");
            stmt.executeUpdate("create table if not exists contract(id bigint auto_increment primary key, name varchar(50) not null, type varchar(50), party_id bigint,period_from timestamp,period_to timestamp, remark varchar(255));");
            
            stmt.executeUpdate("create table if not exists fin_item(id bigint auto_increment primary key,code varchar(20),name varchar(20),type varchar(20),driver_type varchar(20),remark varchar(255),is_stop boolean);");            
            stmt.executeUpdate("create table if not exists modules(id bigint auto_increment primary key,module_name varchar(50));");
            stmt.executeUpdate("create table if not exists modules_privilege(id bigint auto_increment primary key,module_id bigint,privilege_id bigint);");
            stmt.executeUpdate("create table if not exists contract_item(id bigint auto_increment primary key,product_id bigint,contract_id bigint,fin_item_id bigint,pricetype varchar(50),cartype varchar(255),carlength varchar(255),ltlunittype varchar(50), from_id varchar(50),location_from varchar(50),to_id varchar(50),location_to varchar(50) ,amount double,remark varchar(255),unit varchar(20),dayFrom varchar(50),dayTo varchar(50),amountFrom double,amountTo double,kilometer varchar(50));");

            // fin_item
            stmt.executeUpdate("create table if not exists fin_item(id bigint auto_increment primary key,code varchar(50),name varchar(50),type varchar(50),remark varchar(50));");
            // fin_item
            stmt.executeUpdate("create table if not exists delivery_order_fin_item(id bigint auto_increment primary key,order_id bigint,fin_item_id bigint,fin_item_code varchar(50),amount double,status varchar(20),creator bigint,create_date timestamp,last_updator bigint,last_updator_date timestamp,remark varchar(5120),create_name varchar(50),delivery_plan_order_id bigint);");
            // eeda 平台的SP
            stmt.executeUpdate("create table if not exists dp_prof_provider_info(OID bigint auto_increment PRIMARY KEY, ADDITIONAL_SERVICES VARCHAR(600),  BIZNATURE VARCHAR(60),  PROVIDER_SYS_CODE VARCHAR(90),  PROVIDER_NAME  VARCHAR(270),  PROVIDER_BIZ_CODE VARCHAR(60),  MAINTENANCE_OFFICE  VARCHAR(90),  COUNTRY_BAK VARCHAR(90),  PROVINCE_BAK VARCHAR(90),  CITY_BAK VARCHAR(90),  POST_CODE   VARCHAR(90),  CONTACT VARCHAR(120),  FAX_BAK VARCHAR(60),  EMAIL   VARCHAR(450),  TELEPHONE_BAK   VARCHAR(90),  ADDRESS1 VARCHAR(300),  ADDRESS2 VARCHAR(300),  ADDRESS3 VARCHAR(300),  ADDRESS4 VARCHAR(300),  STATUS  CHAR(1) default 'A',  CREATOR VARCHAR(20),  CREATE_DATE DATE,  LAST_UPDATER VARCHAR(20),  LAST_UPDATE_DATE DATE,  COUNTRY_OID bigint,  COUNTRY VARCHAR(300),  PROVINCE_OID bigint,  PROVINCE VARCHAR(300),  CITY_OID bigint,  CITY VARCHAR(300),  PHONE_COUNTRY_CODE  VARCHAR(10),  PHONE_AREA_CODE VARCHAR(10),  PHONE_NO VARCHAR(120),  FAX_COUNTRY_CODE VARCHAR(10),  FAX_AREA_CODE   VARCHAR(10),  FAX_NO  VARCHAR(120),  SPPM_OID bigint,  PROVIDER_FULL_NAME  VARCHAR(300),  CONTROL_OFFICE  VARCHAR(90),  DATA_REALM  VARCHAR(20),  COPY_FROM_SP_OID bigint,  ONE_OFF VARCHAR(1) default 'N',  EFFECTIVE_FROM  DATE,  EFFECTIVE_TO DATE, MAIL_SENT_TIME TIMESTAMP);");

            // 配送单
            stmt.executeUpdate("create table if not exists delivery_order(id bigint auto_increment primary key,order_no varchar(50),customer_id bigint,sp_id bigint,notify_party_id bigint,appointment_stamp timestamp,status varchar(50),audit_status varchar(255),sign_status varchar(255),cargo_nature varchar(20),from_warehouse_id bigint,remark varchar(255),route_from varchar(255),route_to varchar(255),priceType varchar(50),create_by bigint,create_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,business_stamp date,client_order_stamp date,order_delivery_stamp date,client_requirement varchar(1000),customer_delivery_no varchar(50),ltl_price_type varchar(20),car_type varchar(255),delivery_plan_type varchar(50));");

            // delivery_order_milestone 配送单里程碑
            stmt.executeUpdate("create table if not exists delivery_order_milestone(id bigint auto_increment primary key,status varchar(255),location varchar(255),create_by bigint,create_stamp timestamp,last_modified_by bigint,"
                    + "last_modified_stamp timestamp,delivery_id bigint,"
                    + "arrival_time date,performance varchar(255),unfinished_reason varchar(255),finished_seral varchar(10),unseral_reason varchar(255),unusual_handle varchar(255),reimbursement_id bigint,"
                    + "foreign key(delivery_id) references delivery_order(id));");

            // 干线表
            stmt.executeUpdate("create table if not exists route(id bigint auto_increment primary key,from_id varchar(50), location_from varchar(50) not null,to_id varchar(50), location_to varchar(50) not null, remark varchar(255));");

            stmt.executeUpdate("create table if not exists leads(id bigint auto_increment primary key, "
                    + "title varchar(255), priority varchar(50), create_date timestamp, creator varchar(50), status varchar(50),"
                    + "type varchar(50), region varchar(50), addr varchar(256), "
                    + "intro varchar(5120), remark varchar(5120), lowest_price decimal(20, 2), agent_fee decimal(20, 2), "
                    + "introducer varchar(256), sales varchar(256), follower varchar(50), follower_phone varchar(50),"
                    + "owner varchar(50), owner_phone varchar(50), area decimal(10,2), total decimal(10,2), customer_source varchar(50), "
                    + "building_name varchar(255), building_unit varchar(50), building_no varchar(50), room_no varchar(50), is_have_car char(1) default 'N',"
                    + "is_public char(1) default 'N');");

            stmt.executeUpdate("create table if not exists support_case(id bigint auto_increment primary key, title varchar(255), type varchar(50), create_date timestamp, creator varchar(50), status varchar(50), case_desc varchar(5120), note varchar(5120));");

            stmt.executeUpdate("create table if not exists order_header(id bigint auto_increment primary key, order_no varchar(50) not null, type varchar(50), status varchar(50), creator varchar(50), create_date timestamp, remark varchar(256));");
            stmt.executeUpdate("create table if not exists order_item(id bigint auto_increment primary key, order_id bigint, item_name varchar(50), item_desc varchar(50), quantity decimal(10,2), unit_price decimal(10,2), status varchar(50), foreign key(order_id) references order_header(id) );");
            // party 当事人，可以有各种type
            stmt.executeUpdate("create table if not exists party(id bigint auto_increment primary key, party_type varchar(32), contact_id bigint, create_date timestamp, creator varchar(50), last_update_date timestamp, last_updator varchar(50), status varchar(50),remark varchar(255),receipt varchar(50),payment varchar(50), charge_type varchar(50),office_id bigint,is_stop boolean,insurance_rates double);");
            stmt.executeUpdate("create table if not exists party_attribute(id bigint auto_increment primary key, party_id bigint, attr_name varchar(60), attr_value varchar(255), create_date timestamp, creator varchar(50), foreign key(party_id) references party(id));");
            stmt.executeUpdate("create table if not exists contact(id bigint auto_increment primary key,company_id bigint,license varchar(20),identification varchar(50), company_name varchar(100),sp_type varchar(60),abbr varchar(60), contact_person varchar(100),location varchar(255),introduction varchar(255),email varchar(100), mobile varchar(100), phone varchar(100), address varchar(255), city varchar(100), postal_code varchar(60),"
                    + " create_date timestamp, last_updated_stamp timestamp);");

            // category 类别
            stmt.executeUpdate("create table if not exists category(id bigint auto_increment primary key,name varchar(255),parent_id bigint,foreign key(parent_id) references category(id),customer_id bigint,foreign key(customer_id) references party(id));");

            // product 产品
            stmt.executeUpdate("create table if not exists product(id bigint auto_increment primary key,item_name varchar(50),item_no varchar(255),serial_no varchar(255),size double,width double,height double,unit varchar(255),volume double,weight double,item_desc varchar(5120),category_id bigint,insurance_amount double,is_stop boolean,foreign key(category_id) references category(id));");

            // warehouse 仓库
            stmt.executeUpdate("create table if not exists warehouse(id bigint auto_increment primary key,warehouse_name varchar(50),warehouse_address varchar(255),warehouse_area double,path varchar(255),warehouse_type varchar(255),notify_name varchar(50),notify_mobile varchar(50),location varchar(255),status varchar(20),code varchar(20),warehouse_desc varchar(5120),sp_id bigint,office_id bigint,foreign key(sp_id) references party(id),foreign key(office_id) references office(id),sp_name varchar(255));");

            // order_status 里程碑
            stmt.executeUpdate("create table if not exists order_status(id bigint auto_increment primary key,status_code varchar(20),status_name varchar(20),order_type varchar(20),remark varchar(255));");

            // return_order 回单
            stmt.executeUpdate("create table if not exists return_order(id bigint auto_increment primary key, order_no varchar(50), status_code varchar(20),create_date timestamp,receipt_date timestamp,transaction_status varchar(20),order_type varchar(20),creator bigint,remark varchar(5120),import_ref_num varchar(255), _id bigint, delivery_order_id bigint,transfer_order_id bigint, notity_party_id bigint,customer_id bigint,total_amount double,path varchar(255));");
            // Depart_Order_fin_item 回单应收明细
            stmt.executeUpdate("create table if not exists return_order_fin_item (id bigint auto_increment primary key, return_order_id bigint, fin_item_id bigint, "
                    + "fin_item_code varchar(20), amount double, status varchar(50), creator varchar(50), create_date timestamp, last_updator varchar(50), last_update_date timestamp, remark varchar(5120),transfer_order_id bigint,delivery_order_id bigint, contract_id bigint, fin_type varchar(20),create_name varchar(50));");

            // 保险单
            stmt.executeUpdate("create table if not exists insurance_order(id bigint auto_increment primary key,order_no varchar(255),ref_no varchar(255),status varchar(255),audit_status varchar(255),sign_status varchar(255),location varchar(255),create_by bigint,create_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,remark varchar(5120),office_id bigint,foreign key(office_id) references office(id));");
            
            // transfer_order 运输单  no_contract_revenue boolean在mysql中对应 tinyint
            stmt.executeUpdate("create table if not exists transfer_order(id bigint auto_increment primary key,order_no varchar(255),customer_order_no varchar(255),status varchar(255),pickup_assign_status varchar(255),depart_assign_status varchar(255),"
                    + "cargo_nature varchar(255),cargo_nature_detail varchar(255),inventory_id bigint,pickup_mode varchar(255),arrival_mode varchar(255),charge_type varchar(50), ltl_unit_type varchar(50), charge_type2 varchar(50), receiving_unit varchar(255),remark varchar(255),operation_type varchar(255),pickup_seq varchar(255),payment varchar(50),car_size varchar(255),car_no varchar(255),car_type varchar(255),create_by bigint,"
                    + "create_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,eta timestamp,planning_time date,arrival_time date,address varchar(255),customer_province varchar(255),route_from varchar(255),route_to varchar(255),order_type varchar(255),"
                    + "no_contract_revenue boolean default false, no_contract_cost boolean default false, "
                    + "customer_id bigint,sp_id bigint,notify_party_id bigint,driver_id bigint,warehouse_id bigint,office_id bigint,insurance_id bigint,foreign key(customer_id) references party(id),foreign key(sp_id) references party(id),car_summary_order_share_ratio double,"
                    + "foreign key(notify_party_id) references party(id),foreign key(driver_id) references party(id),foreign key(warehouse_id) references warehouse(id),foreign key(office_id) references office(id),foreign key(insurance_id) references insurance_order(id));");
            // transfer_order_item 货品明细
            stmt.executeUpdate("create table if not exists transfer_order_item(id bigint auto_increment primary key,item_no varchar(255),item_name varchar(255),item_desc varchar(255),pickup_number double,"
                    + "amount double,complete_amount double,size double,width double,height double,unit varchar(255),volume double,weight double,sum_weight double,remark varchar(5120),order_id bigint,foreign key(order_id) references transfer_order(id),product_id bigint,foreign key(product_id) references product(id));");

            // carinfo 车辆信息表
            stmt.executeUpdate("create table if not exists carinfo(id bigint auto_increment primary key,type varchar(50),driver varchar(50),phone varchar(50),car_no varchar(50),cartype varchar(50),"
                    + "status varchar(50),length double,hundred_fuel_standard double,rated_load double,rated_cube double,initial_mileage double,identity_number varchar(20),family_contact varchar(20),mobile varchar(100),remark varchar(5120),office_id bigint,is_stop boolean);");

            // 提货单/发车单
            stmt.executeUpdate("create table if not exists depart_order(id bigint auto_increment primary key,depart_no varchar(255),status varchar(255),audit_status varchar(255),sign_status varchar(255),create_by bigint,create_stamp timestamp,combine_type varchar(255),pickup_mode varchar(255),address varchar(255),"
                    + "car_size varchar(255),car_no varchar(255),car_type varchar(255),driver varchar(255),phone varchar(255),car_follow_name varchar(255),car_follow_phone varchar(255),route_from varchar(255),route_to varchar(255),kilometres double,road_bridge double,income double,payment double,arrival_time date,departure_time date,remark varchar(5120),import_ref_num varchar(255), charge_type varchar(50), driver_id bigint,transfer_type varchar(50),"
                    + "ltl_price_type varchar(20),booking_note_number varchar(50), foreign key(driver_id) references party(id),sp_id bigint,foreign key(sp_id) references party(id),warehouse_id bigint,foreign key(warehouse_id) references warehouse(id),carinfo_id bigint,foreign key(carinfo_id) references carinfo(id),car_summary_type varchar(50),turnout_time date,return_time date);");

            // Depart_Order_fin_item 提货单/发车单应付明细
            stmt.executeUpdate("create table if not exists depart_order_fin_item (id bigint auto_increment primary key, depart_order_id bigint, transfer_order_id bigint, transfer_order_item_id bigint, fin_item_id bigint, "
                    + "fin_item_code varchar(20), amount double, status varchar(50), creator varchar(50), create_date timestamp, last_updator varchar(50), last_update_date timestamp, remark varchar(5120),create_name varchar(50));");
            
            stmt.executeUpdate("create table if not exists pickup_order_fin_item (id bigint auto_increment primary key, pickup_order_id bigint, fin_item_id bigint, "
            		+ "fin_item_code varchar(20), amount double, status varchar(50), creator varchar(50), create_date timestamp, last_updator varchar(50), last_update_date timestamp, remark varchar(5120));");

            // Transfer_Order_item_detail 单件货品明细
            stmt.executeUpdate("create table if not exists transfer_order_item_detail(id bigint auto_increment primary key,order_id bigint,item_id bigint,item_no varchar(255),"
                    + "serial_no varchar(255),item_name varchar(255),status varchar(255),item_desc varchar(255),unit varchar(255),volume double,weight double,notify_party_id bigint,"
                    + "remark varchar(5120), is_delivered boolean default false, is_damage boolean,estimate_damage_amount double,damage_revenue double,damage_payment double,pieces int,damage_remark varchar(255),"
                    + "notify_party_company varchar(200), notify_party_name varchar(50), notify_party_phone varchar(20),business_manager varchar(50),station_name varchar(200),responsible_person varchar(50),service_telephone varchar(20),sales_order_no varchar(20)," 
                    + "pickup_id bigint,foreign key(pickup_id) references depart_order(id),depart_id bigint,foreign key(depart_id) references depart_order(id),foreign key(order_id) references transfer_order(id),"
                    + "foreign key(item_id) references transfer_order_item(id),foreign key(notify_party_id) references party(id),delivery_id bigint,foreign key(delivery_id) references delivery_order(id));");

            // Transfer_Order_fin_item 运输单应收应付明细
            stmt.executeUpdate("create table if not exists transfer_order_fin_item (id bigint auto_increment primary key, " +
            		"order_id bigint,depart_id bigint,delivery_id bigint, fin_item_id bigint,depart_order_fin_item_id bigint, contract_id bigint,"
                    + "fin_item_code varchar(20), amount double, status varchar(50), fin_type varchar(20), "
                    + "creator varchar(50), create_date timestamp, last_updator varchar(50), last_update_date timestamp,rate double,remark varchar(5120),create_name varchar(50));");

            // billing_order 应收应付单主表 --total_amount 应收(付)总额, total_actual_amount
            // 实收(付)总额
            stmt.executeUpdate("create table if not exists billing_order(id bigint auto_increment primary key, blling_order_no varchar(255), "
                    + "order_type varchar(50), customer_id bigint, customer_type varchar(50), charge_account_id bigint, payment_account_id bigint, status varchar(255),"
                    + "transfer_order_id bigint, delivery_order_id bigint, remark varchar(1024), creator bigint, create_stamp timestamp,last_modified_by bigint,"
                    + "last_modified_stamp timestamp, approver bigint, approve_date timestamp, total_amount double, total_actual_amount double);");
            // billing_order_item 应收应付单从表
            stmt.executeUpdate("create table if not exists billing_order_item(id bigint auto_increment primary key,blling_order_id bigint, "
                    + "charge_account_id bigint, payment_account_id bigint, status varchar(255), amount double, remark varchar(1024),"
                    + "creator bigint, create_stamp timestamp,last_modified_by bigint,"
                    + "last_modified_stamp timestamp, approver bigint, approve_date timestamp);");
            // stmt.executeUpdate(
            stmt.executeUpdate("create table if not exists pickup_order_item(id bigint auto_increment primary key,order_id bigint,customer_id bigint,serial_no varchar(50),item_no bigint,item_name varchar(50),item_desc varchar(50),amount double,unit varchar(50),volume double,weight double,remark varchar(255));");
            // 配送单货品表
            stmt.executeUpdate("create table if not exists delivery_order_item(id bigint auto_increment primary key,transfer_no varchar(50),delivery_id bigint,transfer_order_id bigint, transfer_item_id bigint, amount double, transfer_item_detail_id bigint,product_id bigint,product_number double);");
            //配送调车单
            stmt.executeUpdate("create table if not exists delivery_plan_order(id bigint auto_increment primary key,order_no varchar(50),status varchar(50),office_id bigint,create_id bigint,create_stamp timestamp,sp_id bigint,carinfo_id bigint,car_no varchar(50),driver varchar(50),phone varchar(50),turnout_time date,return_time date,remark varchar(500));");
            stmt.executeUpdate("create table if not exists delivery_plan_order_detail(id bigint auto_increment primary key,order_id bigint,delivery_id bigint);");
            stmt.executeUpdate("create table if not exists delivery_plan_order_milestone(id bigint auto_increment primary key,order_id varchar(50),idstatus varchar(50),address varchar(50),create_id bigint,create_stamp timestamp);");
            stmt.executeUpdate("create table if not exists delivery_plan_order_carinfo(id bigint auto_increment primary key,order_id bigint,carinfo_id bigint,car_no varchar(50),driver varchar(50),phone varchar(50));");
            
            // 发车单运输单中间表
            stmt.executeUpdate("create table if not exists depart_transfer(id bigint auto_increment primary key,pickup_id bigint,depart_id bigint,order_id bigint,transfer_order_no varchar(255),amount double,order_item_id bigint,belong_depart_id bigint,foreign key(depart_id) references depart_order(id),foreign key(order_id) references transfer_order(id),foreign key(pickup_id) references depart_order(id));");

            // transfer_order_milestone 运输单里程碑


            stmt.executeUpdate("create table if not exists transfer_order_milestone(id bigint auto_increment primary key,status varchar(255),location varchar(255),exception_record varchar(255),create_by bigint,create_stamp timestamp,last_modified_by bigint,"
                    + "last_modified_stamp timestamp,type varchar(255),order_id bigint,foreign key(order_id) references transfer_order(id),pickup_id bigint,depart_id bigint,foreign key(pickup_id) references depart_order(id),car_summary_id bigint);");


            stmt.executeUpdate("create table if not exists warehouse_order(id bigint auto_increment primary key,party_id bigint,warehouse_id bigint,order_no varchar(50),order_type varchar(50),status varchar(50),"
                    + "qualifier varchar(50),remark varchar(255),creator bigint,create_date datetime,last_updater bigint,last_update_date datetime);");

            stmt.executeUpdate("create table if not exists warehouse_order_item(id bigint auto_increment primary key,warehouse_order_id bigint,product_id bigint,item_no varchar(50),item_name varchar(50),item_desc varchar(255),expire_date datetime,"
                    + "lot_no varchar(50),uom varchar(20),caton_no varchar(50),total_quantity double,unit_price double,unit_cost double,serial_no varchar(50),remark varchar(255),creator bigint,create_date datetime,last_updater bigint,last_update_date datetime);");

            stmt.executeUpdate("create table if not exists inventory_item(id bigint auto_increment primary key,party_id bigint,warehouse_id bigint,product_id bigint,item_no varchar(50),item_name varchar(50),status varchar(50),expire_date datetime,"
                    + "lot_no varchar(50),uom varchar(20),caton_no varchar(50),total_quantity double,unit_price double,unit_cost double,serial_no varchar(50),remark varchar(255),creator bigint,create_date datetime,last_updater bigint,last_update_date datetime,available_quantity double);");

            // 应收对账单
            stmt.executeUpdate("create table if not exists arap_charge_order(id bigint auto_increment primary key,order_no varchar(255),order_type varchar(255),status varchar(255),payee_id bigint,create_by bigint,create_stamp timestamp,"
                    + " begin_time date,end_time date,last_modified_by bigint,last_modified_stamp timestamp,remark varchar(5120),total_amount double,debit_amount double,charge_amount double);");
            stmt.executeUpdate("create table if not exists arap_charge_item(id bigint auto_increment primary key,ref_order_type varchar(255),item_code varchar(255),item_status varchar(255),create_by bigint,create_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,"
            		+ " remark varchar(5120),charge_order_id bigint,foreign key(charge_order_id) references arap_charge_order(id),ref_order_id bigint,foreign key(ref_order_id) references return_order(id));");
            stmt.executeUpdate("create table if not exists arap_audit_invoice(id bigint auto_increment primary key,order_no varchar(255),status varchar(255),create_by bigint,create_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,remark varchar(5120));");
            
            // 应付对账单
            stmt.executeUpdate("create table if not exists arap_cost_invoice_application_order(id bigint auto_increment primary key,order_no varchar(255),status varchar(255),payment_method varchar(255),create_by bigint,create_stamp timestamp,audit_by bigint,audit_stamp timestamp,approver_by bigint,"
            		+ " approval_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,remark varchar(5120),payee_id bigint,account_id bigint,foreign key(account_id) references fin_account(id),total_amount double);");
            stmt.executeUpdate("create table if not exists arap_cost_order(id bigint auto_increment primary key,order_no varchar(255),order_type varchar(255),status varchar(255),amount double,payee_id bigint,create_by bigint,confirm_by bigint,begin_time timestamp,end_time timestamp,"
            		+ " create_stamp timestamp,confirm_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,remark varchar(5120),application_order_id bigint,foreign key(application_order_id) references arap_cost_invoice_application_order(id),total_amount double,debit_amount double,cost_amount double);");
            stmt.executeUpdate("create table if not exists arap_cost_item(id bigint auto_increment primary key,ref_order_no varchar(255),item_code varchar(255),item_status varchar(255),create_by bigint,create_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,"
            		+ " remark varchar(5120),ref_order_id bigint,cost_order_id bigint,foreign key(cost_order_id) references arap_cost_order(id));");
            stmt.executeUpdate("create table if not exists arap_cost_invoice(id bigint auto_increment primary key,order_no varchar(255),status varchar(255),create_by bigint,create_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,remark varchar(5120));");
            stmt.executeUpdate("create table if not exists arap_cost_invoice_item_invoice_no(id bigint auto_increment primary key,invoice_no varchar(255),amount double,invoice_id bigint,foreign key(invoice_id) references arap_cost_invoice_application_order(id),payee_id bigint);");
            stmt.executeUpdate("create table if not exists arap_cost_order_invoice_no(id bigint auto_increment primary key,invoice_no varchar(255),cost_order_id bigint,foreign key(cost_order_id) references arap_cost_order(id),application_order_id bigint,foreign key(application_order_id) references arap_cost_invoice_application_order(id),payee_id bigint);");
             
            stmt.executeUpdate("create table if not exists arap_charge_invoice_application_order(id bigint auto_increment primary key,order_no varchar(255),status varchar(255),payment_method varchar(255),create_by bigint,create_stamp timestamp,audit_by bigint,audit_stamp timestamp,"
            		+ " approver_by bigint,approval_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,remark varchar(5120),total_amount double,payee_id bigint,account_id bigint,foreign key(account_id) references fin_account(id),invoice_order_id bigint);");
           
            // 开票申请从表
            stmt.executeUpdate("create table if not exists arap_charge_invoice_application_item(id bigint auto_increment primary key,invoice_application_id bigint,foreign key(invoice_application_id) references arap_charge_invoice_application_order(id),charge_order_id bigint,foreign key(charge_order_id) references arap_charge_order(id));");

            // 开票单费用表
            stmt.executeUpdate("create table if not exists arap_charge_invoice(id bigint auto_increment primary key,order_no varchar(255),status varchar(255),create_by bigint,create_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,remark varchar(5120),payee_id bigint,total_amount double);");
            
            // 开票单费用从表
            stmt.executeUpdate("create table if not exists arap_charge_invoice_item_invoice_no(id bigint auto_increment primary key,invoice_no varchar(255),amount double,invoice_id bigint,foreign key(invoice_id) references arap_charge_invoice(id));");
            
            // 开票单中间表
            stmt.executeUpdate("create table if not exists arap_charge_application_invoice_no(id bigint auto_increment primary key,invoice_no varchar(255),application_order_id bigint,foreign key(application_order_id) references arap_charge_invoice_application_order(id),invoice_order_id bigint,foreign key(invoice_order_id) references arap_charge_invoice(id));");
            
            // 保险单费用从表
            stmt.executeUpdate("create table if not exists insurance_fin_item(id bigint auto_increment primary key,status varchar(255),location varchar(255),insurance_category varchar(255),amount double,rate double,income_rate double,insurance_amount double,insurance_no varchar(255),create_by bigint,"
            		+ " create_stamp timestamp,last_modified_by bigint,last_modified_stamp timestamp,fin_item_id bigint,foreign key(fin_item_id) references fin_item(id),insurance_order_id bigint,foreign key(insurance_order_id) references insurance_order(id),transfer_order_item_id bigint,foreign key(transfer_order_item_id) references transfer_order_item(id));");
            
            //行车单
            stmt.execute("create table if not exists car_summary_order(id bigint auto_increment primary key,order_no varchar(50),status varchar(50),car_no varchar(50),main_driver_name varchar(50),main_driver_amount double,minor_driver_name varchar(50),"
            		+ "minor_driver_amount double,start_car_mileage double,finish_car_mileage double,month_start_car_next int,month_car_run_mileage double,month_refuel_amount double,next_start_car_mileage double,"
            		+ "next_start_car_amount double,deduct_apportion_amount double,actual_payment_amount double,create_data timestamp, reimbursement_order_id bigint);");
            
            //（行车单-拼车单）关联表
            stmt.execute("create table if not exists car_summary_detail(id bigint auto_increment primary key,car_summary_id bigint,pickup_order_id  bigint,pickup_order_no varchar(50));");
            
            //行车单路桥费明细
            stmt.execute("create table if not exists car_summary_detail_route_fee(id bigint auto_increment primary key,"
            		+ "car_summary_id bigint,item bigint,charge_data timestamp,charge_site varchar(100),travel_amount double,remark varchar(500));");
            
            //行车单加油记录
            stmt.execute("create table if not exists car_summary_detail_oil_fee(id bigint auto_increment primary key,car_summary_id bigint,"
            		+ "item bigint,refuel_data timestamp,odometer_mileage double,refuel_site varchar(200),refuel_type varchar(200),"
            		+ "refuel_unit_cost double,refuel_number double,refuel_amount double,payment_type varchar(50), load_amount double,avg_econ double,remark varchar(500));");
            
            //行车单送货员工资明细
            stmt.execute("create table if not exists car_summary_detail_salary(id bigint auto_increment primary key,car_summary_id bigint,item bigint,username varchar(50),salary_sheet varchar(30),work_type varchar(100),deserved_amount double,create_data timestamp,remark varchar(500));");
            
            //行车单费用合计
            stmt.execute("create table if not exists car_summary_detail_other_fee(id bigint auto_increment primary key,car_summary_id bigint,item bigint,amount_item varchar(100),amount double,is_delete varchar(4),remark varchar(500));");
            
            //execl标题表
            stmt.execute("create table if not exists execl_title(id bigint auto_increment primary key,execl_type varchar(50),execl_title varchar(100));");
            
            // 手工收款单
            stmt.execute("create table if not exists arap_misc_charge_order(id bigint auto_increment primary key,order_no varchar(255),type varchar(50),status varchar(50),remark varchar(255),payment_method varchar(255),create_by bigint,create_stamp timestamp,total_amount double,payee_id bigint,charge_order_id bigint,foreign key(charge_order_id) references arap_charge_order(id),account_id bigint,foreign key(account_id) references fin_account(id));");
            stmt.execute("create table if not exists arap_misc_charge_order_item(id bigint auto_increment primary key,fin_item_id bigint,fin_item_code varchar(20),amount double,status varchar(50),creator varchar(50),create_date timestamp,last_updator varchar(50),last_update_date timestamp,remark varchar(5120),misc_order_id bigint,foreign key(misc_order_id) references arap_misc_charge_order(id));");
            
            // 手工付款单
            stmt.execute("create table if not exists arap_misc_cost_order(id bigint auto_increment primary key,order_no varchar(255),type varchar(50),status varchar(50),remark varchar(255),payment_method varchar(255),create_by bigint,create_stamp timestamp,total_amount double,payee_id bigint,cost_order_id bigint,foreign key(cost_order_id) references arap_cost_order(id),account_id bigint,foreign key(account_id) references fin_account(id));");
            stmt.execute("create table if not exists arap_misc_cost_order_item(id bigint auto_increment primary key,fin_item_id bigint,fin_item_code varchar(20),amount double,status varchar(50),creator varchar(50),create_date timestamp,last_updator varchar(50),last_update_date timestamp,remark varchar(5120),misc_order_id bigint,foreign key(misc_order_id) references arap_misc_cost_order(id));");
            
            // 出纳日记账:
            //payment_method: cash(现金)， transfer (转账)
            //payment_type  : charge(收款)，cost(付款)
            stmt.execute("create table if not exists arap_account_audit_log(id bigint auto_increment primary key, payment_method varchar(255), payment_type varchar(50), amount double,creator varchar(50),create_date timestamp,remark varchar(5120),source_order varchar(255), misc_order_id bigint,foreign key(misc_order_id) references arap_misc_charge_order(id),invoice_order_id bigint,foreign key(invoice_order_id) references arap_charge_invoice(id),account_id bigint,foreign key(account_id) references fin_account(id));");
            // 基础信息——单位表
            //stmt.execute("create table if not exists tally(id bigint auto_increment primary key,code varchar(20),name varchar(20));");
            stmt.execute("create table if not exists user_office(id bigint auto_increment primary key,user_name varchar(20),office_id bigint,is_main boolean);");
            stmt.execute("create table if not exists user_customer(id bigint auto_increment primary key,user_name varchar(20),customer_id bigint);");
            
            //报销单
            stmt.execute("create table if not exists reimbursement_order(id bigint auto_increment primary key,order_no varchar(50),status varchar(50),account_name varchar(50),account_no varchar(50),payment_type varchar(30),invoice_payment varchar(30),"
            		+ "amount double,create_id bigint,create_stamp timestamp,audit_id bigint,audit_stamp timestamp,approval_id bigint,approval_stamp timestamp,remark varchar(500), car_summary_order_ids varchar(100));");
            
            //报销单明细
            stmt.execute("create table if not exists reimbursement_order_fin_item(id bigint auto_increment primary key,order_id varchar(50),fin_item_id bigint,invoice_amount double, revocation_amount double,fin_attribution_id bigint, remark varchar(500));");
            
            //配送调车单-主表
            stmt.execute("create table if not exists delivery_plan_order(id bigint auto_increment primary key,order_no varchar(50),status varchar(50),office_id bigint,create_id bigint,create_stamp timestamp,sp_id bigint,carinfo_id bigint,"
            		+ "car_no varchar(50),driver varchar(50), phone varchar(50), turnout_time date,return_time date,remark varchar(500));");
            
            //配送调车单-从表
            stmt.execute("create table if not exists delivery_plan_order_detail(id bigint auto_increment primary key,order_id varchar(50),delivery_id bigint);");
            
            //配送调车单-里程碑
            stmt.execute("create table if not exists delivery_plan_order_milestone(id bigint auto_increment primary key,order_id varchar(50),status varchar(50),address varchar(50),create_id bigint,create_stamp timestamp);");
            
            //发车单从表，记录此次发车的调车单
            stmt.execute("create table if not exists depart_pickup(id bigint auto_increment primary key,depart_id bigint,pickup_id bigint,order_id bigint);");
            
            //自定义字段表，记录不同公司的单据的显示名称
            stmt.execute("create table if not exists customize_field(id bigint auto_increment primary key, order_type varchar(50), office_id bigint, field_code varchar(50), field_name varchar(50), field_desc varchar(500), is_hidden tinyint(1),customize_name varchar(50), remark varchar(500));");
            
            //自定义系统名字，logo, 登陆背景
            stmt.execute("create table if not exists office_config(id bigint auto_increment primary key, office_id bigint, system_title varchar(50), logo varchar(250), login_bg varchar(250), domain varchar(250));");
           
            //单据附件表，记录上传文件信息
            stmt.execute("create table if not exists order_attachment_file(id bigint auto_increment primary key,order_id bigint ,order_type varchar(50),file_path  varchar(255));");
            		
            stmt.close();
            // conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initData(C3p0Plugin cp) {
        try {
            cp.start();
            Connection conn = cp.getDataSource().getConnection();
            Statement stmt = conn.createStatement();

            // initEedaData(stmt);

            // location init
            LocationDataInit.initLocation(stmt);
            initPermission(stmt);
            ProfileDataInit.initProfile(stmt);
            
            
            stmt.executeUpdate("insert into office(office_name, office_person, phone, address,type) values('广州源鸿物流有限公司','侯晓辉','66343695','广州市萝岗区宏明路严田商业街11号','总公司');");
            stmt.executeUpdate("insert into office(office_code, office_name, office_person,phone,address,email,type,company_intro,belong_office) values('1201', '广州分公司', '张三','020-12312322','香洲珠海市香洲区老香洲为农街为农市场','123@qq.com','分公司','这是一家分公司',1);");
            stmt.executeUpdate("insert into office(office_code, office_name, office_person,phone,address,email,type,company_intro,belong_office) values('121', '珠公司', '张三','020-12312322','香洲珠海市香洲区老香洲为农街为农市场','123@qq.com','配送中心RDC','这是一家配送中心RDC',1);");
            stmt.executeUpdate("insert into office(office_code, office_name, office_person,phone,address,email,type,company_intro,belong_office) values('101', '深圳分公司','张三','020-12312322','香洲珠海市香洲区老香洲为农街为农市场','123@qq.com','分公司','这是一家分公司',1);");
            stmt.executeUpdate("insert into office(office_name, office_person, phone, address,type,belong_office) values('源鸿物流珠海分公司','陈秋明','13925642153','广东省珠海市','分公司',1);");
            stmt.executeUpdate("insert into office(office_name, office_person, phone, address,type,belong_office) values('上海源鸿物流有限公司','刘涛','18688696863','上海市','分公司',1);");
            stmt.executeUpdate("insert into office(office_name, office_person, phone, address,type,belong_office) values('贵阳源鸿物流有限公司','林伟军','13358215635','贵阳市','分公司',1);");
            stmt.executeUpdate("insert into office(office_name, office_person, phone, address,type) values('珠海易达信息科技有限公司','Jason Hu','66343695','广州市萝岗区宏明路严田商业街11号','总公司');");

            stmt.executeUpdate("insert into office_config (office_id, system_title, logo, login_bg, domain) values(0, '易达物流', null, '/upload/bg-1.jpg', 'localhost');");
            
            stmt.executeUpdate("insert into inventory_item(party_id, warehouse_id, product_id,total_quantity) values(1, 1, 4, 100);");
            stmt.executeUpdate("insert into inventory_item(party_id, warehouse_id, product_id,total_quantity) values(1, 30, 4, 100);");//广电，源鸿分仓
            stmt.executeUpdate("insert into user_login(user_name, password, password_hint, office_id) values('d_user1', '123456', '1-6',1);");
            stmt.executeUpdate("insert into user_login(user_name, password, password_hint) values('d_user2', '123456', '1-6');");
            stmt.executeUpdate("insert into user_login(user_name,c_name, password, password_hint, office_id) values('demo','管理员','123456', '1-6', 1);");
            stmt.executeUpdate("insert into user_login(user_name, password, password_hint, office_id) values('jason', '123456', '1-6',3);");
            stmt.executeUpdate("insert into user_login(user_name, password, password_hint, office_id) values('ray.liu@eeda123.com', '123456', '1-6',3);");
           
            //金融账户
            stmt.executeUpdate("insert into fin_account(type, currency, bank_name, account_no, bank_person, amount) values('PAY', '人民币','建设银行','12123123123','张三', 12345);");
            stmt.executeUpdate("insert into fin_account(type, currency, bank_name, account_no, bank_person, amount) values('REC', '人民币','中国银行','12123123123','李四',23456);");
            stmt.executeUpdate("insert into fin_account(type, currency, bank_name, account_no, bank_person, amount) values('ALL', '人民币','现金','12123123123','王五',34567);");

            stmt.executeUpdate("insert into contract(name,type,party_id,period_from,period_to,remark) values('广电运通-客户合同','CUSTOMER', 1,'2013-11-12','2015-11-14','无');");
            stmt.executeUpdate("insert into contract(name,type,party_id,period_from,period_to,remark) values('客户合同','CUSTOMER', 5,'2013-10-12','2015-11-15','无');");

            stmt.executeUpdate("insert into contract(name,type,party_id,period_from,period_to,remark) values('干线供应商合同','SERVICE_PROVIDER', 7,'2013-11-12','2015-11-14','无');");
            stmt.executeUpdate("insert into contract(name,type,party_id,period_from,period_to,remark) values('提货供应商合同','DELIVERY_SERVICE_PROVIDER', 6,'2011-1-12','2015-10-14','无');");

            // 定义配送供应商合同 济南骏运展达物流运输有限公司
            stmt.executeUpdate("insert into contract(name,type,party_id,period_from,period_to,remark) values('配送供应商合同','DELIVERY_SERVICE_PROVIDER', 13,'2013-11-12','2015-11-14','无');");
            stmt.executeUpdate("insert into contract(name,type,party_id,period_from,period_to,remark) values('示例干线供应商合同','SERVICE_PROVIDER', 4,'2013-1-12','2015-10-14','无');");

            stmt.executeUpdate("insert into route(from_id,location_from,to_id,location_to,remark) values('110000','北京','110103','宣武区','123123');");
            stmt.executeUpdate("insert into route(from_id,location_from,to_id,location_to,remark) values('110000','北京','120000','天津','123123');");
            stmt.executeUpdate("insert into route(from_id,location_from,to_id,location_to,remark) values('120000','天津','110000','北京','123123');");

            stmt.executeUpdate("insert into contract_item(contract_id,product_id, fin_item_id,pricetype,amount,from_id,location_from,to_id,location_to,remark) values('1', 1, '1','perUnit','13000','440100','广东省 广州市','110101','北京 北京市 东城区','路线');");
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id,pricetype,amount,from_id,location_from,to_id,location_to,remark) values('2','2','perCar','13000','110000','北京','120000','天津','路线2');");
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id,pricetype,amount,from_id,location_from,to_id,location_to,remark) values('1','3','perCargo','12000','120000','天津','110000','北京','路线');");
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id,pricetype,amount,remark) values('2','1','perCar','130000','路线2');");
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id,pricetype,amount,remark) values('3', 1, 'perUnit','120000','路线');");
            // 提货供应商 提货
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id,pricetype,amount,from_id,location_from,remark) values(4,1,'perUnit','200','440100','广州市','路线');");
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id,pricetype,amount,from_id,location_from,remark) values(4,1,'perCar','300','440100','广州市','路线');");
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id,pricetype,cartype,amount,from_id,location_from,remark) values(4,1,'perCar','厢式车','350','440100','广州市','路线');");
            // 干线供应商 计件收费，整车收费，零担收费  定义
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id, pricetype, amount,from_id,location_from,to_id,location_to,remark) values(6, 1,'perUnit','10000','440100','广州市','110100','北京市','计件');");
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id, pricetype, carlength, amount,from_id,location_from,to_id,location_to,remark) values(6, 2,'perCar', '40GP','8888','440100','广州市','110100','北京市','整车');");
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id, pricetype, ltlunittype, amount,from_id,location_from,to_id,location_to, amountfrom, amountto, remark) values(6, 1, 'perCargo', 'perCBM', '100','440100','广州市','110100','北京市', 0, 100, '零担-按立方');");
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id, pricetype, ltlunittype, amount,from_id,location_from,to_id,location_to, amountfrom, amountto, remark) values(6, 1, 'perCargo', 'perKg', '101','440100','广州市','110100','北京市', 0, 50, '零担-按公斤');");
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id, pricetype, ltlunittype, amount,from_id,location_from,to_id,location_to, amountfrom, amountto, remark) values(6, 1, 'perCargo', 'perTon', '5002','440100','广州市','110100','北京市', 0, 1, '零担-按吨');");
            
            // 定义配送供应商 济南骏运展达物流运输有限公司 配送 没有目的地 任意提货路线都收取1000
            stmt.executeUpdate("insert into contract_item(contract_id,pricetype,amount,remark) values('5','perUnit','1000','省内任意提货路线');");
            // 定义配送供应商 济南骏运展达物流运输有限公司 配送 只有目的地 北京市东城区，这条线收1001
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id,pricetype,amount,to_id,location_to,remark) values(5, 1,'perUnit','1001','110101','北京市东城区','配送路线');");
            stmt.executeUpdate("insert into contract_item(contract_id,fin_item_id,pricetype,amount,to_id,location_to,remark) values(5, 2,'perCar', 333,'110101','北京市东城区','配送路线');");
            
            // 回单notity_party_id bigint,customer_id
            stmt.executeUpdate("insert into return_order(order_no,create_date,transaction_status,order_type,delivery_order_id,creator,remark,customer_id) values('HD2013021400001', '2014-08-5 16:35:35.1', '已签收','应收',1,1,'这是一张回单',1);");
            stmt.executeUpdate("insert into return_order(order_no,create_date,transaction_status,order_type,transfer_order_id,creator,remark,customer_id) values('HD2013021400002', '2014-08-6 16:35:35.1', '已签收','应收',1,2,'这是一张回单',1);");
            // 运输单应收应付明细id bigint auto_increment PRIMARY KEY, order_id bigint,
            // fin_item_id bigint,"
            // +
            // "fin_item_code varchar(20), amount double, status varchar(50), "
           /* stmt.executeUpdate("insert into transfer_order_fin_item(order_id,fin_item_id,fin_item_code,amount,status) values('1','1','20132014','5200','完成');");
            stmt.executeUpdate("insert into transfer_order_fin_item(order_id,fin_item_id,fin_item_code,amount,status) values('1','2','20132015','5200','完成');");
            stmt.executeUpdate("insert into transfer_order_fin_item(order_id,fin_item_id,fin_item_code,amount,status) values('1','3','20132016','5200','完成');");
            stmt.executeUpdate("insert into transfer_order_fin_item(order_id,fin_item_id,fin_item_code,amount,status) values('2','1','20132014','3200','未完成');");
            stmt.executeUpdate("insert into transfer_order_fin_item(order_id,fin_item_id,fin_item_code,amount,status) values('2','2','20132015','3200','未完成');");
            */
            
            // 角色定义
            stmt.executeUpdate("insert into role(code, name) values('admin', '系统管理员');");
            stmt.executeUpdate("insert into role(code, name) values('clerk', '文员');");
            stmt.executeUpdate("insert into role(code, name) values('manager', '经理');");
            //stmt.executeUpdate("insert into role(code, name) values('', '老板');");
            stmt.executeUpdate("insert into role(code, name) values('scheduler', '调度员');");
            //stmt.executeUpdate("insert into role(code, name) values('driver', '司机');");

            
       
            stmt.executeUpdate("insert into user_role(user_name, role_code) values('demo', 'admin');");
            stmt.executeUpdate("insert into user_role(user_name, role_code) values('jason', 'clerk');");
            

            
            stmt.executeUpdate("insert into role_permission(role_code, permission_code, remark) values('clerk', 'TransferOrder.list', '运输单查询权限');");
            stmt.executeUpdate("insert into role_permission(role_code, permission_code, remark) values('clerk', 'TransferOrder.create', '运输单创建权限');");
            stmt.executeUpdate("insert into role_permission(role_code, permission_code, remark) values('clerk', 'TransferOrder.update', '运输单保存权限');");
            
            // 模块定义
            stmt.executeUpdate("insert into modules(module_name) values('调度管理');");
            stmt.executeUpdate("insert into modules(module_name) values('运输在途管理');");
            stmt.executeUpdate("insert into modules(module_name) values('配送管理');");
            stmt.executeUpdate("insert into modules(module_name) values('回单管理');");
            stmt.executeUpdate("insert into modules(module_name) values('财务管理');");
            stmt.executeUpdate("insert into modules(module_name) values('合同管理');");
            stmt.executeUpdate("insert into modules(module_name) values('报表管理');");
            stmt.executeUpdate("insert into modules(module_name) values('基础数据设计');");
            stmt.executeUpdate("insert into modules(module_name) values('质量管理');");
            stmt.executeUpdate("insert into modules(module_name) values('XXXX管理');");
            stmt.executeUpdate("insert into modules(module_name) values('YYYY管理');");
            stmt.executeUpdate("insert into modules(module_name) values('ZZZZ管理');");
            // 模块权限定义
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('1','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('1','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('1','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('1','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('1','5');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('2','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('2','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('2','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('2','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('2','5');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('3','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('3','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('3','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('3','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('3','5');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('4','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('4','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('4','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('4','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('4','5');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('5','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('5','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('5','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('5','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('5','5');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('6','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('6','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('6','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('6','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('6','5');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('7','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('7','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('7','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('7','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('7','5');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('8','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('8','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('8','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('8','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('8','5');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('9','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('9','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('9','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('9','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('9','5');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('10','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('10','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('10','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('10','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('10','5');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('11','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('11','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('11','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('11','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('11','5');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('12','1');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('12','2');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('12','3');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('12','4');");
            stmt.executeUpdate("insert into modules_privilege(module_id,privilege_id) values('12','5');");
            
            // 收费条目定义
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('YSF','运输费','应付','这是一张运输单收费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('BYF','搬运费','应付','这是一张运输单收费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('SLF','上楼费','应付','这是一张运输单收费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('YSF','客户费用','应收','这是一张运输单收费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('BYF','搬运费','应收','这是一张运输单收费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('SLF','上楼费','应收','这是一张运输单收费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('FTF','分摊费用','应收','这是分摊费用');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('THF','提货费','应收','这是提货费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('SHF','送货费','应收','这是送货费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('BXF','保险费','应收','这是保险费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('CLCF','超里程费','应收','这是超里程费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('TJF','台阶费','应收','这是台阶费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('AZF','安装费','应收','这是安装费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('ZXF','装卸费','应收','这是装卸费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('CZF','仓租费','应收','这是仓租费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('DDF','等待费','应收','这是等待费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('YSF','运输费','应收','这是运输费');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('QTF','其它费用','应收','这是其它费用');");
            stmt.executeUpdate("insert into fin_item(code,name,type,remark) values('BXF','保险费','应付','这是保险费');");
            
            // 自营车辆付费条目定义
            stmt.executeUpdate("insert into fin_item(driver_type,code,name,type,remark) values('OWN','CYF','柴油费','自营应付','这是柴油费');");
            stmt.executeUpdate("insert into fin_item(driver_type,code,name,type,remark) values('OWN','CCF','出车补贴','自营应付','这是出车补贴费');");
            stmt.executeUpdate("insert into fin_item(driver_type,code,name,type,remark) values('OWN','SJGZ','司机工资','自营应付','这是司机工资');");
            stmt.executeUpdate("insert into fin_item(driver_type,code,name,type,remark) values('OWN','LQF','路桥费','自营应付','这是路桥费');");
            stmt.executeUpdate("insert into fin_item(driver_type,code,name,type,remark) values('OWN','ZXF','装卸费','自营应付','这是装卸费');");
            stmt.executeUpdate("insert into fin_item(driver_type,code,name,type,remark) values('OWN','FKF','罚款','自营应付','这是罚款费');");
            stmt.executeUpdate("insert into fin_item(driver_type,code,name,type,remark) values('OWN','SHGZ','送货员工资','自营应付','这是送货员工资');");
            stmt.executeUpdate("insert into fin_item(driver_type,code,name,type,remark) values('OWN','TCF','停车费','自营应付','这是停车费');");
            stmt.executeUpdate("insert into fin_item(driver_type,code,name,type,remark) values('OWN','ZSF','住宿费','自营应付','这是住宿费');");
            stmt.executeUpdate("insert into fin_item(driver_type,code,name,type,remark) values('OWN','QTF','其它费用','自营应付','这是其它费用');");

            newCustomer();

            // 仓库
            stmt.execute("insert into warehouse(warehouse_area,warehouse_name,warehouse_desc,warehouse_address,office_id,warehouse_type) values('582','源鸿广州总仓', '这是广州总仓','萝岗','4','ownWarehouse');");
            stmt.execute("insert into warehouse(warehouse_area,warehouse_name,warehouse_desc,warehouse_address,sp_id,warehouse_type,office_id) values('582','源鸿分仓', '这是广州分仓','东莞','8','deliverySpWarehouse',1);");

            // 类别 ----采用面向对象的方式来获取party的id， 不必担心id不对。 --ray 2014-06-29
            Party party = Party.dao
                    .findFirst("SELECT p.id FROM party p left join contact c on p.contact_id =c.id where c.company_name ='示例客户---广州广电运通金融电子股份有限公司'");
            Category rootCat = new Category();
            rootCat.set("name", "广电运通").set("customer_id", party.get("id")).save();

            Category subCat1 = new Category();
            subCat1.set("name", "ATM").set("customer_id", party.get("id")).set("parent_id", rootCat.getLong("id")).save();

            Category subCat2 = new Category();
            subCat2.set("name", "普通货品").set("customer_id", party.get("id")).set("parent_id", rootCat.getLong("id")).save();

            // 产品
            stmt.execute("insert into product(item_name,item_no,size,width,height,volume,unit,weight,category_id,item_desc) values('ATM', '2014042600001','1000','5000','7000', 35,'台', 10, 2, '这是一台ATM');");
            stmt.execute("insert into product(item_name,item_no,size,width,height,volume,unit,weight,category_id,item_desc) values('普通货品', '2014042600002','2000','4000','5000', 40, '件', 20, 3, '这是普通货品');");
            stmt.execute("insert into product(item_name,item_no,size,width,height,volume,unit,weight,category_id,item_desc) values('特殊货品', '2014042600003','1000','5000','6000', 30, '套', 30, 2, '这是特殊货品');");
            stmt.execute("insert into product(item_name,item_no,size,width,height,volume,unit,weight,category_id,item_desc) values('SONY-电视1', 'SONY30329','1000','5000','6000', 30, '台', 30, 3, '普通货品');");
            stmt.execute("insert into product(item_name,item_no,size,width,height,volume,unit,weight,category_id,item_desc) values('SONY-电视2', 'SONY30330','1000','5000','6000', 30, '台', 30, 3, '普通货品');");

            //单位
            //stmt.execute("insert into tally(item_name,item_no,size,width,height,volume,unit,weight,category_id,item_desc) values('ATM', '2014042600001','1000','5000','7000', 35,'台', 10, 2, '这是一台ATM');");
            // 运输单
            stmt.executeUpdate("insert into transfer_order(cargo_nature, sp_id, notify_party_id, order_no, create_by, customer_id, status, create_stamp, arrival_mode,address,warehouse_id,route_from,route_to,office_id,order_type,customer_province,operation_type,charge_type2) values('ATM', '4', '12', 'YS2014042600001', '3', '1', '已入货场', '2014-04-20 16:33:35.1', 'delivery','珠海','2','110102','440402','2','salesOrder','provinceOut','own','perUnit');");
            stmt.executeUpdate("insert into transfer_order(cargo_nature, sp_id, notify_party_id, order_no, create_by, customer_id, status, create_stamp, arrival_mode,address,warehouse_id,route_from,route_to,office_id,order_type,customer_province,operation_type, charge_type, pickup_mode,charge_type2) "
                    + "values('ATM', 4, '13', 'YS2014042600002', '4', 1, '新建', CURRENT_TIMESTAMP(), 'gateIn','中山', 4,'440100','110100','3','salesOrder','provinceIn','own','perUnit','routeSP','perUnit');");
            stmt.executeUpdate("insert into transfer_order(cargo_nature, sp_id, notify_party_id, order_no, create_by, customer_id, status, create_stamp, arrival_mode,address,route_from,route_to,office_id,order_type,customer_province,pickup_seq,operation_type,warehouse_id,charge_type2) values('cargo', '6', '12', 'YS2014042600003', '4', '19', '已入货场', '2014-04-28 16:46:35.1', 'gateIn','广州','110106','440403','2','replenishmentOrder','provinceOut','2','own','1','perUnit');");
            stmt.executeUpdate("insert into transfer_order(cargo_nature, sp_id, notify_party_id, order_no, create_by, customer_id, status, create_stamp, arrival_mode,address,route_from,route_to,office_id,order_type,customer_province,pickup_seq,operation_type,warehouse_id,charge_type2) values('cargo', '6', '13', 'YS2014042600004', '3', '19', '新建', '2014-04-25 16:35:35.1', 'gateIn','深圳','110106','440403','1','replenishmentOrder','provinceIn','3','out_source','2','perUnit');");
            stmt.executeUpdate("insert into transfer_order(cargo_nature, sp_id, notify_party_id, order_no, create_by, customer_id, status, create_stamp, arrival_mode,address,route_from,route_to,office_id,order_type,customer_province,pickup_seq,operation_type,charge_type2) values('ATM', '7', '12', 'YS2014042600005', '3', '2', '新建', '2014-04-22 16:28:35.1', 'delivery','东莞','110108','440507','1','arrangementOrder','provinceOut','1','out_source','perCar');");
            stmt.executeUpdate("insert into transfer_order(cargo_nature, sp_id, notify_party_id, order_no, create_by, customer_id, status, create_stamp, arrival_mode,address,route_from,route_to,warehouse_id,office_id,order_type,customer_province,operation_type,charge_type2) values('ATM', '9', '13', 'YS2014042600006', '3', '3', '已签收', '2014-04-24 16:58:35.1', 'gateIn','东莞','110109','440511','2','2','arrangementOrder','provinceIn','out_source','perCar');");
            stmt.executeUpdate("insert into transfer_order(cargo_nature, sp_id, notify_party_id, order_no, create_by, customer_id, status, create_stamp, arrival_mode,address,route_from,route_to,warehouse_id,office_id,order_type,customer_province,operation_type,charge_type2) values('ATM', '10', '12', 'YS2014042600007', '3', '3', '已入库', '2014-04-24 16:58:35.1', 'gateIn','广州','','','1','3','arrangementOrder','provinceOut','out_source','perCargo');");
            
            //这张是做给普通货品的（无单品）
            stmt.executeUpdate("insert into transfer_order(cargo_nature, sp_id, notify_party_id, order_no, create_by, customer_id, status, create_stamp, arrival_mode,address,warehouse_id,route_from,route_to,office_id,order_type,customer_province,operation_type, charge_type, pickup_mode) "
                    + "values('cargo', 4, '13', 'YS2014042600008', '4', 1, '新建', CURRENT_TIMESTAMP(), 'gateIn','中山', 4,'440100','110100','3','salesOrder','provinceIn','own','perCBM','routeSP');");
            
            stmt.executeUpdate("insert into transfer_order_item(amount,order_id,product_id) " + "values(2, 1, 1);");
            // 货品明细
            stmt.executeUpdate("insert into transfer_order_item(item_no, item_name, item_desc, amount,unit,volume,weight,remark,order_id, size,width,height) "
                    + "values('123456', 'ATM', '这是一台ATM','1','台','452','100','一台ATM', 1, 1000,5000,7000);");

            // 示范数据item
            stmt.executeUpdate("insert into transfer_order_item(item_no, item_name, item_desc,amount,unit,volume,weight,remark,order_id, product_id) "
                    + "values('2014042600001', 'ATM', 'ATM机', 5, '台', 35, 10, '2台ATM', 2, 1);");
            // 示范数据item_detail
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,item_no,serial_no,estimate_damage_amount,item_name,is_damage,item_id,notify_party_id) "
                    + "values(2, '2014042600001', 'S001', 0, 'ATM', false, 3, 9);");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,item_no,serial_no,estimate_damage_amount,item_name,is_damage,item_id,notify_party_id) "
                    + "values(2, '2014042600001', 'S002', 0, 'ATM', false, 3, 9);");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,item_no,serial_no,estimate_damage_amount,item_name,is_damage,item_id,notify_party_id) "
                    + "values(2, '2014042600001', 'S003', 0, 'ATM', false, 3, 9);");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,item_no,serial_no,estimate_damage_amount,item_name,is_damage,item_id,notify_party_id) "
                    + "values(2, '2014042600001', 'S004', 0, 'ATM', false, 3, 9);");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,item_no,serial_no,estimate_damage_amount,item_name,is_damage,item_id,notify_party_id) "
                    + "values(2, '2014042600001', 'S005', 0, 'ATM', false, 3, 9);");
            //
            stmt.executeUpdate("insert into transfer_order_item(item_no, item_name, item_desc,amount,unit,volume,weight,remark,order_id) "
                    + "values('123456', 'ATM', '这是很多台ATM',2,'台',1000,2000,'一台ATM','3');");
            stmt.executeUpdate("insert into transfer_order_item(item_no, item_name, item_desc,amount,unit,volume,weight,remark,order_id) "
                    + "values('123456', '电视', '这是一台电视','2','台','452','100','一台ATM','4');");
            stmt.executeUpdate("insert into transfer_order_item(item_no, item_name, item_desc,amount,unit,volume,weight,remark,order_id) "
                    + "values('123456', 'ATM', '这是一台ATM','1','台','454','100','一台ATM','5');");
            stmt.executeUpdate("insert into transfer_order_item(item_no, item_name, item_desc,amount,unit,volume,weight,remark,order_id) "
                    + "values('123456', 'ATM', '这是一台ATM','1','台','452','100','一台ATM','6');");
            stmt.executeUpdate("insert into transfer_order_item(item_no, item_name, item_desc,amount,unit,volume,weight,remark,order_id) "
                    + "values('12222', 'ATM', '这是一台ATM','1','台','452','100','一台ATM','5');");
            stmt.executeUpdate("insert into transfer_order_item(item_no, item_name, item_desc,amount,unit,volume,weight,remark,order_id) "
                    + "values('12aa', 'ATM', '这是一台ATM','1','台','452','100','一台ATM','7');");
            stmt.executeUpdate("insert into transfer_order_item(item_no, item_name, item_desc,amount,unit,volume,weight,remark,order_id, product_id) "
                    + "values('SONY30329', 'SONY-电视1', 'SONY电视','20','台','2','50','普通货品', 8, 4);");
            stmt.executeUpdate("insert into transfer_order_item(item_no, item_name, item_desc,amount,unit,volume,weight,remark,order_id, product_id) "
                    + "values('SONY30330', 'SONY-电视2', 'SONY电视','100','台','2','60','普通货品', 8, 5);");

            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,serial_no,estimate_damage_amount,item_name,is_damage,item_id,notify_party_id) "
                    + "values('1','fdgh1265985','10000', 'ATM', true,'1','9');");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,serial_no,estimate_damage_amount,item_name,is_damage,item_id,notify_party_id) "
                    + "values('5','asdf1265985','10000', 'ATM', false,'7','9');");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,serial_no,estimate_damage_amount,item_name,is_damage,item_id,notify_party_id) "
                    + "values('5','11adasasdf5','10000', 'ATM', false,'9','9');");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,serial_no,estimate_damage_amount,item_name,is_damage,item_id,notify_party_id) "
                    + "values('6','iouu1265985','10000', 'ATM', false,'8','10');");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,serial_no,estimate_damage_amount,item_name,is_damage,item_id,notify_party_id) "
                    + "values('6','2221265985','10000', 'ATM', false,'8','10');");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,serial_no,estimate_damage_amount,item_name,is_damage,item_id,notify_party_id) "
                    + "values('7','aaasswqq63','10000', 'ATM', false, 8,'10');");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,serial_no,item_name,item_id,notify_party_id) "
                    + "values(3,'123', 'ATM001', 6, 24);");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,serial_no,item_name,item_id,notify_party_id) "
                    + "values(3,'456', 'ATM002', 6, 24);");
            stmt.executeUpdate("insert into transfer_order_item_detail(order_id,serial_no,item_name,item_id,notify_party_id) "
                    + "values(3,'789', 'ATM003', 6, 24);");

            // 配送单
            stmt.execute("insert into delivery_order(order_no, cargo_nature, customer_id,sp_id,notify_party_id,status,create_stamp,route_from,route_to) values('PS2014042600013', 'ATM','5','7','9','配送在途','2014-04-25 16:35:35.1','440100','110100');");
            stmt.execute("insert into delivery_order(order_no, cargo_nature,customer_id,sp_id,notify_party_id,status,create_stamp) values('PS2014042600004', 'ATM','6','7','10','已签收','2014-04-25 16:35:35.1');");
            stmt.execute("insert into delivery_order(order_no, cargo_nature,customer_id,sp_id,notify_party_id,status,create_stamp) values('PS2014042600014', 'ATM','5','8','9','取消','2014-04-25 16:35:35.1');");
            stmt.execute("insert into delivery_order(order_no, cargo_nature,customer_id,sp_id,notify_party_id,status,create_stamp) values('PS2014042600003', 'cargo','6','8','10','配送在途','2014-04-25 16:35:35.1');");

            // delivery_order_item
            stmt.execute("insert into delivery_order_item(delivery_id,transfer_order_id) values(1,3);");
            stmt.execute("insert into delivery_order_item(delivery_id,transfer_order_id) values(1,4);");

            // billing_order 应收应付单主表
            String billOrderStr = "insert into billing_order(blling_order_no, order_type, customer_id, customer_type, charge_account_id, payment_account_id, status,"
                    + "transfer_order_id, delivery_order_id, remark, creator, create_stamp, last_modified_by,"
                    + "last_modified_stamp, approver, approve_date, total_amount, total_actual_amount) values";
            stmt.execute(billOrderStr
                    + "('YSDZ001', 'charge_audit_order', 4, 'CUSTOMER', 1, 2, 'new', 1, 1, '演示数据', 1, CURRENT_TIMESTAMP(),1, CURRENT_TIMESTAMP(),"
                    + "1, CURRENT_TIMESTAMP(), 1000, 1000);");
            stmt.execute(billOrderStr
                    + "('YSDZ002', 'charge_audit_order', 4, 'CUSTOMER', 1, 2, 'checking', 1, 1, '演示数据', 1, CURRENT_TIMESTAMP(),1, CURRENT_TIMESTAMP(),"
                    + "1, CURRENT_TIMESTAMP(), 1000, 1000);");
            stmt.execute(billOrderStr
                    + "('YSDZ003', 'charge_audit_order', 4, 'CUSTOMER',1, 2, 'confirmed', 1, 1, '演示数据', 1, CURRENT_TIMESTAMP(),1, CURRENT_TIMESTAMP(),"
                    + "1, CURRENT_TIMESTAMP(), 1000, 1000);");
            stmt.execute(billOrderStr
                    + "('YSDZ004', 'charge_audit_order', 4, 'CUSTOMER',1, 2, 'completed', 1, 1, '演示数据', 1, CURRENT_TIMESTAMP(),1, CURRENT_TIMESTAMP(),"
                    + "1, CURRENT_TIMESTAMP(), 1000, 1000);");
            stmt.execute(billOrderStr
                    + "('YSDZ005', 'charge_audit_order',4, 'CUSTOMER', 1, 2, 'cancel', 1, 1, '演示数据', 1, CURRENT_TIMESTAMP(),1, CURRENT_TIMESTAMP(),"
                    + "1, CURRENT_TIMESTAMP(), 1000, 1000);");
            stmt.execute(billOrderStr
                    + "('YFDZ001', 'pay_audit_order', 7, 'SERVICE_PROVIDER', 1, 2, 'new', 1, 1, '演示数据', 1, CURRENT_TIMESTAMP(),1, CURRENT_TIMESTAMP(),"
                    + "1, CURRENT_TIMESTAMP(), 1000, 1000);");
            stmt.execute(billOrderStr
                    + "('YFDZ002', 'pay_audit_order', 7, 'SERVICE_PROVIDER', 1, 2, 'checking', 1, 1, '演示数据', 1, CURRENT_TIMESTAMP(),1, CURRENT_TIMESTAMP(),"
                    + "1, CURRENT_TIMESTAMP(), 1000, 1000);");
            stmt.execute(billOrderStr
                    + "('YFDZ003', 'pay_audit_order', 7, 'SERVICE_PROVIDER',1, 2, 'confirmed', 1, 1, '演示数据', 1, CURRENT_TIMESTAMP(),1, CURRENT_TIMESTAMP(),"
                    + "1, CURRENT_TIMESTAMP(), 1000, 1000);");
            stmt.execute(billOrderStr
                    + "('YFDZ004', 'pay_audit_order', 7, 'SERVICE_PROVIDER	',1, 2, 'completed', 1, 1, '演示数据', 1, CURRENT_TIMESTAMP(),1, CURRENT_TIMESTAMP(),"
                    + "1, CURRENT_TIMESTAMP(), 1000, 1000);");
            stmt.execute(billOrderStr
                    + "('YFDZ005', 'pay_audit_order',7, 'SERVICE_PROVIDER	', 1, 2, 'cancel', 1, 1, '演示数据', 1, CURRENT_TIMESTAMP(),1, CURRENT_TIMESTAMP(),"
                    + "1, CURRENT_TIMESTAMP(), 1000, 1000);");
            // billing_order_item 应收应付单从表
            stmt.execute("create table if not exists billing_order_item(id bigint auto_increment primary key,blling_order_id bigint, "
                    + "charge_account_id bigint, payment_account_id bigint, status varchar(255), amount double, remark varchar(1024),"
                    + "creator bigint, create_stamp timestamp,last_modified_by bigint,"
                    + "last_modified_stamp timestamp, approver bigint, approve_date timestamp);");

            // 自营车辆司机
            stmt.execute("insert into carinfo(phone, car_no, cartype, length, driver, type, hundred_fuel_standard) values('13312345678', '粤A5687', '平板车', 18.5, '王五', '"+Carinfo.CARINFO_TYPE_OWN+"',13);");
            stmt.execute("insert into carinfo(phone, car_no, cartype, length, driver, type, hundred_fuel_standard) values('13412345678', '粤A2341', '高栏车', 12.5, '赵六', '"+Carinfo.CARINFO_TYPE_OWN+"',13);");
            
            // 供应商司机
            stmt.execute("insert into carinfo(phone, car_no, cartype, length, driver, type) values('13898765432', '粤A9874', '集装车', 17.5, '王五五', '"+Carinfo.CARINFO_TYPE_SP+"');");
            stmt.execute("insert into carinfo(phone, car_no, cartype, length, driver, type) values('13998765432', '粤A1234', '挂车', 14.5, '赵六六', '"+Carinfo.CARINFO_TYPE_SP+"');");

            // 发车单
            /*stmt.execute("insert into depart_order(depart_no,create_stamp,combine_type,car_no,car_type,driver_id,car_size,status) values('FC2014061000001', CURRENT_TIMESTAMP(),'DEPART','粤A876596','平板车',1,23,'新建');");
            stmt.execute("insert into depart_transfer(depart_id,order_id,transfer_order_no) values('1', '1','YS2014042600001');");
            stmt.execute("insert into depart_order(depart_no,create_stamp,combine_type,car_no,car_type,driver_id,car_size,status) values('FC2014061000002', CURRENT_TIMESTAMP(),'DEPART','粤A879588','集装车',2, 23,'新建');");
            stmt.execute("insert into depart_transfer(depart_id,order_id,transfer_order_no) values('2', '2','YS2014042600002');");
            stmt.execute("insert into depart_transfer(depart_id,order_id,transfer_order_no) values('1', '5','YS2014042600005');");
            stmt.execute("insert into depart_order(depart_no,create_stamp,combine_type,driver_id,carinfo_id,status) values('FC2014061000003', CURRENT_TIMESTAMP(),'DEPART',24,1,'新建');");
            stmt.execute("insert into depart_transfer(depart_id,order_id,transfer_order_no) values(3, '3','YS2014042600006');");
            // 拼车单
            stmt.execute("insert into depart_order(depart_no,create_stamp,combine_type,car_no,car_type,driver_id,car_size,status,create_by) values('PC2014061000001', CURRENT_TIMESTAMP(),'PICKUP','粤A876596','平板货车',1, 24,'已入货场',3);");
            stmt.execute("insert into depart_transfer(pickup_id,order_id,transfer_order_no) values(4, '1','YS2014042600001');");
            stmt.execute("insert into depart_order(depart_no,create_stamp,combine_type,car_no,car_type,driver_id,car_size,status,create_by,pickup_mode) "
                    + "values('PC2014061000002', CURRENT_TIMESTAMP(),'PICKUP','粤A879588','箱式货车',2, 24,'已入货场',4,'own');");
            stmt.execute("insert into depart_transfer(pickup_id,order_id,transfer_order_no) values(5, '4','YS2014042600004');");
            stmt.execute("insert into depart_transfer(pickup_id,order_id,transfer_order_no) values(5, '5','YS2014042600005');");
            stmt.execute("insert into depart_transfer(pickup_id,order_id,transfer_order_no) values(5, '3','YS2014042600003');");*/

           /* // 运输里程碑
            stmt.execute("insert into transfer_order_milestone(ORDER_ID, CREATE_BY, CREATE_STAMP, STATUS, TYPE) values(2, 3, '2014-06-28 10:39:35.1', '新建', 'TRANSFERORDERMILESTONE');");
            stmt.execute("insert into transfer_order_milestone(ORDER_ID, CREATE_BY, CREATE_STAMP, STATUS, TYPE) values(3, 3, '2014-06-28 10:40:35.1', '新建', 'TRANSFERORDERMILESTONE');");
            stmt.execute("insert into transfer_order_milestone(ORDER_ID, CREATE_BY, CREATE_STAMP, STATUS, TYPE) values(4, 3, '2014-06-28 10:43:35.1', '新建', 'TRANSFERORDERMILESTONE');");
            stmt.execute("insert into transfer_order_milestone(ORDER_ID, CREATE_BY, CREATE_STAMP, STATUS, TYPE) values(6, 3, '2014-06-28 11:39:35.1', '新建', 'TRANSFERORDERMILESTONE');");
            */
            stmt.execute("insert into arap_charge_order(begin_time, payee_id, order_no, remark, create_by, end_time, create_stamp, status) values('2014-08-15', 1, 'YSDZ2014081800001', '应收对账单测试数据', '3', '2014-08-19', '2014-08-18 9:39:35.1', '已确认');");
            stmt.execute("insert into arap_charge_item(charge_order_id, ref_order_id, create_by, create_stamp) values(1, 1, 3, '2014-08-18 9:39:35.1');");
            
            // 拼车单收费条目
            /*stmt.execute("insert into depart_order_fin_item(depart_order_id, pickup_order_id, fin_item_id, amount) values(1, 5, 1, 300);");
            stmt.execute("insert into depart_order_fin_item(depart_order_id, pickup_order_id, fin_item_id, amount) values(2, 5, 2, 100);");
            stmt.execute("insert into depart_order_fin_item(depart_order_id, pickup_order_id, fin_item_id, amount) values(3, 5, 3, 100);");*/
           
            //execl标题表
            String titles = "客户名称(简称) 运营方式 到达方式 供应商名称(简称) 计划日期 运输单号 货品型号 发货数量 货品属性 单品序列号 单品件数 收货单位 单品收货地址 单品收货人 单品收货人联系电话 单品销售单号 业务经理 服务站名称 责任人 服务站电话 网点 中转仓 始发城市 到达城市 预计到货日期";
            String[] title = titles.split(" ");
            for (int i = 0; i < title.length; i++) {
            	stmt.executeUpdate("insert into execl_title(execl_type, execl_title) values('transferOrder','"+title[i]+"');");
			}
            
           /* 基础数据——用户网点用户客户*/
            stmt.executeUpdate("insert into user_office(user_name,office_id) select 'demo', id from office;");
            stmt.executeUpdate("update user_office set is_main=true where user_name='demo' and  office_id =1");
            
            stmt.executeUpdate("insert into user_customer(user_name,customer_id) select 'demo', id from party where party_type = 'CUSTOMER';");
            
            //cutomized field init
            //CustomizeFieldDataInit.initCustomizeField(stmt);
            
            stmt.close();
            // conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private static void initPermission(Statement stmt) throws SQLException {
		// 运输单权限定义 
		stmt.executeUpdate("insert into permission(module_name,code, name) values('运输单','TransferOrder.list', '运输单查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('运输单','TransferOrder.create', '运输单创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('运输单','TransferOrder.update', '运输单更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('运输单','TransferOrder.delete', '运输单删除权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('运输单','TransferOrder.add_revenue', '运输单添加/删除应收条目权限');");
		/*-----------------------------------------red---------------------------------*/
		/*调车单权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('调车单','PickupOrder.list', '调车单查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('调车单','PickupOrder.create', '调车单创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('调车单','PickupOrder.update', '调车单更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('调车单','PickupOrder.add_cost', '调车单添加应付权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('调车单','PickupOrder.completed', '调车单已完成权限');");
		/*发车单权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('发车单','DepartOrder.list', '发车单查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('发车单','DepartOrder.create', '发车单创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('发车单','DepartOrder.update', '发车单更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('发车单','DepartOrder.add_cost', '发车单添加应付权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('发车单','DepartOrder.completed', '发车单已发车权限');");
		/*自营车辆权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('自营车辆','CarInfo.list', '自营车辆查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('自营车辆','CarInfo.create', '自营车辆创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('自营车辆','CarInfo.update', '自营车辆更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('自营车辆','CarInfo.delete', '自营车辆删除权限');");
		/*自营司机权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('自营司机','Driver.list', '自营司机查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('自营司机','Driver.create', '自营司机创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('自营司机','Driver.update', '自营司机更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('自营司机','Driver.delete', '自营司机删除权限');");
		/*保险单权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('保险单','InsuranceOrder.list', '查询保险单权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('保险单','InsuranceOrder.create', '创建保险单权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('保险单','InsuranceOrder.update', '更新保险单权限');");
		/*在途运输单更新*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('在途更新','Ontrip.list', '在途更新查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('在途更新','Ontrip.update', '入库确认权限');");
		
		/*配送单*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('配送单','DeliveryOder.list', '配送单查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('配送单','DeliveryOder.create', '配送单创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('配送单','DeliveryOder.update', '配送单更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('配送单','DeliveryOder.completed', '配送单已完成权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('配送单','DeliveryOder.add_cost', '配送单添加应付权限');");
		/*配送在途更新*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('配送单在途','DeliveryOderMilestone.list', '配送单在途查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('配送单在途','DeliveryOderMilestone.completed', '配送单在途到达权限');");
		/*回单权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('回单','ReturnOrder.list', '回单查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('回单','ReturnOder.add_revenue', '回单添加应收权限');");
		//stmt.executeUpdate("insert into permission(module_name,code, name) values('ReturnOrder.completed', '回单签收权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('回单','ReturnOrder.update', '回单更改权限');");
		/*入库单权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('入库单','WarehouseOrder.inList', '入库单查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('入库单','WarehouseOrder.inCreate', '入库单创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('入库单','WarehouseOrder.inUpdate', '入库单更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('入库单','WarehouseOrder.inCompleted', '入库单已完成权限');");
		/*出库单权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('出库单','WarehouseOrder.outList', '出库单查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('出库单','WarehouseOrder.outCreate', '出库单创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('出库单','WarehouseOrder.outUpdate', '出库单更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('出库单','WarehouseOrder.outCompleted', '出库单已完成权限');");
		
		/*库存权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('库存','InventoryItem.list', '库存查询权限');");
		
		/*应收明细确认*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收明细确认','ChargeItem.affirm', '应收明细确认权限');");
		//stmt.executeUpdate("insert into permission(module_name,code, name) values('ChargeItem.list', '应收明细确认查询权限');");
		
		/*应收对账单*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收对账单','ChargeCheckOrder.list', '应收对账单查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收对账单','ChargeCheckOrder.update', '应收对账单更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收对账单','ChargeCheckOrder.create', '应收对账单创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收对账单','ChargeCheckOrder.affirm', '应收对账单确认权限');");
		
		/*应收开票申请*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收开票','ChargePreInvoiceOrder.list', '应收开票查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收开票','ChargePreInvoiceOrder.update', '应收开票更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收开票','ChargePreInvoiceOrder.create', '应收开票创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收开票','ChargePreInvoiceOrder.approval', '应收开票审核权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收开票','ChargePreInvoiceOrder.confirmation', '应收开票审批权限');");
		/*应收开票记录*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收开票记录','ChargeInvoiceOrder.list', '应收开票记录查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收开票记录','ChargeInvoiceOrder.create', '应收开票记录创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收开票记录','ChargeInvoiceOrder.update', '应收开票记录更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应收开票记录','ChargeInvoiceOrder.approval', '应收开票记录审核权限');");
		/*收账核销权限*/
		
		/*应付明细确认*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应付明细确认','CostItemConfirm.affirm', '应付明细确认权限');");
		/*应付对账单权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应付对账单','CostCheckOrder.list', '应付对账单查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应付对账单','CostCheckOrder.create', '应付对账单创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应付对账单','CostCheckOrder.update', '应付对账单更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('应付对账单','CostCheckOrder.affirm', '应付对账单审核权限');");
		/*付款申请*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('付款申请','CostPreInvoiceOrder.list', '付款申请查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('付款申请','CostPreInvoiceOrder.create', '付款申请创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('付款申请','CostPreInvoiceOrder.update', '付款申请更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('付款申请','CostPreInvoiceOrder.approval', '付款申请审核权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('付款申请','CostPreInvoiceOrder.confirmation', '付款申请审核权限');");
		/*出纳日记账权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('出纳日记','PaymentCheckOrder.list', '出纳日记账查询权限');");
		/*-------------------------------合同权限-------------------------------------------*/
		/*客户合同权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('客户合同','ContractCustomer.list', '客户合同查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('客户合同','ContractCustomer.create', '客户合同创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('客户合同','ContractCustomer.update', '客户合同更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('客户合同','ContractCustomer.delete', '客户合同删除权限');");
		/*干线供应商合同权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('干线供应商合同','ContractProvider.list', '干线供应商合同查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('干线供应商合同','ContractProvider.create', '干线供应商合同创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('干线供应商合同','ContractProvider.update', '干线供应商合同更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('干线供应商合同','ContractProvider.delete', '干线供应商合同删除权限');");
		/*配送供应商合同权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('配送供应商合同','ContractDelivery.list', '配送供应商合同查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('配送供应商合同','ContractDelivery.create', '配送供应商合同创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('配送供应商合同','ContractDelivery.update', '配送供应商合同更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('配送供应商合同','ContractDelivery.delete', '配送供应商合同删除权限');");
		
		/*-------------------------------基础数据权限---------------------------------------*/
		/*登录用户权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('用户','User.list', '登录用户查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('用户','User.create', '登录用户创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('用户','User.update', '登录用户更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('用户','User.delete', '登录用户删除权限');");
		
		/*角色权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('角色','Role.list', '角色查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('角色','Role.create', '角色创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('角色','Role.update', '角色更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('角色','Role.delete', '角色删除权限');");
		/*用户角色权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('用户角色','UserRole.list', '用户角色查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('用户角色','UserRole.create', '用户角色创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('用户角色','UserRole.update', '用户角色更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('用户角色','UserRole.permission_list', '用户角色权限查询权限');");
		/*角色权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('角色操作','RolePermission.list', '角色操作查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('角色操作','RolePermission.create', '角色操作创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('角色操作','RolePermission.update', '角色操作更新权限');");

		/*客户权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('客户','Customer.list', '客户查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('客户','Customer.create', '客户创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('客户','Customer.update', '客户更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('客户','Customer.delete', '客户删除权限');");
		/*供应商权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商','Provider.list', '供应商查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商','Provider.create', '供应商创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商','Provider.update', '供应商更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商','Provider.delete', '供应商删除权限');");
		/*供应商车辆信息权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商车辆','ProviderCar.list', '供应商车辆信息查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商车辆','ProviderCar.create', '供应商车辆信息创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商车辆','ProviderCar.update', '供应商车辆信息更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商车辆','ProviderCar.delete', '供应商车辆信息删除权限');");
		/*供应商司机信息权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商司机','ProviderDriver.list', '供应商司机信息查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商司机','ProviderDriver.create', '供应商司机信息创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商司机','ProviderDriver.update', '供应商司机信息更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('供应商司机','ProviderDriver.delete', '供应商司机信息删除权限');");
		/*网点[分公司]权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('网点[分公司]','Office.list', '网点[分公司]查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('网点[分公司]','Office.create', '网点[分公司]创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('网点[分公司]','Office.update', '网点[分公司]更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('网点[分公司]','Office.delete', '网点[分公司]删除权限');");
		/*产品信息权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('产品','Product.list', '产品信息查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('产品','Product.create', '产品信息创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('产品','Product.update', '产品信息更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('产品','Product.delete', '产品信息删除权限');");
		/*仓库信息权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('仓库','Warehouse.list', '仓库信息查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('仓库','Warehouse.create', '仓库信息创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('仓库','Warehouse.update', '仓库信息更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('仓库','Warehouse.delete', '仓库信息删除权限');");
		/*干线定义权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('干线定义','Route.list', '干线定义查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('干线定义','Route.create', '干线定义创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('干线定义','Route.update', '干线定义更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('干线定义','Route.delete', '干线定义删除权限');");
		/*收费条目定义*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('收费条目定义','Toll.list', '收费条目定义查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('收费条目定义','Toll.create', '收费条目定义创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('收费条目定义','Toll.update', '收费条目定义更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('收费条目定义','Toll.delete', '收费条目定义删除权限');");
		/*付费条目定义*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('付费条目定义','Pay.list', '付费条目定义查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('付费条目定义','Pay.create', '付费条目定义创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('付费条目定义','Pay.update', '付费条目定义更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('付费条目定义','Pay.delete', '付费条目定义删除权限');");
		/*金融账户信息权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('金融账户','Account.list', '金融账户信息查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('金融账户','Account.create', '金融账户信息创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('金融账户','Account.update', '金融账户信息更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('金融账户','Account.delete', '金融账户信息删除权限');");
		/*行车单权限*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('行车单','CarSummary.list', '行车单查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('行车单','CarSummary.create', '行车单创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('行车单','CarSummary.update', '行车单更新权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('行车单','CarSummary.approval', '行车单审核权限');");
		/*收款确认*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('收款确认','chargeAcceptOrder_list', '收款确认查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('收款确认','chargeAcceptOrder_confirm', '收款确认确认权限');");
		/*付款确认*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('付款确认','costAcceptOrder_list', '付款确认查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('付款确认','costAcceptOrder_confirm', '付款确认确认权限');");
		
		/*报销单*/
		stmt.executeUpdate("insert into permission(module_name,code, name) values('报销单','costReimbureement_list', '报销单查询权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('报销单','costReimbureement_create', '报销单创建权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('报销单','costReimbureement_update', '报销单修改权限');");
		stmt.executeUpdate("insert into permission(module_name,code, name) values('报销单','costReimbureement_confirm', '报销单确认权限');");
		// 将系统管理员 赋予所有权限
        stmt.executeUpdate("insert into role_permission(role_code,permission_code, remark) select 'admin', code, name from permission;");
	}

    private static void initEedaData(Statement stmt) throws SQLException {
        String propertySql = "insert into leads(title, create_date, creator, status, type, "
                + "region, intro, remark, lowest_price, agent_fee, introducer, sales, follower, "
                + "follower_phone, owner, owner_phone, customer_source, building_name, building_no, room_no, building_unit) values("
                + "'%d 初始测试数据-老香洲两盘', CURRENT_TIMESTAMP(), 'jason', '出租', " + "'1房', '老香洲', "
                + "'本月均价8260元/㎡，环比上月 ↑0.22 ，同比去年 ↑14.67 ，查看房价详情>>二 手 房50 套 所在区域香洲 老香洲小区地址香洲珠海市香洲区老香洲为农街为农市场地图>>建筑年代1995-01-01',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'jason', '13509871234',"
                + "'张生', '0756-12345678-123', '58自来客', '五洲花城2期', '2', '1320', '3');";

        // for (int i = 0; i < 50; i++) {
        // String newPropertySql = String.format(propertySql, i);
        // stmt.executeUpdate(newPropertySql);
        // }
        String sqlPrefix = "insert into leads(title, priority, create_date, creator, status, type, "
                + "region, intro, remark, lowest_price, agent_fee, introducer, sales, follower, follower_phone, "
                + "owner, owner_phone, area, total, customer_source, building_name, building_no, room_no, building_unit) values(";

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-老香洲楼盘', '1重要紧急', CURRENT_TIMESTAMP(), 'jason', '出租', " + "'1房', '老香洲', " + "'老香洲楼盘 2房2卫',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'jason', '13509871234',"
                + "'张生', '0756-12345678-123', 36, 1200, '58自来客', '五洲花城2期', '2', '1320', '3');");

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-新香洲楼盘', '1重要紧急', CURRENT_TIMESTAMP(), 'jason', '出售', " + "'2房', '新香洲', " + "'新香洲楼盘 2房2卫',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'jason', '13509871234',"
                + "'张生', '0756-12345678-123', 78, 56, '58自来客', '五洲花城2期', '3', '1321', '5');");

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-老香洲楼盘', '2重要不紧急', CURRENT_TIMESTAMP(), 'jason', '已租', " + "'3房', '老香洲', "
                + "'本月均价8260元/㎡，环比上月 ↑0.22 ，同比去年 ↑14.67 ，查看房价详情>>二 手 房50 套 所在区域香洲 老香洲小区地址香洲珠海市香洲区老香洲为农街为农市场地图>>建筑年代1995-01-01',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'jason', '13509871234',"
                + "'张生', '0756-12345678-123', 92, 2300, '58自来客', '五洲花城2期', '4', '1320', '3');");

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-拱北楼盘', '2重要不紧急', CURRENT_TIMESTAMP(), 'jason', '已售', " + "'4房', '拱北', "
                + "'本月均价8260元/㎡，环比上月 ↑0.22 ，同比去年 ↑14.67 ，查看房价详情>>二 手 房50 套 所在区域香洲 老香洲小区地址香洲珠海市香洲区老香洲为农街为农市场地图>>建筑年代1995-01-01',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'jason', '13509871234',"
                + "'张生', '0756-12345678-123', 150, 120, '58自来客', '五洲花城2期', '6', '1320', '3');");

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-柠溪楼盘', '3不重要紧急', CURRENT_TIMESTAMP(), 'jason', '出租', " + "'5房', '柠溪', "
                + "'本月均价8260元/㎡，环比上月 ↑0.22 ，同比去年 ↑14.67 ，查看房价详情>>二 手 房50 套 所在区域香洲 老香洲小区地址香洲珠海市香洲区老香洲为农街为农市场地图>>建筑年代1995-01-01',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'jason', '13509871234',"
                + "'张生', '0756-12345678-123', 180, 5000, '58自来客', '五洲花城2期', '', '1325', '8');");

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-柠溪楼盘', '3不重要紧急', CURRENT_TIMESTAMP(), 'jason', '出租', " + "'6房', '柠溪', "
                + "'本月均价8260元/㎡，环比上月 ↑0.22 ，同比去年 ↑14.67 ，查看房价详情>>二 手 房50 套 所在区域香洲 老香洲小区地址香洲珠海市香洲区老香洲为农街为农市场地图>>建筑年代1995-01-01',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'jason', '13509871234',"
                + "'张生', '0756-12345678-123', 180, 5000, '58自来客', '五洲花城2期', '2', '', '5');");

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-柠溪楼盘', '3不重要紧急', CURRENT_TIMESTAMP(), 'jason', '出租', " + "'6房以上', '柠溪', "
                + "'本月均价8260元/㎡，环比上月 ↑0.22 ，同比去年 ↑14.67 ，查看房价详情>>二 手 房50 套 所在区域香洲 老香洲小区地址香洲珠海市香洲区老香洲为农街为农市场地图>>建筑年代1995-01-01',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'jason', '13509871234',"
                + "'张生', '0756-12345678-123', 180, 5000, '58自来客', '五洲花城2期', '2', '1322', '');");

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-前山地皮', '4不重要不紧急', CURRENT_TIMESTAMP(), 'd_user1', '已售', " + "'地皮', '前山', "
                + "'本月均价8260元/㎡，环比上月 ↑0.22 ，同比去年 ↑14.67 ，查看房价详情>>二 手 房50 套 所在区域香洲 老香洲小区地址香洲珠海市香洲区老香洲为农街为农市场地图>>建筑年代1995-01-01',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'd_user1', '13509871234',"
                + "'张生', '0756-12345678-123', 40000, 3000, '58自来客', '五洲花城2期', '8', '1320', '3');");

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-柠溪楼盘', '3不重要紧急', CURRENT_TIMESTAMP(), 'jason', '出租', " + "'6房以上', '柠溪', "
                + "'本月均价8260元/㎡，环比上月 ↑0.22 ，同比去年 ↑14.67 ，查看房价详情>>二 手 房50 套 所在区域香洲 老香洲小区地址香洲珠海市香洲区老香洲为农街为农市场地图>>建筑年代1995-01-01',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'jason', '13509871234',"
                + "'张生', '0756-12345678-123', 180, 5000, '58自来客', '五洲花城2期', '2', '1322', '');");

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-前山地皮', '4不重要不紧急', CURRENT_TIMESTAMP(), 'd_user1', '已售', " + "'地皮', '前山', "
                + "'本月均价8260元/㎡，环比上月 ↑0.22 ，同比去年 ↑14.67 ，查看房价详情>>二 手 房50 套 所在区域香洲 老香洲小区地址香洲珠海市香洲区老香洲为农街为农市场地图>>建筑年代1995-01-01',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'd_user1', '13509871234',"
                + "'张生', '0756-12345678-123', 40000, 3000, '58自来客', '五洲花城2期', '8', '1320', '3');");

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-柠溪楼盘', '3不重要紧急', CURRENT_TIMESTAMP(), 'jason', '出租', " + "'6房以上', '柠溪', "
                + "'本月均价8260元/㎡，环比上月 ↑0.22 ，同比去年 ↑14.67 ，查看房价详情>>二 手 房50 套 所在区域香洲 老香洲小区地址香洲珠海市香洲区老香洲为农街为农市场地图>>建筑年代1995-01-01',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'jason', '13509871234',"
                + "'张生', '0756-12345678-123', 180, 5000, '58自来客', '五洲花城2期', '2', '1322', '');");

        stmt.executeUpdate(sqlPrefix + "'初始测试数据-前山地皮', '4不重要不紧急', CURRENT_TIMESTAMP(), 'd_user1', '已售', " + "'地皮', '前山', "
                + "'本月均价8260元/㎡，环比上月 ↑0.22 ，同比去年 ↑14.67 ，查看房价详情>>二 手 房50 套 所在区域香洲 老香洲小区地址香洲珠海市香洲区老香洲为农街为农市场地图>>建筑年代1995-01-01',"
                + "'remark.....', 7000, 7500, " + "'介绍人金', 'kim', 'd_user1', '13509871234',"
                + "'张生', '0756-12345678-123', 40000, 3000, '58自来客', '五洲花城2期', '8', '1320', '3');");

        stmt.executeUpdate("insert into support_case(title, create_date, creator, status, type, case_desc, note) values("
                + "'这是一个建议示例', CURRENT_TIMESTAMP(), 'jason', '新提交','出错', '这是一个建议示例，您可以在这里提交你所遇到的问题，我们会尽快跟进。', '这是回答的地方');");

        stmt.executeUpdate("insert into order_header(order_no, type, status, creator, create_date,  remark) values("
                + "'SalesOrder001', 'SALES_ORDER', 'New', 'jason', CURRENT_TIMESTAMP(), '这是一个销售订单示例');");
        stmt.executeUpdate("insert into order_item(order_id, item_name, item_desc, quantity, unit_price) values("
                + "1, 'P001', 'iPad Air', 1, 3200);");

        // +
        // " values (77, 'TSC;TSR;', 'TP;', 'SYS_PVD_PAS0000000278', 'Maersk Indonesia', 'PAS0000000278', 'OLINL/JKT', '', '', '', '', '', '', '', '', '', '', '', '', 'A', '', null, '', null, null, '', null, '', null, '', '', '', '', '', '', '', null, '', '', null, 'DCS');");
        // 地产客户
        Party p = new Party();
        Date createDate = Calendar.getInstance().getTime();
        p.set("party_type", "地产客户").set("create_date", createDate).set("creator", "jason").save();
        long partyId = p.getLong("id");
        PartyAttribute pa = new PartyAttribute();
        pa.set("party_id", partyId).set("attr_name", "title").set("attr_value", "求2房近3小").save();
        PartyAttribute pa1 = new PartyAttribute();
        pa1.set("party_id", partyId).set("attr_name", "client_name").set("attr_value", "温生").save();
        PartyAttribute paPriority = new PartyAttribute();
        paPriority.set("party_id", partyId).set("attr_name", "priority").set("attr_value", "1重要紧急").save();
        PartyAttribute pa2 = new PartyAttribute();
        pa2.set("party_id", partyId).set("attr_name", "status").set("attr_value", "求租").save();
        PartyAttribute pa3 = new PartyAttribute();
        pa3.set("party_id", partyId).set("attr_name", "region").set("attr_value", "老香洲").save();
        PartyAttribute pa4 = new PartyAttribute();
        pa4.set("party_id", partyId).set("attr_name", "type").set("attr_value", "1房").save();

        // 外部user 创建的客户
        Party p1 = new Party();
        createDate = Calendar.getInstance().getTime();
        p1.set("party_type", "地产客户").set("create_date", createDate).set("creator", "demo").save();
        partyId = p1.getLong("id");
        PartyAttribute p1_pa = new PartyAttribute();
        p1_pa.set("party_id", partyId).set("attr_name", "title").set("attr_value", "求前山小区").save();
        PartyAttribute p1_pa1 = new PartyAttribute();
        p1_pa1.set("party_id", partyId).set("attr_name", "client_name").set("attr_value", "温生").save();
        PartyAttribute p1_paPriority = new PartyAttribute();
        p1_paPriority.set("party_id", partyId).set("attr_name", "priority").set("attr_value", "1重要紧急").save();
        PartyAttribute p1_pa2 = new PartyAttribute();
        p1_pa2.set("party_id", partyId).set("attr_name", "status").set("attr_value", "求购").save();
        PartyAttribute p1_pa3 = new PartyAttribute();
        p1_pa3.set("party_id", partyId).set("attr_name", "region").set("attr_value", "拱北").save();
        PartyAttribute p1_pa4 = new PartyAttribute();
        p1_pa4.set("party_id", partyId).set("attr_name", "type").set("attr_value", "1房").save();
        PartyAttribute p1_pa5 = new PartyAttribute();
        p1_pa5.set("party_id", partyId).set("attr_name", "area").set("attr_value", "120").save();
        PartyAttribute p1_pa6 = new PartyAttribute();
        p1_pa6.set("party_id", partyId).set("attr_name", "total").set("attr_value", "200").save();

        // 贷款客户 attributes
        for (int i = 1; i <= 1; i++) {
            stmt.executeUpdate("insert into party(party_type, create_date, creator) values('贷款客户', CURRENT_TIMESTAMP(), 'demo');");
            stmt.executeUpdate("insert into party_attribute(party_id, attr_name, attr_value) values(" + i + ", 'priority', '1重要紧急');");
            stmt.executeUpdate("insert into party_attribute(party_id, attr_name, attr_value) values(" + i + ", 'name', '温生');");
            stmt.executeUpdate("insert into party_attribute(party_id, attr_name, attr_value) values(" + i + ", 'loan_max', '15万');");
            stmt.executeUpdate("insert into party_attribute(party_id, attr_name, attr_value) values(" + i + ", 'mobile', '1357038829');");
            stmt.executeUpdate("insert into party_attribute(party_id, attr_name, attr_value) values(" + i + ", 'email', 'test@test.com');");
        }

        // 其他客户 attributes
        stmt.executeUpdate("insert into party(party_type, create_date, creator) values('其他客户', CURRENT_TIMESTAMP(), 'demo');");
        stmt.executeUpdate("insert into party_attribute(party_id, attr_name, attr_value) values(1, 'note', '工商注册');");
        stmt.executeUpdate("insert into party_attribute(party_id, attr_name, attr_value) values(1, 'mobile', '1357038829');");
        stmt.executeUpdate("insert into party_attribute(party_id, attr_name, attr_value) values(1, 'email', 'test@test.com');");

    }

    public static void newCustomer() {
        Contact contact = new Contact();
        contact.set("company_name", "珠海创诚易达信息科技有限公司").set("contact_person", "温生").set("email", "test@test.com").set("abbr", "珠海创诚易达");
        contact.set("mobile", "12345671").set("phone", "113527229313").set("address", "香洲珠海市香洲区老香洲为农街为农市场1").set("postal_code", "5190001")
                .set("location", "440116").save();
        Contact contact7 = new Contact();
        contact7.set("company_name", "珠海博兆计算机科技有限公司").set("contact_person", "温生").set("email", "test@test.com").set("abbr", "珠海博兆");
        contact7.set("mobile", "12345671").set("phone", "113527229313").set("address", "香洲珠海市香洲区老香洲为农街为农市场1").set("postal_code", "5190001")
                .set("location", "441900").save();
        Contact contact2 = new Contact();
        contact2.set("company_name", "北京制药珠海分公司").set("contact_person", "黄生").set("email", "test@test.com").set("abbr", "北京制药珠分");
        contact2.set("mobile", "12345672").set("phone", "213527229313").set("address", "香洲珠海市香洲区老香洲为农街为农市场2").set("postal_code", "5190002")
                .set("location", "110102").save();
        Contact contact3 = new Contact();
        contact3.set("company_name", "广州某某运输公司").set("contact_person", "李生").set("email", "test@test.com").set("abbr", "广某运输");;
        contact3.set("mobile", "12345673").set("phone", "313527229313").set("address", "广州罗岗区为农街为农市场").set("postal_code", "5190003")
                .set("location", "440116").save();// 440116
                                                  // 广州罗岗区
        Contact contact4 = new Contact();
        contact4.set("company_name", "天津运输有限公司").set("contact_person", "何生").set("email", "test@test.com");
        contact4.set("mobile", "12345674").set("phone", "413527229313").set("address", "香洲珠海市香洲区老香洲为农街为农市场4").set("postal_code", "5190004")
                .save();
        Contact contact5 = new Contact();
        contact5.set("company_name", "天津佛纳甘科技有限公司").set("contact_person", "何生").set("email", "test@test.com");
        contact5.set("mobile", "12345674").set("phone", "413527229313").set("address", "香洲珠海市香洲区老香洲为农街为农市场4").set("postal_code", "5190004")
                .set("location", "442000").save();
        Contact contact6 = new Contact();
        contact6.set("company_name", "天津佛纳甘科技有限公司").set("contact_person", "何生").set("email", "test@test.com");
        contact6.set("mobile", "12345674").set("phone", "413527229313").set("address", "香洲珠海市香洲区老香洲为农街为农市场4").set("postal_code", "5190004")
                .set("location", "440402").save();
        Contact contact8 = new Contact();
        contact8.set("contact_person", "张三").set("phone", "15512345678").save();
        Contact contact9 = new Contact();
        contact9.set("contact_person", "李四").set("phone", "15812345678").save();
        Contact contact10 = new Contact();
        contact10.set("contact_person", "张三三").set("phone", "13112345678").save();
        Contact contact11 = new Contact();
        contact11.set("contact_person", "李四四").set("phone", "13312345678").save();

        Party p1 = new Party();
        Party p2 = new Party();
        Party p3 = new Party();
        Party p4 = new Party();
        Party p5 = new Party();
        Party p6 = new Party();
        Party p7 = new Party();
        Party p8 = new Party();
        Party p9 = new Party();
        Party p10 = new Party();
        Party p11 = new Party();
        Date createDate = Calendar.getInstance().getTime();
        p1.set("contact_id", contact.getLong("id")).set("party_type", "CUSTOMER").set("create_date", createDate).set("creator", "demo")
                .set("payment", "monthlyStatement").save();
        p7.set("contact_id", contact7.getLong("id")).set("party_type", "CUSTOMER").set("create_date", createDate).set("creator", "demo")
                .set("payment", "freightCollect").save();
        p2.set("contact_id", contact2.getLong("id")).set("party_type", "CUSTOMER").set("create_date", createDate).set("creator", "demo")
                .set("payment", "cashPayment").save();
        p3.set("contact_id", contact3.getLong("id")).set("party_type", "SERVICE_PROVIDER").set("create_date", createDate)
                .set("payment", "demo").save();
        p4.set("contact_id", contact4.getLong("id")).set("party_type", "SERVICE_PROVIDER").set("create_date", createDate)
                .set("creator", "demo").save();
        p5.set("contact_id", contact5.getLong("id")).set("party_type", "NOTIFY_PARTY").set("create_date", createDate)
                .set("creator", "demo").save();
        p6.set("contact_id", contact6.getLong("id")).set("party_type", "NOTIFY_PARTY").set("create_date", createDate)
                .set("creator", "demo").save();
        p8.set("contact_id", contact8.getLong("id")).set("party_type", Party.PARTY_TYPE_DRIVER).set("create_date", createDate)
                .set("creator", "demo").save();
        p9.set("contact_id", contact9.getLong("id")).set("party_type", Party.PARTY_TYPE_DRIVER).set("create_date", createDate)
                .set("creator", "demo").save();
        p10.set("contact_id", contact10.getLong("id")).set("party_type", Party.PARTY_TYPE_SP_DRIVER).set("create_date", createDate)
        		.set("creator", "demo").save();
        p11.set("contact_id", contact11.getLong("id")).set("party_type", Party.PARTY_TYPE_SP_DRIVER).set("create_date", createDate)
        		.set("creator", "demo").save();
    }
    
}
