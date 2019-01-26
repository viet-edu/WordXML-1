Insert into Role (Role_Id,Role_Name,Role_Level) values (1,N'ADMIN',1);
Insert into Role (Role_Id,Role_Name,Role_Level) values (2,N'USER',2);

Insert into HocSinh (Ma_Hoc_Sinh,Ten_Hoc_Sinh,Username,Password,Role_Id,Ma_Lop) values (1,N'Admin',N'admin',N'admin',1,1);
Insert into HocSinh (Ma_Hoc_Sinh,Ten_Hoc_Sinh,Username,Password,Role_Id,Ma_Lop) values (2,N'VuDaiK',N'user',N'user',2,1);

Insert into Permission (Permission_Id,Permission_Name) values (1,N'CONVERT');
Insert into Permission (Permission_Id,Permission_Name) values (2,N'FILE_MANAGEMENT');
Insert into Permission (Permission_Id,Permission_Name) values (3,N'USER_MANAGEMENT');

Insert into Role_Permission(Role_Id,Permission_Id) values (1,1);
Insert into Role_Permission(Role_Id,Permission_Id) values (1,2);
Insert into Role_Permission(Role_Id,Permission_Id) values (1,3);
