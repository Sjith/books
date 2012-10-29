var cacheStatusValues = [];
cacheStatusValues[0] = '未キャッシュ';
cacheStatusValues[1] = 'アイドル';
cacheStatusValues[2] = 'チェック中';
cacheStatusValues[3] = 'ダウンロード中';
cacheStatusValues[4] = 'ダウンロード完了';
cacheStatusValues[5] = '無効';

// 発生する可能性のあるイベントをすべて監視
var cache = window.applicationCache;
cache.addEventListener('cached', logEvent, false);
cache.addEventListener('checking', logEvent, false);
cache.addEventListener('downloading', logEvent, false);
cache.addEventListener('error', logEvent, false);
cache.addEventListener('noupdate', logEvent, false);
cache.addEventListener('obsolete', logEvent, false);
cache.addEventListener('progress', logEvent, false);
cache.addEventListener('updateready', logEvent, false);

function logEvent(e) {
    var online, status, type, message;
    online = (navigator.onLine) ? 'オンライン' : 'オフライン';
    status = cacheStatusValues[cache.status];
    type = e.type;
    message = '状態: ' + online;
    message += ', イベント: ' + type;
    message += ', キャッシュ: ' + status;
    if (type == 'error' && navigator.onLine) {
        message += ' (マニフェストの構文エラーの可能性)';
    }
    console.log(message);
}

// ダウンロードが完了したら、新しいファイルでアプリケーションを置き換える
window.applicationCache.addEventListener(
    'updateready',
    function() {
        window.applicationCache.swapCache();
        console.log('キャッシュが新しいバージョンに置き換えられました');
    },
    false
);

// マニフェストへの変更を10秒おきにチェック
setInterval(function(){cache.update()}, 10000);
