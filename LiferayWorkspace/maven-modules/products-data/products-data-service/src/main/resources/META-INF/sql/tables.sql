create table LS_Product (
	uuid_ VARCHAR(75) null,
	productId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	groupId LONG,
	companyId LONG,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);