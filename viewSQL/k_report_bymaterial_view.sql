CREATE VIEW k_report_bymaterial_view AS
    SELECT 
        CONCAT((SELECT CURTIME()), '_', (SELECT RAND())) AS id,
        c.`status` AS STATUS,
        e.sequence_number,
        e.owner_name,
        e.owner_domain_id,
        e.office_code,
        e.create_time,
        c.contract_seq,
        e.contractor_code,
        e.contractor_class_code,
        e.contractor_class_name,
        e.order_type_code,
        i.*
    FROM
        (SELECT 
            j.k_order_version_id AS k_order_version_id,
                k_orders_id,
                STATUS,
                k_order_info_id,
                k.sequence_number AS contract_seq
        FROM
            (SELECT 
            a.k_order_version_id,
                a.k_orders_id,
                a.STATUS,
                b.k_order_info_id
        FROM
            (SELECT 
            MAX(id) AS k_order_version_id, k_orders_id, STATUS
        FROM
            k_order_version
        WHERE
            STATUS = '1'
        GROUP BY k_orders_id) a, k_order_version b
        WHERE
            a.k_order_version_id = b.id) j
        LEFT JOIN k_contract k ON j.k_order_version_id = k.k_order_version_id) c,
        (SELECT 
            id AS k_order_info_id,
                office_name,
                is_reformed,
                contract_rmb_amount,
                warranty,
                currency_code,
                currency_name,
                contract_amount,
                exchange,
                sales_type,
                group_code,
                group_name,
                install_term_code,
                install_term_name,
                contactor_1_id,
                contactor_1_tel,
                contactor_2_id,
                contactor_2_tel,
                contactor_3_id,
                contactor_3_tel,
                is_new,
                terminal_industry_code,
                terminal_industry_code_name,
                body_discount,
                main_discount,
                h.*
        FROM
            k_order_info d
        LEFT JOIN (SELECT 
            f.k_order_info_id AS order_id,
                material_code,
                material_name,
                is_purchased,
                quantity,
                sale_amount,
                sale_amount / quantity AS price,
                discount,
                measure_unit_code,
                earliest_delivery_date
        FROM
            k_forms f
        LEFT JOIN k_item_details g ON f.id = g.k_forms_id) h ON d.id = h.order_id) i,
        k_orders e
    WHERE
        c.k_order_info_id = i.order_id
            AND c.k_orders_id = e.id;