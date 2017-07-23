package com.tz.util.ip;

import com.tz.util.TmStringUtils;

public class TmStringSubUtil {
	public static String SupChar(String paramString1, String paramString2,
			int paramInt, boolean paramBoolean) {
		if (paramString1.length() < paramInt) {
			if (paramBoolean) {
				while (paramString1.length() < paramInt)
					paramString1 = paramString2 + paramString1;
			}
			while (true) {
				paramString1 = paramString1 + paramString2;
				if (paramString1.length() >= paramInt)
					return paramString1;
			}
		}
		return paramString1;
	}

	public static String SupChar(String paramString1, String paramString2,
			Object paramObject1, Object paramObject2) {
		if (paramString1.length() < Integer.parseInt((String) paramObject1)) {
			if (Boolean.valueOf((String) paramObject2).booleanValue()) {
				while (paramString1.length() < Integer
						.parseInt((String) paramObject1))
					paramString1 = paramString2 + paramString1;
			}
			while (true) {
				paramString1 = paramString1 + paramString2;
				label102: if (paramString1.length() >= Integer
						.parseInt((String) paramObject1))
					return paramString1;
			}
		}
		return paramString1;
	}

	public static String StartSubStr(String paramString1, String paramString2) {
		int i = 0;
		if (!(TmStringUtils.isEmpty(paramString1))) {
			i = paramString1.indexOf(paramString2);
			if ((i != -1)
					&& (paramString1.length() - (i + paramString2.length()) == paramString1
							.length()
							- paramString2.length()))
				paramString1 = paramString1.substring(paramString2.length());
		}
		return paramString1;
	}

	public static String LastSubStr(String paramString1, String paramString2) {
		int i = 0;
		if (!(TmStringUtils.isEmpty(paramString1))) {
			i = paramString1.lastIndexOf(paramString2);
			if ((i != -1)
					&& (i + paramString2.length() == paramString1.length()))
				paramString1 = paramString1.substring(0, i);
		}
		return paramString1;
	}

	public static String StartStr(String paramString1, String paramString2) {
		int i = 0;
		if (!(TmStringUtils.isEmpty(paramString1))) {
			i = paramString1.lastIndexOf(paramString2);
			if (i != -1)
				paramString1 = paramString1.substring(0, i);
		}
		return paramString1;
	}

	public static String LastStr(String paramString1, String paramString2) {
		int i = 0;
		if (!(TmStringUtils.isEmpty(paramString1))) {
			i = paramString1.lastIndexOf(paramString2);
			if (i != -1)
				paramString1 = paramString1
						.substring(i + paramString2.length());
		}
		return paramString1;
	}

	public static String Left(String paramString, int paramInt) {
		String str = null;
		try {
			if (!(TmStringUtils.isEmpty(paramString))) {
				if (paramString.length() - paramInt > 0) {
					str = paramString.substring(0, paramInt);
				}
				str = paramString;
			}
			str = "";
		} catch (Exception localException) {
			str = "";
		}
		label43: return str;
	}

	public static String Left(String paramString, Object paramObject) {
		return Left(paramString, Integer.parseInt((String) paramObject));
	}

	public static String Right(String paramString, int paramInt) {
		String str = null;
		try {
			if (!(TmStringUtils.isEmpty(paramString))) {
				if ((paramString.length() - paramInt > 0)
						&& (paramString.length() - paramInt <= paramString
								.length())) {
					str = paramString.substring(
							paramString.length() - paramInt, paramString
									.length());
				}
				str = paramString;
			}
			str = "";
		} catch (Exception localException) {
			str = "";
		}
		label64: return str;
	}

	public static String Right(String paramString, Object paramObject) {
		return Right(paramString, Integer.parseInt((String) paramObject));
	}

	public static String Mid(String paramString, int paramInt1, int paramInt2) {
		String str = null;
		try {
			if (!(TmStringUtils.isEmpty(paramString))) {
				if (paramString.length() - paramInt2 > 0) {
					str = paramString.substring(paramInt1, paramInt2);
				}
				str = paramString;
			}
			str = "";
		} catch (Exception localException) {
			str = "";
		}
		return str;
	}

	public static String Mid(String paramString, Object paramObject1,
			Object paramObject2) {
		return Mid(paramString, Integer.parseInt((String) paramObject1),
				Integer.parseInt((String) paramObject2));
	}
}