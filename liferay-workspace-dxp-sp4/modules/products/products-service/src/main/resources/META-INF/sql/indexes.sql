create index IX_A9A28F13 on LS_Product (groupId, status);
create index IX_B2EB4A03 on LS_Product (status);
create index IX_287E0CD1 on LS_Product (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_51159393 on LS_Product (uuid_[$COLUMN_LENGTH:75$], groupId);