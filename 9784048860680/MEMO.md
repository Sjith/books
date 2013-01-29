Google Androidプログラミング入門 改訂2版
========================================

## 2 基本編: アプリケーションの基本概念

### 2.1 アプリケーションを構成する要素

#### 2.1.1 はじめに

把握しておくべき構成要素とトピック

* Activity
* Fragment
* Intent
* ViewGroup
* View
* Service
* BroadcastReceiver
* ContentProvider
* SQLite
* データとUIのバインディング
* ApplicationResource

#### 2.1.2 アクティビティとフラグメント

##### 2.1.2.1 アクティビティ

##### 2.1.2.2 フラグメント

* Android 3.0(APIレベル11)から追加された機能

#### 2.1.3 ライフサイクル

##### 2.1.3.1 アクティビティのライフサイクル

* 生存しているアクティビティの重要度 (KILLされにくい順番)
** フォアグラウンドアクティビティ
*** 画面の最前面にいるアクティビティ
** ビジブルアクティビティ
*** フォーカスは失っているが、ユーザの目に見えている状態
** バックグラウンドアクティビティ
*** 他のアクティビティに隠れてしまい、ユーザから見えなくなっている状態

* アクティビティのライフサイクル

    // ライフタイム全体
    onCreate
    
        // ビジブルライフタイム
        (onRestart)
        onStart
    
            // フォアグラウンドライフタイム
            onResume
                アクティビティが動いている状態
            onPause
    
        onStop
    
    onDestroy

#### 2.1.3.2 フラグメントのライフサイクル

* フラグメントのライフサイクル

    onAttatch
        onCreate
            onCreateView
            onActivityCreated
                onStart
                    onResume
                        Fragmentがアクティブな状態
                    onPause
                onStop
            onDestroyView
        onDestroy
    onDetach

* onAttach(Activity)
** フラグメントがアクティビティと関連づけられたときに一度だけ呼ばれる
* onCreateView(LayoutInflater, ViewGroup, Bundle)
** フラグメントがView改装に関連づけられるときに呼ばれる
** このメソッドの中でレイアウトファイル読み込みやViewオブジェクト生成を行う
* onActivityCreated(Bundle)
** フラグメントが関連付いているアクティビティの生成が完了したときに呼ばれる
** 厳密には、アクティビティのonCreate()が呼ばれた直後に呼ばれる
** 今までアクティビティのonCreate()に書いていた処理はここに移すと良い
* onDestroyView()
** フラグメントが関連付いているView階層が取り除かれるときに呼ばれる
* onDetach()
** アクティビティとの関連が取り除かれるときに呼ばれる

#### 2.1.4 インテント

##### 2.1.4.1 インテントの基本構造

##### 2.1.4.2 インテントが持つ情報

* 基本情報
** アクション (action)
** データ (data)
* 補足情報
** カテゴリ (category)
** 付加情報 (extra)
** タイプ (type)
** フラグ (flag)

* アクション
** アクティビティアクション
*** 他のアクティビティを起動する際に設定されるアクション
*** e.g. 電話をかけたい、何かを表示したいなど
** ブロードキャストアクション
*** なんらかのイベントが発生したことを他のオブジェクトに知らせる
*** e.g. 電池残量が少ない、システムの起動が完了したなど
** 予め定義されているアクション
*** ACTION_MAIN
*** ACTION_VIEW
*** ACTION_DEFAULT
*** ACTION_INSERT
*** ACTION_EDIT
*** ACTION_DELETE
*** ACTION_ATTACH_DATA
*** ACTION_PICK_ACTIVITY
*** ACTION_PICK
*** ACTION_GET_CONTENT
*** ACTION_CHOOSER
*** ACTION_RUN
*** ACTION_SYNC
*** ACTION_CALL
*** ACTION_DIAL
*** ACTION_SEND
*** ACTION_SENDTO
*** ACTION_ANSWER
*** ACTION_BATTERY_CHANGED
*** ACTION_BATTERY_LOW
*** ACTION_BOOT_COMPLETED
*** ACTION_PACKAGE_ADDED
*** ACTION_PACKAGE_CHANGED
*** ACTION_PACKAGE_REMOVED
*** ACTION_TIMEZONE_CHANGED
*** ACTION_TIME_SET
*** ACTION_TIME_TICK
*** ACTION_HEADSET_PLUG

* データ
** インテントの取り扱い方に関する情報をUriクラス(android.net.Uri)として表現したもの
** [スキーマ:]スキーム固有部分[#フラグメント]
** 例
*** ex1) http://www.example.com/index.html#p2
*** ex2) content://foo/bar
*** ex3) file:///usr/local/bin/hoge
*** ex4) mailto:foo@example.com
** Uriクラスが表す主な情報
*** スキーマ
*** 権限
*** ユーザ情報
*** ホスト
*** ポート
*** パス
*** クエリ
*** フラグメント

* カテゴリ
** アクションに対する追加情報
** 予め定義されているカテゴリ
*** CATEGORY_DEFAULT
*** CATEGORY_BROWSABLE
*** CATEGORY_ALTERNATIVE
*** CATEGORY_SELECTED_ALTERNATIVE
*** CATEGORY_HOME
*** CATEGORY_LAUNCHER
*** CATEGORY_TAB

* 付加情報
** 他のオブジェクトに与えたい情報を示すBundleクラスのインスタンス
** Bundleクラスは、文字列をキーとするHashMapを内部に持つ
** 予め定義されている付加情報のキー
*** EXTRA_TEMPLATE
*** EXTRA_TEXT
*** EXTRA_INTENT
*** EXTRA_STREAM

* タイプ
** インテントが表現しているデータの型をMIME形式で示したもの

* フラグ
** 当該インテントを取り扱えるアクティビティが起動される際に、どのように起動してほしいかをシステムに伝えるもの
** 主なフラグ
*** FLAG_FROM_BACKGROUND
*** FLAG_ACTIVITY_SINGLE_TOP
*** FLAG_ACTIVITY_NO_HISTORY
*** FLAG_ACTIVITY_NEW_TASK
*** FLAG_ACTIVITY_MULTIPLE_TASK

##### 2.1.4.3 「明示的なインテント」と「暗黙的なインテント」

* 明示的なインテント
* 暗黙的なインテント

##### 2.1.4.4 インテントフィルタ

* アクティビティやサービスが取り扱うことの出来るインテントを明らかにするもの
* インテントフィルタは、アクション・カテゴリ・データなどのインテント属性に対して絞り込みを行うことができる

### 2.2 レイアウト

#### 2.2.1 ViewとViewGroup

* View
* ViewGroup

#### 2.2.2 ViewGroup

* 代表的なViewGroup
** LinearLayout
** ListView
** GridLayout
** RelativeLayout
** FrameLayout

#### 2.2.3 ViewGroupの利用方法

##### 2.2.3.1 XMLによる定義

##### 2.2.3.2 XMLの読込・利用

* setContentView
** setContentViewメソッドで読み込んだXMLは画面部品に変換され、画面に表示される

* LayoutInflater
** LayoutInflaterを使うと、どこでも好きなタイミングでXMLから画面部品を構築することが出来る
** FragmentやListViewなどのように動的に画面の外観を変える必要がある局面でよく利用される

* hierarchyviewerというツールが便利

##### 2.2.3.3 レイアウト設定項目

* "layout_"から始まる属性は、親要素となるViewGroupに対して、子要素となるViewが自分自身をどのようにレイアウトしてほしいかを知らせるための項目

* 代表的なレイアウト項目
** layout_width
** layout_height
** layout_margin

* LinearLayoutの主な設定項目
** layout_gravity
** layout_weight

* レイアウト設定項目と通常の設定項目の違い
** gravity vs layout_gravity
*** gravity: 自分自身を描画する際の位置を決めるために利用される
*** layout_gravity: 親のViewGroupが子要素を配置する際の位置を決めるための設定

#### 2.2.4 ViewGroupの仕組み

##### 2.2.4.1 BearingLayout (サンプルプログラム)

* 仕様
** layout_direction
*** north: 上部
*** south: 下部
*** center_latitude: 上下方向の中央
*** west: 左部
*** east: 右部
*** center_longitude: 左右方向の中央


