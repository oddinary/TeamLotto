
import java.util.List;

public class User {
	String pw;
	String name;
	String phoneNum;
	boolean Premier;
	int haveMoney;
	List<List<Integer>> lottoNumber;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

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

	public List<List<Integer>> getLottoNumber() {
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

	public void setLottoNumber(List<List<Integer>> lottoNumber) {
		this.lottoNumber = lottoNumber;
	}

}
