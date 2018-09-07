package org.green.di;

import org.springframework.stereotype.Component;

// bean ��� : spring�� �����ϴ� ��ü��~

@Component
/*	@Controller : Controller�� ���Ǵ� ��ü
 * 	@Repository : DAO ��ü
 * 	@Service : Cmd��ü(����)
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
