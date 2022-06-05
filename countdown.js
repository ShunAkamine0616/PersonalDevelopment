// タイピング
// let words = ['red', 'blue', 'green', 'yellow', 'black', 'white', 'pink'];
var words = { 赤:"red", りんご: "apple", みかん: "orange", 空っぽ: "empty", 
"個性、性格":"personality", "現れる(e)": "emerge", "...にもかかわらず(d)":"despite", "（...に対する）考え方(a)":"attitude",
を関与させる:"involve", 手に入る:"available"}
var checkTexts = []; // １文字ずつ格納するための配列
let english = ''  // 現在入力中の文字(英語)
let japanese = '' // 日本語
let index = 0; // 入力中の文字の位置
let display = 0 // 表示中の文字の位置
let end = false;
let score = 0;
var objLength = 0; // 連想配列の大きさ
var wordsArray = []; // 連想配列の値を入れるための配列

const jp = document.getElementById('japanese');
const target = document.getElementById('target');

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
                const button = document.getElementById("timerstart");
                end = true; 
                button.disabled = false;
                target.textContent = 'スコア: ' + (score - missCount); // 画面にクリアと表示  
                countEl.textContent = 'タイプミス' + missCount + '個';
                jp.textContent = '';
                endCallBack(); // 終了通知
            } else {
                tickCallBack(calcTime(nowSec)); // 残り時間通知
            }
        }, 1000
    )

};

window.addEventListener("DOMContentLoaded", () => {

    const button = document.getElementById("timerstart");

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

    button.addEventListener("click", () => {
        score = 0;
        button.disabled = true;
        coutdownTimer(tickFunc, endFunc, 60);
    });
});




// ボタン押下時にゲームスタート処理を実行
document.getElementById('timerstart').addEventListener('click', e => {
    end = false; // まだ終了しない
    target.textContent = ''; // ターゲット初期化
    checkTexts();
    jp.textContent = japanese; // 日本語を表示
    countEl.textContent = ''; // タイプミスの表示　初期化
    missCount = 0; // ミスを0に初期化
});

// キー押下時の処理
document.addEventListener('keydown', e => {

    // 入力したキーが先頭の文字と一致した場合
    if ((english.charAt(index) === e.key || 32 === e.keyCode) && end === false) {
        // 英単語が最後の文字まで表示されていないとき
        if(target.textContent.length !== english.length) {
            if(32 === e.keyCode) { // スペース押したとき
                target.innerHTML += english.charAt(display); // 1文字増やす
                display++;
            } else { // スペース以外
                if (display === index) {
                    target.innerHTML += '<span class="add-blue">'+ english.charAt(index) + '</span>'; // 1文字増やす
                    display++;
                }
                target.innerHTML.replace(target.innerHTML.charAt(index), '<span class="add-blue">'+ english.charAt(index) + '</span>');
                score = score + 1; // スコアカウントアップ
                index = index + 1; // 文字の位置をカウントアップ
            }
            console.log("index = " + index);
            console.log("display = " + display);
        } 

        // 入力した文字が単語の最後だった場合
        if (index === english.length) {
            // 1単語終了時
            index = 0; // 初期化
            display = 0;

            // 次の単語を画面にセットする
            var objLength = 0; // 連想配列の大きさ
            var wordsArray = []; // 英単語の配列
            for (var i in words) { 
                objLength++;
                wordsArray.push(words[i]);
            }
            var rnd = Math.floor(Math.random() * objLength); // ランダム生成
            english = wordsArray[rnd]; // 英単語をランダムに選ぶ
            japanese = Object.keys(words).filter((key) => { // 日本語をセット
                return words[key] === english;
            });
        
            target.textContent = ''; // ターゲット
            jp.textContent = japanese;  // 日本語表示
        }
    } else {
        missCount = missCount + 1; // ミスを追加
    }
});

// createText();

function createText() {
    target.textContent = ''; // 表示用
    for (var i in words) { 
        objLength++; 
        wordsArray.push(words[i]); // 英単語を配列に入れる
    }
    var rnd = Math.floor(Math.random() * objLength); // ランダム生成

    english = wordsArray[rnd];  // ランダムに英単語を選ぶ
    japanese = Object.keys(words).filter((key) => {
        return words[key] === english; // 対応する日本語を代入
    });

    checkTexts = english.split('').map(function (value) {
        var span = document.createElement('span'); // spanタグ生成

        span.textContent = value; // スパンタグに1文字入れる
        // span.display=none;
        target.appendChild(span); // pタグの中にspanタグを入れる
        return span;
    });
}

document.addEventListener('keydown', keyDown); 

function keyDown(e) {
    if ((e.key === checkTexts[0].textContent || 32 === e.keyCode) && end === false) { // ボタンを押して一致したら
        // 英単語が最後の文字まで表示されていないとき
        if(display !== english.length) { 
            if(32 === e.keyCode) {
                target.innerHTML += checkTexts[display]; // 1文字追加
                display++;
            } else {
                if(index < display)
                target
            }
        } 
        
        checkTexts[0].className = 'add-blue';  

        checkTexts.shift(); // 最初の要素を消す

        if (!checkTexts.length) createText(); // 大きさが0だったら次の文字を生成 
    }

    
}