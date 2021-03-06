USE [QuanLyDoXe]
GO
/****** Object:  Table [dbo].[XeDap]    Script Date: 29/07/2017 9:52:53 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[XeDap](
	[SoXe] [nvarchar](50) NOT NULL,
	[ThoiGianVao] [datetime] NULL,
	[ThoiGianRa] [datetime] NULL,
	[TinhTien] [nvarchar](50) NULL,
 CONSTRAINT [PK_XeDap] PRIMARY KEY CLUSTERED 
(
	[SoXe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[XeMay]    Script Date: 29/07/2017 9:52:54 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[XeMay](
	[BienSo] [nvarchar](50) NOT NULL,
	[ThoiGianVao] [datetime] NULL,
	[ThoiGianRa] [datetime] NULL,
	[TinhTien] [nvarchar](50) NULL,
 CONSTRAINT [PK_XeMay] PRIMARY KEY CLUSTERED 
(
	[BienSo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[XeOTo]    Script Date: 29/07/2017 9:52:54 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[XeOTo](
	[BienSo] [nvarchar](50) NOT NULL,
	[ThoiGianVao] [datetime] NULL,
	[TinhTrangLucVao] [nvarchar](50) NULL,
	[ThoiGianRa] [datetime] NULL,
	[TinhTrangLucRa] [nvarchar](50) NULL,
	[TinhTien] [nvarchar](50) NULL,
 CONSTRAINT [PK_XeOTo] PRIMARY KEY CLUSTERED 
(
	[BienSo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
