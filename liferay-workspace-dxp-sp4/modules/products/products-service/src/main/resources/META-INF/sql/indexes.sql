create index IX_B2FCA947 on FOO_Foo (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_905CD589 on FOO_Foo (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3CEB3F70 on FOO_Product (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8A1CE1F2 on FOO_Product (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_287E0CD1 on LS_Product (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_51159393 on LS_Product (uuid_[$COLUMN_LENGTH:75$], groupId);