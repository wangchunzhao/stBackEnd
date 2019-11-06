CREATE VIEW `sap_material_info_view` AS
    SELECT 
        t.code AS price_type_code,
        t.name AS price_type_name,
        p.price,
        m.code,
        m.description,
        m.is_configurable,
        m.is_purchased,
        m.stand_price,
        m.sap_unit_of_measurement_code,
        unit.name AS unit_name,
        m.sap_material_groups_code,
        g.name AS group_name,
		m.sap_clazz_code
    FROM
        sap_materials m
            LEFT JOIN
        sap_material_groups g ON m.sap_material_groups_code = g.code
            LEFT JOIN
        sap_unit_of_measurement unit ON unit.code = m.sap_unit_of_measurement_code
            RIGHT JOIN
        sap_materials_price p ON p.sap_materials_code = m.code
            LEFT JOIN
        sap_price_type t ON t.code = p.sap_price_type_code