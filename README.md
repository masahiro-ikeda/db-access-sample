ソース取得後、次の作業を行うと実行できる状態になります。

※JDKのインストールと設定が終わっていることが前提です。 
　　また、eclipseのマーケットプレイスから『Gradle IDE Pack』をインストールして下さい。

※MySQLをローカルにインストールして、/db-access-sample/src/main/resource/配下のSQLを実行してテーブルを作成してください。
 databaseやアカウントの設定の確認もお忘れなく。

①プロジェクトフォルダ直下の、『gradlew.bat』をダブルクリック
②①の処理が終わったら、プロジェクトフォルダを右クリックして、構成 -> Gradle ネーチャーの追加
③プロジェクトフォルダを右クリックして、Gradle -> Gradleプロジェクトのリフレッシュ 
④MessageInsert.javaまたはMessageSelect.javaを選択して実行
