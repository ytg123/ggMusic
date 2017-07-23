(function(win){
/**
 * 成员变量：id src title author
 * 	揭示模式模块模式：返回的仅需要的模块
 * 
 * */
function Gmusic(id,src,title,author){
	//公共属性
	this.id = id;
	//路径
	this.src = src;
	this.title = title;
	this.author = author;
	this.audioDom = null;
};
//静态方法
//Gmusic.author = "广广";
//格式化时间
Gmusic.format = function(timer){
	var m = Math.floor(timer /60);
	var s = Math.floor(timer%60);
	//补0
	if(m<10)m = "0" + m;
	if(s<10)s = "0" + s;
	return m + ":"+ s;
};
//特权方法(公共方法)
Gmusic.prototype = {
	constructor:Gmusic,
	//初始化
	init:function(callback){
		//创建播放器
		this.audioDom = dom(this.id);
		//设置显示默认界面属性
		//this.audioDom.controls = "controls";
		//设置路径
		this.audioDom.src = this.src;
		//设置音量
		this.audioDom.volume = 0.2;
		if(callback)callback(this.audioDom.volume);
		//将播放器放到指定div中
		//dom(this.id).appendChild(this.audioDom);
	},
	//播放
	play:function(){
		this.audioDom.play();
	},
	//暂停
	stop:function(){
		this.audioDom.pause();
	},
	//静音
	muted:function(callback){
		this.audioDom.muted = !this.audioDom.muted;
		if(callback)callback.call(this,this.audioDom.muted);
	},
	//时间
	time:function(callback){
		//存放各种时间
		var json = {};
		//总时间
		this.audioDom.oncanplaythrough = function(){
			//总时长
			json.fztime = Gmusic.format(this.duration);
			if(callback)callback.call(json,"time");
		};
		//当前时间
		this.audioDom.ontimeupdate = function(){
			//当前时间
			json.fcurrent = Gmusic.format(this.currentTime);
			//进度
			json.percent = ((this.currentTime / this.duration)*100).toFixed(0);
			if(callback)callback.call(json,"current");
		};
	},
	//拖拽进度条
	drag:function(obj,callback,callback2){
		//获取音乐播放器
		var zdom = this.audioDom;
		obj.onmousedown = function(e){
			//起始位置
			var posS = getXY(e);
			var St = this.offsetLeft;
			//最大位置
			var maxL = this.parentElement.offsetWidth - this.offsetWidth;
			document.onmousemove = function(e){
				//终点位置
				var posE = getXY(e);
				var nl = posE.x - posS.x + St;
				//边界的判断
				if(nl <= 0)nl = 0;
				if(nl >= maxL) nl = maxL;
				obj.style.left = nl + "px";
				var gbit = nl / maxL;
				if(callback)callback.call(zdom,gbit);
			};
			document.onmouseup = function(){
				this.onmousemove = null;
				this.onmouseup = null;
				if(callback2)callback2.call(zdom);
			};
		};
		//点击改变音乐
		Gon(obj.nextElementSibling,"click",function(e){
			//获取鼠标距离浏览器的位置  鼠标x - 其父类左偏移量
			var x = getXY(e).x - this.parentElement.offsetLeft;
			//拿到其父类的宽度
			var pw = this.parentElement.offsetWidth;
			//拿到比例
			var pbit = x / pw;
			console.log(zdom.duration);
			if(zdom.paused){
				zdom.play();
			}
			//改变音乐
			zdom.currentTime = zdom.duration * pbit;
		});
	}
};
//绑定到window下方
	win.Music = Gmusic;
})(window);