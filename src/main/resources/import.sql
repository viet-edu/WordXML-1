Insert into Role (Role_Id,Role_Name,Role_Level) values (99998,N'ADMIN',1);
Insert into Role (Role_Id,Role_Name,Role_Level) values (99999,N'USER',2);

Insert into HocSinh (Ma_Hoc_Sinh,Ten_Hoc_Sinh,Username,Password,Role_Id,Ma_Lop) values (99998,N'Admin',N'admin',N'admin',99998,1);
Insert into HocSinh (Ma_Hoc_Sinh,Ten_Hoc_Sinh,Username,Password,Role_Id,Ma_Lop) values (99999,N'VuDaiK',N'user',N'user',99999,1);

Insert into Permission (Permission_Id,Permission_Name) values (99997,N'CONVERT');
Insert into Permission (Permission_Id,Permission_Name) values (99998,N'FILE_MANAGEMENT');
Insert into Permission (Permission_Id,Permission_Name) values (99999,N'USER_MANAGEMENT');

Insert into Role_Permission(Role_Id,Permission_Id) values (99998,99997);
Insert into Role_Permission(Role_Id,Permission_Id) values (99998,99998);
Insert into Role_Permission(Role_Id,Permission_Id) values (99998,99999);
