DROP VIEW
IF EXISTS `bohemian`.k_report_material_view;

CREATE VIEW k_report_material_view AS SELECT
	CONCAT(
		c.k_order_version_id,
		'_',
		c.k_orders_id,
		'_',
		c.k_order_version_id1
	) AS id,
	c.`status` AS STATUS,
	e.sequence_number AS sequence_number,
	-- 流水号
	e.owner_name AS owner_name,
	-- 客户经理
	e.owner_domain_id AS owner_domain_id,
	-- 客户经理
	e.office_code AS office_code,
	-- 区域
	-- 中心
	-- 合同号
	e.contractor_code AS contractor_code,
	-- 合同编号
	e.contractor_class_code AS contractor_class_code,
	e.contractor_class_name AS contractor_class_name,
	i.*
FROM
	(
		SELECT
			a.k_order_version_id,
			a.k_orders_id,
			a. STATUS,
			b.k_order_info_id,
			b.k_order_version_id1
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
			a.k_order_version_id = b.k_order_version_id
	) c,
	(
		SELECT
			id AS k_order_info_id,
			office_name,
			is_reformed,
			-- 是否改造店
			contract_rmb_amount,
			-- 合同金额
			sales_type,
			-- 合同状态
			h.discount -- 毛利率
			-- --是否长期折扣
			main_discount,
			-- 折扣
			-- 到货地址
			install_term_code,
			-- 安装公司
			install_term_name,
			-- 安装公司名
			-- 收货方式
			contactor_1_id,
			contactor_1_tel,
			contactor_2_id,
			contactor_2_tel,
			contactor_3_id,
			contactor_3_tel,
			-- 授权人
			-- 收货人身份证
			-- 结算方式
			warranty,
			-- 保修期限
			currency_code,
			-- 币种
			currency_name,
			contract_amount,
			-- 原币合同金额
			exchange,
			-- 汇率
			is_new,
			terminal_industry_code,
			terminal_industry_code_name,
			-- 是否新客户
			h.*
		FROM
			k_order_info d
		LEFT JOIN (
			SELECT
				f.k_order_info_id AS order_id,
				material_code,
				-- 物料
				material_name,
				-- 物料名称
				material_specific_Number,
				-- 物料专用号
				material_attribute,
				-- 物料属性
				quantity,
				-- 数量
				amount,
				-- 金额
				amount / quantity AS price,
				-- 单价
				discount,
				-- 折扣
				measure_unit_code,
				-- 单位
				measure_unit_name,
				-- 单位名
				earliest_delivery_date -- 要求发货时间
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



