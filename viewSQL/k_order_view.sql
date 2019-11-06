CREATE VIEW k_order_view AS
    SELECT 
        o.sequence_number,
        o.order_type_code,
        o.create_time,
        o.owner_domain_id,
        o.owner_name,
        o.sales_tel,
        o.contractor_code,
        o.contractor_name,
        o.contractor_class_code,
        o.contractor_class_name,
        o.office_code AS sales_office_code,
        v.k_orders_id AS order_id,
        v.id AS version_id,
        v.version,
        v.status,
        v.create_time AS version_create_time,
        v.submit_date,
        v.bpm_submit_time,
        v.opt_time,
        v.k_order_info_id AS order_info_id,
        d.*,
        f.id AS form_id,
        f.earliest_delivery_date,
        f.earliest_product_date,
        f.comments AS form_comments,
        f.operator AS form_operator,
        f.type AS form_type,
        f.opt_time AS form_opt_time,
        s.id AS support_info_id,
        s.contract_number,
        s.opterator_domain_id,
        s.opt_time AS support_info_opt_time
    FROM
        k_orders o
            LEFT JOIN
        k_order_support_info s ON s.k_orders_id = o.id
            LEFT JOIN
        k_order_version v ON v.k_orders_id = o.id
            LEFT JOIN
        k_order_info d ON d.id = v.k_order_info_id
            LEFT JOIN
        k_forms f ON f.k_order_info_id = d.id;