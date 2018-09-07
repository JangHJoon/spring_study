package org.green.di;

import org.springframework.stereotype.Component;

// bean 등록 : spring이 관리하는 객체다~

@Component
/*	@Controller : Controller로 사용되는 객체
 * 	@Repository : DAO 객체
 * 	@Service : Cmd객체(로직)
 */
public class Ball {
	private int num;
	
	public Ball() {	}

	public Ball(int num) {		
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "Ball [num=" + num + "]";
	}
	
	
}
