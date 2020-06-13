# SolitaireSearch

概要
------------
18年1月授業「人工知能」自由課題
「探索問題を設定し、コンピュータに各種サーチ手法を実装し、それらの効率を評価してみよ。」

- 選択した問題
	-「ペグソリティア」である。
- 問題の概要
	- 一人用のパズルゲームである。ルールはいくつかあるが、今回は以下のように問題設定、用語定義を行なった。
		- 33個の「穴」からなる「盤」を用意する。
		- その盤の穴には、1個以上最大32個の「駒」が置かれている。1つの穴に置くことのできる駒は1つまでである。
		- 盤と置かれているすべての駒の位置を合わせて「状態」と呼ぶ。
		- ある状態に以下のような「操作」を行うことで、駒の位置を移動した別の状態に移行することができる。
			- ある駒に対し、その駒を始点として上下左右いずれか一直線上に、「駒が置かれている」穴(中点と呼ぶ)「駒が置かれていない」穴(終点と呼ぶ)が連続した場合、始点の駒を終点におき、中点の駒を取り去る。
		- 「操作」を繰り返すことで、盤上にある駒の数を1つの「目的状態」を作り出すことがこのパズルの目的である。
		- 最終状態までの一連の操作をまとめたものを「解」とする。
		- ただし、複数解答がある場合、最後の1つの駒が盤の中心に近いものを「解」とする。
	![盤面イメージ](/slides/004.jpeg)

アルゴリズム
------------
- 解答指針
	- 盤面の状態とその変化で表せるので、状態空間法を利用する。
		- 状態を「穴」の二次元配列で表す。各配列要素に、穴があるか、穴に駒があるかの情報を埋め込む
		- 初期状態はランダム、目的状態は盤上に駒が1個
		- オペレータは、UP,DOWN,LEFT,RIGHT。それぞれ、上下左右に、上で示した操作を行う。
	- 探索方法は今回は3つ。
		- 深さ優先探索(反復深化)DFS
		- 幅優先探索BFS
		- A*search(ヒューリスティック関数は距離)
  - 盤面の状態保持の方法として、リストおよびhashリストを利用。
    - hashリストの説明
      - 以前に探索したノードの盤面情報数は最大2^33。単純に過去に検索したノードをリストに格納するだけでは膨大になることが予測できる。そこでハッシュを使う。
      - 盤面に対してのハッシュ関数(左上から駒が存在するごとに2^0,2^1...2^32を足し合わせ適当な数で割る)を作成し、同一の盤面を再度検索しないようにする。
    
実験結果、考察
------------
- 各手法について、それぞれのn数に対しての探索ノード数、時間、有効分岐数は以下の通り。
![実験結果](/slides/019.jpeg)
![実験結果2](/slides/020.jpeg)
- この問題において、手数は必ず初期配置の玉の個数-1。つまり、深さは固定。この条件でなら、GCにより、探索し終わったノード(=使われないノード)は削除されるので、深さ優先探索が良い。(c.f. 幅優先探索。解にたどり着くまで、残りの候補のノードを抱え込んでしまう。)実際、n=32で深さ優先を行うと、割り当てられたヒープ領域を使い切ってエラーが出ることがある。


試し方
------------
発表用のデモを試すことができる。
Eclipseへのインポート方法(一例)
- Eclipseをダウンロード
- Git リポジトリからのインポート
	- Eclipseメニューの File > Import
	- Git > Project from Gitを選択してNext
	- Clone URIを選択
	- URIとして https://github.com/YuumiK/SolitaireSearch.gitを指定
	- Next2回(ブランチ指定, ローカル上でのクローン先の指定)
	- Working Directoryを選択してNext
	- Finish

SolitarieDemoでは、コマ数12, DFS+List, BFS+List, A*+List, A*+Hashの4つの手法での探索結果を並列でみることができる。

SolitarieDemo2では、コマ数32, A*+Hashの探索結果をみることができる。