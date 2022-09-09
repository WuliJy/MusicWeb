//函数：根据ID或者Class找到标签
function $(selector) {
	return document.querySelector(selector);
}

//函数：根据position显示音乐
function showMusic(position) {
	var select_music = musicArr[position];
	$('.title_name').innerHTML = select_music.musicName;
	$('.title_singer').innerHTML = select_music.musicArtist;
	$('.totalTime').innerHTML = formateTime(select_music.musicDuration);
	$('.audio').src = select_music.playPath;
	$('.disk_play_song').src = select_music.ablumCoverPath;
}

//函数：移动指针
function moveNeedle(isPlaying) {
	if(isPlaying) {
		//播放的时候样式
		$('#needle').className = 'needle play_needle';
		$('#disk_cover').className = 'disk_cover disk_cover_animation';
		$('#play').src='img/play_rdi_btn_pause.png';
	} else {
		//暂停的时候样式
		$('#needle').className = 'needle pause_needle';
		$('#disk_cover').className = 'disk_cover';
		$('#play').src='img/play_rdi_btn_play.png';
	}
}

var position = 0;
var player = $('#audio'); //音频播放器
var isPlaying = false; //是否正在播放
showMusic(position);

//为指针添加单击事件
$('#needle').addEventListener('click', function() {
	moveNeedle(isPlaying);
	isPlaying = !isPlaying;
});

//为play按钮添加单击事件
$('#play').addEventListener('click', function() {
	if(isPlaying == false) {
		player.pause();
		$('#play').src = 'img/play_rdi_btn_play.png';
		moveNeedle(isPlaying);
		isPlaying = true;
		$('#disk_cover').className = 'disk_cover';
	} else {
		player.play();
		$('#play').src = 'img/play_rdi_btn_pause.png';
		moveNeedle(isPlaying);
		isPlaying = false;
		$('#disk_cover').className = 'disk_cover disk_cover_animation';
	}
});

//处理进度条
var play_progress = $('.play_progress');
var progress_slide = $('.progress_slide');

//函数：对时间进行格式化
function formateTime(time) {
	if(time > 3600) {
		var h = parseInt(time / 3600);
		var m = parseInt(time % 3600 / 60);
		var s = parseInt(time % 3600);
		h = h >= 10 ? h : '0' + h;
		m = m >= 10 ? m : '0' + m;
		s = s >= 10 ? s : '0' + s;
		return h + ':' + m + ':' + s;
	} else {
		var m = parseInt(time / 60);
		var s = parseInt(time % 60);
		m = m >= 10 ? m : '0' + m;
		s = s >= 10 ? s : '0' + s;
		return m + ':' + s;
	}
}

//设置定时器，让进度条移动
window.setInterval(function() {
	//console.info('当前音乐播放器播放的时间:'+formateTime(player.currentTime));
	$('#time').innerHTML = formateTime(player.currentTime);
	//获取当前音乐总时长(秒数)
	var percent = player.currentTime / player.duration;
	//修改滚动条的宽度
	$('.progress_slide').style.width = percent * 100 + "%";
}, 100);

//函数：播放歌曲
function playMusic() {
	player.play();
	$('#play').src = 'img/play_rdi_btn_pause.png';
	moveNeedle(true);
	$('#disk_cover').className = 'disk_cover disk_cover_animation';
	isPlaying = true;
}

//为歌曲添加一个播放完毕事件
var loop = 1; // 1代表单曲循环   0 代表顺序播放

player.addEventListener('ended', function() {

	if(loop == 1) {
		playMusic();
	} else {
		position = ++position % musicArr.length;
		showMusic(position);
		playMusic();
	}

});

//上一曲

$('#pre').addEventListener('click', function() {

	if(position == 0) {
		position = musicArr.length - 1;
	} else {
		position--;
	}

	showMusic(position);
	playMusic();
});

//下一曲
$('#next').addEventListener('click', function() {

	if(position == musicArr.length-1) {
		position = 0;
	} else {
		position++;
	}

	showMusic(position);
	playMusic();
});


//改变播放方式
$('#play_state').addEventListener('click',function(){
	
	if(loop==1){
		//顺序播放
		$('#play_state').src='img/play_icn_src.png'
		loop=0;
	}else{
		//单曲循环
		$('#play_state').src='img/play_icn_loop.png';
		loop=1;
	}
	
});
