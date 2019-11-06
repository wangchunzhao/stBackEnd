CREATE VIEW k_speical_order_vo_view AS
    SELECT 
        CONCAT(a.k_orders_id,
                '_',
                a.k_order_version_id,
                '_',
                c.id,
                '_',
                d.id) AS id,
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
        (SELECT 
            k_orders_id, MAX(id) AS k_order_version_id
        FROM
            k_order_version
        GROUP BY k_orders_id
        ORDER BY create_time DESC) a,
        k_order_version b,
        k_order_info c,
        k_orders d
    WHERE
        a.k_order_version_id = b.id
            AND b.k_order_info_id = c.id
            AND a.k_orders_id = d.id
            AND d.order_type_code IN ('05' , '06');