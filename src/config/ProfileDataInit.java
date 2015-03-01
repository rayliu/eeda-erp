package config;

import java.sql.Statement;

public class ProfileDataInit {
    public static void initProfile(Statement stmt) {
        try {
            // 客户信息
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS) values('示例客户---广州广电运通金融电子股份有限公司','示例客户---广电运通','蒙思哲','440100','（简单说明客户性质和产品情况）','mszhe@grgbanking.com','82188856','广州市萝岗区科林路11号');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS) values('湖南长城信息金融设备有限责任公司','长城信息','任学安','430100','国企，我司主要负责运输客户大型ATM机器','renxuean@gwi.com.cn','0731-84932734/13787054674','长沙经济技术开发区东3路5号');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS) values('中钞科宝现金处理技术（北京）有限公司','中钞科堡','杨济如','110100','','yangjiru@cbpm-keba.com','010-56627760','北京市海淀区上地创业路20号');");
            // 供应商信息                                                                                              
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('示例干线供应商---海南城市之星物流有限公司','示例干线供应商---海南城市之星物流有限公司','邓孚任','330000','海南省海口市海口农商银行','','18608903177','海口市龙昆南路56号龙泉家园C栋2103房','line');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('浙江淇辉物流有限公司','浙江淇辉物流有限公司','谢经理','440100','工商银行 广州市环城支行','','37417190','白云区沙太路林安物流园十街1001-1008档','line');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('示例提货供应商---广州市其辉物流有限公司（原4部）','示例提货供应商---广州市其辉物流有限公司','小林','330000','工商银行 广州市环城支行','','87434270','白云区沙太路林安物流园十街1009-1023档','pickup');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('济南世纪顺通运输有限公司','济南世纪顺通运输有限公司','小潇','440100','中国工行太和分行','','020-87439061','白云区太和镇白云货运市场A4区17、18档','line');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('广东盛丰物流有限公司','广东盛丰物流有限公司','刘经理','440100','中国工商银行  佛山市南海金沙湾支行','','13560012022','白云区太和镇大源北路，林安物流园六街西面21-22档','line');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('广州市贵腾物流有限公司','广州市贵腾物流有限公司','','440100','中国银行 广州东风中路支行','','62192677','沙太北路丰和南场D5栋009-011档','line');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('海南城市之星物流有限公司（广州分公司）','海南城市之星物流有限公司（广州分公司）','邓孚任','460106','海南省海口市海口农商银行','','18608903177','广州市白云区石井镇鸦岗大道锦亿物流中心A34-35档','delivery');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('杭州汉唐货运有限公司','杭州汉唐货运有限公司','小潘','330000','建行天水支行（浙江省杭州市）','','18268803197','杭州市江干区杭海路189号','delivery');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('天地货运','天地货运','毛伟刚','330000','建行天水支行（浙江省杭州市）','','13806507826','宁波市石碶街道雍景苑小区25幢602室','delivery');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('示例配送供应商---济南骏运展达物流运输有限公司','济南骏运展达物流运输有限公司','李骏含','370000','山东省齐鲁银行蓝翔路支行','','13181709281','济南市天桥区蓝翔路7号','delivery');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('广州宅急送快运有限公司福州分公司','广州宅急送快运有限公司福州分公司','吕华','350000','工商银行 福州星光支行','','13960829976','福州市仓山区六风村物流仓库','delivery');");
            stmt.executeUpdate("insert into contact(COMPANY_NAME, ABBR, CONTACT_PERSON, LOCATION, INTRODUCTION, EMAIL, phone, ADDRESS, SP_TYPE) values('贵州宝彩物流有限公司','贵州宝彩物流有限公司','邓碧云','330000','工商银行 贵阳市甲秀支行','','13985541890','乌当区保利温泉3期6幢3单元1801','delivery');");

            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, payment) values('CUSTOMER','1','demo','广州广电运通金融电子股份有限公司','monthlyStatement');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, payment) values('CUSTOMER','2','demo','湖南长城信息金融设备有限责任公司','freightCollect');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, payment) values('CUSTOMER','3','demo','中钞科宝现金处理技术（北京）有限公司','cashPayment');");

            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','4','demo','邓孚任','perUnit');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','5','demo','广州市其辉物流有限公司','perUnit');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','6','demo','广州市其辉物流有限公司','perUnit');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','7','demo','冯芝超','perUnit');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','8','demo','广东盛丰物流有限公司','perUnit');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','9','demo','广州市贵腾物流有限公司','perCar');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','10','demo','邓孚任','perCar');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','11','demo','浙江天翔航空货运代理有限公司','perCar');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','12','demo','浙江天翔航空货运代理有限公司','perCargo');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','13','demo','济南骏运展达物流运输有限公司','perCargo');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','14','demo','吕华','perCargo');");
            stmt.executeUpdate("insert into party(PARTY_TYPE, CONTACT_ID, CREATOR, RECEIPT, charge_type) values('SERVICE_PROVIDER','15','demo','邓碧云','perCargo');");

            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('源鸿广州仓','广州市萝岗区宏明路严天商业街11号','2000','自营仓库','广州源鸿物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('济南中转仓','济南市天桥区蓝翔中路2-1号 ','1000','配送供应商仓库','济南骏运展达物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('青岛中转仓','青岛市西元庄物流市场45.46号库','450','配送供应商仓库','济南骏运展达物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('北京中转仓','北京市朝阳区东风乡高庙村68号','1000','配送供应商仓库','北京文成物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('天津中转仓','天津市北辰区外环线与北辰西道交口天津韩家墅海吉星物流园区商业31-32号，仓库4栋1号','450','配送供应商仓库','天津源鸿物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('太原中转仓','太原小店区许坦东街23号','450','配送供应商仓库','山西启程物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('上海中转仓','上海市南翔嘉好路230号','450','配送供应商仓库','上海源鸿物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('石家庄中转仓','石家庄市西二环北路106号','450','配送供应商仓库','石家庄源鸿物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('杭州中转仓','杭州市江干区九堡家苑二区多层公寓三幢三单元11-12号','450','配送供应商仓库','杭州汉唐货运有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('义乌中转仓','义乌市西城一区22幢1号','300','配送供应商仓库','杭州汉唐货运有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('宁波中转仓','宁波市江北区康庄南路','300','配送供应商仓库','宁波天地货运');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('西安中转仓','西安市石化大道北徐十字西口广利丰物流园7号库，门口挂的是徐工集团的牌子 ','450','配送供应商仓库','西安乐三家物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('贵阳中转仓','贵阳市南明区龙洞堡','450','配送供应商仓库','贵州宝彩物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('厦门中转仓','厦门市湖里区电前6路','450','配送供应商仓库','福建畅佳物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('福州中转仓','福州市仓山区战备路580盘屿仓库','450','配送供应商仓库','福州宅急送物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('南宁中转仓','南宁市高新区科园大道70号','600','配送供应商仓库','南宁源鸿物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('海口中转仓','海口市椰海大道苍西村路口进来村1000米，二十六小便利店对面','300','配送供应商仓库','海南嘉诚物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('兰州中转仓','兰州市城关区古城坪40号','300','配送供应商仓库','兰州诚博物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('拉萨中转仓','拉萨市藏大西路','150','配送供应商仓库','拉萨蓝天物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('西宁中转仓','西宁市城东区互助中路92号东方物流','150','配送供应商仓库','西宁东方货运有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('武汉中转仓','武汉市硚口区汉正西物流中心A座24号','150','配送供应商仓库','武汉正旭物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('长沙中转仓','长沙市雨花区高桥物流中心新2号小区C23栋3单元302室','100','配送供应商仓库','长沙源鸿物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('呼市中转仓','内蒙古呼和浩特市110国道503公里处路南广硕物流园南方物流','200','配送供应商仓库','南方物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('成都中转仓','成都市武侯区金花镇金兴北路518号','200','配送供应商仓库','成都千顺物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('郑州中转仓','郑州市航海路七里河美景鸿5-1-2402 ','300','配送供应商仓库','郑州晨曦物流有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('沈阳中转仓','沈阳市和平区三好街118号','300','配送供应商仓库','沈阳一运实业有限责任公司科技商城配送中心');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('长春中转仓','长春市绿园区青年路与荣祥路交汇西行300米金星技校院内联捷物流','300','配送供应商仓库','吉林省联捷物流仓储有限公司');");
            stmt.executeUpdate("insert into warehouse(WAREHOUSE_NAME, WAREHOUSE_ADDRESS, WAREHOUSE_AREA,WAREHOUSE_TYPE,sp_name) values('重庆中转仓','重庆市九龙坡区谢家湾正街3号','200','配送供应商仓库','重庆兵工物流有限公司');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
