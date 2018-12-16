window.onload=function(){
	
	var music=document.getElementById('music');
	var audio=document.getElementsByTagName('audio')[0];
	var page1=document.getElementById('page1');
	var page2=document.getElementById('page2');
	var page3=document.getElementById('page3');

	// 当音乐播放完之后暂停
	audio.addEventListener('ended',function(event){
 			music.setAttribute('class','');
	},false)
	// 控制音乐暂停和播放
    music.addEventListener('touchstart',function(event){
    	if(audio.paused){
    		audio.play();
            this.setAttribute('class','play');
    	}else{
    		audio.pause();
    	   this.setAttribute('class','');
    	}
    },false)
    // 翻页效果的实现
    page1.addEventListener('touchstart',function(event){
    	page1.style.display='none';
    	page2.style.display='block';
    	page3.style.display='block';
    	page3.style.top="100%";
    	setTimeout(function(){
    		page2.setAttribute('class','page fadeOut');
    		page3.setAttribute('class','page fadeIn');
    	},5500)
    },false)
    

}