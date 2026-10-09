// DOM（HTML）の読み込みが完了してから実行する
document.addEventListener('DOMContentLoaded', () => {
    
    // id="form" が設定されたフォーム要素を取得
    const form = document.getElementById('form');
    
    if (form) {
        // フォームの送信（submit）イベントを監視
        form.addEventListener('submit', (e) => {
            // 送信イベントを発生させたサブミットボタンを取得
            const btn = e.submitter;
            if (btn) {
                // ボタンを無効化して連続クリックできないようにする
                btn.disabled = true;
            }
        });
    }
});