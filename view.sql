DROP VIEW IF EXISTS `bohemian`.k_report_bymaterial_view;

 CREATE VIEW k_report_bymaterial_view AS 

SELECT
	CONCAT(
		(SELECT CURTIME()),
		'_',
		(SELECT RAND())
	) AS id,
	c.`status` AS STATUS,
	e.sequence_number,
	-- 流水号
	e.owner_name,
	-- 客户经理
	e.owner_domain_id,
	-- 客户经理
	e.office_code,
	-- 区域
	-- 签约日期
	e.create_time,
 -- 合同号
  c.contract_seq,
 -- 签约人
	e.contractor_code,
	-- 客户性质
	e.contractor_class_code,
	e.contractor_class_name,
	-- 订单状态
	e.order_type_code,
	i.*
FROM
	(
		select 
j.k_order_version_id as k_order_version_id,
k_orders_id,
STATUS,
k_order_info_id,
k.sequence_number as contract_seq
 from
(SELECT
			a.k_order_version_id,
			a.k_orders_id,
			a. STATUS,
			b.k_order_info_id,
			b.k_order_version_id_parent
		FROM
			(
				SELECT
					max(id) AS k_order_version_id,
					k_orders_id,
					STATUS
				FROM
					k_order_version
				WHERE
					STATUS = '1'
				GROUP BY
					k_orders_id
			) a,
			k_parent_order_version b
		WHERE
			a.k_order_version_id = b.k_order_version_id) j
left join k_contract k
  on j.k_order_version_id = k.k_order_version_id
	) c,
	(
		SELECT
			id AS k_order_info_id,
			-- 区域
			office_name,
			-- 是否改造店
			is_reformed,
			-- 合同金额
			contract_rmb_amount,
			-- 保修期限
			warranty,
			-- 币种
			currency_code,
			currency_name,
			-- 原币合同金额
			contract_amount,
			-- 汇率
			exchange,
			-- 合同状态
			sales_type,
			-- 毛利率
			-- 中心
			group_code,
			group_name,
			-- 到货地址
			-- 安装公司
			install_term_code,
			-- 安装公司名
			install_term_name,
			-- 收货方式
			-- 授权人
			contactor_1_id,
			contactor_1_tel,
			contactor_2_id,
			contactor_2_tel,
			contactor_3_id,
			contactor_3_tel,
			-- 收货人身份证
			-- 结算方式
			-- 是否新客户
			is_new,
			-- 终端客户性质
			terminal_industry_code,
			terminal_industry_code_name,
			-- 机身、机主折扣
			body_discount,
			main_discount,
			h.*
		FROM
			k_order_info d
		LEFT JOIN (
			SELECT
				f.k_order_info_id AS order_id,
				-- 物料
				material_code,
				-- 物料名称
				material_name,
				-- 物料专用号
				
				-- 物料属性
				is_purchased,
				-- 数量
				quantity,
				-- 金额
				sale_amount,
				-- 单价
				sale_amount / quantity AS price,
				-- 折扣
				discount,
				-- 单位
				measure_unit_code,
				-- 单位名
				
				-- 要求发货时间
				earliest_delivery_date
			FROM
				k_forms f
			LEFT JOIN k_item_details g ON f.id = g.k_forms_id
		) h ON d.id = h.order_id
	) i,
	k_orders e
WHERE
	c.k_order_info_id = i.order_id
AND c.k_orders_id = e.id;







DROP VIEW
IF EXISTS `bohemian`.`k_speical_order_vo_view`;

CREATE VIEW k_speical_order_vo_view AS SELECT
	CONCAT(
		a.k_orders_id,
		'_',
		b.k_order_version_id,
		'_',
		c.id,
		'_',
		d.id
	) AS id,
	d.sequence_number AS sequence_number,
	d.order_type_code AS order_type_code,
	d.create_time AS create_time,
	d.owner_domain_id AS owner_domain_id,
	d.owner_name AS owner_name,
	d.sales_tel AS sales_tel,
	d.contractor_code AS contractor_code,
	d.contractor_name AS contractor_name,
	d.contractor_class_code AS contractor_class_code,
	d.contractor_class_name AS contractor_class_name,
	d.office_code AS office_code,
	a.k_order_version_id AS k_order_version_id,
	a.k_orders_id AS k_orders_id
FROM
	(
		SELECT
			k_orders_id,
			max(id) AS k_order_version_id
		FROM
			k_order_version
		GROUP BY
			k_orders_id
		ORDER BY
			create_time DESC
	) a,
	k_parent_order_version b,
	k_order_info c,
	k_orders d
WHERE
	a.k_order_version_id = b.k_order_version_id
	
AND b.k_order_info_id = c.id
AND a.k_orders_id = d.id
AND d.order_type_code IN ('05', '06');


-- 订单版本
DROP VIEW
IF EXISTS `bohemian`.`k_order_version_view`;

CREATE VIEW k_order_version_view AS 
SELECT o.sequence_number, 
	   v.k_orders_id as order_id
       v.id as version_id, 
	   v.version, 
	   v.status, 
	   v.create_time, 
	   pv.opt_time, 
	   pv.k_order_version_id_parent as parent_version_id, 
	   pv.k_order_info_id as order_info_id
FROM k_orders o 
left join k_order_version v on v.k_orders_id = o.id
left join k_parent_order_version pv on pv.k_order_version_id = v.id;


-- 订单
DROP VIEW
IF EXISTS `bohemian`.`k_order_view`;

CREATE VIEW k_order_view AS 
SELECT o.sequence_number, 
       o.order_type_code,
	   o.create_time,
	   o.owner_domain_id,
	   o.owner_name,
	   o.sales_tel,
	   o.contractor_code,
	   o.contractor_name,
	   o.contractor_class_code,
	   o.contractor_class_name,
	   -- 销售员所属区域
	   o.office_code as sales_office_code,
	   v.k_orders_id as order_id,
       v.id as version_id, 
	   v.version, 
	   v.status, 
	   v.create_time as version_create_time, 
	   pv.opt_time, 
	   pv.k_order_version_id_parent as parent_version_id, 
	   pv.k_order_info_id as order_info_id,
	   d.*,
	   f.id as form_id,
	   f.earliest_delivery_date,
	   f.earliest_product_date,
	   f.comments as form_comments,
	   f.operator as form_operator,
	   f.type as form_type,
	   f.opt_time as form_opt_time
FROM k_orders o 
left join k_order_version v on v.k_orders_id = o.id
left join k_parent_order_version pv on pv.k_order_version_id = v.id
left join k_order_info d on d.id = pv.k_order_info_id
left join k_forms f on f.k_order_info_id = d.id;


