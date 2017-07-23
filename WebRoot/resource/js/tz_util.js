/**
 * 判断非空
 * 
 * @param val
 * @returns {Boolean}
 */
function isEmpty(val) {
	val = $.trim(val);
	if (val == null)
		return true;
	if (val == undefined || val == 'undefined')
		return true;
	if (val == "")
		return true;
	if (val.length == 0)
		return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val))
		return true;
	return false;
}

function isNotEmpty(val) {
	return !isEmpty(val);
}

//判断两个元素是否相等
function eqauls(str,tstr){
	if(str == tstr){
		return true;
	}
	return false;
};

/** ******************************数组相关结束*********************************** */
/**
 * 禁止窗体选中
 */
function forbiddenSelect() {
	$(document).bind("selectstart", function() {
		return false;
	});
	document.onselectstart = new Function("event.returnValue=false;");
	$("*").css({
		"-moz-user-select" : "none"
	});
}

/* 窗体允许选中 */
function autoSelect() {
	$(document).bind("selectstart", function() {
		return true;
	});
	document.onselectstart = new Function("event.returnValue=true;");
	$("*").css({
		"-moz-user-select" : ""
	});
};


/**
 * 对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
 * 可以用 1-2 个占位符 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) eg: (new
 * Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 (new
 * Date()).format("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04 (new
 * Date()).format("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04 (new
 * Date()).format("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04 (new
 * Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1,
		// 月份
		"d+" : this.getDate(),
		// 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12,
		// 小时
		"H+" : this.getHours(),
		// 小时
		"m+" : this.getMinutes(),
		// 分
		"s+" : this.getSeconds(),
		// 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		// 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
								: "/u5468")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
};

/**
 * 将数字转换成对应的中文 将阿拉伯数字翻译成中文的大写数字
 * 
 * @param {Object}
 *            num 比如:1对应一 11：十一 101:一百零一
 * @return {TypeName}
 */
function tm_NumberToChinese(num) {
    var AA = new Array("零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十");
    var BB = new Array("", "十", "百", "仟", "萬", "億", "点", "");
    var a = ("" + num).replace(/(^0*)/g, "").split("."),
    k = 0,
    re = "";
    for (var i = a[0].length - 1; i >= 0; i--) {
        switch (k) {
        case 0:
            re = BB[7] + re;
            break;
        case 4:
            if (!new RegExp("0{4}//d{" + (a[0].length - i - 1) + "}$").test(a[0])) re = BB[4] + re;
            break;
        case 8:
            re = BB[5] + re;
            BB[7] = BB[5];
            k = 0;
            break;
        }
        if (k % 4 == 2 && a[0].charAt(i + 2) != 0 && a[0].charAt(i + 1) == 0) re = AA[0] + re;
        if (a[0].charAt(i) != 0) re = AA[a[0].charAt(i)] + BB[k % 4] + re;
        k++;
    }

    if (a.length > 1) // 加上小数部分(如果有小数部分)
    {
        re += BB[6];
        for (var i = 0; i < a[1].length; i++) re += AA[a[1].charAt(i)];
    }
    if (re == '一十') re = "十";
    if (re.match(/^一/) && re.length == 3) re = re.replace("一", "");
    return re;
};

/**
 * 获取窗体可见度高度
 * 
 * @returns
 */
function getClientHeight() {
	var clientHeight = 0;
	if (document.body.clientHeight && document.documentElement.clientHeight) {
		clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight
				: document.documentElement.clientHeight;
	} else {
		clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight
				: document.documentElement.clientHeight;
	}
	return clientHeight;
}
/**
 * 获取窗体可见度宽度
 * 
 * @returns
 */
function getClientWidth() {
	var clientWidth = 0;
	if (document.body.clientWidth && document.documentElement.clientWidth) {
		clientWidth = (document.body.clientWidth < document.documentElement.clientWidth) ? document.body.clientWidth
				: document.documentElement.clientWidth;
	} else {
		clientWidth = (document.body.clientWidth > document.documentElement.clientWidth) ? document.body.clientWidth
				: document.documentElement.clientWidth;
	}
	return clientWidth;
}

function getScrollHeight() {
	return Math.max(getClientHeight(), document.body.scrollHeight,
			document.documentElement.scrollHeight);
}

function getScrollTop() {
	var scrollTop = 0;
	if (document.documentElement && document.documentElement.scrollTop) {
		scrollTop = document.documentElement.scrollTop;
	} else if (document.body) {
		scrollTop = document.body.scrollTop;
	}
	return scrollTop;
}

/* 文件大小转换为MB GB KB格式 */
function tm_countFileSize(size) {
	var fsize = parseFloat(size, 2);
	var fileSizeString;
	if (fsize < 1024) {
		fileSizeString = fsize.toFixed(2) + "B";
	} else if (fsize < 1048576) {
		fileSizeString = (fsize / 1024).toFixed(2) + "KB";
	} else if (fsize < 1073741824) {
		fileSizeString = (fsize / 1024 / 1024).toFixed(2) + "MB";
	} else if (fsize < 1024 * 1024 * 1024) {
		fileSizeString = (fsize / 1024 / 1024 / 1024).toFixed(2) + "GB";
	} else {
		fileSizeString = "0B";
	}
	return fileSizeString;
};

/* 获取文件后缀 */
function tm_getExt(fileName) {
	if (fileName.lastIndexOf(".") == -1)
		return fileName;
	var pos = fileName.lastIndexOf(".") + 1;
	return fileName.substring(pos, fileName.length).toLowerCase();
}

/* 获取文件名称 */
function tm_getFileName(fileName) {
	var pos = fileName.lastIndexOf("/") + 1;
	if (pos == -1) {
		return fileName;
	} else {
		return fileName.substring(pos, fileName.length);
	}
}


/**
 * 禁止窗体选中
 */
function tm_forbiddenSelect() {
	$(document).bind("selectstart", function() {
		return false;
	});
	document.onselectstart = new Function("event.returnValue=false;");
	$("*").css({
		"-moz-user-select" : "none"
	});
}


/* 窗体允许选中 */
function tm_autoSelect() {
	$(document).bind("selectstart", function() {
		return true;
	});
	document.onselectstart = new Function("event.returnValue=true;");
	$("*").css({
		"-moz-user-select" : ""
	});
}

//获取几秒钟以前 startTime==== Date
function getTimeFormat(startTime) {
	var startTimeMills = startTime.getTime();
	var endTimeMills = new Date().getTime();
	var diff = parseInt((endTimeMills - startTimeMills) / 1000);//秒
	var day_diff = parseInt(Math.floor(diff / 86400));//天
	var buffer = Array();
	if (day_diff < 0) {
		return "[error],时间越界...";
	} else {
		if (day_diff == 0 && diff < 60) {
			if (diff <= 0)
				diff = 1;
			buffer.push(diff + "秒前");
		} else if (day_diff == 0 && diff < 120) {
			buffer.push("1 分钟前");
		} else if (day_diff == 0 && diff < 3600) {
			buffer.push(Math.round(Math.floor(diff / 60)) + "分钟前");
		} else if (day_diff == 0 && diff < 7200) {
			buffer.push("1小时前");
		} else if (day_diff == 0 && diff < 86400) {
			buffer.push(Math.round(Math.floor(diff / 3600)) + "小时前");
		} else if (day_diff == 1) {
			buffer.push("1天前");
		} else if (day_diff < 7) {
			buffer.push(day_diff + "天前");
		} else if (day_diff < 30) {
			buffer.push(Math.round(Math.floor(day_diff / 7)) + " 星期前");
		} else if (day_diff >= 30 && day_diff <= 179) {
			buffer.push(Math.round(Math.floor(day_diff / 30)) + "月前");
		} else if (day_diff >= 180 && day_diff < 365) {
			buffer.push("半年前");
		} else if (day_diff >= 365) {
			buffer.push(Math.round(Math.floor(day_diff / 30 / 12)) + "年前");
		}
	}
	return buffer.toString();
}