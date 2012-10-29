#!/usr/bin/env js

// 関数リテラル（無名）をその場で呼び出す
// 関数リテラルの返り値は関数なので変数sumは関数
var sum = (function() {
    // 関数の外部からこの名前にアクセスできない
    // 事実上、プライベートな変数
    // 通常、関数の呼び出しが終わればアクセスできない名前だが
    // 返り値の無名関数の中から使える
    var position = { x:2, y:3 };

    // 同じく関数の外部からアクセスできないプライベート関数
    // 名前をsumにしても問題ないが、余計な混乱を避けるためここでは別名にしている
    function sum_internal(a, b) {
        return Number(a) + Number(b);
    }

    // 上記二つの名前を強引に使うだけの恣意的な返り値
    return function(a, b) {
        print('x =', position.x);
        return sum_internal(a, b);
    };
})();

var result = sum(3, 4);
print(result);
