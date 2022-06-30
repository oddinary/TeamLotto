

import java.util.List;

public class User {
	String pw;
	String name;
	boolean Premier;
	int haveMoney;
	List<Integer> lottoNumber;

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public boolean isPremier() {
		return Premier;
	}

	public int getHaveMoney() {
		return haveMoney;
	}

	public List<Integer> getLottoNumber() {
		return lottoNumber;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPremier(boolean premier) {
		Premier = premier;
	}

	public void setHaveMoney(int haveMoney) {
		this.haveMoney = haveMoney;
	}

	public void setLottoNumber(List<Integer> lottoNumber) {
		this.lottoNumber = lottoNumber;
	}

}
