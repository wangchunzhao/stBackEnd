CREATE VIEW `sap_default_character_value_view` AS
    SELECT 
        v.id,
        v.code,
        v.name,
        m.sap_materials_code,
        m.sap_characteristic_code AS key_code
    FROM
        sap_material_default_characteristic m
            LEFT JOIN
        sap_characteristic_value v ON m.sap_characteristic_value_id = v.id