# SimpleToDo
<br>  

## アプリ説明
SimpleToDoAppは、シンプルで使いやすいTodoアプリです。
 [「Compose を用いた Android アプリ開発の基礎」](https://developer.android.com/courses/android-basics-compose/course?hl=ja)のユニット６の学習内容を主に使用しました。(Room)
<br>  

## 使用言語・技術
kotlin / Android Studio / Jetpack Compose <br>
*** 他のライブラリ**
 - Room : 追加したタスクをローカルストレージに保存するため。
<br>  

## アプリを作った目的
 - Roomを使って、ローカルデータベースの実装方法を学びたかった
 - シンプルなTodoアプリで基本機能を実装し、実践的な経験を積みたかった
 - 二作目として、より新しい技術や設計パターン（ファイル・関数分割、MVVM 等）に挑戦したかった
<br>  

## 機能一覧
 - 右下の追加ボタンで新規タスクを追加
 - 編集画面でタスクを編集
 - タスクの一覧表示
 - タスクを押すと編集可能
 - 左側のチェックを押すとタスクが消える
 - 無限スクロール機能
 - navControllerによる画面遷移
 - タスクをローカル保存（Roomを使用）
<br>  

## 工夫した点
### **ファイル分け 関数分けに挑戦**
前作「一割貯金箱」ではMainファイルに全コードを記述していましたが、可読性と保守性に課題がありました。
そこで今回は、各機能ごとにファイルや関数を分割し、モジュール化を意識した設計に努めました。
<br>  

## 苦労した点
### Roomの実装
Room自体は順調に進められたものの、依存関係のエラーに悩まされました。
色々と参考にし調べた結果、プロジェクトのJavaバージョンを統一し、room_versionを調整し無事動作しました。
<br>  

### ViewModelの理解
サンプルコードや公式リファレンスなど色々な資料を参考にしながら進めましたが、ViewModelの役割や仕組みを理解するのに時間がかかりました。
<br>  

### **学んだこと**
 - Room を用いたデータ保存
 - LazyColumn による一覧表示
 - ViewModel の意味
<br>  
ホーム画面

![SimpleToDo1](https://github.com/user-attachments/assets/1f08553a-ad85-41aa-b141-d564c6dfcfca)


新規作成画面

![SimpleToDo2 png](https://github.com/user-attachments/assets/901bfae1-e0f5-4618-b2e4-5e9236a95c7d)
