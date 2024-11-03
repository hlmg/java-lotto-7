package lotto.domain;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    public MatchResult match(Lotto lotto) {
        int matchingNumberCount = this.lotto.countMatchingNumbers(lotto);
        boolean bonusMatched = lotto.contains(bonusNumber);

        return new MatchResult(matchingNumberCount, bonusMatched);
    }

}