<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=7" />
    <link href="styles/core.css" type="text/css" rel="stylesheet"/>
    <script src="scripts/jquery.1.3.2.js" type="text/javascript" language="javascript"></script>
    <script src="scripts/popup_layer.js" type="text/javascript" language="javascript"></script>
    <script language="javascript">
		$(document).ready(function() {
			//示例1，默认弹出层
			new PopupLayer({trigger:"#ele1",popupBlk:"#blk1",closeBtn:"#close1"});
			//示例2，弹出层位置可偏移
			new PopupLayer({trigger:"#ele2",popupBlk:"#blk2",closeBtn:"#close2",
				offsets:{
					x:102,
					y:-41
				}
			});
			//示例3，使用弹出层默认特效
			new PopupLayer({trigger:"#ele3",popupBlk:"#blk3",closeBtn:"#close3",useFx:true});
			//示例4，使用弹出层特效，并重载特效
			var t4 =  new PopupLayer({trigger:"#ele4",popupBlk:"#blk4",closeBtn:"#close4",useFx:true});
			t4.doEffects = function(way){
				way == "open"?this.popupLayer.slideDown("slow"):this.popupLayer.slideUp("slow");
			};
			//示例5，使用自定义样式，给最外层容器套上一个class,然后在css定义即可
			new PopupLayer({trigger:"#ele5",popupBlk:"#blk5",closeBtn:"#close5",popupLayerClass:"t5"});

			//示例6，使用遮罩
			new PopupLayer({trigger:"#ele6",popupBlk:"#blk6",closeBtn:"#close6",useOverlay:true});
			//示例7，使用不同的事件来触发
			new PopupLayer({trigger:"#ele7",popupBlk:"#blk7",closeBtn:"#close7",eventType:"mouseover"});
			//示例8，使用自定义事件
			new PopupLayer({trigger:"#ele8",popupBlk:"#blk8",closeBtn:"#close8",
				onBeforeStart:function(){
					this.isDoPopup = false;
					setTimeout(function(){this.isDoPopup = true}.binding(this),5000);
				}
			});
			//示例9，综合效果
			var t9 = new PopupLayer({trigger:"#ele9",popupBlk:"#blk9",closeBtn:"#close9",useOverlay:true,useFx:true,
				offsets:{
					x:0,
					y:-41
				}
			});
			t9.doEffects = function(way){
				if(way == "open"){
					this.popupLayer.css({opacity:0.3}).show(400,function(){
						this.popupLayer.animate({
							left:($(document).width() - this.popupLayer.width())/2,
							top:(document.documentElement.clientHeight - this.popupLayer.height())/2 + $(document).scrollTop(),
							opacity:0.8
						},1000,function(){this.popupLayer.css("opacity",1)}.binding(this));
					}.binding(this));
				}
				else{
					this.popupLayer.animate({
						left:this.trigger.offset().left,
						top:this.trigger.offset().top,
						opacity:0.1
					},{duration:500,complete:function(){this.popupLayer.css("opacity",1);this.popupLayer.hide()}.binding(this)});
				}
			}
		});
	</script>
</head>
<body>
	<h1>Javascripters大作--jQuery弹层类.html</h1>
	<div id="emample1" class="example">
        <div id="ele1" class="tigger">触发元素1</div><div class="description">示例1：默认弹出层,只须传入触发元素、弹出层、关闭按钮的jquery对象或#ID，其中关闭按钮为可选项。<br />new PopupLayer({trigger:"#ele1",popupBlk:"#blk1",closeBtn:"#close1"});</div>
        <div class="clr"></div>
        <select>
        	<option>ie6下能罩住我吗？</option>
        </select>
        <div id="blk1" class="blk" style="display:none;">
            <div class="head"><div class="head-right"></div></div>
            <div class="main">
                <h2>示例1，默认弹出层</h2>
                <a href="javascript:void(0)" id="close1" class="closeBtn">关闭</a>
                <ul>
                    <li><a href="#">项目1</a></li>
                    <li><a href="#">项目2</a></li>
                    <li><a href="#">项目3</a></li>
                    <li><a href="#">项目4</a></li>
                    <li><a href="#">项目5</a></li>
                    <li><a href="#">项目6</a></li>
                    <li><a href="#">项目7</a></li>
                    <li><a href="#">项目8</a></li>
                    <li><a href="#">项目9</a></li>
                    <li><a href="#">项目10</a></li>
                    <li><a href="#">项目11</a></li>
                    <li><a href="#">项目12</a></li>
                </ul>
            </div>
            <div class="foot"><div class="foot-right"></div></div>
        </div>
    </div>
    <div id="emample2" class="example">
        <div id="ele2" class="tigger">触发元素2</div><div class="description">示例2：参数offsets可设置弹出层位置偏移量，让弹出层的位置随心所欲<br />new PopupLayer({trigger:"#ele2",popupBlk:"#blk2",closeBtn:"#close2",offsets:{x:102,y:-41}});</div>
        <div class="clr"></div>
        <select>
        	<option>ie6下能罩住我吗？</option>
        </select>
        <div id="blk2" class="blk" style="display:none;">
            <div class="head"><div class="head-right"></div></div>
            <div class="main">
                <h2>示例2，弹出层位置可偏移</h2>
                <a href="javascript:void(0)" id="close2" class="closeBtn">关闭</a>
                <ul>
                    <li><a href="#">项目1</a></li>
                    <li><a href="#">项目2</a></li>
                    <li><a href="#">项目3</a></li>
                    <li><a href="#">项目4</a></li>
                    <li><a href="#">项目5</a></li>
                    <li><a href="#">项目6</a></li>
                    <li><a href="#">项目7</a></li>
                    <li><a href="#">项目8</a></li>
                    <li><a href="#">项目9</a></li>
                    <li><a href="#">项目10</a></li>
                    <li><a href="#">项目11</a></li>
                    <li><a href="#">项目12</a></li>
                </ul>
            </div>
            <div class="foot"><div class="foot-right"></div></div>
        </div>
    </div>
    <div id="emample3" class="example">
        <div id="ele3" class="tigger">触发元素3</div><div class="description">示例3：useFx设置为true即可使用弹出层默认特效<br />new PopupLayer({trigger:"#ele3",popupBlk:"#blk3",closeBtn:"#close3",useFx:true});</div>
        <div class="clr"></div>
        <select>
        	<option>ie6下能罩住我吗？</option>
        </select>
        <div id="blk3" class="blk" style="display:none;">
            <div class="head"><div class="head-right"></div></div>
            <div class="main">
                <h2>示例3，使用弹出层特效</h2>
                <a href="javascript:void(0)" id="close3" class="closeBtn">关闭</a>
                <ul>
                    <li><a href="#">项目1</a></li>
                    <li><a href="#">项目2</a></li>
                    <li><a href="#">项目3</a></li>
                    <li><a href="#">项目4</a></li>
                    <li><a href="#">项目5</a></li>
                    <li><a href="#">项目6</a></li>
                    <li><a href="#">项目7</a></li>
                    <li><a href="#">项目8</a></li>
                    <li><a href="#">项目9</a></li>
                    <li><a href="#">项目10</a></li>
                    <li><a href="#">项目11</a></li>
                    <li><a href="#">项目12</a></li>
                </ul>
            </div>
            <div class="foot"><div class="foot-right"></div></div>
        </div>
    </div>
    <div id="emample4" class="example">
        <div id="ele4" class="tigger">触发元素4</div><div class="description">示例4：使用弹出层特效，重载特效函数来完成自定义特效<br />var t4 =  new PopupLayer({trigger:"#ele4",popupBlk:"#blk4",closeBtn:"#close4",useFx:true});<br />
        t4.doEffects = function(way){<br />&nbsp;&nbsp;&nbsp;&nbsp;
        way == "open"?this.popupLayer.slideDown("slow"):this.popupLayer.slideUp("slow");<br />}
        </div>
        <div class="clr"></div>
        <select>
        	<option>ie6下能罩住我吗？</option>
        </select>
        <div id="blk4" class="blk" style="display:none;">
            <div class="head"><div class="head-right"></div></div>
            <div class="main">
                <h2>示例4，使用弹出层特效，并重载特效</h2>
                <a href="javascript:void(0)" id="close4" class="closeBtn">关闭</a>
                <ul>
                    <li><a href="#">项目1</a></li>
                    <li><a href="#">项目2</a></li>
                    <li><a href="#">项目3</a></li>
                    <li><a href="#">项目4</a></li>
                    <li><a href="#">项目5</a></li>
                    <li><a href="#">项目6</a></li>
                    <li><a href="#">项目7</a></li>
                    <li><a href="#">项目8</a></li>
                    <li><a href="#">项目9</a></li>
                    <li><a href="#">项目10</a></li>
                    <li><a href="#">项目11</a></li>
                    <li><a href="#">项目12</a></li>
                </ul>
            </div>
            <div class="foot"><div class="foot-right"></div></div>
        </div>
    </div>
    <div id="emample5" class="example">
        <div id="ele5" class="tigger">触发元素5</div><div class="description">示例5：在弹出层容器上加上自定义的class<br />new PopupLayer({trigger:"#ele5",popupBlk:"#blk5",closeBtn:"#close5",popupLayerClass:"t5"});</div>
        <div class="clr"></div>
        <select>
        	<option>ie6下能罩住我吗？</option>
        </select>
        <div id="blk5" class="blk" style="display:none;">
            <div class="head"><div class="head-right"></div></div>
            <div class="main">
                <h2>示例5,使用自定义样式</h2>
                <a href="javascript:void(0)" id="close5" class="closeBtn">关闭</a>
                <ul>
                    <li><a href="#">项目1</a></li>
                    <li><a href="#">项目2</a></li>
                    <li><a href="#">项目3</a></li>
                    <li><a href="#">项目4</a></li>
                    <li><a href="#">项目5</a></li>
                    <li><a href="#">项目6</a></li>
                    <li><a href="#">项目7</a></li>
                    <li><a href="#">项目8</a></li>
                    <li><a href="#">项目9</a></li>
                    <li><a href="#">项目10</a></li>
                    <li><a href="#">项目11</a></li>
                    <li><a href="#">项目12</a></li>
                </ul>
            </div>
            <div class="foot"><div class="foot-right"></div></div>
        </div>
    </div>
    <div id="emample6" class="example">
        <div id="ele6" style="float:left;" class="tigger">触发元素6</div><div class="description">示例6：useOverlay设置为true即可在弹出层后使用遮罩<br />new PopupLayer({trigger:"#ele6",popupBlk:"#blk6",closeBtn:"#close6",useOverlay:true});</div>
        <div class="clr"></div>
        <select>
        	<option>ie6下能罩住我吗？</option>
        </select>
        <div id="blk6" class="blk" style="display:none;">
            <div class="head"><div class="head-right"></div></div>
            <div class="main">
                <h2>示例6,使用遮罩</h2>
                <a href="javascript:void(0)" id="close6" class="closeBtn">关闭</a>
                <ul>
                    <li><a href="#">项目1</a></li>
                    <li><a href="#">项目2</a></li>
                    <li><a href="#">项目3</a></li>
                    <li><a href="#">项目4</a></li>
                    <li><a href="#">项目5</a></li>
                    <li><a href="#">项目6</a></li>
                    <li><a href="#">项目7</a></li>
                    <li><a href="#">项目8</a></li>
                    <li><a href="#">项目9</a></li>
                    <li><a href="#">项目10</a></li>
                    <li><a href="#">项目11</a></li>
                    <li><a href="#">项目12</a></li>
                </ul>
            </div>
            <div class="foot"><div class="foot-right"></div></div>
        </div>
    </div>
    <div id="emample7" class="example">
        <div id="ele7" class="tigger">触发元素7</div><div class="description">示例7：eventType为事件触发类型，表示以何种方式触发弹出层<br />new PopupLayer({trigger:"#ele7",popupBlk:"#blk7",closeBtn:"#close7",eventType:"mouseover"});</div>
        <div class="clr"></div>
        <select>
        	<option>ie6下能罩住我吗？</option>
        </select>
        <div id="blk7" class="blk" style="display:none;">
            <div class="head"><div class="head-right"></div></div>
            <div class="main">
                <h2>示例7,使用不同的事件来触发</h2>
                <a href="javascript:void(0)" id="close7" class="closeBtn">关闭</a>
                <ul>
                    <li><a href="#">项目1</a></li>
                    <li><a href="#">项目2</a></li>
                    <li><a href="#">项目3</a></li>
                    <li><a href="#">项目4</a></li>
                    <li><a href="#">项目5</a></li>
                    <li><a href="#">项目6</a></li>
                    <li><a href="#">项目7</a></li>
                    <li><a href="#">项目8</a></li>
                    <li><a href="#">项目9</a></li>
                    <li><a href="#">项目10</a></li>
                    <li><a href="#">项目11</a></li>
                    <li><a href="#">项目12</a></li>
                </ul>
            </div>
            <div class="foot"><div class="foot-right"></div></div>
        </div>
    </div>
    <div id="emample8" class="example">
        <div id="ele8" class="tigger">触发元素8</div><div class="description">示例8：自定义事件，onBeforeStart在触发弹出前做一些自己想做的事。<br />这里我做的事是：在dom加载完成后，延迟5秒才能使用弹出层。（提示：可refresh页面看效果）<br />new PopupLayer({trigger:"#ele8",popupBlk:"#blk8",closeBtn:"#close8",<br />&nbsp;&nbsp;&nbsp;&nbsp;
				onBeforeStart:function(){<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					this.isDoPopup = false;<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					setTimeout(function(){this.isDoPopup = true}.binding(this),5000);<br />&nbsp;&nbsp;&nbsp;&nbsp;
				}<br />});
    </div>
        <div class="clr"></div>
        <select>
        	<option>ie6下能罩住我吗？</option>
        </select>
        <div id="blk8" class="blk" style="display:none;">
            <div class="head"><div class="head-right"></div></div>
            <div class="main">
                <h2>示例8，使用自定义事件</h2>
                <a href="javascript:void(0)" id="close8" class="closeBtn">关闭</a>
                <ul>
                    <li><a href="#">项目1</a></li>
                    <li><a href="#">项目2</a></li>
                    <li><a href="#">项目3</a></li>
                    <li><a href="#">项目4</a></li>
                    <li><a href="#">项目5</a></li>
                    <li><a href="#">项目6</a></li>
                    <li><a href="#">项目7</a></li>
                    <li><a href="#">项目8</a></li>
                    <li><a href="#">项目9</a></li>
                    <li><a href="#">项目10</a></li>
                    <li><a href="#">项目11</a></li>
                    <li><a href="#">项目12</a></li>
                </ul>
            </div>
            <div class="foot"><div class="foot-right"></div></div>
        </div>
    </div>
    <div id="emample9" class="example">
        <div id="ele9" class="tigger">触发元素9</div><div class="description">示例9：综合效果<br />var t9 = new PopupLayer({trigger:"#ele9",popupBlk:"#blk9",closeBtn:"#close9",<br />useOverlay:true,useFx:true,offsets:{x:0,y:-41}});<br />
t9.doEffects = function(way){<br />
&nbsp;&nbsp;&nbsp;&nbsp;if(way == "open"){<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;this.popupLayer.css({opacity:0.3}).show(400,function(){<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;this.popupLayer.animate({<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;left:($(document).width() - this.popupLayer.width())/2,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;top:(document.documentElement.clientHeight -<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;this.popupLayer.height())/2 + $(document).scrollTop(),<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;opacity:0.8<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},1000,function(){this.popupLayer.css("opacity",1)}.binding(this));<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}.binding(this));<br />
&nbsp;&nbsp;&nbsp;&nbsp;}<br />
&nbsp;&nbsp;&nbsp;&nbsp;else{<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;this.popupLayer.animate({<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;left:this.trigger.offset().left,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;top:this.trigger.offset().top,<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;opacity:0.1<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},{duration:500,complete:function(){<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;this.popupLayer.css("opacity",1);this.popupLayer.hide()}.binding(this)});<br />
&nbsp;&nbsp;&nbsp;&nbsp;}<br />
}<br /></div>
        <div class="clr"></div>
        <select>
        	<option>ie6下能罩住我吗？</option>
        </select>
        <div id="blk9" class="blk" style="display:none;">
            <div class="head"><div class="head-right"></div></div>
            <div class="main">
                <h2>示例9,综合效果</h2>
                <a href="javascript:void(0)" id="close9" class="closeBtn">关闭</a>
                <ul>
                    <li><a href="#">项目1</a></li>
                    <li><a href="#">项目2</a></li>
                    <li><a href="#">项目3</a></li>
                    <li><a href="#">项目4</a></li>
                    <li><a href="#">项目5</a></li>
                    <li><a href="#">项目6</a></li>
                    <li><a href="#">项目7</a></li>
                    <li><a href="#">项目8</a></li>
                    <li><a href="#">项目9</a></li>
                    <li><a href="#">项目10</a></li>
                    <li><a href="#">项目11</a></li>
                    <li><a href="#">项目12</a></li>
                </ul>
            </div>
            <div class="foot"><div class="foot-right"></div></div>
        </div>
    </div>
    <div id="blk10" class="blk" style="display:none;">
        <div class="head"><div class="head-right"></div></div>
        <div class="main">
            <h2>示例10,永远在中间的层</h2>
            <a href="javascript:void(0)" id="close10" class="closeBtn">关闭</a>
            <ul>
                <li><a href="#">项目1</a></li>
                <li><a href="#">项目2</a></li>
                <li><a href="#">项目3</a></li>
                <li><a href="#">项目4</a></li>
                <li><a href="#">项目5</a></li>
                <li><a href="#">项目6</a></li>
                <li><a href="#">项目7</a></li>
                <li><a href="#">项目8</a></li>
                <li><a href="#">项目9</a></li>
                <li><a href="#">项目10</a></li>
                <li><a href="#">项目11</a></li>
                <li><a href="#">项目12</a></li>
            </ul>
        </div>
        <div class="foot"><div class="foot-right"></div></div>
    </div>
</body>
</html>