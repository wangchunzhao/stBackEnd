CREATE VIEW `sap_class_characteristic_value_view` AS
    SELECT 
        v.id AS id,
        `l`.`sap_clazz_code` AS `sap_clazz_code`,
        `c`.`code` AS `key_code`,
        `c`.`name` AS `key_name`,
        `v`.`code` AS `value_code`,
        `v`.`name` AS `value_name`
    FROM
        (`sap_characteristic_value` `v`
        LEFT JOIN (`sap_clazz_and_character` `l`
        LEFT JOIN `sap_characteristic` `c` ON ((`l`.`sap_characteristic_code` = `c`.`code`))) ON ((`c`.`code` = `v`.`sap_characteristic_code`)))