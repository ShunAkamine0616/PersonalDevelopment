// タイピング
// let words = ['red', 'blue', 'green', 'yellow', 'black', 'white', 'pink'];
var words = [];
//document.addEventListener('DOMContentLoaded', function(){
//	function menuClick(){
//		// クリックした際に実行する処理を記述
////		fetch("getWords")
////		   .then(response => response.json())
////		  .then(data => words = data);
//			}
//
//	// 引数に指定したclassの値をもつ要素をすべて取得
//	const buttons = document.getElementsByClassName('timerstart');
//	// 上記で取得したすべての要素に対してクリックイベントを適用
//	for(let i = 0; i < buttonss.length; i++) {
//    	buttons[i].addEventListener('click', menuClick(), false);
//	}
//    
//}, false);



var checkTexts = []; // １文字ずつ格納するための配列
let english = '';  // 現在入力中の文字(英語)
let japanese = '' // 日本語
let index = 0; // 入力中の文字の位置
let display = 0 // 表示中の文字の位置
let end = false;
let score = 0;
var objLength = 0; // 連想配列の大きさ
var wordsArray = []; // 連想配列の値を入れるための配列
var level = 0;

const jp = document.getElementById('japanese');
const target = document.getElementById('target');
const explanation = document.getElementById('explanation');
const loginId = document.getElementById('loginId').value;
console.log('loginId = ' + loginId)
let missCount = 0;
const countEl = document.getElementById('count');

// タイマー処理
const coutdownTimer = (tickCallBack, endCallBack
    , daysOrSeconds, hours = null, minutes = null, seconds = null) => {

    // 秒を日数・時間・分・秒に変換
    const calcTime = sec => {
        const days = Math.floor(sec / 86400);
        const hours = Math.floor((sec -= days * 86400) / 3600);
        const minutes = Math.floor((sec -= hours * 3600) / 60);
        sec -= minutes * 60;
        return [days, hours, minutes, sec];
    };

    // 引数を秒数に変換
    let sec = (hours === null) ? daysOrSeconds
        : seconds + minutes * 60 + hours * 3600 + daysOrSeconds * 86400;

    // スタートのタイムスタンプ取得
    const startTime = Date.now();
    // 初回の残り時間通知
    tickCallBack(calcTime(sec));

    // タイマースタート（1秒間隔)
    const timer = setInterval(
        () => {
            // 残り時間を秒で計算
            let nowSec = sec - Math.floor((Date.now() - startTime) / 1000);
            if (nowSec <= 0) { // 残り時間なし
                clearInterval(timer); // タイマー停止
                const buttons = document.getElementsByClassName("timerstart");
                buttons[0].hidden = false;
                buttons[1].hidden = false;
                buttons[2].hidden = false;
                explanation.hidden = false;
                end = true; 
                target.textContent = 'スコア: ' + (score - missCount); // 画面にクリアと表示  
                countEl.textContent = 'タイプミス' + missCount + '個';
                jp.textContent = '';
                endCallBack(); // 終了通知
      			console.log("loginId = " + loginId)
                fetch('socreInsert?loginId='+loginId+'&score='+(score-missCount)+'&level='+level);
            } else {
                tickCallBack(calcTime(nowSec)); // 残り時間通知
            }
        }, 1000
    )

};

window.addEventListener("DOMContentLoaded", () => {


    const p = document.getElementById("timer");

    // 残り時間通知受け取り関数
    const tickFunc = (time) => {
        p.textContent = `残り${time[2]}分  ${time[3]}秒`;
    };
    // 終了通知受け取り関数
    const endFunc = () => {
        p.textContent = "終了しました";
        jp.textContent = '';
    };
	const buttons = document.getElementsByClassName("timerstart");
	for(let i = 0; i < buttons.length; i++) {
	    buttons[i].addEventListener("click", () => {
			buttons[0].hidden = true;
			buttons[1].hidden = true;
			buttons[2].hidden = true;
	        score = 0;
	        coutdownTimer(tickFunc, endFunc, 90);
	    });
    }
});




// ボタン押下時にゲームスタート処理を実行
var buttons = document.getElementsByClassName('timerstart');
for(let i = 0; i < buttons.length; i++) {
	buttons[i].addEventListener('click', event => {
		explanation.hidden = true;
		console.log("event.target.id = " + event.target.id)
		fetch("getWords?level="+event.target.id)
		   .then(response => response.json())
		  .then(data => {
			words = data;
			console.log("words = " + words);
			level = event.target.id;
	    end = false; // まだ終了しない
	    target.textContent = ''; // ターゲット初期化
	    createText();
	    jp.textContent = japanese; // 日本語を表示
	    countEl.textContent = ''; // タイプミスの表示　初期化
	    missCount = 0; // ミスを0に初期化
		  });
	});
}
function createText() {
    target.textContent = ''; // 表示用
//    for (var i in words) { 
//        objLength++; 
//        wordsArray.push(words[i]); // 英単語を配列に入れる
//    }
    var rnd = Math.floor(Math.random() * words.length); // ランダム生成

    let word = words[rnd];  // ランダムに単語を選ぶ
    english = word.english;
    japanese = words.find(e => e.english === english).japanese;
    console.log("japanese = " + japanese);
//    console.log("words.japanese = " + words.japanese)
//    console.log("english = "+ english)
	console.log("word = " + word);
//    japanese = Object.keys(words).filter((key) => {
//        return words[key] === english; // 対応する日本語を代入
//    });
	console.log("japanese = "+ japanese)
    checkTexts = english.split('').map(function (value) {
        var span = document.createElement('span'); // spanタグ生成

        span.textContent = value; // スパンタグに1文字入れる
        // span.display=none;
        // target.appendChild(span); // pタグの中にspanタグを入れる
        return span;
    });
}

// キー押したとき　新しいやつ
document.addEventListener('keydown', keyDown); 

function keyDown(e) {
    if ((e.key === checkTexts[0].textContent || 32 === e.keyCode) && end === false) { // ボタンを押して一致したら
        // 英単語が最後の文字まで表示されていないとき
        console.log(english + english.length)
        console.log("display = " + display)
        
            if(32 === e.keyCode) { // スペースを押したとき
                if(display !== english.length) { 
                    console.log("checkTexts[display] = "+ checkTexts[display])
                    // target.innerHTML += checkTexts[display].textContent; // 1文字追加
                    target.appendChild(checkTexts[display]); // 1文字追加
                    console.log(target.textContent)
                    display++;
                    score--;
                } 
            } else { // スペース以外
                // console.log("checkTexts[0] = " + checkTexts[0]);
                score++;
                checkTexts[0].className = 'add-blue'; // 青に変える
                if(display === 0) { // 表示されているものがないなら
                    target.append(checkTexts[0]); // 文字追加
                }
                checkTexts.shift(); // 最初の要素を消す
                if(display !== 0) {
                    display--;
                }
            }
        if (!checkTexts.length) { // 文字がなくなったら
            var aud = new Audio('/audio/crrect_answer2.mp3');
            aud.play();
            createText(); // 次の文字を生成 
            target.textContent = ''; // ターゲット
            display = 0;
            jp.textContent = japanese; // 日本語を表示
        } 
    } else if(isUpperCase(e.key)) {
		console.log("e.key" + e.key)
	    missCount++;
	    var aud = new Audio('/audio/blip04.mp3');
	    aud.play();
	} else {
		if(e.shiftKey !== true) {
			console.log("e.key" + e.key)
	        missCount++;
	        var aud = new Audio('/audio/blip04.mp3');
	        aud.play();
        }
    }
}

function sleep(waitMsec) {
  var startMsec = new Date();
 
  // 指定ミリ秒間だけループさせる（CPUは常にビジー状態）
  while (new Date() - startMsec < waitMsec);
}

const isUpperCase = str => {
  return str === str.toUpperCase() ? true : false
}