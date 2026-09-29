-- Hier können Testdaten definiert werden, die in den UI-Test zur Verfügung stehen.

-- ACHTUNG H2-Syntax nicht PostgreSQL!!!!!!
-- Feldnamen und Tabellen namen müssen in Anführungszeichen stehen 

-- Admin-Rolle bekannt machen
INSERT INTO "public"."rightsandrolesservice_roles"("role_id", "shorttext", "defaulttext", "longtext", "uniquename", "valid") 
	VALUES (1, '', '', '', 'ADMIN', true);

	
-- FaME: Workflow erstellen
insert into "public"."fame_rollen" ("name")
	values ('ROLE_ADMIN');
set @r1id = (SELECT MAX("id") FROM "public"."fame_rollen");

insert into "public"."fame_input_type" ("name", "uniq_name")
	values ('ALL', 'ALL');

insert into "public"."fame_workflow" ("name", "connector", "kommentar") 
	values ('Test', '-', '');
set @wf1id = (SELECT MAX("id") FROM "public"."fame_workflow");

insert into "public"."fame_status" ("name", "kommentar", "workflow_id", "position", "hidden") 
	values ('ALL', '', @wf1id, null, 1);
set @s0id = (SELECT MAX("id") FROM "public"."fame_status");

insert into "public"."fame_status" ("name", "kommentar", "workflow_id", "position", "hidden") 
	values ('Test Status', null, @wf1id, 1, 0);
set @s1id = (SELECT MAX("id") FROM "public"."fame_status");

insert into "public"."fame_status_berechtigung" ("status_from_id", "rolle_id", "status_to_id") 
	values (@s0id, @r1id, @s0id);
	
insert into "public"."fame_input_type" ("name", "uniq_name")
	values ('Test Inputtyp', 'TEXT');

insert into "public"."fame_input" ("workflow_id", "name", "input_type_id", "beschreibung", "options", "position", "kommentar", "pflicht", "hidden", "regex", "connector_mapping") 
	values (@wf1id, 'Test Input', (SELECT "id" FROM "public"."fame_input_type" WHERE "uniq_name" = 'TEXT'), '', '', 1, null, null, 0, null, null);
set @i1id = (SELECT MAX("id") FROM "public"."fame_input");

insert into "public"."fame_input" ("workflow_id", "name", "input_type_id", "beschreibung", "options", "position", "kommentar", "pflicht", "hidden", "regex", "connector_mapping") 
	values (@wf1id, 'Test Input ohne recht', (SELECT "id" FROM "public"."fame_input_type" WHERE "uniq_name" = 'TEXT'), '', '', 1, null, null, 0, null, null);

insert into "public"."fame_workflow_instance" ("workflow_id", "name", "parameter", "kommentar") 
	values (@wf1id, 'Test Instanz', '', '');

insert into "public"."fame_input_berechtigung" ("input_id", "rolle_id", "status_id", "recht", "kommentar") 
	values (@i1id, @r1id, @s1id, 'W', '');
	
insert into "public"."fame_vorgang" ("workflow_instance_id", "status_id", "connector_key")
	values (@wf1id, @s1id, '');
	



