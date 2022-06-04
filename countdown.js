// タイピング
let words = ['red', 'blue', 'green', 'yellow', 'black', 'white', 'pink'];
let wordCount = 0; // 完了した文字数
let word = ''  // 現在入力中の文字
let index = 0; // 入力中の文字の位置
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

                button.disabled = false;   
                target.textContent = 'クリア'; // 画面にクリアと表示  
                countEl.textContent = 'タイプミス' + missCount + '個';  
                
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
        p.textContent = `残り${time[0]} 日 ${time[1]}時間 ${time[2]}分  ${time[3]}秒`;
    };
    // 終了通知受け取り関数
    const endFunc = () => {
        p.textContent = "終了しました";
    };

    button.addEventListener("click", () => {
        button.disabled = true;
        coutdownTimer(tickFunc, endFunc, 10);
    });
});




// ボタン押下時にゲームスタート処理を実行
document.getElementById('timerstart').addEventListener('click', e => {
    wordCount = 0;
    word = words.splice(Math.floor(Math.random() * words.length), 1).join();
    target.textContent = word;
    missCount = 0;
});

// キー押下時の処理
document.addEventListener('keydown', e => {

    // 入力したキーが先頭の文字と一致した場合
    if (word.charAt(index) === e.key) {
        index = index + 1; // 文字の位置をカウントアップ
        // 入力した文字を「_」に置き換える
        target.textContent = '_'.repeat(index) + word.substring(index);

        // 入力した文字が単語の最後だった場合
        if (index === word.length) {
            // 1単語終了時
            index = 0; // 初期化
            wordCount = wordCount + 1;;   // 単語数をマイナス1


            // まだ入力する単語が残っている場合
            // 次の単語を画面にセットする
            word = words.splice(Math.floor(Math.random() * words.length), 1).join();
            target.textContent = word;

        }
    } else {
        missCount = missCount + 1;
    }
});