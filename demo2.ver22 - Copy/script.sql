USE [master]
GO
/****** Object:  Database [Store]    Script Date: 9/22/2021 4:10:51 PM ******/
CREATE DATABASE [Store]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'VapeStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\VapeStore.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'VapeStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\VapeStore_log.ldf' , SIZE = 816KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Store] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Store].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Store] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Store] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Store] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Store] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Store] SET ARITHABORT OFF 
GO
ALTER DATABASE [Store] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Store] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Store] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Store] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Store] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Store] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Store] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Store] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Store] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Store] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Store] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Store] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Store] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Store] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Store] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Store] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Store] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Store] SET RECOVERY FULL 
GO
ALTER DATABASE [Store] SET  MULTI_USER 
GO
ALTER DATABASE [Store] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Store] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Store] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Store] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Store] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Store', N'ON'
GO
USE [Store]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 9/22/2021 4:10:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[password] [nvarchar](250) NOT NULL,
	[email] [nvarchar](70) NULL,
	[phonenumber] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
	[enable] [bit] NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 9/22/2021 4:10:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Contact]    Script Date: 9/22/2021 4:10:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Contact](
	[name] [nvarchar](50) NOT NULL,
	[email] [nvarchar](70) NOT NULL,
	[subject] [nvarchar](100) NOT NULL,
	[message] [nvarchar](800) NULL,
PRIMARY KEY CLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Order]    Script Date: 9/22/2021 4:10:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Order](
	[ID] [int] NOT NULL,
	[amount] [float] NOT NULL,
	[customer_address] [varchar](255) NOT NULL,
	[customer_email] [varchar](128) NOT NULL,
	[customer_name] [varchar](255) NOT NULL,
	[customer_phone] [varchar](128) NOT NULL,
 CONSTRAINT [PK__ORDERS__3214EC27665AA789] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Order_details]    Script Date: 9/22/2021 4:10:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_details](
	[id] [int] NOT NULL,
	[amount] [float] NOT NULL,
	[price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[order_id] [int] NOT NULL,
	[product_id] [int] NOT NULL,
 CONSTRAINT [PK__ORDER_DE__3214EC2731BC6D3E] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product]    Script Date: 9/22/2021 4:10:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[price] [int] NOT NULL,
	[categoryID] [int] NOT NULL,
	[detail] [nvarchar](2000) NULL,
	[stock] [int] NOT NULL,
	[image] [nvarchar](500) NULL,
 CONSTRAINT [PK_Product_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 9/22/2021 4:10:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userid] [int] NULL,
	[role] [nvarchar](50) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [username], [fullname], [password], [email], [phonenumber], [address], [enable]) VALUES (1, N'admin', N'admin', N'$2a$12$5OrbUzE2Pdq1.Ru3su9h..UZsXvWmfx3Y1wEhFAyH9X13GaKcH37i', N'admin@gmail.com', N'0987654321', N'admin', 1)
INSERT [dbo].[Account] ([id], [username], [fullname], [password], [email], [phonenumber], [address], [enable]) VALUES (2, N'member', N'member', N'$2a$12$5OrbUzE2Pdq1.Ru3su9h..UZsXvWmfx3Y1wEhFAyH9X13GaKcH37i', N'member@gmail.com', N'0987654321', N'member', 1)
INSERT [dbo].[Account] ([id], [username], [fullname], [password], [email], [phonenumber], [address], [enable]) VALUES (4, N'admin1', N'doan trung hieu ', N'$2a$10$ZQc2p6PfErFFjaQmcxK.RObWGGGKsBOZrqpnwIYCM4qH/QvZ3uc4S', N'doantrunghieu1108', N'123', N'123', 1)
SET IDENTITY_INSERT [dbo].[Account] OFF
INSERT [dbo].[Category] ([id], [name]) VALUES (1, N'Laptop')
INSERT [dbo].[Category] ([id], [name]) VALUES (2, N'PC')
INSERT [dbo].[Category] ([id], [name]) VALUES (3, N'Monitor')
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (22, N'LAPTOP ASUS ZENBOOK UX425EA-KI429T', 1, 1, N'I5 1135G7/8GB RAM/512GB SSD/14 FHD/WIN10/XÁM)', 222, N'https://hanoicomputercdn.com/media/product/250_59623_laptop_asus_zenbook_ux425_19.png')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (23, N'LAPTOP ASUS GAMING TUF FX506LH-HN002T', 1, 1, N'(I5 10300H/8GB RAM/512GB SSD/15.6 FHD 144HZ', 3, N'https://hanoicomputercdn.com/media/product/250_58034_fx506__6_.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (24, N'LAPTOP ASUS GAMING ROG STRIX G533QR-HF113T', 4, 1, N'RYZEN 9 5900HX/2*8GB RAM/1TB', 4, N'https://hanoicomputercdn.com/media/product/250_58878_laptop_asus_gaming_rog_strix_g533_23.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (25, N'LAPTOP ASUS VIVOBOOK S533EQ-BN161T', 5, 1, N'I5 1135G7/8GB RAM/512GB SSD/15.6 FHD/MX350', 5, N'https://hanoicomputercdn.com/media/product/250_59416_laptop_asus_vivobook_s_s533_15.png')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (26, N'LAPTOP ASUS VIVOBOOK M513UA-L1221T', 6, 1, N'R5 5500U/8GB RAM/512GB SSD/15.6 FHD/WIN', 6, N'https://hanoicomputercdn.com/media/product/250_60522_laptop_asus_vivobook_m513ua_l1240t_bac.png')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (27, N'LAPTOP ASUS X415EA-EK048T', 7, 1, N'(I3 1115G4/4GB RAM/256GB SSD/14 FHD/WIN 10/XÁM)', 7, N'https://hanoicomputercdn.com/media/product/250_58182_x415__5_.png')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (28, N'LAPTOP ASUS GAMING ROG STRIX G533QR-HQ098T', 8, 1, N'RYZEN 9 5900HX/2*8GB RAM/1TB SSD/15.6 WQHD 165HZ/RTX 3070', 8, N'https://hanoicomputercdn.com/media/product/250_58877_laptop_asus_gaming_rog_strix_g533_23.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (29, N'LAPTOP ASUS GAMING TUF FX506HC-HN001T', 9, 1, N'I7 11800H/8GB RAM/512GB SSD/15.6 FHD', 9, N'https://hanoicomputercdn.com/media/product/250_60025_laptop_asus_gaming_tuf_fx506_5.png')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (30, N'PC GAMING HACOM 015', 1, 2, N'(I3 10100/H410/8GB RAM/1050TI/450W)', 3, N'https://hanoicomputercdn.com/media/product/250_60199_pc_gaming_hacom_015.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (31, N'PC GAMING HACOM 002 ', 2, 2, N'(I5 10400F/B460/8GB RAM/240GB SSD/GTX 1050TI/500W)', 3, N'https://hanoicomputercdn.com/media/product/250_59548_pc_gaming_hacom_002_hncom.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (32, N'PC GAMING HACOM PRO 001', 3, 2, N'(R5 3600G/B450/16GB RAM/500GB SSD/550W)', 3, N'https://hanoicomputercdn.com/media/product/250_60662_pc_gaming_hacom_pro_001.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (33, N'PC GAMING HACOM PRO 002 ', 4, 2, N'I5 10500/B460/16GB RAM/500GB SSD/RTX', 3, N'https://hanoicomputercdn.com/media/product/250_60663_pc_gaming_hacom_pro_002.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (34, N'PC GAMING FALCON 054', 5, 2, N'(R5 3600/B450/16GB RAM/256GB SSD/GTX 1660 SUPER/550W/RGB)', 3, N'https://hanoicomputercdn.com/media/product/250_46231_pc_gaming_falcon_054.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (35, N'PC ENTHUSIAST GAMING PLATINUM 04', 6, 2, N'I9 10900K/Z490/32GB RAM/250GB SSD/2TB', 3, N'https://hanoicomputercdn.com/media/product/250_53831_pc_gaming_enthusiast_p04.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (36, N'PC GAMING ASUS TUF 003', 7, 2, N'I7 10700K/Z490/16GB RAM/500GB SSD/RTX', 3, N'https://hanoicomputercdn.com/media/product/250_56467_pc_gaming_tuf_003.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (37, N'PC GAMING SHARK 041 ', 8, 2, N'(I3 10100/H410/8GB RAM/240GB SSD/GTX 1650/500W/RGB)', 3, N'https://hanoicomputercdn.com/media/product/250_57910_pc_gaming_shark_041_pc.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (38, N'PC GAMING COOLER MASTER 003', 9, 2, N'I7 11700/B560/16GB RAM/512GB SSD/RTX 3060/700W)', 3, N'https://hanoicomputercdn.com/media/product/250_58599_pc_gaming_cooler_master_003.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (39, N'MÀN HÌNH SAMSUNG LC27H580FDEXXV', 1, 3, N'(27 INCH/FHD/PLS/250CD/M²/DP+HDMI/60HZ/5MS/', 3, N'https://hanoicomputercdn.com/media/product/250_43078_samsung_lc27h580fdexxv__4_.png')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (40, N'MÀN HÌNH GIGABYTE AORUS CV27F', 2, 3, N'(27 INCH/FHD/VA/165HZ/1MS/350CD/M²/DP+HDMI/', 3, N'https://hanoicomputercdn.com/media/product/250_49014_cv27f__1_.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (41, N'MÀN HÌNH VIEWSONIC VA2261H-2 ', 3, 3, N' (21.5 INCH/FHD/TN/60HZ/5MS/200 NITS/HDMI+VGA)', 3, N'https://hanoicomputercdn.com/media/product/250_52117_viewsonic_va2261h_2__4_.png')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (43, N'MÀN HÌNH DI ĐỘNG LENOVO M14', 4, 3, N'(14INCH/FHD/IPS/60HZ/6MS/300NITS/USB-C) ', 3, N'https://hanoicomputercdn.com/media/product/250_52548_lenovo_m14__6_.jpg')
INSERT [dbo].[Product] ([id], [name], [price], [categoryID], [detail], [stock], [image]) VALUES (44, N'MÀN HÌNH VIEWSONIC VP2458', 5, 3, N'(23.8 INCH/FHD/IPS/75HZ/5MS/250', 3, N'https://hanoicomputercdn.com/media/product/250_52843_vp2458__10_.jpg')
SET IDENTITY_INSERT [dbo].[Product] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([id], [userid], [role]) VALUES (1, 1, N'ROLE_ADMIN')
INSERT [dbo].[Role] ([id], [userid], [role]) VALUES (2, 1, N'ROLE_USER')
INSERT [dbo].[Role] ([id], [userid], [role]) VALUES (3, 2, N'ROLE_USER')
SET IDENTITY_INSERT [dbo].[Role] OFF
ALTER TABLE [dbo].[Order_details]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Order_details]  WITH CHECK ADD  CONSTRAINT [ORDER_DETAIL_ORD_FK] FOREIGN KEY([order_id])
REFERENCES [dbo].[Order] ([ID])
GO
ALTER TABLE [dbo].[Order_details] CHECK CONSTRAINT [ORDER_DETAIL_ORD_FK]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([id])
GO
USE [master]
GO
ALTER DATABASE [Store] SET  READ_WRITE 
GO
