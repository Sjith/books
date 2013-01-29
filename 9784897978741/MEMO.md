

## 4 レイアウトとビュー

### 4.1 レイアウトとビューとは

#### 4.1.1 ビュー

* 画面の各部品のこと (テキスト、テキストボックス、チェックボックスなど)
* すべてのビューは、android.view.Viewクラスを継承する
* 画面にビューを配置する方法は2通り
** XMLファイルに設定する
** プログラムの中で各ビュークラスをインスタンス化して画面に設定する

#### 4.1.2 レイアウト

* 各ビューをどのように配置するかを設定するための仕組み
* すべてのレイアウトは、android.view.ViewGroupクラスを継承する
* 画面にレイアウトを設定する方法はビューと同様 (XMLファイルorプログラム内)

### 4.2 代表的なビュー

* ScrollView
* TextView
* EditText
* CheckBox
* RadioButton
* Spinner
* ListView
* Button

#### Androidアプリの画面設定で使用する単位

* dp (density-independent pixel)
** 画面の解像度に依存せず、異なる解像度の端末でも同じように画像を表示できる
** 1dpは160dpiの画面で1ピクセルに等しくなる
* sp (scale-independent pixel)
** 画面の解像度とフォントサイズに依存
** フォントサイズを指定する場合、この単位を使用
* pt (point)
** 1pt=1/72インチ
* px (pixel)
** 画面の実際のピクセル数に対応
** 画面の解像度に依存
* mm (millimeter)
** 画面の物理サイズ(ミリメートル)
* in
** 画面の物理サイズ(インチ)

### 4.3 代表的なレイアウト

* LinearLayout
** ビューを縦まとは横に表示する
* TableLayout
** ビューを表形式に表示する
* FrameLayout
** ビューを原則1つ配置するレイアウト
** 複数のビューを配置した場合、左上に重ねて配置される
** 後から追加したビューが全面に表示される
* RelativeLayout
** 複数のビューを相対的に表示する
* AbsoluteLayout
** ビューを絶対座標で配置する

#### 4.3.3 TableLayout

* android:stretchColumns
* android:shrinkColumns
* android:layout_span
* android:layout_column

## 5 アクティビティ

### 5.1 アクティビティとは

#### 5.1.1 アクティビティとは

* 画面の制御機能を担当する
* アクティビティ:画面 = 1:1
* 画面に実装したいイベントのハンドラを実装する
* Activityクラスを継承する

### 画面初期表示のイベントハンドラ

* onCreate
** 画面の初期表示に呼ばれるイベントハンドラ
** 引数に画面の動的な情報を保持するBundleクラスを受け取る
** Bundleオブジェクトには前回の画面情報が格納されている
** onSaveInstanceStateで画面の情報を保持
** onRestoreInstanceStateで画面を復元
** オーバーライドしたメソッド(onCreateなど)はsuperを呼び出すルール

#### 5.2.2 アクティビティのライフサイクル

* onCreate
** 生成:初めて画面が生成されるとき
** 表示
* onStart
** 表示:画面を表示する直前
** 対話開始
* onResume
** 対話開始:入力の受付を開始したとき
** 対話
* onPause
** 対話:画面が非表示になったとき
** 非表示
* onStop
** 非表示:画面がしばらく非表示なったとき
** 停止
* onRestart
** 停止:停止状態から再開するとき
** 表示
* onDestroy
** 停止:停止状態から破棄される直前
** 終了
* onSaveInstanceState
** 適宜:適切なタイミングで画面情報を保持
** 状態を維持

基本的な状態遷移

* アプリケーション起動
** 初期状態 -> onCreate -> onStart -> onResume
* アプリケーション終了
** 実行中 -> onPause -> onStop -> onDestroy
* 他のアプリケーションが起動
** 実行中 -> onSaveInstanceState -> onPuase -> onStop
* アプリケーション再起動 (他のアプリケーション終了)
** 停止中 -> onRestart -> onStart -> onResume

#### 代表的なイベントリスナ

* イベントリストクラス名
** 説明
** 対応するイベントハンドラ
** ビューと関連づけるメソッド

* OnItemClickLisner
** 選択リストなどでアイテムがクリックされたとき
** onItemClick()
** setOnItemClickListner()
* OnKeyListener
** ビューがフォーカスされていて、キーが入力されたとき
** onKey()
** setOnKeyListener()
* OnFocusChangedListener
** ビューがフォーカスを取得したとき、消失したとき
** onFocusChange()
** setOnFocusChangeListener()
* OnTouchListener
** ビューがタッチされたとき
** onTouch()
** setOnTouchListener()
* OnLongClickListener
** ビューが長押しされたとき
** onLongClick()
** setOnLongClickListener()
* OnItemLongClickListener()
** 選択リストの1つのアイテムを長押し
** onItemLongClick()
** setOnItemLongClickListener()
* OnItemSelectedListener
** 選択リストで1つのアイテムをフォーカス
** onItemSelected()
** setOnLitemSelectedListener()

#### 選択リストの一覧表示

* 複数の値を一覧表示して選択できるようなビュー (ListView, Spinner)
* 一覧表示する値を関連づけることをデータバインディングという
* データバインディングを実現するのはAdapterViewクラス
* android:entriesに文字列の配列情報を設定するとAdapterViewが良きに計らう
* 選択ボックスや選択リストの値選択のイベントリスナーもAdapterViewにある

## 6 インテント

### 6.1 インテントとは

* あるアクティビティから別のアクティビティを呼び出す

#### インテントが保持する情報

* action
** ActivityAction
*** 電話をかけたい、画面表示したいなど個々のアクティビティに通知
** BroadcastAction
*** バッテリが少ない、システム起動完了などアプリケーション全体に通知
* category
** アクションを分類するための追加情報
* data
** 他のアクティビティやサービスを呼び出すためのURI
** データではandroid.net.Uriクラスを使用する
* extra
** 他のアクティビティやサービスに引き渡したい付加情報
** Bundleクラスを使用し、付加情報をキーバリュー形式で保持させる
* type
** インテントのデータの種類をMIME形式で示した情報
* flag
** バックグラウンドで起動する、すでに起動している場合は起動しないなどを指示

#### インテントの分類

* 明示的インテント
** 起動するアクティビティやサービスを明示的に指定したインテント
** 主に同一アプリケーション内で連携するときに使用
* 暗黙的インテント
** 起動するアクティビティやサービスを明示的に指定しない
** 何をしたいかだけを指定するインテント (地図を表示したいなど)
** 主に他のアプリケーションを呼び出す場合に使用

### 6.2 明示的インテント

### 6.3 暗黙的インテント

#### 6.3.1 暗黙的インテントの動作原理

* 呼び出される側のAndroidManifest.xmlにインテントフィルタを設定しておく
* Androidシステムは条件に合うインテントフィルタを持つアプリを呼び出す

#### 6.3.3 インテントフィルタの設定

* intent-filterタグ
** actionタグ
*** ACTION_MAIN
*** ACTION_VIEW
*** ACTION_DEFAULT
*** ACTION_CALL
*** ACTION_DIAL
*** ACTION_RUN
** categoryタグ
*** CATEGORY_DEFAULT
*** CATEGORY_BROWSABLE
*** CATEGORY_HOME
*** CATEGORY_LAUNCHER
*** CATEGORY_ALTERNATIVE
*** CATEGORY_SELECTED_ALTERNAME
** dataタグ
*** android:shceme
*** android:host
*** android:port
*** android:path
*** android:pathPrefix
*** android:pathPattern
*** android:mimeType

## 7 サービス

### 7.1 サービス

#### 7.1.1 サービスとは

* 画面を持たずにバックグランドで独立して動作するプログラムのこと
* サービスには2つの実装方法がある
** ActivityクラスのstartService(), stopService()
** バインド, AIDL

#### 7.1.2 サービスのライフサイクル

* onCreate
** 生成:初めてサービスが起動されるとき
** 開始
* onStart
** 開始:startServiceメソッドでサービスが開始されるとき
** 動作中
* onBind
** バインド:bindServiceメソッドでサービスとバインドするとき
** 動作中
* onUnbind
** バインド解除:サービスとのバインドを解除するとき
** 動作中
* onRebind
** 再バインド:サービスと再度バインドするとき
** 動作中
* onDestroy
** 停止:停止状態から破棄される直前
** 終了

バインドを使用したサービスの処理の流れ

