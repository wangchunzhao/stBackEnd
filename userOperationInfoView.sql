CREATE VIEW user_operation_view AS 

SELECT
	CONCAT(user_id,'_',role_id,'_',operation_id) as id,
	user_id,
	u.user_mail AS user_mail,
	u.user_identity AS user_identity,
	u.isActive AS user_isActive,
	role_id,
	role_name,
	attached_code,
	attached_name,
	operation_id,
	operation_name
FROM
	(
		SELECT
			bo.b_users_id AS user_id,
			bo.b_roles_id AS role_id,
			r. NAME AS role_name,
			bo.attached_code AS attached_code,
			bo.attached_name AS attached_name
		FROM
			(
				SELECT
					b.id AS id,
					b.b_roles_id AS b_roles_id,
					b.b_users_id AS b_users_id,
					s. CODE AS attached_code,
					s. NAME AS attached_name
				FROM
					b_application_of_rolechange b
				LEFT JOIN sap_sales_office s ON b.attached_code = s. CODE
			) bo
		LEFT JOIN b_roles r ON bo.b_roles_id = r.id
	) ars,
	(
		SELECT
			r1.id AS id,
			o1.id AS operation_id,
			o1. NAME AS operation_name
		FROM
			b_roles r1,
			b_operation2role x1,
			b_operations o1
		WHERE
			r1.id = x1.b_roles_id
		AND x1.b_operations_id = o1.id
	) rxo,
	b_users u
WHERE
	ars.role_id = rxo.id
AND ars.user_id = u.id
ORDER BY
	user_id,role_id,operation_id ASC
