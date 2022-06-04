var p = document.getElementById('text');
var textLists = [
    'Hello World',
    'This is my App',
    'How are you?',
    'Hello Hello'
];

var checkTexts = [];

createText();

function createText() {
    var rnd = Math.floor(Math.random() * textLists.length);

    p.textContent = '';
    checkTexts = textLists[rnd].split('').map(function (value) {
        var span = document.createElement('span');

        span.textContent = value;
        p.appendChild(span);

        return span;
    });
}
console.log(checkTexts);

document.addEventListener('keydown', keyDown);

function keyDown(e) {
    if (e.key === checkTexts[0].textContent) {
        checkTexts[0].className = 'add-blue';

        checkTexts.shift();

        if (!checkTexts.length) createText();
    }
}
