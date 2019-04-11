package com.tianma.util;

public class DateUtil {

	/*
	 * 实体类日期 转 数据库日期
	 */
	public static java.sql.Timestamp d2t(java.util.Date d) {
        if (null == d)
            return null;
        return new java.sql.Timestamp(d.getTime());
    }
 
	/*
	 * 数据库日期 转 实体类日期
	 */
    public static java.util.Date t2d(java.sql.Timestamp t) {
        if (null == t)
            return null;
        return new java.util.Date(t.getTime());
    }
	
}
