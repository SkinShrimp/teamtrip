function play68_init() {
	updateShare(0);
}
var HOME_PATH="";
function goHome() {
}

function play68_submitScore(score) {
	updateShareScore(score);
}

function updateShare(bestScore) {
	imgUrl = '';
	lineLink = '';
	descContent = "";
	updateShareScore(bestScore);
	appid = '';
}

function updateShareScore(bestScore) {
	window.__score__ = {
        score: bestScore
    }
		
}