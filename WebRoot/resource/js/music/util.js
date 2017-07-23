//通过id获取dom对象
function dom(id){
	return document.getElementById(id);
};
//获取样式
function getStyle(el,attr){
	return el.currentStyle ? el.currentStyle[attr] : window.getComputedStyle(el,false)[attr];
};
//鼠标坐标的获取
function getXY(e){
	var ev = e || window.event;
	var x = 0,y = 0;
	if(ev.pageX && ev.pageY){
		x = ev.pageX;
		y = ev.pageY;
	}else{
		//ie 678
		if(document.documentElement){
			cleft = document.documentElement.scrollLeft;
			ctop = document.documentElement.scrollTop;
		}else{
			//谷歌
				cleft = document.body.scrollLeft;
				ctop = document.body.scrollTop;
			}
			x = ev.clientX + cleft;
			y = ev.clientY + ctop;
		};
		var pos = {
			x:x,
			y:y
		};
		return pos;
};

//数字时钟
function numLock(obj1){
	//获取系统时间
	var date = new Date();
	//时
	var hour = date.getHours();
	//分
	var min = date.getMinutes();
	//秒
	var sec = date.getSeconds();
	if(hour < 10){hour = "0" + hour;}
	if(min < 10){min = "0" + min;}
	if(sec < 10){sec = "0" + sec;}
	obj1.style.cssText = "font-size: 20px;color:#ff0033;";
	obj1.innerHTML = hour+":"+min+":"+sec;
};

//画布时钟
function drawlock(obj2){
	//获取系统时间
	var date = new Date();
	//时
	var h = date.getHours();
	//分
	var m = date.getMinutes();
	//秒
	var s = date.getSeconds();
	h > 12 ? h-12 : h ;
	h += (m / 60);
	//创建context对象
	var mycontext = obj2.getContext("2d");
	//先清空画布
	mycontext.clearRect(0,0,obj2.width,obj2.height);
	//画表盘大圆    圆心：x=20 y=20
	mycontext.strokeStyle = "#ff99ff";
	mycontext.lineWidth = 5;
	mycontext.beginPath();
	mycontext.arc(150,70,60,0,360);
	mycontext.stroke();//打蜡（上色）
	mycontext.closePath();
	
	//时刻度
	for(var i = 0;i<12;i++){
		mycontext.save();//保存当前状态
		mycontext.lineWidth = 3;
		mycontext.strokeStyle = "#ff0033";
		
		//设置原点
		mycontext.translate(150,70);
		//设置旋转角度
    	mycontext.rotate(30 * i * Math.PI / 180);
    	mycontext.beginPath();
    	mycontext.moveTo(0,-46);
    	mycontext.lineTo(0,-55);
    	mycontext.stroke();
    	mycontext.closePath();
    	mycontext.restore();//恢复原来状态
	}
	//分刻度
    for (var i = 0; i < 60; i++) {
        mycontext.save();
        mycontext.lineWidth = 2;
        mycontext.strokeStyle = "#ff0033";
        mycontext.translate(150, 70);
        mycontext.rotate(i * 6 * Math.PI / 180);
        mycontext.beginPath();
        mycontext.moveTo(0, -52);
        mycontext.lineTo(0, -55);
        mycontext.stroke();
        mycontext.closePath();
        mycontext.restore();
    }
    
    //时针
     //-----------------------------时针-----------------------------
    mycontext.save();
    mycontext.lineWidth = 3;
    mycontext.strokeStyle = "#00FFFF";
    mycontext.translate(150, 70);
    mycontext.rotate(h * 30 * Math.PI / 180);//每小时旋转30度
    mycontext.beginPath();
    mycontext.moveTo(0, -30);
    mycontext.lineTo(0, 10);
    mycontext.stroke();
    mycontext.closePath();
    mycontext.restore();
    //-----------------------------分针-----------------------------
    mycontext.save();
    mycontext.lineWidth = 3;
    mycontext.strokeStyle = "#FFFF00";
    mycontext.translate(150, 70);
    mycontext.rotate(m * 6 * Math.PI / 180);//每分钟旋转6度
    mycontext.beginPath();
    mycontext.moveTo(0, -35);
    mycontext.lineTo(0, 10);
    mycontext.stroke();
    mycontext.closePath();
    mycontext.restore();
    //-----------------------------秒针-----------------------------
    mycontext.save();
    mycontext.lineWidth = 2;
    mycontext.strokeStyle = "#FF0000";
    mycontext.translate(150, 70);
    mycontext.rotate(s * 6 * Math.PI / 180);//每秒旋转6度
    mycontext.beginPath();
    mycontext.moveTo(0, -40);
    mycontext.lineTo(0, 10);
    mycontext.stroke();
    mycontext.closePath();
    
    //美化表盘，画中间的小圆
    mycontext.beginPath();
    mycontext.arc(0, 0, 4, 0, 360);
    mycontext.fillStyle = "#FFFF00";
    mycontext.fill();
    mycontext.strokeStyle = "#FF0000";
    mycontext.stroke();
    mycontext.closePath();
    
    //秒针上的小圆
    mycontext.beginPath();
    mycontext.arc(0, -30, 3, 0, 360);
    mycontext.fillStyle = "#FFFF00";
    mycontext.fill();
    mycontext.stroke();
    mycontext.closePath();
    mycontext.restore();
};
/*验证生成*/
var code;
function change(objj){
	code = "";
	var len = 4;
	var arr = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
      'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','北','京','欢','迎','您'];
	for(var i = 0;i<len;i++){
		var h = Math.floor(Math.random()*57);
		code += arr[h];
	}
	objj.innerHTML = code;
};
/*验证码判断*/
function qren(oobj,obj3,form) {
	var val = oobj.value;
	if(val == ""){
		alert("验证码不能为空,请输入验证码！");
	}else{
		//alert("恭喜您，验证通过，可以去肆意的happy啦...");
		if(val.toUpperCase() != code.toUpperCase()){
			alert("您输入的验证码有误，请您重新输入！");
			change(obj3);
		}else{
			form.submit();
		}
	};
};
//两个对象的混入
function mixin(obj,obj1){
	for(var key in obj){
		obj1[key] = obj[key];
	}
	return obj1;
};
//多对象混入
function mix(target,source){
 	var args = [].slice.call(arguments);
 	var i = 1;
 	if(args.length == 1){
 		return target;
 	};
 	while(source = args[i++]){
 		for(var k in source){
 			if(source.hasOwnProperty(k)){
 				target[k] = source[k];		     				
 			}
 		}
 	}
 	return target;
};

/**
 * 拖拽3要素：
 * 		1、加定位，改变元素的left和top
 * 		2、绑定事件：onmousedown  onmousemove onmouseup
 * 		3、清空事件
 * */
function dragg(obj,obj2){
	//鼠标按下事件
	obj.onmousedown = function(e){
		//起始位置
		var sleft = this.offsetLeft;
		var stop = this.offsetTop;
		var spos = getXY(e);
		
		//最大拖拽距离
		var maxW = window.innerWidth - this.offsetWidth;
		var maxH = window.innerHeight - this.offsetHeight;
		
		//层级
		//this.style.zIndex = ++zindex;
		//鼠标移动事件
		document.onmousemove = function(e){
			//终点位置
			var epos = getXY(e);
			var eleft = epos.x-spos.x + sleft;
			var etop = epos.y - spos.y + stop;
			
			//边界的判断
			if(eleft<0)eleft = 0;
			if(etop<0)etop = 0;
			if(eleft > maxW)eleft = maxW;
			if(etop > maxH)etop = maxH;
			obj.style.left = eleft + "px";
			obj.style.top =  etop+ "px";
		};
		//鼠标抬起事件  清空事件
		document.onmouseup = function(){
			this.onmousemove = null;
			this.onmouseup = null;
			//obj.style.zIndex = 0;
		};
	};
};
//元素居中
function getCenter(obj){
	var nw = obj.offsetWidth;
	var nh = obj.offsetHeight;
	
	var ww = window.innerWidth;
	var wh = window.innerHeight;
	
	var cleft = (ww - nw)/2;
	var ctop = (wh - nh)/2;
	obj.style.left = cleft + "px";
	obj.style.top = ctop + "px";
	//窗口改变事件
	window.onresize = function(){
		getCenter(obj);
	};
};

//随机颜色
function getRandomColor(){
	var r = Math.floor(Math.random()*256);
	var g = Math.floor(Math.random()*256);
	var b = Math.floor(Math.random()*256);
	return "rgb("+r+","+g+","+b+")";
};
//随机数
function rangeRandom(start,end){
	return Math.floor(Math.random()*(end - start+1)+start);
};

//动画的封装 move(要改变的对象,具体改变的属性,回调函数)
function move(dom,json,callback){
	//让每一次动画都是全新的
	clearInterval(dom.timer);
	dom.timer = setInterval(function(){
		//所有元素执行完毕以后的状态
		var mark = true;
		//循环所有动画的属性
		for(var attr in json){
			var cur = null;
			//如果是opacity的透明的动画
			if(attr=="opacity"){
				//获取已经产生的透明度
				cur = getStyle(dom,attr) * 100;
			}else{
				//获取已经执行的距离
				cur = parseInt(getStyle(dom,attr)) || 0;
			}
			//获取目标终止值
			var target = json[attr];
			//速度，*0.2是增加摩擦力
			var speed = (target - cur) *0.6725;
			//如果cur在执行过程中因为已经除去了小数部分。而速度可能会产生小数位 
			//所有说当cur执行的递增，那么可能cur到达不了目标,当速度是大于0的上取整，然后整数+speed==目标
			//199 +1 200 反之向下取整
			speed = (speed>0 ? Math.ceil(speed): Math.floor(speed));
			if(cur != target){
				mark = false;
				if(attr=="opacity"){
					dom.style.opacity= (cur+speed)/100;
					dom.style.filter = "alpha(opacity="+((cur+speed))+")";
				}else{
					dom.style[attr]= cur+speed+"px";
				}
			}
		}
		//如果执行完毕，
		if(mark){
			//清楚动画
			clearInterval(dom.timer);
			//回调函数，一定要放在下面。要不然你动画没有关闭。
			if(callback)callback.call(dom);
		}
	},30);
};

//文字打印的简单封装
function getText(arr,obj,time){
	var pos = 1;
	var strlen = arr[0].length;//获取数组第一个字符串长度
	var index = 0;//记录数组索引
	var len = arr.length;//记录数组长度
	var timer = null;
	var p = "";
	var row = 0;//记录行数
	function printText(){
		if(row<index){
			p = p + arr[row++]+"<br/>";
		}
		console.log(row+"===="+index);
		obj.innerHTML = p + arr[index].substring(0,pos);
		if(strlen==pos){
			index++;//下一个字符串的位置
			pos = 0;
			if(index<len){
				strlen = arr[index].length;
			timer = setTimeout(printText,time);
			}else{
				clearTimeout(timer);
			}	
		}else{
			pos++;//
			timer = setTimeout(printText,time);
		}
	}
	printText();
}
/*function getGText(arr,obj){
	var timer = null;
	var index = 0;
	timer = setInterval(function(){
		if(index > arr.length-1){
			index = 0;
			//obj.innerHTML = "";
			clearInterval(timer);
		}else{
			obj.innerHTML += arr[index];
			index++;
		};
	},300);
};
*/
//循环封装
function gEach(obj,callback){
	var doms = obj.children;
	var len = doms.length;
	for(var i = 0;i<len;i++){
		doms[i].index = i;
		if(callback)callback.call(doms[i],doms[i].index);
	};
};

/**
 * 事件委托的封装   
 * GGon(元素id,事件类型,回调函数,要委托的对象)
 * (id,eventType,callback,type)
 * GGon(ops)
 * 
 * ops = {  id:"box",
 * 			eventType:"click",
 * 			callback:function(){alert(this.innerHTML);},
 * 			type:"li"
 * 		}
 * */
function GGon(ops){
	var obj = dom(ops.id);
	obj.addEventListener(ops.eventType,function(e){
		//事件类型兼容写法
		var ev = e || window.event;
		//当前对象
		var currentTarget = ev.currentTarget;
		//目标对象
		var target = ev.target || ev.srcElement;
		//如果type为真委托,否则不委托
		if(ops.type){
			if(target.tagName.toLowerCase() === ops.type){
				if(ops.callback)ops.callback.call(target);
			}
		}else{
			if(ops.callback)ops.callback.call(currentTarget);
		};
	},false);
};

//事件的兼容封装(添加)
function Gon(obj,type,callback){
	if(document.addEventListener){
		obj.addEventListener(type,callback,false);
	}else if(document.attachEvent){
		obj.attachEvent("on"+type,callback);
	}else{
		obj[on+"type"] = callback;
	};
};
//删除
function Goff(obj,type,callback){
	if(document.removeEventListener){
		obj.removeEventListener(type,callback,false);
	}else if(document.dettachEvent){
		obj.dettachEvent("on"+type,callback);
	}else{
		obj[on+"type"] = null;
	};
};
//阻止默认行为
function offDefault(e){
	if(event.preventDefault){
		e.preventDefault();
	}else{
		e.returnValue = false;
	};
};
//阻止冒泡
function StopProp(e){
	if(event.stopPropagation){
		e.stopPropagation();
	}else{
		e.cancelBubble = true;
	}
};
//ajax的封装 type,url,flag,callback
function Gajax(ops){
	var xhr = null;
	if(window.XMLHttpRequest){
		//创建ajax对象
		xhr = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	};
	//打开
	xhr.open(ops.type,ops.url,ops.flag);
	//处理数据
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			if(ops.success)ops.success(xhr.responseText);
		}else if(xhr.status != 200){
			if(ops.error)ops.error();
		};
	};
	xhr.send();
};
//获取XMLHttpRequest对象
function getXML(){
	var xhr = null;
	if(XMLHttpRequest){
		xhr = new XMLHttpRequest();
	}else{
		var ids = ["MSXML2.XMLHTTP","MSXML2.XMLHTTP.3.0","Microsoft.XMLHTTP"];
		for(var i = 0,len = ids.length;i<len;i++){
			try{
				xhr = new ActiveXObject(ids[i]);
			}catch(e){
				
			}
		}
	}
	return xhr;
};
//省市县级联的封装 ProvinceCity(省)(市)
function ProvinceCity(obj,obj2,arr){
	//清空
	obj2.innerHTML = "<option value='0'>--请选择--</option>";
	//省的value
	if(obj.value == 0){
		obj2.innerHTML = "<option value='0'>--请选择--</option>";
		return false;
	}else{
		var val = (obj.value-1);
		//省下包含的城市
		var newArr = arr[val];
		var len = newArr.length;
		for(var i = 0;i<len;i++){
			//创建option元素并且添加到市select中
			obj2.innerHTML += "<option value='"+(i+1)+"'>"+newArr[i]+"</option>";
		};
	};
};

//设置cookie添加，修改，删除
var ggCookie = {
	setCookie : function(name, value,time,option){
	    var str=name+'='+escape(value); 
	    var date = new Date();
	    date.setTime(date.getTime()+this.getCookieTime(time)); 
	    str += "; expires=" + date.toGMTString();
	    if(option){ 
	        if(option.path) str+='; path='+option.path; 
	        if(option.domain) str+='; domain='+option.domain; 
	        if(option.secure) str+='; true'; 
	    } 
	    document.cookie=str; 
	},
	getCookie : function(name){
		var arr = document.cookie.split('; '); 
	    if(arr.length==0) return ''; 
	    for(var i=0; i <arr.length; i++){ 
	        tmp = arr[i].split('='); 
	        if(tmp[0]==name) return unescape(tmp[1]); 
	    } 
	    return ''; 
	},
	delCookie : function(name){
		ggCookie.setCookie(name,'',-1); 
		var date=new Date();
        date.setTime(date.getTime()-10000);
		document.cookie=name+"=; expires="+date.toGMTString()+"; path=/";
	},
	
	getCookieTime : function(time){
	   if(time<=0)return time;
	   var str1=time.substring(1,time.length)*1;
	   var str2=time.substring(0,1);
	   if (str2=="s"){
	        return str1*1000;
	   }
	   else if (str2=="m"){
	       return str1*60*1000;
	   }
	   else if (str2=="h"){
		   return str1*60*60*1000;
	   }
	   else if (str2=="d"){
	       return str1*24*60*60*1000;
	   }
	}
};

//Storage  设置  不传的话为mark为false进入localStorage，mark为true进入sessionStorage
function setStorage(key,value){
	if(window.localStorage){
		var mark = arguments[2] ? true : false;
		var storage = mark ?   sessionStorage : localStorage;
		storage.setItem("GG_"+key,value);
	}else{
		ggCookie.setCookie("GG_"+key,value,"d");
	}
};
//获取
function getStorage(key){
	if(window.localStorage){
		var mark = arguments[1] ? true : false;
		var storage = mark ? sessionStorage : localStorage;
		return storage.getItem("GG_"+key);
	}else{
		ggCookie.getCookie("GG_"+key);
	}
};
//清空
function removeStorage(key){
	if(window.localStorage){
		var mark = arguments[1] ? true : false;
		var storage = mark ? sessionStorage : localStorage;
		storage.removeItem("GG_"+key);
	}else{
		ggCookie.delCookie("GG_"+key);
	}
};

//高度的获取
function getTop(obj){
	var top = 0;
	while(obj){
		top += obj.offsetTop;
		obj = obj.offsetParent;
	}
	return top;
};

//判断图片是否加载完毕，执行的函数
function ImageFinised(src,callback){
	var img = new Image();
	img.src = src;
	if(img.complete){//ie的写法678
		if(callback)callback.call(img);
	}else{
		//goole firefox写法
		img.onreadystatechange = function () {

		};
		img.onload = function () {
			if(callback)callback.call(img);
		};
		img.onerror = function () {
			alert("图片加载失败或没有找到...",1);
		};
	};
};
//图片的等比例缩放
function resizeImg(img,iwidth,iheight){ 
    var image= img;  
    var boxWH = {};
    if(image.width>0 && image.height>0){
     	boxWH.width=image.width;
     	boxWH.height=image.height;	    
        if(boxWH.width>iwidth){    
          	boxWH.height = (boxWH.height*iwidth)/boxWH.width;  
            boxWH.width = iwidth;   
        }

        if(boxWH.height>iheight){    
          	boxWH.width = (boxWH.width*iheight)/boxWH.height;;   
            boxWH.height = iheight;	             	 
         }    	           
    }   
    return boxWH;
};

//序列化form表单
function serilize(els){
	var params = "";
	for(var i = 0,len=els.length;i<len;i++){
		//获取到元素空件的type值，并将其转化成小写
		var type = els[i].type.toLowerCase();
		if(type != "button" && type != "submit" && type != "reset"){
			if((type == "radio" || type == "checkbox") && els[i].checked){
				params += ((i>0?"&":"")+els[i].name+"="+encodeURIComponent(els[i].value));
			}else{
				if(type != "radio" && type != "checkbox"){
					params += ((i>0?"&":"")+els[i].name+"="+encodeURIComponent(els[i].value));
				}
			}
		}
	};
	return params;
};
//数组
function serilizeArray(els){
	var params = [];
	for(var i = 0,len=els.length;i<len;i++){
	//获取到元素空件的type值，并将其转化成小写
	var type = els[i].type.toLowerCase();
	if(type != "button" && type != "submit" && type != "reset"){
			if((type == "radio" || type == "checkbox") && els[i].checked){
				params.push({
					"name":els[i].name,
					"value":encodeURIComponent(els[i].value)
				});
			}else{
				if(type != "radio" && type != "checkbox"){
					params.push({
						"name":els[i].name,
						"value":encodeURIComponent(els[i].value)
					});
				}
			}
		}
	};
	return params;
};
//获取参数值
function getFromValue(formDom){
	var formElements = formDom.elements;
	var json = {};
	for(var i=0,len=formElements.length;i<len;i++){
		var type = formElements[i].type.toLowerCase();
		if(type!="button" && type!="submit" && type!="reset"){
			if(type=="checkbox" && formElements[i].checked){
				var value = json[formElements[i].name]||formElements[i].value;
				if(formElements[i].name in json){
					value +=","+formElements[i].value;	
				}
				json[formElements[i].name] = {
					value:value,
					ele:formElements[i]
				};
			}else if(type=="radio" && formElements[i].checked){
				json[formElements[i].name] = {
					value:formElements[i].value,
					ele:formElements[i]
				};
			}

			if(type!="radio" && type!="checkbox"){
				json[formElements[i].name] = {
					value:formElements[i].value,
					ele:formElements[i]
				};
			}
		}
	}
	return json;
};
//将对象转化成字符串
function jsonToString(obj) {
	var THIS = this;
	switch (typeof (obj)) {
	case 'string':
		return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';
	case 'array':
		return '[' + obj.map(THIS.jsonToString).join(',') + ']';
	case 'object':
		if (obj instanceof Array) {
			var strArr = [];
			var len = obj.length;
			for (var i = 0; i < len; i++) {
				strArr.push(THIS.jsonToString(obj[i]));
			}
			return '[' + strArr.join(',') + ']';
		} else if (obj == null) {
			return 'null';

		} else {
			var string = [];
			for ( var property in obj)
				string.push(THIS.jsonToString(property) + ':'
						+ THIS.jsonToString(obj[property]));
			return '{' + string.join(',') + '}';
		}
	case 'number':
		return obj;
	case "false":
		return obj;
	}
};

//获取class的方法
function getClass(obj,className){
	//此对象下的所有元素
	var eles = obj.getElementsByTagName("*");
	var arr = [];
	for(var i = 0,len = eles.length;i<len;i++){
		if(eles[i].className.indexOf(className) != -1){
			arr.push(eles[i]);
		}
	};
	return arr;
};
//音轨解析
var gg = {
	mark:false,
	
	init:function(){//ie11以上的浏览器才支持 
		//1:音频上下文===html5+ajax+audioContext   html5+audio+audioContext  
		window.AudioContext = window.AudioContext || window.webkitAudioContext || window.mozAudioContext || window.msAudioContext;
		/*动画执行的兼容写法*/
		window.requestAnimationFrame = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.msRequestAnimationFrame;
		//2:初始化音轨对象
		var audioContext = new window.AudioContext();
		return audioContext;
	},
	
	parse:function(audioContext,audioDom,callback){
		try{
			//拿到播放器去解析你音乐文件
			var audioBufferSouceNode = audioContext.createMediaElementSource(audioDom);
			//创建解析对象
			var analyser = audioContext.createAnalyser();
			//将source与分析器连接
			audioBufferSouceNode.connect(analyser); 
			//将分析器与destination连接，这样才能形成到达扬声器的通路
			analyser.connect(audioContext.destination);
			//调用解析音频的方法
			gg.data(analyser,callback);
		}catch(e){
			
		}
	},
	
	data:function(analyser,callback){
		if(gg.mark){
			//讲音频转换一个数组
			var array = new Uint8Array(analyser.frequencyBinCount);
			analyser.getByteFrequencyData(array);
			//通过回调函数返回
			if(callback)callback(array);
			requestAnimationFrame(function(){
				gg.data(analyser,callback);
			});
		}
	}
};
//分栏
var subfield = {
	arr:[],
	mw:3,//每栏的宽度
	init:function(){
		//获取wrapbox对象
		var wrapDom = $("#wrapbox");
		//宽度
		var w = wrapDom.width();
		//列数
		var cells = Math.floor(w / this.mw);
		for(var i = 0;i<=cells;i++){
			var wbDom = $("<div class='myitem' style='width:"+this.mw+"px;left:"+(i * this.mw)+"px;'></div>");
			wrapDom.append(wbDom);
			subfield.arr.push(wbDom);
		}
	}
};
