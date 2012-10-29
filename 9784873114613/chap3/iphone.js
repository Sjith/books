var hist = [];
var startUrl = 'index.html';
$(document).ready(function() {
    loadPage(startUrl);
});
function loadPage(url) {
    $('body').append('<div id="progress">読み込み中...</div>');
    scrollTo(0,0);
    if (url == startUrl) {
        //$('#container').load('index.html #header ul', hijackLinks);
        var element = ' #header ul';
    } else {
        //$('#container').load(url + ' #content', hijackLinks);
        var element = ' #content';
    }
    $('#container').load(url + element, function(){
        var title = $('h2').html() || 'こんにちは！';
        $('h1').html(title);
        $('h2').remove();
        $('.leftButton').remove();
        hist.unshift({'url':url, 'title':title});
        if (hist.length > 1) {
            $('#header').append('<div class="leftButton">' + hist[1].title + '</div>');
            $('#header .leftButton').click(function(){
                $(e.target).addClass('clicked');
                var thisPage = hist.shift();
                var previousPage = hist.shift();
                loadPage(previousPage.url);
            });
        }
        $('#container a').click(function(e){
            var url = e.target.href;
            if (url.match(/localhost/)) {
                e.preventDefault();
                loadPage(url);
            }
        });
        $('#progress').remove();
    });
}
/*
function hijackLinks() {
    $('#container a').click(function(e) {
        var url = e.target.href;
        if (url.match(/localhost/)) {
            e.preventDefault();
            loadPage(e.target.href);
        }
    });
    var title = $('h2').html() || 'こんにちは！';
    $('h1').html(title);
    $('h2').remove();
    $('#progress').remove();
}
*/
