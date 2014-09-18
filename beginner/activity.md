## Activity

最初に覚えるべきライフサイクル。

### onCreate(Bundle savedInstanceState) と onDestroy()

`onCreate`  と`onDestroy` は、 Activity の生成時と破棄時にそれぞれ一度だけ呼ばれる。

``` java

public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView の前に findViewById をすると NPE になってハマる事も多いので、
        // 慣れるまでは「super.onCreate と setContentView は先頭で呼ぶ」ようにしよう
        setContentView(R.layout.activity_my);

        // ここに色々書いていく
        // 慣れるまでは「全ての処理を onCreate に書く」スタイルでもおおよそ問題ないはず
    }

    @Override
    protected void onDestroy() {
        //Activity を終了するタイミングで後始末をする場合はここに書くとよい
        super.onDestroy();
    }

}
```


### onResume() と onPause()

onResume と onPause は、ウィンドウがアクティブになった時・非アクティブになった際に呼ばれる。

一度 Activity が生成された後でも何回でも呼ばれる。

#### onResume が呼ばれるタイミング

Activity がフォアグラウンドになった際に呼ばれる

とにかく「見えている画面(Activity)が `A` から `B` へ切り替わった際に `B#onResume` が呼ばれる」という認識で問題ない。

例えば PV をカウントしたい場合は onPause を利用するのが一般的。

#### onPause が呼ばれるタイミング

Activity がバックグラウンドになる等、フォアグラウンドではなくなる際に呼ばれる。

- バックキーで画面を閉じる
- ホームボタンを押す
- 端末のボタンから最近使ったアプリ一覧を開く
- などなど…

### Fragment

Activity に慣れるまでは無視してもok(仕事で触れる場合はこの限りではない。慣れてほしい。)

Activity に慣れてからじゃないと、Activity と Fragment のライフサイクルで混乱したり、取り扱いを間違えて IllegalStateException によってクラッシュが頻発するリスクが高い。

### その他ライフサイクル

普段気にする必要の無いものも多分に含まれているが、Activity と Fragment のライフサイクルが見やすくまとまっているところがあまりないので、参考情報という事で以下を貼っておく。

https://github.com/xxv/android-lifecycle

## ビュー

### 命名規則を意識する

慣れないうちは(きっと) findViewById で null が返って来てハマる事が多い。

そのために以下のルールで名前をつける事を推奨。

ルールを決めておく事で、何がどこにあるのかを見失う時間を減らせてオトク。

- リソース(res/)以下の命名は、ファイル名もIDも全てキャメルケースに統一する
- setContentView でセットするレイアウトのファイル名は `activity_` を接頭辞にする
- setContentView でセットするレイアウトのファイル名は Activity と同じにする
- レイアウトファイル内の View の id はファイル名を接頭辞にする

````
// 例

public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activity のレイアウトファイルなので activity_ を接頭辞にする
        // Activity のクラス名が My なので、レイアウトも my にする
        setContentView(R.layout.activity_my);

        // 命名規則が徹底されている前提があれば、「R.layout.activity_my」と入力するだけで、
        // IDE による入力補完で activity_my.xml に属する id の一覧が見やすくなって便利
        TextView nicknameView = (TextView) findViewById(R.id.activity_my_nickname);
    }

}

// res/layout/activity_my.xml

<?xml version="1.0" encoding="utf-8"?>

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >

    <!-- ファイル名が activity_my.xml なので、idの接頭辞を activity_my_ にしている  -->
    <TextView
        android:id="@+id/activity_my_nickname"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="My name is..."
        />

    <Button
        android:id="@+id/activity_my_random"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text=":)"
        />

</LinearLayout>
```
