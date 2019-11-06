CREATE VIEW `k_order_version_view` AS
    SELECT 
        o.sequence_number,
        v.k_orders_id AS order_id,
        v.id AS version_id,
        v.version,
        v.status,
        v.create_time,
        v.submit_date,
        v.bpm_submit_time,
        v.opt_time,
        v.k_order_info_id AS order_info_id
    FROM
        k_orders o
            LEFT JOIN
        k_order_version v ON v.k_orders_id = o.id;