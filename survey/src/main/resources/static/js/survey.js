document.addEventListener('DOMContentLoaded', () => {

    const scoreField = document.getElementById('able');
    const orderedRadio = document.getElementById('order-yes');
    const orderRadios = document.getElementsByName('hamburgOrder');

    if (!scoreField || !orderedRadio) return;

    // 初期表示
    if (orderedRadio.checked) {
        scoreField.classList.remove('is-hidden');
    } else {
        scoreField.classList.add('is-hidden');
    }

    // 「はい・いいえ」を変更したとき
    orderRadios.forEach((radio) => {
        radio.addEventListener('change', () => {

            if (orderedRadio.checked) {
                // はい → 評価を表示
                scoreField.classList.remove('is-hidden');

            } else {
                // いいえ → 評価を非表示
                scoreField.classList.add('is-hidden');

                // 評価の選択も解除
                scoreField.querySelectorAll('input[type="radio"]').forEach((r) => {
                    r.checked = false;
                });
            }
        });
    });
});