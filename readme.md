# AndroidBoilerplate

My Best Android Architecture.

## 構成

- 言語: kotlin
- アーキテクチャ構成: Dagger2 + RxKotlin + Redux
- ネットワークライブラリ: retrofit?
- DB: Room?
- その他キーワード: databinding, eventbinding

ReduxはReactで使われることが多いアーキテクチャだが、それをAndroidにアレンジしてみた。  
Androidのアクティビティをフロントエンドの1ページと考えている。  
(Webページ遷移時のデータのやり取りはURLパラメータ等、Androidだとintentに渡すことに該当)  
非同期も扱えるように設計した。

### ディレクトリ構成

- actions  
ReduxのActionを格納する。  
アプリケーションで発生するイベントは全てこの中に定義する。  
kotlinの`sealed class`を用いることで`when`でパターンマッチングを実現した。

- dao  
アプリケーション管理外のデータにアクセスする部品を定義する。  
e.g. ネットワーク、DBなど  
非同期で返しても良いが、同期で返してもこの上のレイヤでmainスレッドをブロックしないようにするためok.

- di  
Dagger2の設定ファイルが入っているディレクトリ。  
中では、Activityごとのインジェクションとglobalのインジェクション部分に分かれている。

- entity  
アプリケーションのentityクラスを格納する場所。  
今回のアーキテクチャには全く関係ない。

- middleware  
Reduxアーキテクチャのmiddlewareを格納する場所。  
middlewareなしのReduxでは非同期処理は扱えない。よって、ここで非同期処理を扱う。  
e.g. アクションAが発行されたら、非同期処理を実行し、終了したらアクションBを発行する。

- reducer  
Reduxのreducerを定義する場所。  
reducerはあるアクションと現在の状態を元に、新しい状態を返す副作用の無い関数。

- repository  
主にreducerやmiddlewareの内部で呼ばれるアプリケーションのビジネスロジックを格納する場所。  
ここからdaoを呼ぶ。テスト時はdaoをmock化する。

- store
Reduxのstoreを定義する場所。stateもここに定義する。  
storeはstateとdispatch関数を持っている。stateはアプリケーションの状態を表しており、dispatch関数にactionを渡すことでstateを更新する。

- ui
fragmentやfragmentのviewModelを格納する場所。  

- util
extensionや汎用的な諸々を置く。